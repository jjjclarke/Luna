package com.jjjclarke.luna;

import java.util.List;

public interface LunaCallable {
    int arity();

    Object call(Interpreter interpreter, List<Object> arguments);
}
