package com.yp.refactor.after;

public class BooldGroup {
    public static final BooldGroup O = new BooldGroup(0);
    public static final BooldGroup A = new BooldGroup(1);
    public static final BooldGroup B = new BooldGroup(2);
    public static final BooldGroup AB = new BooldGroup(3);

    private static final BooldGroup[] values = {O, A, B, AB};

    private final int code;

    public BooldGroup(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BooldGroup code(int code) {
        return values[code];
    }
}
