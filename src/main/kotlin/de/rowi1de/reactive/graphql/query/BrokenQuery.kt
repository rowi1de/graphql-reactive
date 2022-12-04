package de.rowi1de.reactive.graphql.query

import de.rowi1de.reactive.graphql.model.HelloGraphql
import graphql.GraphqlErrorBuilder.newError
import graphql.execution.DataFetcherResult
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BrokenQuery {

    @QueryMapping
    suspend fun broken(): HelloGraphql = TODO("I told you")

    @QueryMapping
    suspend fun partialBroken(env: DataFetchingEnvironment): DataFetcherResult<HelloGraphql> =
        DataFetcherResult.newResult<HelloGraphql>()
            .data(HelloGraphql("Broken"))
            .error(newError(env).message("Still something wrong").build()).build()
}
