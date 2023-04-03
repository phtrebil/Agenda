package br.com.pedro.agenda.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.pedro.agenda.R
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.viewmodel.FormularioViewModel
import kotlinx.android.synthetic.main.fragments_formulario.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioFragments : Fragment() {

    private var cliente = Cliente(0, "", "", "", "")
    var vaiParaListaDeClientesFragment: () -> Unit = {}

    private val viewModel: FormularioViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val args = arguments
        if (args != null && args.containsKey("cliente2")) {
            val clienteRecebido = args.getParcelable<Cliente>("cliente2")
            clienteRecebido?.let {
                cliente = it

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragments_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preencheCampo(cliente)
    }


    private fun preencheCampo(cliente: Cliente) {
        nome_add_cliente.text = cliente.nome.toEditable()
        edereco_add_cliente.text = cliente.endreco.toEditable()
        email_add_cliente.text = cliente.email.toEditable()
        telefone_add_cliente.text = cliente.telefone.toEditable()
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflate: MenuInflater = inflater
        inflate.inflate(R.menu.menu_formulario_salvar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_formulario_salvar -> {
                criarCliente()
                vaiParaListaDeClientesFragment()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun criarCliente() {
        val nome = nome_add_cliente.text.toString()
        val endereco = edereco_add_cliente.text.toString()
        val email = email_add_cliente.text.toString()
        var telefone = telefone_add_cliente.text.toString()
        lifecycleScope.launch {
            salvaCliente(nome, endereco, email, telefone)

        }

    }

    private suspend fun salvaCliente(
        nome: String,
        endereco: String,
        email: String,
        telefone: String
    ) {
        withContext(Dispatchers.IO) {
            viewModel.salvar(
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