package com.example.deletelist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.deletelist.R
import com.example.deletelist.data.repository.RepositoryImpl
import com.example.deletelist.data.storages.StorageName
import com.example.deletelist.databinding.ActivityMainBinding
import com.example.deletelist.domain.useCase.PersonUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val storage = StorageName()
    private val repository = RepositoryImpl(storage)
    private val personAdapter = PersonAdapter()
    private val viewModel: MainViewModel by viewModels{
        ViewModelFactory(PersonUseCase(repository))
    }
    companion object{
        val TAG = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initView()
        initData()
        initObserves()


    }
    private fun initObserves(){
        viewModel.listPerson.observe(this){
            if (it != null){
                personAdapter.submitList(it.toMutableList())
                personAdapter.items = it
            }
        }
    }
    private fun initData(){

        viewModel.getPerson()

        personAdapter.callBack = { position: Int ->
            viewModel.removePerson(position)
        }
        personAdapter.callBackPositionUp = {  position: Int ->
            viewModel.moveItemUp(position)
        }
        personAdapter.callBackPositionDown = {  position: Int ->
            viewModel.moveItemDowm(position)
        }

    }
    private fun initView(){

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = personAdapter
        }
        binding.button.setOnClickListener{
            viewModel.sortAsc()
        }
        binding.button2.setOnClickListener{
            viewModel.sortDesc()
        }
    }
}