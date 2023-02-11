package br.com.pedro.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.room.Room
import br.com.pedro.agenda.R
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ActivityFormularioBinding
import br.com.pedro.agenda.model.Cliente


class FormularioActivity : AppCompatActivity() {


    private val binding by lazy{
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

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
        val db = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java, "Cliente.db"
        ).allowMainThreadQueries().build()
        db.clienteDatabase().insertAll(
            Cliente(
                0,
                binding.nomeAddCliente.text.toString(),
                binding.ederecoAddCliente.text.toString(),
                binding.emailAddCliente.text.toString(),
                binding.telefoneAddCliente.text.toString()

            )
        )
    }
}