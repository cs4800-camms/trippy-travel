package com.samm.trippytravel.controllers;

import com.samm.trippytravel.data.domain.User;
import com.samm.trippytravel.data.model.ERole;
import com.samm.trippytravel.data.model.Role;
import com.samm.trippytravel.payload.request.user.LoginRequest;
import com.samm.trippytravel.payload.request.user.SignUpRequest;
import com.samm.trippytravel.payload.response.JwtResponse;
import com.samm.trippytravel.payload.response.MessageResponse;
import com.samm.trippytravel.repository.RoleRepository;
import com.samm.trippytravel.repository.UserRepository;
import com.samm.trippytravel.security.jwt.JwtUtils;
import com.samm.trippytravel.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> registerUser(
            @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        //Create new user's account
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();
        userRepository.insert(user);

        return new ResponseEntity<>(MessageResponse.builder()
                .message("User registered successfully!")
                .build(), HttpStatus.OK);
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<JwtResponse> authenticateUser(
            @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        //gets user details (refer to AuthTokenFilter)
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(toList());

        return new ResponseEntity<>(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles), HttpStatus.OK);
    }

//    @PutMapping("{userIdNumber}/update")
//    @ResponseBody
//    public ResponseEntity<UserResponse> updateUser(
//            @PathVariable("userIdNumber") String userIdNumber,
//            @RequestBody UpdateUserRequest updateUserRequest) {
//        return new ResponseEntity<>(userService.updateUser(userIdNumber, updateUserRequest), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{userIdNumber}")
//    @ResponseBody
//    public ResponseEntity<UserResponse> deleteUser(
//            @PathVariable("userIdNumber") String userIdNumber) {
//        return new ResponseEntity<>(userService.deleteUser(userIdNumber), HttpStatus.OK);
//    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete() {
        userRepository.deleteAll();
    }
}
