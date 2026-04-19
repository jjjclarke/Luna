# Luna

## Building & Running

### Eclipse

1. Clone

```
git clone https://github.com/jjjclarke/Luna.git
```

2. Import in Eclipse

Click **File**, then **Import**, search for "*Existing Maven Projects*", and point at the Luna directory that was just clone.

3. Run in Eclipse

Two pre-configured launch configurations are included: **Launch IDE** and **Launch REPL**. Right-click, go to **Run as...** and then select the only option.

### Maven

Using a modern version of Maven is recommended. No Maven executable is included in the repository.

1. Clone

```
git clone https://github.com/jjjclarke/Luna.git
cd Luna
```

2. Use Maven to build

```
mvn clean package
```

This will build a copy of `Luna-0.3.1.jar` in the `target` directory.

3. Use Maven to run (required for IDE)

First: Run the REPL directly.

```
mvn exec:java
```

Second: Run the built-in IDE directly.

```
mvn -P studio exec:java
```