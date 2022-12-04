package de.rowi1de.reactive.graphql.mutation.employees

import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.graphql.model.employees.NewEmployee
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class EmployeesMutation(private val service: EmployeeService)  {

    @MutationMapping
    suspend fun newEmployee(newEmployee: NewEmployee): Employee = service.addEmployee(newEmployee)
}
