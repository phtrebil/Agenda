package br.com.pedro.agenda.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente

class ListaDeClientesViewModel(
    private val repository: ClienteDatabase
): ViewModel() {
    fun getAll(): LiveData<List<Cliente>> {
        val mutableLiveData = MutableLiveData<List<Cliente>>()
        mutableLiveData.value = repository.clienteDatabase().getAll()
        return mutableLiveData
    }

    fun delete(cliente: Cliente) : LiveData<Unit> {
        val mutableLiveData = MutableLiveData<Unit>()
        mutableLiveData.value = repository.clienteDatabase().delete(cliente)
        return mutableLiveData
    }


}
