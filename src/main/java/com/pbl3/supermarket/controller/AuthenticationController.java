package com.pbl3.supermarket.controller;


import com.nimbusds.jose.JOSEException;
import com.pbl3.supermarket.dto.request.AuthenticationRequest;
import com.pbl3.supermarket.dto.request.IntrospectRequest;
import com.pbl3.supermarket.dto.request.LogoutRequest;
import com.pbl3.supermarket.dto.response.ApiResponse;
import com.pbl3.supermarket.dto.response.AuthenticationResponse;
import com.pbl3.supermarket.dto.response.IntrospectResponse;
import com.pbl3.supermarket.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .message("Login successfully")
                .result(authenticationResponse)
                .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .message("Introspect successfully")
                .result(authenticationService.introspect(introspectRequest)).build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .message("Logout successfully")
                .build();
    }

}
