package com.jjjclarke.luna;

import java.util.List;

class LunaFunction implements LunaCallable {
    private final Stmt.Function declaration;
    private final Environment closure;

    private final boolean isInitialiser;

    public LunaFunction(Stmt.Function declaration, Environment closure, boolean isInitialiser) {
        this.closure = closure;
        this.declaration = declaration;
        this.isInitialiser = isInitialiser;
    }

    public LunaFunction bind(LunaInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);

        return new LunaFunction(declaration, environment, isInitialiser);
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);

        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            if (isInitialiser)
                return closure.getAt(0, "this");

            return returnValue.value;
        }

        if (isInitialiser)
            return closure.getAt(0, "this");
        return null;
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme +">";
    }
}
