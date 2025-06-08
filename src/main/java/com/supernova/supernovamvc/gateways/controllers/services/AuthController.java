package com.supernova.supernovamvc.gateways.controllers.services;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.auth.TokenResponseDto;
import com.supernova.supernovamvc.gateways.dtos.requests.services.LoginRequestDto;
import com.supernova.supernovamvc.utils.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));

        String token = jwtUtil.generateToken(auth.getName());

        TokenResponseDto response = new TokenResponseDto();
        response.setToken(token);
        return response;
    }


}
