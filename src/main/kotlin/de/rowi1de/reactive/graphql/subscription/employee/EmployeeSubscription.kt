package de.rowi1de.reactive.graphql.subscription.employee

import com.expediagroup.graphql.server.operations.Subscription
import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.service.employees.EmployeeService
import kotlinx.coroutines.reactor.asFlux
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class EmployeeSubscription(private val service: EmployeeService) : Subscription {
    suspend fun employees(): Flux<Employee> = service.employees().asFlux()
}
