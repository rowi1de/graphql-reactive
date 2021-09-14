package de.rowi1de.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraphqlReactiveApplication

fun main(args: Array<String>) {
    runApplication<GraphqlReactiveApplication>(*args)
}
