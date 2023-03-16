package br.com.pedro.agenda.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityListaDeClientesBinding
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaDeClientesActivity : AppCompatActivity() {

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
        scope.launch {
            val clientes = withContext(Dispatchers.IO){
                repository.getAll()
            }
            adapter.atualiza(clientes)
        }

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
            scope.launch {
                val clientes = withContext(Dispatchers.IO){
                    repository.delete(it)
                }
            }

        }
        adapter.quandoClicaItem = {
            val intent = Intent(this, DetalhesActivity::class.java)
                .apply {
                    putExtra("cliente", it)
                }
            startActivity(intent)
        }
    }
}