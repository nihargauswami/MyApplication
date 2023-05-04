package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Model.Data


class LoginFragment : Fragment() {

    private lateinit var countryCode: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val continueButton: Button = view.findViewById(R.id.Continue)
        val mobileNumber: EditText = view.findViewById(R.id.Mobile_Number)
        val password: EditText = view.findViewById(R.id.Password)
        countryCode = view.findViewById(R.id.Country_Code)
        navigateToCountryCode()
        setFragmentListener()

        continueButton.setOnClickListener {
            if (mobileNumber.text.isEmpty()) {
                Toast.makeText(activity, "Enter Mobile Number", Toast.LENGTH_SHORT).show()
            } else if (password.text.isEmpty()) {
                Toast.makeText(activity, "Enter a Password", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    private fun setFragmentListener() {
        setFragmentResultListener("1") { requestKey, bundle ->
            val result = bundle.getString("phonecode")
            countryCode.text = result
        }
    }

    private fun navigateToCountryCode() {
        countryCode.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_countryCodeFragment)
        }
    }


}