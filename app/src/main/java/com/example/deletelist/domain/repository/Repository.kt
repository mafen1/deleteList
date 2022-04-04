package com.example.deletelist.domain.repository

import com.example.deletelist.data.models.Person

interface Repository {
    fun getName(): String
    fun getAge(): Int
    fun getPersonList(): MutableList<Person>
}