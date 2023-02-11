package br.com.pedro.agenda.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedro.agenda.databinding.ItemListaClientesBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter.ViewHolder

class ListaDeCLientesAdapter(

    private val context: Context,
    clientes: List<Cliente> = emptyList(),

    ) : RecyclerView.Adapter<ViewHolder>() {

    private val clientes = clientes.toMutableList()

    inner class ViewHolder(
        private val binding: ItemListaClientesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(cliente: Cliente) {
            binding.nomeItem.text = cliente.nome
            binding.telefoneItem.text = cliente.telefone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(ItemListaClientesBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.vincula(cliente)
    }

    override fun getItemCount(): Int = clientes.size
    }

