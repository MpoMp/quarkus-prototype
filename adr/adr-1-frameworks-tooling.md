
# ADR-1: Choice of frameworks and tooling

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

This repository is intended to be used as a practice ground for Quarkus development. 
Some setup decisions are to be taken here.

## Decision

_What is the change that we're proposing and/or doing?_ 

The framework to be used will be Quarkus. 
Upgrades will be done using their tooling, e.g. running  `./mvnw io.quarkus.platform:quarkus-maven-plugin:3.25.0:update -N` 
at the root directory of the Maven project.

For the build/development setup, [Maven](https://quarkus.io/guides/maven-tooling) will be used to handle setup and 
build tasks. 

The latest [Temurin LTS JDK](https://adoptium.net/en-GB/temurin/releases) shall be used, starting from 21.

For JDK and Maven, [SDKMAN](https://sdkman.io/) will be used for installation and management.

The latest LTS version shall be used for all frameworks and tools and, depending on the available time, they shall be 
kept up to date. Unless there are serious issues preventing or holding back some upgrade.

## Consequences

### Pros 

_List the advantages of the decision here._

- Standard way of setting up the development environment.

### Cons

_List the disadvantages of the decision here._

- Whatever disadvantages the frameworks and tools themselves bring.
