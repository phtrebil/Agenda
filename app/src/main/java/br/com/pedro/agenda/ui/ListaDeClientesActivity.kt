package br.com.pedro.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.com.pedro.agenda.R
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityListaDeClientesBinding
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter

class ListaDeClientesActivity : AppCompatActivity() {

    private val adapter = ListaDeCLientesAdapter(this)
    private val binding by lazy {
        ActivityListaDeClientesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java, "Cliente.db"
        ).allowMainThreadQueries().build()

        carregaRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()

        val db = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java, "Cliente.db"
        ).allowMainThreadQueries().build()

        adapter.atualiza(db.clienteDatabase().getAll())
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
    }
}