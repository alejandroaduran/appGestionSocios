package com.example.velezAdminApp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.velezAdminApp.R
import com.example.velezAdminApp.adapters.PartidosAdapter
import com.example.velezAdminApp.entidades.Partido
import com.example.velezAdminApp.viewmodels.HomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var v: View
    private lateinit var viewModel: HomeViewModel
    lateinit var recPartidos : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var partidosAdapter: PartidosAdapter
    var partidos : MutableList<Partido> = ArrayList<Partido>()
    private lateinit var progessBar : ProgressBar
    private lateinit var logOutBtn: FloatingActionButton
    private lateinit var goToAgregarPartidoBtn: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        recPartidos = v.findViewById(R.id.rec_partidos)
        progessBar = v.findViewById((R.id.progressBar))
        logOutBtn = v.findViewById(R.id.logOut)
        goToAgregarPartidoBtn = v.findViewById(R.id.goToAgregarPartidoBtn)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        viewModel.getPartidos()

        recPartidos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recPartidos.layoutManager = linearLayoutManager

        viewModel.partidos.observe(viewLifecycleOwner, Observer { result ->
            partidos = result.toMutableList()
            progessBar.visibility = View.GONE
            partidosAdapter = PartidosAdapter(partidos) { pos ->
                val action = HomeDirections.actionHomeToEditarPartido(partidos[pos])
                v.findNavController().navigate(action)
            }

            recPartidos.adapter = partidosAdapter
        })


        goToAgregarPartidoBtn.setOnClickListener{
            val action = HomeDirections.actionHomeToEditarPartido(null)
            v.findNavController().navigate(action)
        }

        logOutBtn.setOnClickListener {
            Firebase.auth.signOut()
            val action = HomeDirections.actionHomeToLoginActivity()
            v.findNavController().navigate(action)
        }

    }
}