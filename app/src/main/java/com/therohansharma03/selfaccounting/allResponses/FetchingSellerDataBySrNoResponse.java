package com.therohansharma03.selfaccounting.allResponses;

public class FetchingSellerDataBySrNoResponse {
    Seller seller;
    String error,message;

    public FetchingSellerDataBySrNoResponse(Seller seller, String error, String message) {
        this.seller = seller;
        this.error = error;
        this.message = message;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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
