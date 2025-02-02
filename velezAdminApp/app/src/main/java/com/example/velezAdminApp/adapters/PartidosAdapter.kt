package com.example.velezAdminApp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velezAdminApp.R
import com.example.velezAdminApp.entidades.Partido

class PartidosAdapter (
    private var partidosList: MutableList<Partido>,
    val onClick : (Int) -> Unit
) : RecyclerView.Adapter<PartidosAdapter.PartidoHolder>() {

    class PartidoHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun setRival(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_rival_item)
            txt.text = name
        }

        fun setDia(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_dia)
            txt.text = name
        }
        fun setMes(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_mes)
            txt.text = name
        }
        fun setHora(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_hora)
            txt.text = name
        }
        fun setTorneo(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_torneo)
            txt.text = name
        }
        fun setFecha(name: String) {
            val txt: TextView = view.findViewById(R.id.txt_fecha)
            txt.text = name
        }

        fun getCardLayout(): CardView {
            return view.findViewById(R.id.card_package_item)
        }

        fun getCardView () : CardView {
            return view.findViewById(R.id.card_package_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_partido, parent, false)
        return (PartidoHolder(view))
    }

    override fun onBindViewHolder(holder: PartidoHolder, position: Int) {

        holder.setName("Velez")
        holder.setRival(partidosList[position].rival)
        holder.setDia(partidosList[position].dia)
        holder.setHora(partidosList[position].hora)
        holder.setMes(partidosList[position].mes)
        holder.setFecha(partidosList[position].fecha)
        holder.setTorneo(partidosList[position].torneo)

        holder.getCardView().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return partidosList.size
    }

}