package com.example.socialnetworkproject.constants;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtDocument {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TYPE = "Bearer ";
    public static final String JWT_SECRET_KEY = "kt-social";
    public static final int JWT_EXPIRED_TIME = 600_000;
    public static final String JWT_CLAIMS_KEY = "username";
    public static final String JWT_ISSUER = "social";
    public static final int REFRESH_TOKEN_EXPIRED_TIME = 1_200_000;
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET_KEY);
}
