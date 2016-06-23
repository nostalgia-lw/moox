package com.moox.system.exception;

/**
 * Created by SYT-PC on 2016/6/15.
 */
public class WorkOrderNotExistsException extends Throwable {
    public WorkOrderNotExistsException() {
        super();
    }

    public WorkOrderNotExistsException(String message) {
        super(message);
    }

    public WorkOrderNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkOrderNotExistsException(Throwable cause) {
        super(cause);
    }

    protected WorkOrderNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
