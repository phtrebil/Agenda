package br.com.pedro.agenda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.agenda.R
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel
import br.com.pedro.agenda.ui.viewmodel.factory.ListaDeClientesFactory
import kotlinx.android.synthetic.main.fragments_lista_de_clientes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListaDeClientesFragment : Fragment() {

    var vaiParaDetalhesActivity: (cliente: Cliente) -> Unit = {}
    var vaiParaFormularioActivity: () -> Unit = {}

    private val adapter by lazy {
        context?.let {
            ListaDeCLientesAdapter(it)
        } ?: throw java.lang.IllegalArgumentException("Conteto inválido")
    }

    private val viewModel by lazy {
        val db = ClienteDatabase.instancia(requireContext())
        val factory = ListaDeClientesFactory(db)
        val provider = ViewModelProviders.of(this, factory)
        provider[ListaDeClientesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.getAll().collect {
                adapter.atualiza(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments_lista_de_clientes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregaRecyclerView()
        configuraFab()
    }

    private fun configuraFab() {
        fab_adiciona_cliente.setOnClickListener {
            vaiParaFormularioActivity
        }
    }


    private fun carregaRecyclerView() {
        val recyclerView = rv_lista_de_alunos
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        adapter.quandoSeguraItem = {

            lifecycleScope.launch {
                deletaCliente(it)
            }

        }

        adapter.quandoClicaItem = {
            vaiParaDetalhesActivity
        }

    }

    private suspend fun deletaCliente(cliente: Cliente) {
        withContext(Dispatchers.IO) {
            viewModel.delete(cliente)
        }

    }


}

