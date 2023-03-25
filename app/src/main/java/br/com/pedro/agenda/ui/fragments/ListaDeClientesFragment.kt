package br.com.pedro.agenda.ui.fragments

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.activity.DetalhesActivity
import br.com.pedro.agenda.ui.activity.FormularioActivity
import br.com.pedro.agenda.ui.activity.ListaDeClientesActivity
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel
import br.com.pedro.agenda.ui.viewmodel.factory.ListaDeClientesFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaDeClientesFragment: Fragment() {

    private val adapter by lazy {
        context?.let {
            ListaDeCLientesAdapter(it)
        } ?: throw java.lang.IllegalArgumentException("Conteto inválido")

    private val viewModel by lazy {
        val db = ClienteDatabase.instancia(requireContext())
        val factory = ListaDeClientesFactory(db)
        val provider = ViewModelProviders.of(this, factory)
        provider[ListaDeClientesViewModel::class.java]
    }

    lifecycleScope.launch {
        viewModel.getAll().collect{
            adapter.atualiza(it)
        }
    }

    private fun carregaRecyclerView() {
        val recyclerView = binding.rvListaDeAlunos
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter.quandoSeguraItem = {

            lifecycleScope.launch {
                deletaCliente(it)
            }

        }

    }

    private suspend fun deletaCliente(cliente: Cliente) {
        withContext(Dispatchers.IO) {
            viewModel.delete(cliente)
        }

    }

    private fun configuraFab() {
        binding.fabAdicionaCliente.setOnClickListener {
            startActivity(Intent(this, FormularioActivity::class.java))
        }
    }
}