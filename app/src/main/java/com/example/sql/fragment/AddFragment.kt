package com.example.sql.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sql.R
import com.example.sql.data.Classe
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var itemSelecionado: Classe? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        carregarDados()

        binding.buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    private fun inputCheck(tipo: String, arma: String, bonusHp: String, bonusDano: String): Boolean{
        return !(tipo == "" || arma == "" || bonusHp == "" || bonusDano == "")
    }

    private fun inserirNoBanco(){
        val tipo = binding.editTipo.text.toString()
        val arma = binding.editArma.text.toString()
        val bonusHp = binding.editBonusHp.text.toString()
        val bonusDano = binding.editBonusDano.text.toString()

        if(inputCheck(tipo, arma, bonusHp, bonusDano)){
            val salvar: String
            if(itemSelecionado != null){
                salvar = "Personagem Atualizado"
                val classe = Classe(itemSelecionado?.id!!, tipo, arma, bonusHp.toDouble(), bonusDano.toDouble())
                mainViewModel.updateUser(classe)
            }else{
                salvar = "Personagem Criado"
                val classe = Classe(0, tipo, arma, bonusHp.toDouble(), bonusDano.toDouble())
                mainViewModel.addUser(classe)
            }
            Toast.makeText(
                context, salvar,
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listaFragment)
        }else{
            Toast.makeText(
                context, "Preencha todos os campos!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun carregarDados(){
        itemSelecionado = mainViewModel.itemSelecionado
        if(itemSelecionado != null){
            binding.editTipo.setText(itemSelecionado?.tipo)
            binding.editArma.setText(itemSelecionado?.arma)
            binding.editBonusHp.setText(itemSelecionado?.bonusHp?.toString())
            binding.editBonusDano.setText(itemSelecionado?.bonusDano?.toString())
        }
    }

}