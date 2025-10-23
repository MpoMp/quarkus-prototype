# Project setup and development processes

## Initial setup

Initial setup involves git, IDE, JDK and Maven installation.

Quarkus project was initialized with:
```shell
mvn io.quarkus.platform:quarkus-maven-plugin:3.23.0:create \
    -DprojectGroupId=dev.mbogdanos \
    -DprojectArtifactId=quarkus-prototype \
    -Dextensions="quarkus-rest"
```

## Updates

Use: 
```shell
mvn io.quarkus.platform:quarkus-maven-plugin:3.25.0:update -N
```

## Tooling 

[HTTPie](https://httpie.io/) is the preferred tool to access the exposed APIs.

## Build and run 

You can use the Maven wrapper, if you prefer so.

To run in dev mode, use:
```shell
mvn quarkus:dev
```

To package use (optionally with `clean`): 
```shell
mvn package
``` 
The `quarkus-run.jar` under `target/quarkus-app` is not an Uber JAR, it depends on `target/quarkus-app/lib`. 

Create Linux executable without GraalVM (using Docker):
```shell
./mvnw install -Dnative -DskipTests -Dquarkus.native.container-build=true
```

JVM mode with JAR: 
```shell
java -jar target/quarkus-app/quarkus-run.jar 
```

Native executable; use the `runner` under `target`.
