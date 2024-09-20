package com.mizamarzes.pushup_3_backend.infrastructure.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private String code;
    private String message;
}
