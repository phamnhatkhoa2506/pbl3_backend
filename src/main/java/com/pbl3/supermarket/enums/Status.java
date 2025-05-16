package com.pbl3.supermarket.enums;

public enum Status {
    PAID("DA THANH TOAN"),
    PROCESSING("DON HANG DANG DUOC XU LI"),
    DELIVERD("DA GIAO HANG THANH CONG"),
    DENIED("DON HANG BI TU CHOI"),
    ;
    Status(String message){
        this.message = message;
    }
    public String message;
}
