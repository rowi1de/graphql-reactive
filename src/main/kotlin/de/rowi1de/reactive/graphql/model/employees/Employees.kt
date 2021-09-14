package de.rowi1de.reactive.graphql.model.employees

import java.util.UUID

data class Teams(
    val teams: List<Team>
)

data class Team(
    val name: String,
    val employees: MutableList<Employee> = mutableListOf()
) {
    val id: String by lazy { UUID.randomUUID().toString() }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

data class Employees(
    val employees: List<Employee>
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val team: Team
) {
    val id: String by lazy { UUID.randomUUID().toString() }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }
}

data class NewEmployee(val firstName: String, val lastName: String, val teamName: String)
