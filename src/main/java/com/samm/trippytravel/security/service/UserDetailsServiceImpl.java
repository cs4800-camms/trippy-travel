package com.samm.trippytravel.security.service;

import com.samm.trippytravel.data.domain.User;
import com.samm.trippytravel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

//    public UserResponse addUser(CreateUserRequest createUserRequest) throws BadRequestException {
//        log.info(createUserRequest.getEmail());
//        User user = userRepository.findUserByEmail(createUserRequest.getEmail())
//                .orElseThrow(() -> new BadRequestException("A user already exists with email:" + createUserRequest.getEmail()));
//
//        log.info("user: " + String.valueOf(user));
//
//        //check if user already exists
//        if(user != null) {
//            throw new BadRequestException(
//                    String.format("A user already exists with email: %s", createUserRequest.getEmail()));
//        }
//
//        //String encodedPassword = bCryptPasswordEncoder.encode(createUserRequest.getPassword());
//
//        return toUserResponse(userRepository.insert(User.builder()
//                .firstName(createUserRequest.getFirstName())
//                .lastName(createUserRequest.getLastName())
//                .email(createUserRequest.getEmail())
//                .password(createUserRequest.getPassword())
//                .build()));
//    }
//
//    public UserResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest) throws BadRequestException {
//        log.info(authenticateUserRequest.getEmail());
//        User user = userRepository.findUserByEmail(authenticateUserRequest.getEmail())
//                .orElseThrow(() -> new BadRequestException("Invalid password or email."));
//
//        log.info("user: " + String.valueOf(user));
//
//        if(!authenticateUserRequest.getPassword().equals(user.getPassword())) {
//            throw new BadRequestException("Invalid password or email.");
//        }
//
//        //bCryptPasswordEncoder.matches(authenticateUserRequest.getPassword(), user.getPassword())
//
//        return toUserResponse(user);
//    }
//
//    public UserResponse updateUser(String userIdNumber, UpdateUserRequest updateUserRequest) {
//        return toUserResponse(userRepository.save(User.builder()
//                ._id(userIdNumber)
//                .firstName(updateUserRequest.getFirstName())
//                .lastName(updateUserRequest.getLastName())
//                .email(updateUserRequest.getEmail())
//                .password(updateUserRequest.getPassword())
//                .build()));
//    }
//
//    public UserResponse deleteUser(String userIdNumber) {
//        return toUserResponse(userRepository.deleteById(userIdNumber));
//    }
//
//    private UserResponse toUserResponse(User user) {
//        return UserResponse.builder()
//                ._id(user.get_id())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .build();
//    }
}
