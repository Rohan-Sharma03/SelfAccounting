package com.therohansharma03.selfaccounting.allResponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchingAllUserData {
    @SerializedName("user")
    List<User> userList;
    String error,message;

    public FetchingAllUserData(List<User> userList, String error, String message) {
        this.userList = userList;
        this.error = error;
        this.message = message;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
