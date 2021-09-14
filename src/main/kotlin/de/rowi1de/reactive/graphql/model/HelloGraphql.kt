package de.rowi1de.reactive.graphql.model

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import java.time.ZonedDateTime

@GraphQLDescription("Simple World")
data class HelloGraphql(

    private val name: String
) {
    @GraphQLDescription("Say Hello")
    val greeting: String
        get() = "Hello $name!"

    @GraphQLDescription("Time of greeting")
    val time: String
        get() = ZonedDateTime.now().toString()
}
