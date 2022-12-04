package de.rowi1de.reactive.graphql.query.employees

import de.rowi1de.reactive.graphql.model.employees.Employees
import de.rowi1de.reactive.graphql.model.employees.Teams
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class EmployeesQuery(private val service: EmployeeService)  {

    @QueryMapping
    suspend fun employees(): Employees = Employees(service.getEmployees())

    @QueryMapping
    suspend fun teams(): Teams = Teams(service.getTeams())
}
