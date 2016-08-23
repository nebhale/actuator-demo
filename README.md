# Spring Boot Actuator Demo
This documentation is intentionally terse because it is intended for an internal Pivotal audience.

## Building
To build and the application run the following:

```
$ ./mvnw package
$ java -jar target/actuator-demo-0.0.1-SNAPSHOT.jar
```

## Endpoints
The following endpoints are available:

* `/actuator`
* `/autoconfig`
* `/beans`
* `/configprops`
* `/docs`
* `/dump`
* `/env`
* `/health`
* `/heapdump`
* `/info`
* `/jolokia/`
* `/logfile`
* `/mappings`
* `/metrics`
* `/test`
* `/trace`

A [Paw](https://paw.cloud) definition file with all of these endpoints is included in the repository.
