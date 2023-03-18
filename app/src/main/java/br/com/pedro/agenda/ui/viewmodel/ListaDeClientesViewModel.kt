package br.com.pedro.agenda.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.pedro.agenda.dao.ClienteDao
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente

class ListaDeClientesViewModel(
    val context: Context
): ViewModel() {

    private val repository by lazy {
        ClienteDatabase.instancia(context).clienteDatabase()
    }

        fun getAll(): LiveData<List<Cliente>> {
            return repository.getAll()
        }
    }
