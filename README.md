# Luna

**Notice:** This is my submission for my final year project. 0.3.0 is the last major release: I may release a minor update over the next few weeks, but I'm not going to add any major changes so that my final submission is (a) stable, and (b) matches what was shown in the project presentation.

**Notice:** Luna and its IDE, Luna Studio, are currently fundamentally part of the same program, and they cannot be disconnected from each other without some work, which I'm not going to do due to the point above. If/when I return to the project in the future, beyond graduation, I'll work on that!

## Dev

### Maven
Luna is, technically, a Maven project and will work in any IDE, and can be built via the command line:

```
mvn clean package
java -jar target/Luna-0.3.0.jar
```

### Eclipse

However, Eclipse is the preferred development environment. You can launch both the REPL and the IDE with the `Launch *.launch` files in the project directory. Ensure you import the project as an existing Maven project, through File -> Import -> Maven -> Existing Maven Projects

## Sample Luna programs

A number of sample Luna programs can be found in the `/luna` directory. These can be accessed in Luna Studio.