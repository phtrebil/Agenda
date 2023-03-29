package br.com.pedro.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pedro.agenda.R
import br.com.pedro.agenda.databinding.ActivityBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.fragments.DetalhesFragment
import br.com.pedro.agenda.ui.fragments.ListaDeClientesFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
       ActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container_main_activity, ListaDeClientesFragment())
        transaction.commit()
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
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = DetalhesFragment()
        val dados = Bundle()
        dados.putParcelable("cliente", it)
        fragment.arguments = dados
        transaction.add(R.id.container_main_activity, fragment)
        transaction.commit()
    }


    private fun vaiParaFormularioActivity() {
        startActivity(Intent(this, FormularioActivity::class.java))
    }


}