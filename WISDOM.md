# Valuable insights from the book or elsewhere

- For long-running processes, JVM mode is typically better. Use native if you (re)start often. 
- On the topic of Quarkus not really encouraging traditional unit tests: https://github.com/quarkusio/quarkus/discussions/27153 
  - TL;DR, Use `@QuarkusIntegrationTest` for tests treating the application as a black box 
  - and use `@QuarkusTest` for tests bringing up the DI framework, which is the kind of unit testing that Quarkus encourages 
  - and use `QuarkusComponentTestExtension` if you want unit tests with basic DI and mocks (https://quarkus.io/guides/testing-components) 
  - or use test framework annotations if you REALLY want plain unit tests.
- For dynamic config (e.g. on K8S) use JVM system props or environment variables. 
  - Precedence (evaluation starts from right); `system <- env <- application.properties`
  - Example with env var, run with `GREETING="STAHP" mvn quarkus:dev` which will override the application config.
- Why multi-module with separate apps is not encouraged by the Quarkus Maven setup; https://github.com/quarkusio/quarkus/issues/42750#issuecomment-2309537445 
- `/q/swagger-ui` can also be enabled on production. Document available `/q/openapi`.