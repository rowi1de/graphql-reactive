package de.rowi1de.reactive.graphql.subscription.employee

import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.service.employees.EmployeeService
import kotlinx.coroutines.reactor.asFlux
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class EmployeeSubscription(private val service: EmployeeService)  {
    @SubscriptionMapping
    suspend fun employees(): Flux<Employee> = service.employees().asFlux()
}
