package de.rowi1de.reactive.graphql.query

import com.expediagroup.graphql.server.operations.Query
import de.rowi1de.reactive.graphql.model.HelloGraphql
import graphql.GraphqlErrorBuilder.newError
import graphql.execution.DataFetcherResult
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class BrokenQuery : Query {

    suspend fun broken(): HelloGraphql = TODO("I told you")

    suspend fun partialBroken(env: DataFetchingEnvironment): DataFetcherResult<HelloGraphql> =
        DataFetcherResult.newResult<HelloGraphql>()
            .data(HelloGraphql("Hello"))
            .error(newError(env).message("Still something wrong").build()).build()
}
