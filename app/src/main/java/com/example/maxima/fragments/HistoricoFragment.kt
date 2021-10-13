package com.example.maxima.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        super.onCreate(savedInstanceState)

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

}