package br.com.pedro.agenda.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pedro.agenda.R
import br.com.pedro.agenda.model.Cliente
import kotlinx.android.synthetic.main.detalhes.*


class DetalhesFragment : Fragment() {

    private var cliente = Cliente(0, "", "", "", "")
    private val controlador by lazy {
        findNavController()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val args = arguments
        if (args != null && args.containsKey("cliente")) {
            val clienteRecebido = args.getParcelable<Cliente>("cliente")
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

        val view = inflater.inflate(R.layout.detalhes, container, false)
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preencheCampo(cliente)
    }

    private fun preencheCampo(cliente: Cliente) {
        nome.text = cliente.nome
        endereco.text = cliente.endreco
        email.text = cliente.email
        telefone.text = cliente.telefone
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflate: MenuInflater = inflater
        inflate.inflate(R.menu.menu_detalhes_editar, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_detalhes_editar -> {
                vaiParaFormulario(cliente)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun vaiParaFormulario(cliente: Cliente) {
        val dados = Bundle()
        dados.putParcelable("cliente2", cliente)
        controlador.navigate(R.id.action_detalhes_to_formulario, dados)
    }

}