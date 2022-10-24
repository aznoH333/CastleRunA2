package com.mygdx.game.data.enums;

public enum BarType {
    RED( "meter1", "meter0"),
    BLU( "meter3", "meter2");
    // haha tf2 reference


    public final String barEnd;
    public final String bar;

    BarType( String bar, String barEnd) {
        this.bar = bar;
        this.barEnd = barEnd;
    }
}
