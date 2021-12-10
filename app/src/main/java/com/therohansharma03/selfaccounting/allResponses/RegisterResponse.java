package com.therohansharma03.selfaccounting.allResponses;

public class RegisterResponse {

    String error;
//    to change the name we need to use annotation i.e @SerializedName("Actual Name");
    String message;

    public RegisterResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
