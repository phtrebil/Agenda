package br.com.pedro.agenda.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente

class ListaDeClientesViewModel(
    private val repository: ClienteDatabase
): ViewModel() {
    fun getAll(): List<Cliente> {
        return  repository.clienteDatabase().getAll()
    }

    fun delete(cliente: Cliente) {
        repository.clienteDatabase().delete(cliente)
    }


}
