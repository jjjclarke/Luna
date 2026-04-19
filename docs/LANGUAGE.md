Luna is a high-level, dynamically typed programming language designed for simplicity and ease of use. It is implemented as a tree-walking interpreter written in Java and is heavily inspired by the Lox language described in Crafting Interpreters.

Luna adopts a syntax similar to JavaScript, with a focus on readability and minimal boilerplate. It supports object-oriented programming, control flow constructs, and an interactive REPL (Read-Eval-Print Loop).

# Key Features

- Dynamically typed variables
- Object-oriented programming with classes and inheritance
- First-class functions
- Control flow (if, while, for)
- Automatic memory management
- Interactive REPL
- Simple and expressive syntax

# Differences from Lox

Luna is largely based on Lox, with the following notable changes:

- The keyword `fun` is replaced with `void` for function declarations.
- Support for multi-line comments has been added.
- Minor syntax and implementation adjustments.

# Basic Syntax

## Variables

Variables are declared using `var`:

```
var x = 10;
var name = "Luna";
```

## Functions

Functions are declared using `void`:

```
void greet(name) {
    print "Hello, " + name;
}
```

## Control Flow

```
if (x > 5) {
    print "Greater than five";
} else {
    print "Five or less";
}

while (x > 0) {
    x = x - 1;
}
```

## Classes and Inheritance

```
class Person {
    speak() {
        print "A brand new day!";
    }
}

class Student < Person {
    speak() {
        print "Another day, another assignment!";
    }
}
```

## Comments

```
// Single-line comment

/* 
   Multi-line comment
*/
```

## Data Types

Luna supports the following built-in data types:

- Number (integers and floating-point)
- String
- Nil (represents absence of value)
- Objects (instances of classes)

## Example Program

```
void fib(n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}

print fib(6);
```