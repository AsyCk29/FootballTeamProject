package com.team.utils;

import com.team.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Rest {
    public static ResponseEntity success(Object object, String ex_message) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setResult(object);
        responseModel.setStatus(true);
        responseModel.setMessage(ex_message);
        return new ResponseEntity(responseModel, HttpStatus.OK);
    }
    public static ResponseEntity fail(Object object, String ex_message, HttpStatus httpStatus) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setResult(object);
        responseModel.setStatus(false);
        responseModel.setMessage(ex_message);
        return new ResponseEntity(responseModel, httpStatus);
    }
}
