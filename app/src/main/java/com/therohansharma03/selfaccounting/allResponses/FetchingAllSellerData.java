package com.therohansharma03.selfaccounting.allResponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchingAllSellerData {
    @SerializedName("seller")
    List<Seller> sellersList;
    String error,message;

    public FetchingAllSellerData(List<Seller> sellersList, String error, String message) {
        this.sellersList = sellersList;
        this.error = error;
        this.message = message;
    }

    public List<Seller> getSellersList() {
        return sellersList;
    }

    public void setSellersList(List<Seller> sellersList) {
        this.sellersList = sellersList;
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
