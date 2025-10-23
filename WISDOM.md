# Valuable insights from the book or elsewhere

- For long-running processes, JVM mode is typically better. Use native if you (re)start often.
- On the topic of Quarkus not really encouraging traditional unit tests: https://github.com/quarkusio/quarkus/discussions/27153 
  - TL;DR, Use `@QuarkusIntegrationTest` for tests treating the application as a black box
  - use `@QuarkusTest` for tests bringing up the DI framework, which is the kind of unit testing that Quarkus encourages
  - use `QuarkusComponentTestExtension` if you want unit tests with basic DI and mocks; https://quarkus.io/guides/testing-components
  - or use test framework annotations if you REALLY want plain unit tests.

