package com.example.sql.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.data.OnItemSelectedListener
import com.example.sql.data.Classe
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.CustomRowBinding

class ListAdapter (
    private val onItemSelectedListener: OnItemSelectedListener,
    private val mainViewModel: MainViewModel,
    private val context: Context
        ): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var classeList = emptyList<Classe>()

    class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = classeList[position]

        holder.binding.textTipo.text = user.tipo
        holder.binding.textArma.text = user.arma
        holder.binding.textBonusHp.text = user.bonusHp.toString()
        holder.binding.textBonusDano.text = user.bonusDano.toString()

        holder.binding.buttonEdit.setOnClickListener {
            onItemSelectedListener.onItemSelectedListener(user)
        }

        holder.binding.buttonDelete.setOnClickListener {
            showAlertDialog(user)
        }

    }

    override fun getItemCount(): Int {
        return classeList.size
    }

    fun setData(classe: List<Classe>){
        this.classeList = classe
        notifyDataSetChanged()
    }

    private fun showAlertDialog(classe: Classe){
        AlertDialog.Builder(context)
            .setTitle("Deletar Personagem")
            .setMessage("Você realmente deseja deletar este personagem?")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteUser(classe)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }

}