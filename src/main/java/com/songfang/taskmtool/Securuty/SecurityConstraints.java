package com.songfang.taskmtool.Securuty;

public class SecurityConstraints {
    public static final String SIGN_UP_URL = "/api/user/**";
    public static final String H2_URL = "h2-console/**";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEAD_STRING = "Authorization";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME=30_000;
}
