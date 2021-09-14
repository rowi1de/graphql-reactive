package de.rowi1de.reactive.graphql.subscription

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Subscription
import de.rowi1de.reactive.graphql.model.HelloGraphql
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration

@Component
class HelloSubscription : Subscription {
    @GraphQLDescription("Greet every second")
    suspend fun hello(name: String): Flux<HelloGraphql> = Flux.interval(Duration.ofSeconds(1))
        .map { HelloGraphql("$name $it") }
}
