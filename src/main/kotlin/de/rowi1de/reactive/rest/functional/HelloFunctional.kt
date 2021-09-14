package de.rowi1de.reactive.rest.functional

import de.rowi1de.reactive.rest.model.HelloRest
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.reactor.awaitSingle
import org.springdoc.core.annotations.RouterOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
internal class HelloFunctional(
    private val handler: HelloFunctionalHandler
) {
    @Bean
    @RouterOperation(
        path = "/rest/functional/hello",
        operation = Operation(method = "GET", operationId = "hello")
    ) // OpenAPI only
    fun router() = coRouter {
        "/rest/functional".nest {
            GET("/hello").invoke(handler::helloResponse)
        }
    }
}

@Component
internal class HelloFunctionalHandler {
    suspend fun helloResponse(request: ServerRequest): ServerResponse =
        ServerResponse.ok().bodyValue(hello()).awaitSingle()

    private fun hello(): HelloRest = HelloRest("World")
}
