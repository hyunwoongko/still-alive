package com.nightonke.blurlockview;

public enum Password {
    NUMBER(0),
    TEXT(1);
    
    private int type;

    private Password(int i) {
        this.type = i;
    }
}
