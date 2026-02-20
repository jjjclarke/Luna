package com.jjjclarke.luna;

public class Return extends RuntimeException {
    public final Object value;

    public Return(Object value) {
        // This constructor disables some JVM functionality that is unnecessary and unneeded.
        super(null, null, false, false);

        this.value = value;
    }
}
