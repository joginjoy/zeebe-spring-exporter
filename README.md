# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/3.5.3/reference/messaging/kafka.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Set-up
* Docker should already be available and running on your machine
* Kafka
  * Ref: https://github.com/obsidiandynamics/kafdrop 
  * Run  ```docker compose -f .\dev\docker-compose-kafka\docker-compose.yaml up -d``` from project root
* Zeebe
  * Ref: https://docs.camunda.io/docs/self-managed/setup/deploy/local/docker-compose/#run-camunda-8-with-docker-compose
  * Run ```docker compose --env-file .\dev\docker-compose-camunda\.env -f .\dev\docker-compose-camunda\docker-compose-core.yaml up -d``` from project root
* Sample bpmn and related files available under camunda-modeler folder
