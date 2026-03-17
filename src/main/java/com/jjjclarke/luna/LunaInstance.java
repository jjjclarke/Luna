package com.jjjclarke.luna;

import java.util.HashMap;
import java.util.Map;

public class LunaInstance {
    private LunaClass clas;
    private final Map<String, Object> fields = new HashMap<>();

    public LunaInstance(LunaClass clas) {
        this.clas = clas;
    }

    public Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        LunaFunction method = clas.findMethod(name.lexeme);
        if (method != null)
            return method.bind(this);

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    public void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return clas.name + " instance";
    }
}
