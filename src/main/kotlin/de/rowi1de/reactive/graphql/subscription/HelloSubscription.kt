package de.rowi1de.reactive.graphql.subscription

import de.rowi1de.reactive.graphql.model.HelloGraphql
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.time.Duration

@Controller
class HelloSubscription {
    @SubscriptionMapping
    suspend fun hello(name: String): Flux<HelloGraphql> = Flux.interval(Duration.ofSeconds(1))
        .map { HelloGraphql("$name $it") }
}
