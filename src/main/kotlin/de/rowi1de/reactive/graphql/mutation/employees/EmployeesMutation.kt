package de.rowi1de.reactive.graphql.mutation.employees

import com.expediagroup.graphql.server.operations.Mutation
import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.graphql.model.employees.NewEmployee
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.stereotype.Component

@Component
class EmployeesMutation(private val service: EmployeeService) : Mutation {

    suspend fun newEmployee(newEmployee: NewEmployee): Employee = service.addEmployee(newEmployee)
}
