package com.pbl3.supermarket.configuration;

import com.nimbusds.jose.JOSEException;
import com.pbl3.supermarket.dto.request.IntrospectRequest;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomDecoder  implements JwtDecoder {
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    @Lazy
    private AuthenticationService authenticationService;


    private NimbusJwtDecoder nimbusJwtDecoder;

    @Override
    public Jwt decode(String token) {
        try{
            var response = authenticationService.introspect(IntrospectRequest.builder()
                    .token(token)
                    .build());
            if(!response.isValid()) {
                throw new AppException(ErrorCode.UNAUTHORIZED);
            }
        }
        catch (JOSEException | ParseException e)
        {
            throw new JwtException(e.getMessage());
        }
        if(Objects.isNull(nimbusJwtDecoder))
        {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
