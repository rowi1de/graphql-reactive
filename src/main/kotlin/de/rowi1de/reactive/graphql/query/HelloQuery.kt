package de.rowi1de.reactive.graphql.query

import de.rowi1de.reactive.graphql.model.HelloGraphql
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class HelloQuery {

    @QueryMapping
    suspend fun hello(
            @Argument name: String? = null
    ): HelloGraphql = HelloGraphql(name ?: "World")
}
