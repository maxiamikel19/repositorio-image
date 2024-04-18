package com.amikel.maxi.repositorioimage.application.jwt;

public class JwtInvalidTokenException extends RuntimeException{

    public JwtInvalidTokenException(String message){ super(message); }
    public JwtInvalidTokenException(String message, Throwable cause){ super(message, cause); }
    
}
