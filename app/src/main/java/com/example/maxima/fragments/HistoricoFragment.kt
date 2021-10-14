package com.example.maxima.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amulyakhare.textdrawable.TextDrawable
import com.example.maxima.R
import com.example.maxima.adapters.AdapterPedido
import com.example.maxima.data.Pedido
import com.example.maxima.databinding.FragmentHistoricoBinding
import com.example.maxima.viewModel.HistoricoFragmentViewModel

class HistoricoFragment : Fragment() {

    private lateinit var adapterPedido: AdapterPedido
    private lateinit var binding: FragmentHistoricoBinding
    private lateinit var historicoFragmentViewModel: HistoricoFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_historico, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.ic_menu_legendas -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setNegativeButton("Fechar") { _,_ ->

                }
                builder.setView(R.layout.alert_dialog_legendas)
               // desenharLetraCircular(R.layout.alert_dialog_legendas,)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeData()
        getPedidos()
    }

    private fun setupViewModel() {
        historicoFragmentViewModel =
            ViewModelProvider(this).get(HistoricoFragmentViewModel::class.java)
    }


    private fun observeData() {
        historicoFragmentViewModel.pedidos.observe(viewLifecycleOwner, { pedidos ->
            setupRecyclerView(pedidos)
        })
    }

    private fun getPedidos() {
        historicoFragmentViewModel.getPedidos(requireContext())
    }

    private fun setupRecyclerView(pedidos: List<Pedido>) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterPedido = AdapterPedido(pedidos)
        binding.recyclerPedidos.apply {
            layoutManager = layout
            adapter = adapterPedido
        }
    }


    private fun desenharLetraCircular(viewRaiz: View, idImvLetra: Int, idLetraDefault: Int, corPreenchimento: String, letra: String = "") {
        val cor = if (corPreenchimento == "") "#95989A" else corPreenchimento

        val drawable = TextDrawable.builder()
            .beginConfig()
            .bold()
            .endConfig()
            .buildRound(letra,
                Color.parseColor(cor))
        viewRaiz.findViewById<ImageView>(idImvLetra).setImageDrawable(drawable)
    }

}