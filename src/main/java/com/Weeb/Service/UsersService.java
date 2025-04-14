package com.Weeb.Service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Weeb.DTO.UserDTO.AddContactDto;
import com.Weeb.DTO.UserDTO.GetContactsDto;
import com.Weeb.DTO.UserDTO.LoginRequestDto;
import com.Weeb.DTO.UserDTO.LoginResponseDto;
import com.Weeb.DTO.UserDTO.RegisterRequestDto;
import com.Weeb.DTO.UserDTO.RegisterResponseDto;
import com.Weeb.Entity.Users;
import com.Weeb.Exception.UserException;
import com.Weeb.Repository.UserRepository;

@Service
public class UsersService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtUtils jwt;
    private final PasswordEncoder passwordEncoder;

    UsersService(UserRepository userRepository, JwtUtils jwt, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByUsername(username);
    }

    public GetContactsDto getContacts(String username) {
        if (!repository.isUsernameAvailable(username)) {
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        }
        return GetContactsDto.builder()
                .contacts(repository.findByUsername(username).getContacts().stream().map(Users::getUsername).toList())
                .build();
    }

    public GetContactsDto addContact(String username, AddContactDto contact) {
        Users user = repository.findByUsername(username);
        Users contactUser = repository.findByUsername(contact.getUsername());
        user.getContacts().add(contactUser);
        user = repository.save(user);
        return GetContactsDto.builder()
                .contacts(user.getContacts().stream().map(Users::getUsername).toList())
                .build();
    }

    public LoginResponseDto login(LoginRequestDto loginDto) {
        UserDetails user = repository.findByUsername(loginDto.getUsername());
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new UserException("Username and Password not match.", HttpStatus.UNAUTHORIZED);
        }
        return LoginResponseDto.builder()
                .jwtToken(jwt.generateToken(user))
                .build();
    }

    public RegisterResponseDto registerUser(RegisterRequestDto requestDto) {
        if (isUsernameAvailable(requestDto.getUsername())) {
            return RegisterResponseDto.builder()
                    .error("Username already exists!")
                    .build();
        }
        repository.save(
                Users.builder()
                        .username(requestDto.getUsername())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .build());
        return RegisterResponseDto.builder()
                .success("User Created Successfully.")
                .build();
    }

    private boolean isUsernameAvailable(String username) {
        return repository.isUsernameAvailable(username);
    }
}
