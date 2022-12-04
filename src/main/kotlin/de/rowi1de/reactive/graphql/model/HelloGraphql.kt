package de.rowi1de.reactive.graphql.model

import java.time.ZonedDateTime

data class HelloGraphql(

    private val name: String
) {
    val greeting: String
        get() = "Hello $name!"

    val time: String
        get() = ZonedDateTime.now().toString()
}
