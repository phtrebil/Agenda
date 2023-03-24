package br.com.pedro.agenda.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente
import kotlinx.coroutines.flow.Flow

class ListaDeClientesViewModel(
    private val repository: ClienteDatabase
) : ViewModel() {
    fun getAll(): Flow<List<Cliente>> {
        return repository.clienteDatabase().getAll()
    }

    suspend fun delete(cliente: Cliente) {
        repository.clienteDatabase().delete(cliente)

    }


}
