package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        vaiParaDetalhes()
        configuraFab()



    }

    private fun vaiParaDetalhes() {
        adapter.quandoClicaItem = {
            val intent = Intent(this, DetalhesActivity::class.java)
                .apply {
                    putExtra("cliente", it)
                }
            startActivity(intent)
        }
    }

    private fun configuraFab() {
        binding.fabAdicionaCliente.setOnClickListener {
            startActivity(Intent(this, FormularioActivity::class.java))
        }
    }





}