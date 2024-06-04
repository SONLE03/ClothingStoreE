package com.sa.clothingstore.exception;

import com.sa.clothingstore.constant.APIStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final APIStatus apiStatus;
}
