package com.moox.system.exception;

/**
 * Created by SYT-PC on 2016/6/14.
 */
public class UserNotExistsException extends Throwable {
    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message) {
        super(message);
    }

    public UserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistsException(Throwable cause) {
        super(cause);
    }

    protected UserNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
