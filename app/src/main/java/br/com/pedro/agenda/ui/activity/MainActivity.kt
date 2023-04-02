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
        if(savedInstanceState == null){
            abreListaNoticias()
        }
    }

    private fun abreListaNoticias() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main_activity, ListaDeClientesFragment())
        transaction.commit()
    }

    private fun configuraFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main_activity, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)

        when(fragment){
            is ListaDeClientesFragment -> {
                configuraListaDeClientesFragment(fragment)
            }

            is DetalhesFragment -> {
                configuraDetalhesFragment(fragment)
            }

            is FormularioFragments -> {
                configuraFormularioFragment(fragment)
            }
        }

    }

    private fun configuraFormularioFragment(fragment: FormularioFragments) {
        fragment.vaiParaListaDeClientesFragment = this::vaiParaListaDeClientesFragment
    }

    private fun configuraDetalhesFragment(fragment: DetalhesFragment) {
        fragment.vaiParaFormulario = this::vaiParaFormularioPreenchido
    }

    private fun configuraListaDeClientesFragment(fragment: ListaDeClientesFragment) {
        fragment.vaiParaFormularioActivity = this::vaiParaFormularioFragment
        fragment.vaiParaDetalhesActivity = this::vaiParaDetalhesActivity
    }

    private fun vaiParaDetalhesActivity(cliente: Cliente) {
        val fragment = DetalhesFragment()
        val dados = Bundle()
        dados.putParcelable("cliente", cliente)
        fragment.arguments = dados
        configuraFragment(fragment)
    }
    private fun vaiParaFormularioFragment() {
        configuraFragment(FormularioFragments())
    }
    private fun vaiParaListaDeClientesFragment() {
        configuraFragment(ListaDeClientesFragment())
    }
    private fun vaiParaFormularioPreenchido(cliente: Cliente) {
        val fragment = FormularioFragments()
        val dados = Bundle()
        dados.putParcelable("cliente2", cliente)
        fragment.arguments = dados
        configuraFragment(fragment)
    }


}