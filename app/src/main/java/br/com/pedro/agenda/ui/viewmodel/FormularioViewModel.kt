package br.com.pedro.agenda.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente

class FormularioViewModel(
    private val repository: ClienteDatabase
):ViewModel() {

    suspend fun salvar(cliente: Cliente) {
        repository.clienteDatabase().salvar(cliente)
    }
}