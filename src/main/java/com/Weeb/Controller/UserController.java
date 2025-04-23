package com.Weeb.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weeb.DTO.UserDTO.AddContactDto;
import com.Weeb.DTO.UserDTO.GetContactsDto;
import com.Weeb.DTO.UserDTO.LoginRequestDto;
import com.Weeb.DTO.UserDTO.LoginResponseDto;
import com.Weeb.DTO.UserDTO.RegisterRequestDto;
import com.Weeb.DTO.UserDTO.RegisterResponseDto;
import com.Weeb.Service.UsersService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
        LoginResponseDto loginResponse = usersService.login(loginDto);
        return ResponseEntity.ok()
                .header("Set-Cookie", "jwtToken=" + loginResponse.getJwtToken() + "; HttpOnly; Secure; SameSite=Strict")
                .body(loginResponse);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerDto) {
        return ResponseEntity.ok(usersService.registerUser(registerDto));
    }

    @GetMapping("/users/{username}/contacts")
    public ResponseEntity<GetContactsDto> getContacts(@PathVariable String username) {
        return ResponseEntity.ok(usersService.getContacts(username));
    }

    
    @PostMapping("/users/{username}/contacts")
    public ResponseEntity<GetContactsDto> addContact(@PathVariable String username, @RequestBody AddContactDto contact) {
        return ResponseEntity.ok(usersService.addContact(username, contact));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("Validated");
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Health Check Successful");
    }
}
