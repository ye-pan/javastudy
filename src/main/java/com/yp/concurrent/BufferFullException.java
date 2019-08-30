package com.yp.concurrent;

public class BufferFullException extends RuntimeException {

    public BufferFullException() {
    }

    public BufferFullException(String message) {
        super(message);
    }

    public BufferFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferFullException(Throwable cause) {
        super(cause);
    }
}
