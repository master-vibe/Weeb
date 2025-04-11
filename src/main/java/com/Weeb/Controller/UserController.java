package com.Weeb.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weeb.DTO.UserDTO.LoginRequestDto;
import com.Weeb.DTO.UserDTO.LoginResponseDto;
import com.Weeb.DTO.UserDTO.RegisterRequestDto;
import com.Weeb.DTO.UserDTO.RegisterResponseDto;
import com.Weeb.Service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto){
        return ResponseEntity.ok(usersService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerDto){
        return ResponseEntity.ok( usersService.registerUser(registerDto));
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("Health Check Successful");
    }
}
