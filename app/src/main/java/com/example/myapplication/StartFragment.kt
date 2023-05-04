package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_start, container, false)

        val loginButton : Button = view.findViewById(R.id.Login_Button)
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
        val createAccountButton : Button = view.findViewById(R.id.Create_Account_Button)
        createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_createAccountFragment)
        }



        return view
    }


}