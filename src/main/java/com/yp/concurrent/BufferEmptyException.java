package com.yp.concurrent;

public class BufferEmptyException extends RuntimeException {
    public BufferEmptyException() {
    }

    public BufferEmptyException(String message) {
        super(message);
    }

    public BufferEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferEmptyException(Throwable cause) {
        super(cause);
    }
}
