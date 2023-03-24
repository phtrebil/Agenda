package br.com.pedro.agenda.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.pedro.agenda.dao.ClienteDatabase
import br.com.pedro.agenda.databinding.ItemListaClientesBinding
import br.com.pedro.agenda.model.Cliente
import br.com.pedro.agenda.ui.adapter.ListaDeCLientesAdapter.ViewHolder

class ListaDeCLientesAdapter(


    private val context: Context,
    clientes: List<Cliente> = emptyList(),
    var quandoSeguraItem: (cliente: Cliente) -> Unit = {},
    var quandoClicaItem: (cliente: Cliente) -> Unit = {}

    ) : RecyclerView.Adapter<ViewHolder>() {

    private val clientes = clientes.toMutableList()

    inner class ViewHolder(
        private val binding: ItemListaClientesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var cliente: Cliente

        init {
            itemView.setOnLongClickListener(View.OnLongClickListener {
                quandoSeguraItem(cliente)
                true
            })
            
            itemView.setOnClickListener {
                if(::cliente.isInitialized){
                    quandoClicaItem(cliente)
                }
            }
        }

        fun vincula(cliente: Cliente) {
            this.cliente = cliente
            binding.nomeItem.text = cliente.nome
            binding.telefoneItem.text = cliente.telefone
        }

    }

    fun atualiza(clientes: List<Cliente>){
        this.clientes.clear()
        this.clientes.addAll(clientes)
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


