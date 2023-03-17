package br.com.pedro.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.pedro.agenda.R
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityFormularioBinding
import br.com.pedro.agenda.model.Cliente
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FormularioActivity : AppCompatActivity() {


    private val scope = MainScope()
    private var cliente = Cliente(0, "", "", "", "")
    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }
    private val repository by lazy {
        ClienteDatabase.instancia(this).clienteDatabase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTitle("Salvar Cliente")
        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra("cliente2")) {
            intent.getParcelableExtra<Cliente>("cliente2")?.let { clienteDetalhes ->
                setTitle("Editar Cliente")
                preencheCampo(clienteDetalhes)
                cliente = clienteDetalhes
            }
        }

    }

    private fun preencheCampo(cliente: Cliente) {
        binding.nomeAddCliente.text = cliente.nome.toEditable()
        binding.ederecoAddCliente.text = cliente.endreco.toEditable()
        binding.emailAddCliente.text = cliente.email.toEditable()
        binding.telefoneAddCliente.text = cliente.telefone.toEditable()
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_formulario_salvar, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_formulario_salvar -> {
                criarCliente()
                startActivity(Intent(baseContext, ListaDeClientesActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun criarCliente() {
        val nome = binding.nomeAddCliente.text.toString()
        val endereco = binding.ederecoAddCliente.text.toString()
        val email = binding.emailAddCliente.text.toString()
        var telefone = binding.telefoneAddCliente.text.toString()
        scope.launch() {
            salvaCliente(nome, endereco, email, telefone)

        }

    }

    private suspend fun salvaCliente(
        nome: String,
        endereco: String,
        email: String,
        telefone: String
    ) {
        withContext(IO) {
            repository.insertAll(
                Cliente(
                    cliente.id,
                    nome,
                    endereco,
                    email,
                    telefone
                )
            )
        }
    }

}