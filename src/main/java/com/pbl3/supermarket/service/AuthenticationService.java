package com.pbl3.supermarket.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.pbl3.supermarket.dto.request.AuthenticationRequest;
import com.pbl3.supermarket.dto.request.IntrospectRequest;
import com.pbl3.supermarket.dto.request.LogoutRequest;
import com.pbl3.supermarket.dto.response.AuthenticationResponse;
import com.pbl3.supermarket.dto.response.IntrospectResponse;
import com.pbl3.supermarket.entity.InvalidatedToken;
import com.pbl3.supermarket.entity.User;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.repository.InvalidatedTokenRepository;
import com.pbl3.supermarket.repository.UserRepository;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Optional<User> user = userRepository.findByUsername(authenticationRequest.getUsername());
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USERNAME_NOT_FOUND);
        }
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword());
        if(!authenticated) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        var token = generateToken(user.get());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var token = introspectRequest.getToken();
        boolean valid = true;
        try {
            var jwtToken = verifyToken(token);
        }
        catch (AppException e) {
            valid = false;
        }

        return IntrospectResponse.builder()
                .valid(valid)
                .build();

    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> stringJoiner.add(role.name()));
        }
        return stringJoiner.toString();
    }
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);
        if (!verified || expiration.before(new Date()) || invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }
    private String generateToken(User user)
    {
        String username = user.getUsername();
        //Header: Xac' dinh. algorithm
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //ClaimSet:

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("com.pbl3.supermarket")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(12, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();

        //Payload:
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        //Sign Token:

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e)
        {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        String token = request.getToken();
        var signToken = verifyToken(token);
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expirationTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

}
