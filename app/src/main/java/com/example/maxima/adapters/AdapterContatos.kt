package com.example.maxima.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maxima.data.Contatos
import com.example.maxima.databinding.ItemContatoAdapterBinding

class AdapterContatos(private val contatos: List<Contatos>) :
    RecyclerView.Adapter<AdapterContatos.ViewHolder>() {

    class ViewHolder(view: ItemContatoAdapterBinding) : RecyclerView.ViewHolder(view.root) {

        val textViewNome = view.textViewNomeContato
        val textViewTelefone = view.textViewTelefone
        val textViewCelular = view.textViewCelular
        val textViewEmail = view.textViewEmail
        val textViewDataNasc = view.textViewDataNasc
        val textViewConjuge = view.textViewConjuge
        val textViewDataNascConjuge = view.textViewDataNascConjuge
        val textViewTipo = view.textViewTipo
        val textViewTime = view.textViewTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContatoAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        initView(contatos[position], holder)
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    private fun initView(contato: Contatos, holder: ViewHolder) {
        holder.textViewNome.text = contato.nome
        holder.textViewTelefone.text = contato.telefone
        holder.textViewCelular.text = contato.celular
        holder.textViewEmail.text = contato.e_mail
        holder.textViewDataNasc.text = contato.data_nascimento
        holder.textViewConjuge.text = contato.conjuge
        holder.textViewDataNascConjuge.text = contato.dataNascimentoConjuge
        holder.textViewTipo.text = contato.tipo
        holder.textViewTime.text = contato.time
    }
}