package com.example.ctsalarm.Real_Time_Database;

public class UserHelperClass {

    String MobileNumber,UserName,PinCode,Gender;
    public UserHelperClass(){}

    public UserHelperClass(String mobileNumber, String userName, String pinCode, String gender) {
        MobileNumber = mobileNumber;
        UserName = userName;
        PinCode = pinCode;
        Gender = gender;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
