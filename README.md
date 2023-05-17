# Introduction

[//]: # TODO

# installing
In order to compile and install application locally, execute:
```bash
./mvnw clean install
```

# Running locally
Run the spring server locally, either from IDE, or with maven wrapper, by executing:
```bash
./mvnw spring-boot:run
```
Test app locally by:
```bash
curl localhost:8080/lambdaHandlerWithFunction -H "Content-Type: text/plain" -d "ITMagination"
```
```bash
curl localhost:8080/lambdaHandlerWithConsumer -H "Content-Type: text/plain" -d "ITMagination"
```
```bash
curl localhost:8080/lambdaHandlerWithSupplier -H "Content-Type: text/plain"
```
```bash
curl localhost:8080/functionHandler -H "Content-Type: text/plain" -d "ITMagination"
```