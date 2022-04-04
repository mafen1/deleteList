package com.example.deletelist.data.repository

import com.example.deletelist.data.models.Person
import com.example.deletelist.data.storages.StorageName
import com.example.deletelist.domain.repository.Repository

class RepositoryImpl(private val storageName: StorageName): Repository {

    override fun getName(): String = storageName.listName().random()

    override fun getAge(): Int = (1..70).random()

    override fun getPersonList() = mutableListOf<Person>().apply {
        repeat(100){
            add(Person(getName(), getAge()))
        }
    }

}