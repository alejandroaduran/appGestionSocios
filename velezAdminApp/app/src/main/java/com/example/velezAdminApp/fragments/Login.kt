package com.example.velezAdminApp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.velezAdminApp.R
import com.example.velezAdminApp.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var v: View
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginBtn: Button
    private lateinit var email: TextView
    private lateinit var pass: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        loginBtn = v.findViewById(R.id.login_btn)
        email = v.findViewById(R.id.editTextTextEmailAddress)
        pass = v.findViewById(R.id.editTextTextPassword)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        viewModel.user.observe( viewLifecycleOwner, Observer {
            val contextView = v
            if(it != null){
                val action = LoginDirections.actionLoginToMainActivity2()
                v.findNavController().navigate(action)
            }

        })

        loginBtn.setOnClickListener {

            val action = LoginDirections.actionLoginToMainActivity2()
            v.findNavController().navigate(action)
        }

        loginBtn.setOnClickListener {
            val contextView = v
            val email = email.text.toString()
            val pass = pass.text.toString()



            if(!email.isNullOrEmpty() && !pass.isNullOrEmpty()){
                viewModel.login(email, pass)
            }else{
                Snackbar.make(contextView, "Email Y Contraseña INCOMPLETOS, ingrese la informacion para iniciar sesion",Snackbar.LENGTH_LONG).show()
            }

        }
    }
}