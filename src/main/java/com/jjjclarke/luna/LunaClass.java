package com.jjjclarke.luna;

import java.util.List;
import java.util.Map;

public class LunaClass implements LunaCallable {
    public final String name;
    public final LunaClass superclass;
    private final Map<String, LunaFunction> methods;

    public LunaClass(String name, LunaClass superclass, Map<String, LunaFunction> methods) {
        this.superclass = superclass;
        this.name = name;
        this.methods = methods;
    }

    public LunaFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }

    @Override
    public int arity() {
        LunaFunction initialiser = findMethod("init");
        if (initialiser == null) {
            return 0;
        } else {
            return initialiser.arity();
        }
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        /*
        When you call a class in Luna, it creates and instantiates a
        new instance of for the class and then returns it.
         */
        LunaInstance instance = new LunaInstance(this);
        LunaFunction initialiser = findMethod("init");
        if (initialiser != null) {
            initialiser.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public String toString() {
        return name;
    }
}
