package com.example.maxima.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
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

        for (legenda in pedido.legendas) {
            when (legenda) {
                "PEDIDO_SOFREU_CORTE" ->
                    holder.imageViewLegendaCorte.visibility = View.VISIBLE
                "PEDIDO_CANCELADO_ERP" ->
                    holder.imageViewLegendaCancelado.visibility = View.VISIBLE
                "PEDIDO_FEITO_TELEMARKETING" ->
                    holder.imageViewLegendaTelemarketing.visibility = View.VISIBLE
            }
        }


        holder.textViewCodCliente.text = pedido.codigoCliente + " - " + pedido.NOMECLIENTE
        holder.textViewNumPeds.text =
            pedido.numero_ped_Rca.toString() + " / " + pedido.numero_ped_erp

        holder.textViewHoraPedido.text = getTimeNow(pedido.data)


    }


    private fun setupImage(pedido: Pedido) {
        if (pedido.tipo == "ORCAMENTO") {

        } else {
            when (pedido.status) {
                "Processado" -> {

                }
                "Em processamento" -> {

                }
                "Pendente" -> {

                }
            }
        }
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





}