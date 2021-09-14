# Reactive Graphql

Simple Spring WebFlux Project (see [Help.md](HELP.md)) with:

* Classic Annotation Based REST
* WebFlux.Fn / coRouter REST
* [graphql-kotlin](https://github.com/ExpediaGroup/graphql-kotlin) GraphQL

## Kotlin

This project uses [ktlint](https://ktlint.github.io/)

## ktlint

- `brew install ktlint`
- `ktlint --apply-to-idea` or `ktlint --apply-to-idea-project`
- `ktlint --install-git-pre-commit-hook`
- `ktlint -F "src/**/*.kt" "*.kts"`

## Build & Run

* Open `build.gradle.kts` with IntelliJ
* Run Application via IntelliJ or `./gradlew bootRun`

### Graphql
* graphql Endpoints
  * [graphql](http://localhost:8082/graphql)
  * [playground](http://localhost:8082/playground)
  * [sdl](http://localhost:8082/sdl)
  * [subscription](http://localhost:8082/subscription)

Use [playground](http://localhost:8082/playground) to execute queries and browse schema
or [js-graphql](https://plugins.jetbrains.com/plugin/8097-js-graphql)

#### Query

```grahpql
#default value
query helloWorld{
  hello {
    greeting
  }
}

# with paramter
query helloIITS{
  hello(name:"ITTS") {
    greeting
  }
}
```

#### Subscription

```grahpql
subscription {
  hello(name: "ITTS") {
    greeting
    time
  }
}
```

### REST

* [OpenAPI v3 API](http://localhost:8082/api-docs.yaml)
* [Swagger-ui](http://localhost:8082/swagger-ui.html)

#### API

* Annotation based:
  * [GET Hello (Default)](http://localhost:8082/rest/annotations/hello)
  * [GET Hello (Custom)](http://localhost:8082/rest/annotations/hello?name=IITS)
  * [GET Hello (SSE)](http://localhost:8082/rest/annotations/hello/sse?name=IITS)
* Functional:
  * [GET Hello (Default)](http://localhost:8082/rest/functional/hello)


# GitHub

## Actions