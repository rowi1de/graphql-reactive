package de.rowi1de.reactive.rest.model

import java.time.ZonedDateTime

internal data class HelloRest(

    private val name: String
) {
    val greeting: String
        get() = "Hello $name!"

    val time: String
        get() = ZonedDateTime.now().toString()
}
