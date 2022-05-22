package com.mygdx.game.data.enums;

public enum BarType {
    RED("meter0", "meter1", "meter2"),
    BLU("meter6", "meter7", "meter8");
    // haha tf2 reference


    public final String barStart;
    public final String barEnd;
    public final String bar;

    BarType(String barStart, String bar, String barEnd) {
        this.barStart = barStart;
        this.bar = bar;
        this.barEnd = barEnd;
    }
}
