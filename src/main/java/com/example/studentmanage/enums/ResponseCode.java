package com.example.studentmanage.enums;


import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(0,"success"),
    FAILED(1,"failed"),



    //
    STUDENT_CODE_NOT_UNIQUE(20001,"Student code existed in system"),
    STUDENT_ID_INCORRECT_FORMAT(20002, "Student id incorrect format"),
    STUDENT_NOT_FOUND(20003,"Student not found"),

    ;

    ResponseCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

}
