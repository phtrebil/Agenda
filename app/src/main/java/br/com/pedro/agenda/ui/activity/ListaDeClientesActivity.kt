package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pedro.agenda.databinding.ActivityBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter

class ListaDeClientesActivity : AppCompatActivity() {

    private val adapter = ListaDeCLientesAdapter(this)
    private val binding by lazy {
       ActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


    private fun vaiParaDetalhesActivity(it: Cliente) {
        val intent = Intent(this, DetalhesActivity::class.java)
            .apply {
                putExtra("cliente", it)
            }
        startActivity(intent)
    }


    private fun vaiParaFormularioActivity() {
        startActivity(Intent(this, FormularioActivity::class.java))
    }


}