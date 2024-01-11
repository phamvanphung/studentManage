package com.example.studentmanage.exception;


import com.example.studentmanage.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
public class BusinessException extends RuntimeException{
    private ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode){
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
