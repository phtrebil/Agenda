package br.com.pedro.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.room.Room
import br.com.pedro.agenda.R
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityFormularioBinding
import br.com.pedro.agenda.model.Cliente


class FormularioActivity : AppCompatActivity() {


    private var cliente = Cliente(0, "", "", "","")
    private val binding by lazy{
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
        return when (item.itemId){
            R.id.menu_formulario_salvar ->{
                criarCliente()
                finish()
                true
            }else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun criarCliente() {
        val dadosRecebidos = intent
        val nome = binding.nomeAddCliente.text.toString()
        val endereco = binding.ederecoAddCliente.text.toString()
        val email = binding.emailAddCliente.text.toString()
        val telefone = binding.telefoneAddCliente.text.toString()
        val db = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java, "Cliente.db"
        ).allowMainThreadQueries().build()
        if(dadosRecebidos.hasExtra("cliente2")){
            db.clienteDatabase().Update(Cliente(
                cliente.id,
                nome,
                endereco,
                email,
                telefone
            ))
            startActivity(Intent(this, ListaDeClientesActivity::class.java))
        }else{
            db.clienteDatabase().insertAll(
                Cliente(
                    0,
                    nome,
                    endereco,
                    email,
                    telefone
                )
            )
        }

    }
}