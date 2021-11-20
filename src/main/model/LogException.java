package model;

import java.awt.image.LookupOp;

public class LogException extends Exception {

    public LogException() {
        super("error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}
