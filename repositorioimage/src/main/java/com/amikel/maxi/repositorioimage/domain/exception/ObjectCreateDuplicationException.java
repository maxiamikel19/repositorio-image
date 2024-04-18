package com.amikel.maxi.repositorioimage.domain.exception;

public class ObjectCreateDuplicationException extends RuntimeException{
    
    public ObjectCreateDuplicationException(String message){ super(message); }
    public ObjectCreateDuplicationException(String message, Throwable cause){ super(message, cause); }
}
