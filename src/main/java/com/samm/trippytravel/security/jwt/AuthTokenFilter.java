package com.samm.trippytravel.security.jwt;

import com.samm.trippytravel.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthTokenFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private UserDetailsServiceImpl userService;

    @Autowired
    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request); //gets JWT from authorization header
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) { //validates JWT and gets the username
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                //from username get UserDetails to create an Authentication Object
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //set current UserDetails in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

                /*
                After this, everytime you want to get UserDetails, just use SecurityContext like this:
                    UserDetails userDetails =
                            (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                */
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: " + e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
