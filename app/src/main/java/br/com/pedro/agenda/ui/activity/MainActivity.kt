package br.com.pedro.agenda.ui.activity

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pedro.agenda.databinding.ActivityBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.fragments.ListaDeClientesFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
       ActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is ListaDeClientesFragment){
            fragment.vaiParaDetalhesActivity = {
                vaiParaDetalhesActivity(it)
            }
            fragment.vaiParaFormularioActivity = {
                vaiParaFormularioActivity()
            }
        }
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