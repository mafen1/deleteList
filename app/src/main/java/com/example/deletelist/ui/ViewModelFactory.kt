package com.example.deletelist.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deletelist.domain.useCase.PersonUseCase
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val personUseCase: PersonUseCase): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.personUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }

}