package de.rowi1de.reactive.graphql.query.employees

import com.expediagroup.graphql.server.operations.Query
import de.rowi1de.reactive.graphql.model.employees.Employees
import de.rowi1de.reactive.graphql.model.employees.Teams
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.stereotype.Component

@Component
class EmployeesQuery(private val service: EmployeeService) : Query {

    suspend fun employees(): Employees = Employees(service.getEmployees())

    suspend fun teams(): Teams = Teams(service.getTeams())
}
