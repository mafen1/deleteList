package com.example.deletelist.domain.useCase

import com.example.deletelist.domain.repository.Repository

class PersonUseCase(private val repository: Repository) {

    fun getList() = repository.getPersonList()

}