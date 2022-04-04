package com.example.deletelist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deletelist.data.models.Person
import com.example.deletelist.domain.useCase.PersonUseCase
import com.example.deletelist.ui.MainActivity.Companion.TAG
import java.util.*

class MainViewModel(private val personUseCase: PersonUseCase): ViewModel() {
    private val personAdapter = PersonAdapter()
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
    fun moveItemUp(position: Int){
        if (position != 0) {
            val persons = _listPerson.value
            val person = _listPerson.value?.get(position)
            _listPerson.value?.removeAt(position)
            _listPerson.value?.add(position - 1, person!!)
            _listPerson.value = persons
        }
    }

    fun moveItemDowm(position: Int) {
        if (position != -1) {
            val persons = _listPerson.value
            val person = _listPerson.value?.get(position)
            _listPerson.value?.removeAt(position)
            _listPerson.value?.add(position + 1, person!!)
            _listPerson.value = persons
        }
    }

}