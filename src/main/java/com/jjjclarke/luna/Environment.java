package com.jjjclarke.luna;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    public Environment() {
        enclosing = null;
    }

    public Environment(Environment environment) {
        this.enclosing = environment;
    }

    public Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        if (enclosing != null)
            return enclosing.get(name);

        // This will be thrown if neither of the two earlier conditions are met.
        throw new RuntimeError(name, "Undefined variable '" + name.lexeme +"'.");
    }

    public void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        // This will be thrown if neither of the two earlier conditions are met.
        throw new RuntimeError(name, "Undefined variable '" + name.lexeme +"'.");
    }

    public void define(String name, Object value) {
        values.put(name, value);
    }

    public Object getAt(int distance, String name) {
        return ancestor(distance).values.get(name);
    }

    public void assignAt(int distance, Token name, Object value) {
        ancestor(distance).values.put(name.lexeme, value);
    }

    public Environment ancestor(int distance) {
        Environment env = this;
        for (int i = 0; i < distance; i++) {
            env = env.enclosing;
        }
        return env;
    }

    @Override
    public String toString() {
        String result = values.toString();

        if (enclosing != null) {
            result += " -> " + enclosing.toString();
        }

        return result;
    }
}
