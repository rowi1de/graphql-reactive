package de.rowi1de.reactive.rest.annotations

import de.rowi1de.reactive.rest.model.HelloRest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/rest/annotations")
internal class HelloController {

    @GetMapping("/hello")
    fun hello(@RequestParam(required = false) name: String?): HelloRest = HelloRest(name ?: "World")

    @GetMapping("/hello/sse", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun helloFlux(@RequestParam(required = false) name: String?): Flux<HelloRest> =
        Flux.interval(Duration.ofSeconds(1)).map { HelloRest(name ?: "World") }
}
