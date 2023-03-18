package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityListaDeClientesBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
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
    private val repository by lazy {
        ClienteDatabase.instancia(this).clienteDatabase()
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
        val clientes = withContext(IO) {
            repository.getAll()
        }
        return clientes
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
            repository.delete(it)
            listaDeClientes = repository.getAll()
        }
    }

}