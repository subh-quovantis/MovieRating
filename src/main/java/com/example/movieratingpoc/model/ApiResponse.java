package com.example.movieratingpoc.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {


    private int httpStatusCode;
    private Object data;

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private ApiResponse() {
    }

    public static  ResponseEntity<ApiResponse> buildResponseEntity(Object data, HttpStatus httpStatusCode){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
       apiResponse.setHttpStatusCode(httpStatusCode.value());
       return new ResponseEntity<ApiResponse>(httpStatusCode);

    }
}
