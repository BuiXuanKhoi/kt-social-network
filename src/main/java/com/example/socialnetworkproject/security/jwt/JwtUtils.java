package com.example.socialnetworkproject.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialnetworkproject.constants.JwtDocument;
import com.example.socialnetworkproject.constants.JwtErrorDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    private JwtUtils(){}

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    public static String generateByUserName(String userName){

        long expirationTime = new Date().getTime() + JwtDocument.JWT_EXPIRED_TIME;
        Date expiresDate = new Date(expirationTime);

        return JWT.create()
                  .withClaim(JwtDocument.JWT_CLAIMS_KEY, userName)
                  .withIssuer(JwtDocument.JWT_ISSUER)
                  .withExpiresAt(expiresDate)
                  .sign(JwtDocument.ALGORITHM);
    }

    public static String parse(String authHeader){
        if (Boolean.TRUE.equals(isTokenExist(authHeader))){
            return authHeader.substring(7);
        }
        return null;
    }

    public static Boolean isTokenExist(String authHeader){
        return StringUtils.hasText(authHeader) && authHeader.startsWith(JwtDocument.JWT_TYPE);
    }


    public static DecodedJWT verify(String token, HttpServletRequest httpRequest){

        try
        {
            JWTVerifier jwtVerifier = JWT.require(JwtDocument.ALGORITHM)
                                         .withIssuer(JwtDocument.JWT_ISSUER)
                                         .build();

            return jwtVerifier.verify(token);
        }
        catch (InvalidClaimException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.INVALID_CLAIMS, exception.getMessage());
            logger.error(exception.getMessage());

        }
        catch (TokenExpiredException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.EXPIRED_ERROR, exception.getMessage());
            logger.error(exception.getMessage());
        }
        catch (SignatureVerificationException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.WRONG_SECRET_KEY, exception.getMessage());
            logger.error(exception.getMessage());
        }
        catch (AlgorithmMismatchException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.WRONG_ALGORITHM, exception.getMessage());
            logger.error(exception.getMessage());
        }
        catch (JWTDecodeException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.INVALID_FORMAT, exception.getMessage());
            logger.error(exception.getMessage());
        }
        catch (JWTVerificationException exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.UNDEFINED_JWT_ERROR, exception.getMessage());
            logger.error(exception.getMessage());
        }
        catch (Exception exception)
        {
            httpRequest.setAttribute(JwtErrorDocument.OTHER_ERROR, exception.getMessage());
            logger.error(exception.getMessage());
        }
        return null;
    }

}
