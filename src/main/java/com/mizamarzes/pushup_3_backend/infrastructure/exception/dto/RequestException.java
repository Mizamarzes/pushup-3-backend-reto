
package com.mizamarzes.pushup_3_backend.infrastructure.exception.dto;

import lombok.Data;

@Data
public class RequestException extends RuntimeException {
    private String code;

    public RequestException(String message, String code) {
        super(message);
        this.code = code;
    }

    
}
