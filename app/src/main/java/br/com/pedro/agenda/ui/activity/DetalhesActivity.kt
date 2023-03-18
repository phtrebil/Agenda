package br.com.pedro.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.pedro.agenda.R
import br.com.pedro.agenda.databinding.ActivityDetalhesBinding
import br.com.pedro.agenda.model.Cliente

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    private var cliente = Cliente(0, "", "", "","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra("cliente")) {
            intent.getParcelableExtra<Cliente>("cliente")?.let { clienteDetalhes ->
                preencheCampo(clienteDetalhes)
                cliente = clienteDetalhes
            }
        }
    }

    private fun preencheCampo(cliente: Cliente) {
        binding.nome.text = cliente.nome
        binding.endereco.text = cliente.endreco
        binding.telefone.text = cliente.telefone
        binding.email.text = cliente.email
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_detalhes_editar, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_detalhes_editar -> {
                vaiParaFormulario()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun vaiParaFormulario() {
        startActivity(Intent(this, FormularioActivity::class.java)
            .apply {
                putExtra("cliente2", cliente)
            })
    }
}