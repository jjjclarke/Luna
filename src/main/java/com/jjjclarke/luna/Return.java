package com.jjjclarke.luna;

public class Return extends RuntimeException {
    final Object value;

    public Return(Object value) {
        super(null, null, false, false); // disalbes JVM stuff
        this.value = value;
    }
}
