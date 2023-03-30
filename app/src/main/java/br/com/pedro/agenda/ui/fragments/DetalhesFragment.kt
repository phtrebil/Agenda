package br.com.pedro.agenda.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import br.com.pedro.agenda.R
import br.com.pedro.agenda.model.Cliente
import kotlinx.android.synthetic.main.fragment_detalhes.*

class DetalhesFragment: Fragment() {

    var vaiParaFormulario: (cliente:Cliente) -> Unit ={}
    private var cliente = Cliente(0, "", "", "","")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalhes, container, false)
        val args = arguments
        if (args != null && args.containsKey("cliente")) {
            val clienteRecebido = args.getParcelable<Cliente>("cliente")
            clienteRecebido?.let {
                preencheCampo(it)
                cliente = clienteRecebido
            }

        }

        return view
    }

    private fun preencheCampo(cliente: Cliente) {

            nome.text = cliente.nome
            endereco.text = cliente.endreco
            telefone.text = cliente.telefone
            email.text = cliente.email

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val inflate: MenuInflater = inflater
        inflate.inflate(R.menu.menu_detalhes_editar, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_detalhes_editar -> {
                vaiParaFormulario(cliente)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}