package de.rowi1de.reactive.rest.employees

import de.rowi1de.reactive.graphql.model.employees.Teams
import de.rowi1de.reactive.service.employees.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/teams")
internal class TeamController(private val service: EmployeeService) {

    @GetMapping
    internal suspend fun teams(): Teams = Teams(service.getTeams())
}
