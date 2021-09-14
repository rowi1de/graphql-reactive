package de.rowi1de.reactive.graphql.query

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import de.rowi1de.reactive.graphql.model.HelloGraphql
import org.springframework.stereotype.Component

@Component
class HelloQuery : Query {

    @GraphQLDescription("Simple Hello World Query")
    suspend fun hello(
        @GraphQLDescription("Who should we greet?") name: String? = null
    ): HelloGraphql = HelloGraphql(name ?: "World")
}
