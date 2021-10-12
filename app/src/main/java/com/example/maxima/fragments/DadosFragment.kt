package com.example.maxima.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.maxima.R
import com.example.maxima.data.Cliente
import com.example.maxima.databinding.FragmentDadosBinding
import com.example.maxima.viewModel.FragmentDadosViewModel

class DadosFragment : Fragment() {

    private lateinit var binding: FragmentDadosBinding
    private lateinit var fragmentDadosViewModel: FragmentDadosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDadosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        observeData()
        getCliente()
    }


    private fun setupViewModel() {
        fragmentDadosViewModel = ViewModelProvider(this).get(FragmentDadosViewModel::class.java)
    }

    private fun observeData() {
        fragmentDadosViewModel.cliente.observe(viewLifecycleOwner, { clientes ->
            initView(clientes[0])
        })

    }

    private fun getCliente() {
        fragmentDadosViewModel.getCliente(requireContext())
    }

    private fun initView(cliente: Cliente) {
        binding.apply {
            textViewNameCliente.text = cliente.codigo + "-" + cliente.razao_social
            textViewFantasia.text = cliente.nomeFantasia
            textViewCpf.visibility = View.GONE
            textCpf.visibility = View.GONE
            textViewCnpj.text = cliente.cnpj
            textViewRamoAtividade.text = cliente.ramo_atividade
            textViewEnderecos.text = cliente.endereco
        }
    }

}