package com.jjjclarke.luna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Luna {
    private static final Interpreter interpreter = new Interpreter();

    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: luna-interpreter [path-to-script]");
            System.exit(1);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runREPL();
        }
    }

    // ====================================================================

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError)
            System.exit(1);
        if (hadRuntimeError)
            System.exit(1);
    }

    private static void runREPL() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true) {
            System.out.print(">> ");
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);

            hadError = false; // An error in the REPL should not kill the entire program
        }
    }

    private static void run(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        if (hadError)
            return;

        interpreter.interpret(statements);
    }

    // ====================================================================

    public static void error(int line, String message) {
        report(line, "", message);
    }

    public static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    public static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error " + where + ": " + message);

        hadError = true;
    }
}