package de.rowi1de.reactive.service.employees

import de.rowi1de.reactive.graphql.model.employees.Employee
import de.rowi1de.reactive.graphql.model.employees.NewEmployee
import de.rowi1de.reactive.graphql.model.employees.Team
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Service

@Service
class EmployeeService {
    fun getTeams(): List<Team> = teams.values.toList()

    fun getEmployees(): List<Employee> = employees.toList()

    private val channel = ConflatedBroadcastChannel<Employee>()

    suspend fun addEmployee(newEmployee: NewEmployee): Employee =
        addEmployee(newEmployee.firstName, newEmployee.lastName, newEmployee.teamName)

    suspend fun addEmployee(firstName: String, lastName: String, teamName: String): Employee {
        val team = teams[teamName] ?: Team(name = teamName).also { teams[teamName] = it }
        val newEmployee = Employee(firstName, lastName, team).also { employees.add(it) }
        team.employees.add(newEmployee)
        channel.send(newEmployee)
        return newEmployee
    }

    suspend fun employees(): Flow<Employee> = channel.asFlow()

    private val teams: MutableMap<String, Team> = mutableMapOf()

    private val employees: MutableSet<Employee> = mutableSetOf()
}
