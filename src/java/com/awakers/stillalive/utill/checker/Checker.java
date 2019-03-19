package com.awakers.stillalive.utill.checker;

public class Checker {
    private static volatile Checker checker;

    private Checker() {
    }

    public static Checker get() {
        if (checker != null) {
            return checker;
        }
        Checker checker;
        synchronized (Checker.class) {
            if (checker == null) {
                checker = new Checker();
            }
            checker = checker;
        }
        return checker;
    }
}
