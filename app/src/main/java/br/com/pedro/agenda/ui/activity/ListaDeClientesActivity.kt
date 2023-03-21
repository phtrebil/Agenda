package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityListaDeClientesBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import br.com.pedro.agenda.ui.viewmodel.ListaDeClientesViewModel
import br.com.pedro.agenda.ui.viewmodel.factory.ListaDeClientesFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaDeClientesActivity : AppCompatActivity() {

    private var listaDeClientes: List<Cliente> = emptyList()
    private val scope = MainScope()
    private val adapter = ListaDeCLientesAdapter(this)
    private val binding by lazy {
        ActivityListaDeClientesBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        val db = ClienteDatabase.instancia(this)
        val factory = ListaDeClientesFactory(db)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(ListaDeClientesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        carregaRecyclerView()
        configuraFab()


    }

    override fun onResume() {
        super.onResume()
        scope.launch() {
            val clientes = geraLista()
            adapter.atualiza(clientes)
        }

    }

    private suspend fun geraLista(): List<Cliente> {
        withContext(IO) {
            listaDeClientes = viewModel.getAll()

        }
        return listaDeClientes
    }


    private fun configuraFab() {
        binding.fabAdicionaCliente.setOnClickListener {
            startActivity(Intent(this, FormularioActivity::class.java))
        }
    }

    private fun carregaRecyclerView() {
        val recyclerView = binding.rvListaDeAlunos
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter.quandoSeguraItem = {

            scope.launch() {
                deletaCliente(it)
            }
            adapter.atualiza(listaDeClientes)
        }
        adapter.quandoClicaItem = {
            val intent = Intent(this, DetalhesActivity::class.java)
                .apply {
                    putExtra("cliente", it)
                }
            startActivity(intent)
        }
    }

    private suspend fun deletaCliente(it: Cliente) {
        withContext(IO) {
            viewModel.delete(it)
            listaDeClientes = viewModel.getAll()
            adapter.atualiza(listaDeClientes)
        }

    }

}