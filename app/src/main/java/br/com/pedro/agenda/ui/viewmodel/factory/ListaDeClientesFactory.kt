package br.com.pedro.agenda.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel

class ListaDeClientesFactory(
    private val repository: ClienteDatabase
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListaDeClientesViewModel(repository) as T
    }
}