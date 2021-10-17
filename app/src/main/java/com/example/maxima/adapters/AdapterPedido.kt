package com.example.maxima.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.example.maxima.Entities.EntityCritica
import com.example.maxima.Entities.EntityLegenda
import com.example.maxima.R
import com.example.maxima.data.Pedido
import com.example.maxima.databinding.ItemPedidoAdapterBinding
import java.text.SimpleDateFormat
import java.util.*


class AdapterPedido(private val pedidos: List<Pedido>) :
    RecyclerView.Adapter<AdapterPedido.ViewHolder>() {

    class ViewHolder(view: ItemPedidoAdapterBinding) : RecyclerView.ViewHolder(view.root) {

        val imageViewLegendaCorte = view.imageViewLegendaCorte
        val imageViewLegendaFalta = view.imageViewLegendaFalta
        val imageViewLegendaCancelado = view.imageViewLegendaCancelado
        val imageViewLegendaDevolucao = view.imageViewLegendaDevolucao
        val imageViewLegendaTelemarketing = view.imageViewLegendaTelemarketing

        val imageViewCriticaAguardando = view.imageViewCriticaAguardando
        val imageViewCriticaSucesso = view.imageViewCriticaSucesso
        val imageViewCriticaParcial = view.imageViewCriticaParcial
        val imageViewCriticaTotal = view.imageViewCriticaTotal
        val textViewCritica = view.textCritica

        val textViewStatus = view.textViewStatus

        val imageViewStatus = view.imageViewStatus
        val textViewNumPeds = view.textViewNumpeds
        val textViewCodCliente = view.textViewCodCliente
        val textViewHoraPedido = view.textViewHoraPedido

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPedidoAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        initView(pedidos[position], holder)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    private fun initView(pedido: Pedido, holder: ViewHolder) {

        holder.imageViewLegendaCorte.visibility = View.GONE
        holder.imageViewLegendaFalta.visibility = View.GONE
        holder.imageViewLegendaCancelado.visibility = View.GONE
        holder.imageViewLegendaDevolucao.visibility = View.GONE
        holder.imageViewLegendaTelemarketing.visibility = View.GONE

        holder.imageViewCriticaAguardando.visibility = View.GONE
        holder.imageViewCriticaSucesso.visibility = View.GONE
        holder.imageViewCriticaParcial.visibility = View.GONE
        holder.imageViewCriticaTotal.visibility = View.GONE
        if (pedido.numero_ped_Rca == 542188) {
            pedido.status
        }
        if (pedido.critica != null) {
            when (pedido.critica) {
                EntityCritica.FALHA_PARCIAL -> {
                    holder.imageViewCriticaParcial.visibility = View.VISIBLE
                    desenharLetraCircular(
                        holder.itemView,
                        holder.imageViewStatus.id,
                        "#FF9900",
                        "!"
                    )
                }
                EntityCritica.SUCESSO ->
                    holder.imageViewCriticaSucesso.visibility = View.VISIBLE
                else ->
                    holder.textViewCritica.visibility = View.GONE
            }
        }

        if (pedido.status?.compareTo("Em processamento", true) == 0) {
            holder.imageViewStatus.setImageResource(R.drawable.ic_maxima_em_processamento)
            holder.imageViewStatus.setBackgroundResource(R.drawable.circle_processamento)
        } else
            if (pedido.status?.compareTo("Pendente", true) == 0) {
                desenharLetraCircular(
                    holder.itemView,
                    holder.imageViewStatus.id,
                    "#606060",
                    "P"
                )
            } else {
                if (pedido.tipo == "Orcamento") {
                    desenharLetraCircular(
                        holder.itemView,
                        holder.imageViewStatus.id,
                        "#2D3E4E",
                        "O"
                    )
                } else {
                    for (legenda in pedido.legendas) {
                        when (legenda) {
                            EntityLegenda.PEDIDO_SOFREU_CORTE -> {
                                holder.imageViewLegendaCorte.visibility = View.VISIBLE
                                desenharLetraCircular(
                                    holder.itemView,
                                    holder.imageViewStatus.id,
                                    "#606060",
                                    "P"
                                )
                            }
                            EntityLegenda.PEDIDO_CANCELADO_ERP -> {
                                holder.imageViewLegendaCancelado.visibility = View.VISIBLE
                                desenharLetraCircular(
                                    holder.itemView,
                                    holder.imageViewStatus.id,
                                    "#E40613",
                                    "C"
                                )
                            }
                            EntityLegenda.PEDIDO_FEITO_TELEMARKETING ->
                                holder.imageViewLegendaTelemarketing.visibility = View.VISIBLE
                            else -> {
                                desenharLetraCircular(
                                    holder.itemView,
                                    holder.imageViewStatus.id,
                                    "#186096",
                                    "L"
                                )
                            }
                        }
                    }
                }
            }
        holder.textViewCodCliente.text = pedido.codigoCliente + " - " + pedido.NOMECLIENTE
        holder.textViewNumPeds.text =
            pedido.numero_ped_Rca.toString() + " / " + pedido.numero_ped_erp

        holder.textViewHoraPedido.text = getTimeNow(pedido.data)
        holder.textViewStatus.text = pedido.status


    }


    private fun getTimeNow(hora: String): String {

        val dataJson = hora.replace("T".toRegex(), " ").replace("Z".toRegex(), "")
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dataFormatada = Date(format.parse(dataJson).time)

        val date = Calendar.getInstance().time
        val dateTimeFormatNow = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return if (dateTimeFormatNow.format(date) == dateTimeFormatNow.format((dataFormatada))) {
            val dateTimeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
            dateTimeFormat.format(dataFormatada)
        } else {
            val dateTimeFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
            dateTimeFormat.format(dataFormatada)
        }
    }


    private fun desenharLetraCircular(
        viewRaiz: View,
        idImvLetra: Int,
        corPreenchimento: String,
        letra: String = ""
    ) {
        val drawable = TextDrawable.builder()
            .beginConfig()
            .bold()
            .endConfig()
            .buildRound(
                letra,
                Color.parseColor(corPreenchimento)
            )
        viewRaiz.findViewById<ImageView>(idImvLetra).setImageDrawable(drawable)
    }


}