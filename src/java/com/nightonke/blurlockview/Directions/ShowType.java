package com.nightonke.blurlockview.Directions;

public enum ShowType {
    FROM_TOP_TO_BOTTOM(0),
    FROM_RIGHT_TO_LEFT(1),
    FROM_BOTTOM_TO_TOP(2),
    FROM_LEFT_TO_RIGHT(3),
    FADE_IN(4);
    
    int type;

    private ShowType(int i) {
        this.type = i;
    }
}
