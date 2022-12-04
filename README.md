# Reactive Graphql [![Kotlin CI with Gradle, Build & Push OCI Image](https://github.com/rowi1de/graphql-reactive/actions/workflows/gradle-build-push-image.yml/badge.svg)](https://github.com/rowi1de/graphql-reactive/actions/workflows/gradle-build-push-image.yml) [![Release & Push Helm Charts](https://github.com/rowi1de/graphql-reactive/actions/workflows/helm-release-push-charts.yaml/badge.svg)](https://github.com/rowi1de/graphql-reactive/actions/workflows/helm-release-push-charts.yaml)

Simple Spring WebFlux Project (see [Help.md](HELP.md)) with:

* Classic Annotation Based REST
* WebFlux.Fn / coRouter REST
* [graphql-kotlin](https://github.com/ExpediaGroup/graphql-kotlin) GraphQL ([Why?](https://opensource.expediagroup.com/graphql-kotlin/docs/framework-comparison))

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
  * [graphiql](http://localhost:8082/graphiql)
  * [subscription](http://localhost:8082/subscriptions)

Use [graphiql](http://localhost:8082/graphiql) to execute queries and browse schema
or [js-graphql](https://plugins.jetbrains.com/plugin/8097-js-graphql)

#### Query
```graphql
#default value
query helloWorld{
  hello {
    greeting
  }
}

# with paramter
query helloRob{
  hello(name:"Rob") {
    greeting
  }
}
```
- Partial Results / Errors
```graphql
query hello {
  hello(name: "Rob") {
    greeting
  }
  partialBroken {
    greeting
  }
}

```


#### Subscription

```graphql
subscription {
  hello(name: "Rob") {
    greeting
    time
  }
}
```

#### Employee Example
```graphql
subscription employees {
  employees {
    firstName
    team {
      name
    }
  }
}

mutation newEmployee {
  newEmployee(
    newEmployee: { firstName: "Tim", lastName: "Test", teamName: "Dev" }
  ) {
    firstName
    team {
      employees {
        firstName
      }
    }
  }
}
```

### REST

* [OpenAPI v3 API](http://localhost:8082/api-docs.yaml)
* [Swagger-ui](http://localhost:8082/swagger-ui.html)

#### API

* Annotation based:
  * [GET Hello (Default)](http://localhost:8082/rest/annotations/hello)
  * [GET Hello (Custom)](http://localhost:8082/rest/annotations/hello?name=rowi1de)
  * [GET Hello (SSE)](http://localhost:8082/rest/annotations/hello/sse?name=rowi1de)
* Functional:
  * [GET Hello (Default)](http://localhost:8082/rest/functional/hello)


# GitHub
- [Dependabot](https://github.com/rowi1de/graphql-reactive/blob/main/.github/dependabot.yml)
## Actions
- [![Automatically Update Dependencies](https://github.com/rowi1de/graphql-reactive/actions/workflows/auto-merge-dependabot.yaml/badge.svg)](https://github.com/rowi1de/graphql-reactive/actions/workflows/auto-merge-dependabot.yaml) self-approving Dependabot PRs

# CI / CD
- GitHub Actions + ArgoCD
## CI GitHub Actions
- See [Releases](https://github.com/rowi1de/graphql-reactive/releases) for current released versions
- See [Packages](https://github.com/rowi1de?tab=packages&repo_name=graphql-reactive) for current published helm chart as OCI

##  CD ArgoCD [![App Status](https://argocd.robert-wiesner.de/api/badge?name=services&revision=true)](https://argocd.robert-wiesner.de/applications/services)
- See [Deployments](https://github.com/rowi1de/graphql-reactive/deployments)
- See [Cluster Repo](https://github.com/rowi1de/argocd)
- To deploy, change the version in the argocd repo and create a PR (you can also manually change the version in [argocd for testing (!)](https://argocd.robert-wiesner.de/applications/graphql-reactive?view=tree&resource=&node=argoproj.io%2FApplication%2Fargocd%2Fgraphql-reactive%2F0)):
- [services/graphql-reactive](https://github.com/rowi1de/argocd/blob/main/services/graphql-reactive.yaml#L11) [![App Status](https://argocd.robert-wiesner.de/api/badge?name=graphql-reactive&revision=true)](https://argocd.robert-wiesner.de/applications/graphql-reactive)
  
