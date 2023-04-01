package br.com.pedro.agenda.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.pedro.agenda.R
import br.com.pedro.agenda.databinding.ActivityBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.fragments.DetalhesFragment
import br.com.pedro.agenda.ui.fragments.FormularioFragments
import br.com.pedro.agenda.ui.fragments.ListaDeClientesFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFragment(ListaDeClientesFragment())
    }

    private fun configuraFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main_activity, fragment)
        transaction.commit()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is ListaDeClientesFragment) {
            fragment.vaiParaDetalhesActivity = {
                vaiParaDetalhesActivity(it)
            }
            fragment.vaiParaFormularioActivity = {
                vaiParaFormularioFragment()
            }
        }

        if (fragment is DetalhesFragment) {
            fragment.vaiParaFormulario = {
                vaiParaFormularioPreenchido(it)
            }
        }

        if (fragment is FormularioFragments) {
            fragment.vaiParaListaDeClientesFragment = {
                vaiParaListaDeClientesFragment()
            }
        }

    }

    private fun vaiParaDetalhesActivity(cliente: Cliente) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = DetalhesFragment()
        val dados = Bundle()
        dados.putParcelable("cliente", cliente)
        fragment.arguments = dados
        transaction.replace(R.id.container_main_activity, fragment)
        transaction.commit()
    }


    private fun vaiParaFormularioFragment() {
        configuraFragment(FormularioFragments())
    }

    private fun vaiParaListaDeClientesFragment() {
        configuraFragment(ListaDeClientesFragment())
    }

    private fun vaiParaFormularioPreenchido(cliente: Cliente) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FormularioFragments()
        val dados = Bundle()
        dados.putParcelable("cliente2", cliente)
        fragment.arguments = dados
        transaction.replace(R.id.container_main_activity, fragment)
        transaction.commit()
    }


}