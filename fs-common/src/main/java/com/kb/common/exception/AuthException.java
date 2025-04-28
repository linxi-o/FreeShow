package com.kb.common.exception;

/**
 * @author syg
 * @version 1.0.0
 * @description
 */
public class AuthException extends RuntimeException {
    private String msg;

    public AuthException(){
        super("权限不足！");
    }
    public AuthException(String msg){
        super(msg);
        this.msg=msg;
    }
    public AuthException(String msg,Throwable e){
        super(msg,e);
        this.msg=msg;
    }
}
