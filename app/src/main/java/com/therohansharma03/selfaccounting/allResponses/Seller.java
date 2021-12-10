package com.therohansharma03.selfaccounting.allResponses;

public class Seller {
    int SrNo;
    String FirstName;
    String LastName;
    String Commodity;
    float BasicRates;
    float Weight;
    float BeneficiaryAmount;
    String PaymentMethod;
    String AccountNumber;
    String IFSCode;
    String PurchaseDate;
    String MobileNumber;
    String ResidentialAddress;
    String CustomerOf;

    public Seller(int SrNo, String FirstName, String LastName, String Commodity, float BasicRates, float Weight, float BeneficiaryAmount, String PaymentMethod, String AccountNumber, String IFSCode, String PurchaseDate, String MobileNumber, String ResidentialAddress, String CustomerOf) {
        this.SrNo = SrNo;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Commodity = Commodity;
        this.BasicRates = BasicRates;
        this.Weight = Weight;
        this.BeneficiaryAmount = BeneficiaryAmount;
        this.PaymentMethod = PaymentMethod;
        this.AccountNumber = AccountNumber;
        this.IFSCode = IFSCode;
        this.PurchaseDate = PurchaseDate;
        this.MobileNumber = MobileNumber;
        this.ResidentialAddress = ResidentialAddress;
        this.CustomerOf = CustomerOf;
    }

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int SrNo) {
        SrNo = SrNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        LastName = LastName;
    }

    public String getCommodity() {
        return Commodity;
    }

    public void setCommodity(String commodity) {
        Commodity = Commodity;
    }

    public float getBasicRates() {
        return BasicRates;
    }

    public void setBasicRates(float basicRates) {
        BasicRates = BasicRates;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float Weight) {
        Weight = Weight;
    }

    public float getBeneficiaryAmount() {
        return BeneficiaryAmount;
    }

    public void setBeneficiaryAmount(float BeneficiaryAmountv) {
        BeneficiaryAmount = BeneficiaryAmount;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        PaymentMethod = PaymentMethod;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String AccountNumber) {
        AccountNumber = AccountNumber;
    }

    public String getIFSCode() {
        return IFSCode;
    }

    public void setIFSCode(String IFSCode) {
        this.IFSCode = IFSCode;
    }

    public String getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(String PurchaseDate) {
        PurchaseDate = PurchaseDate;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String MobileNumber) {
        MobileNumber = MobileNumber;
    }

    public String getResidentialAddress() {
        return ResidentialAddress;
    }

    public void setResidentialAddress(String ResidentialAddress) {
        ResidentialAddress = ResidentialAddress;
    }

    public String getCustomerOf() {
        return CustomerOf;
    }

    public void setCustomerOf(String CustomerOf) {
        CustomerOf = CustomerOf;
    }
}
