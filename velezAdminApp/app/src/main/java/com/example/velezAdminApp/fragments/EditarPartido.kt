package com.example.velezAdminApp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.velezAdminApp.R
import com.example.velezAdminApp.entidades.Partido
import com.example.velezAdminApp.viewmodels.ABMViewModel

class EditarPartido : Fragment() {

    companion object {
        fun newInstance() = EditarPartido()
    }

    private lateinit var v: View
    private lateinit var title: TextView
    private lateinit var viewModel: ABMViewModel
    private lateinit var editarPartidoBtn: Button
    private var partido: Partido? = null
    private lateinit var inputRival: TextView
    private lateinit var inputSurAlta: TextView
    private lateinit var inputSurBaja: TextView
    private lateinit var inputNorte: TextView
    private lateinit var inputEste: TextView
    private lateinit var inputOeste: TextView
    private lateinit var inputVisitante: TextView
    private lateinit var inputHora: TextView
    private lateinit var inputDia: TextView
    private lateinit var inputMes: TextView
    private lateinit var inputFechaTorneo: TextView
    private lateinit var inputTorneo: TextView
    private lateinit var estaDisp: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_editar_partido, container, false)
        partido = EditarPartidoArgs.fromBundle(requireArguments()).partido
        title = v.findViewById(R.id.editarPartidoTitle)
        editarPartidoBtn = v.findViewById(R.id.editarPartidoBtn)
        inputRival = v.findViewById(R.id.inputRival)
        inputSurAlta = v.findViewById(R.id.inputSurAlta)
        inputSurBaja = v.findViewById(R.id.inputSurBaja)
        inputNorte = v.findViewById(R.id.inputNorte)
        inputEste = v.findViewById(R.id.inputEste)
        inputOeste = v.findViewById(R.id.inputOeste)
        inputVisitante = v.findViewById(R.id.inputVisitante)
        inputHora = v.findViewById(R.id.inputHora)
        inputDia = v.findViewById(R.id.inputDia)
        inputMes = v.findViewById(R.id.inputMes)
        inputFechaTorneo = v.findViewById(R.id.inputFechaTorneo)
        inputTorneo = v.findViewById(R.id.inputTorneo)
        estaDisp = v.findViewById(R.id.checkBox2)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ABMViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        if(partido == null){
            title.text = "Agregar Partido"
            editarPartidoBtn.text = "Agregar"

            editarPartidoBtn.setOnClickListener {
                val nuevoPartido = Partido(
                    inputDia.text.toString(),
                    inputFechaTorneo.text.toString(),
                    "",
                    "",
                    inputHora.text.toString(),
                    0,
                    inputMes.text.toString(),
                    inputRival.text.toString(),
                    inputEste.text.toString().toInt(),
                    inputNorte.text.toString().toInt(),
                    inputOeste.text.toString().toInt(),
                    inputSurAlta.text.toString().toInt(),
                    inputSurBaja.text.toString().toInt(),
                    inputVisitante.text.toString().toInt(),
                    inputTorneo.text.toString(),
                    estaDisp.isChecked
                )
                viewModel.agregar(nuevoPartido)
                val action = EditarPartidoDirections.actionEditarPartidoToHome()
                findNavController().navigate(action)
            }
        }
        else {
            title.text = "Editar Partido"
            editarPartidoBtn.text = "Editar"
            inputRival.text = partido!!.rival
            inputSurAlta.text = partido!!.sectorSurAlta.toString()
            inputSurBaja.text = partido!!.sectorSurBaja.toString()
            inputNorte.text = partido!!.sectorNorte.toString()
            inputEste.text = partido!!.sectorEste.toString()
            inputOeste.text = partido!!.sectorOeste.toString()
            inputVisitante.text = partido!!.sectorVisitante.toString()
            inputHora.text = partido!!.hora
            inputMes.text = partido!!.mes
            inputDia.text = partido!!.dia
            inputFechaTorneo.text = partido!!.fecha
            inputTorneo.text = partido!!.torneo
            estaDisp.isChecked = partido!!.estaDisp

            editarPartidoBtn.setOnClickListener {
//                val updatePartido = hashMapOf(
//                    "rival" to inputRival.text.toString(),
//                    "dia" to inputDia.text.toString(),
//                    "hora" to inputHora.text.toString(),
//                    "mes" to inputMes.text.toString(),
//                    "fecha" to inputFechaTorneo.text.toString(),
//                    "sectorEste" to inputEste.text.toString().toInt(),
//                    "sectorNorte" to inputNorte.text.toString().toInt(),
//                    "sectorOeste" to inputOeste.text.toString().toInt(),
//                    "sectorSurAlta" to inputSurAlta.text.toString().toInt(),
//                    "sectorSurBaja" to inputSurBaja.text.toString().toInt(),
//                    "sectorvisitante" to inputVisitante.text.toString().toInt(),
//                    "torneo" to inputTorneo.text.toString()
//                )
                val updatePartido = Partido(
                    inputDia.text.toString(),
                    inputFechaTorneo.text.toString(),
                    "",
                    "",
                    inputHora.text.toString(),
                    partido!!.id,
                    inputMes.text.toString(),
                    inputRival.text.toString(),
                    inputEste.text.toString().toInt(),
                    inputNorte.text.toString().toInt(),
                    inputOeste.text.toString().toInt(),
                    inputSurAlta.text.toString().toInt(),
                    inputSurBaja.text.toString().toInt(),
                    inputVisitante.text.toString().toInt(),
                    inputTorneo.text.toString(),
                    estaDisp.isChecked
                )
                viewModel.editar(partido!!, updatePartido)

                val action = EditarPartidoDirections.actionEditarPartidoToHome()
                findNavController().navigate(action)
            }
        }

    }
}