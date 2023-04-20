package br.com.pedro.agenda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.agenda.R
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel
import kotlinx.android.synthetic.main.fragments_lista_de_clientes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListaDeClientesFragment : Fragment() {

    private val adapter by lazy {
        context?.let {
            ListaDeCLientesAdapter(it)
        } ?: throw java.lang.IllegalArgumentException("Conteto inválido")
    }

    private val controlador by lazy {
        findNavController()
    }

    private val viewModel: ListaDeClientesViewModel by viewModel()
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
            vaiParaFormularioActivity()
        }
    }

    private fun vaiParaFormularioActivity() {
        controlador.navigate(R.id.action_listaDeClientes_to_formulario)
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
           val direcao = ListaDeClientesFragmentDirections
               .actionListaDeClientesToDetalhes(it)
            controlador.navigate(direcao)
        }

    }

    private suspend fun deletaCliente(cliente: Cliente) {
        withContext(Dispatchers.IO) {
            viewModel.delete(cliente)
        }

    }


}



