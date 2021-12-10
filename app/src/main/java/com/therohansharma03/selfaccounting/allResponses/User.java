package com.therohansharma03.selfaccounting.allResponses;

public class User {

    int SrNo;
    String Name,EmailId,MobileNumber;

    public User(int srNo, String name, String emailId, String mobileNumber) {
        SrNo = srNo;
        Name = name;
        EmailId = emailId;
        MobileNumber = mobileNumber;
    }

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int srNo) {
        SrNo = srNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }
}
