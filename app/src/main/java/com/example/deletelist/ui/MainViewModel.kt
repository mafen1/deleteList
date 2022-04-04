package com.example.deletelist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deletelist.data.models.Person
import com.example.deletelist.domain.useCase.PersonUseCase

class MainViewModel(private val personUseCase: PersonUseCase): ViewModel() {

    private var _listPerson = MutableLiveData<MutableList<Person>?>()
    val listPerson: LiveData<MutableList<Person>?> = _listPerson

    fun getPerson(){
        _listPerson.value = personUseCase.getList()
    }

    fun removePerson(position: Int){
        val person = _listPerson.value
        person?.removeAt(position)
        _listPerson.value = person
    }
    fun sortAsc(){
        val person = _listPerson.value
        person?.sortWith( compareBy{it.age} )
        _listPerson.value = person
    }

    fun sortDesc() {
        val persons = _listPerson.value
        persons?.sortWith( compareByDescending{it.age} )
        _listPerson.value = persons
    }

}