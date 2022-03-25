package de.rowi1de.reactive.rest.employees

import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.graphql.model.employees.Employees
import de.rowi1de.reactive.graphql.model.employees.NewEmployee
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * These endpoints won't work due to cycle in Model (Employee<>Team)
 * and REST doesn't support a Graph with specified depth out of the box
 */
@RestController
@RequestMapping("/rest/employees")
internal class EmployeeController(private val service: EmployeeService) {

    @GetMapping
    internal suspend fun employees(): Employees = Employees(service.getEmployees())

    @PostMapping
    internal suspend fun addEmployee(employee: NewEmployee): Employee =
        service.addEmployee(employee.firstName, employee.lastName, employee.teamName)
}
