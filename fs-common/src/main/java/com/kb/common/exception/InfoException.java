package com.kb.common.exception;

/**
 * @author syg
 * @version 1.0
 */
public class InfoException extends RuntimeException{
    String msg;

    public InfoException(String msg){
        super(msg);
        this.msg=msg;
    }

    public InfoException(String msg,Throwable e){
        super(msg,e);
        this.msg=msg;
    }

}
