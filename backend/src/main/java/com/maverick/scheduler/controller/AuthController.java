package com.maverick.scheduler.controller;

import com.maverick.scheduler.dto.AuthenticationDTO;
import com.maverick.scheduler.dto.LoginResponseDTO;
import com.maverick.scheduler.dto.UserResponseDTO;
import com.maverick.scheduler.model.User;
import com.maverick.scheduler.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe() {
        // O SecurityContextHolder recupera o usuário que o nosso SecurityFilter validou
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        ));
    }
}
