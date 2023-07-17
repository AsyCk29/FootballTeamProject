package com.team.models;

import lombok.Data;

@Data
public class ResponseModel {
    private boolean status ;
    private String message;
    private Object result;
}
