package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Model.AddUserResponse
import com.example.myapplication.Model.PostData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("DEPRECATION")
class CreateAccountFragment : Fragment() {

    private lateinit var fullName: EditText
    private lateinit var emailAddress: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var countryCode: TextView
    private lateinit var mobileNumber: EditText
    private lateinit var companyName: EditText
    private lateinit var currentDesignation: EditText
    private lateinit var postalAddress: EditText
    private lateinit var city: EditText
    private lateinit var pinCode: EditText
    private lateinit var country: TextView
    private lateinit var selectIndustry: TextView
    private lateinit var selectExpertise: TextView
    private lateinit var button: Button
    private var country_id: Int = 1
    private var selectExperties_id: Int = 1
    private var selectIndustry_id: Int = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account, container, false)

        fullName = view.findViewById(R.id.Full_Name)
        emailAddress = view.findViewById(R.id.Email_Address)
        password = view.findViewById(R.id.Password)
        confirmPassword = view.findViewById(R.id.Confirm_Password)
        countryCode = view.findViewById(R.id.Number)
        mobileNumber = view.findViewById(R.id.Mobile_Number_Create)
        companyName = view.findViewById(R.id.Company_Name)
        currentDesignation = view.findViewById(R.id.Current_Desg)
        postalAddress = view.findViewById(R.id.Postal_Address)
        city = view.findViewById(R.id.City)
        pinCode = view.findViewById(R.id.Pin_Code)
        country = view.findViewById(R.id.Select_Country_Create_Account)
        selectIndustry = view.findViewById(R.id.Select_Industry)
        selectExpertise = view.findViewById(R.id.Select_Expertise)

        button = view.findViewById(R.id.Create_Account_to_Login)
        button.setOnClickListener {
            validation()
            addData()
        }

        navigateToCountry()
        setFragmentListener()

        return view
    }

    private fun setFragmentListener() {
        setFragmentResultListener("1") { requestKey, bundle ->
            val result = bundle.getString("country")
            country.text = result
        }
        setFragmentResultListener("2") { requestKey, bundle ->
            val result = bundle.getString("experties")
            selectExpertise.text = result
        }
        setFragmentResultListener("3") { requestKey, bundle ->
            val result = bundle.getString("industry")
            selectIndustry.text = result
        }
        setFragmentResultListener("4") { requestKey, bundle ->
            val result = bundle.getString("phonecode")
            countryCode.text = result
        }
        setFragmentResultListener("5") { requestKey, bundle ->
            val result = bundle.get("id")
            country_id = result as Int
        }
        setFragmentResultListener("6") { requestKey, bundle ->
            val result = bundle.get("id")
            selectExperties_id = result as Int

        }
        setFragmentResultListener("7") { requestKey, bundle ->
            val result = bundle.get("id")
            selectIndustry_id = result as Int

        }

    }

    private fun navigateToCountry() {
        countryCode.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_countryCodeFragment)
        }
        country.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_countryFragment2)
        }
        selectIndustry.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_selectIndustryFragment)
        }

        selectExpertise.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_selectExpertiseFragment)
        }
    }


    private fun validation() {
        if (fullName.text.isEmpty()) {
            Toast.makeText(activity, "Provide Full Name", Toast.LENGTH_SHORT).show()
        } else if (emailAddress.text.isEmpty()) {
            Toast.makeText(activity, "provide Email", Toast.LENGTH_SHORT).show()
        } else if (password.text.isEmpty()) {
            Toast.makeText(activity, "provide password", Toast.LENGTH_SHORT).show()
        } else if (confirmPassword.text.isEmpty()) {
            Toast.makeText(activity, "provide confirm Password", Toast.LENGTH_SHORT).show()
        } else if (mobileNumber.text.isEmpty()) {
            Toast.makeText(activity, "provide mobile Number", Toast.LENGTH_SHORT).show()
        } else if (companyName.text.isEmpty()) {
            Toast.makeText(activity, "provide company Name", Toast.LENGTH_SHORT).show()
        } else if (currentDesignation.text.isEmpty()) {
            Toast.makeText(activity, "provide current Designation", Toast.LENGTH_SHORT).show()
        } else if (postalAddress.text.isEmpty()) {
            Toast.makeText(activity, "provide postal Address", Toast.LENGTH_SHORT).show()
        } else if (pinCode.text.isEmpty()) {
            Toast.makeText(activity, "provide pinCode", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
        }


    }

    private fun addData() {
        val fullName = fullName.text.toString()
        val emailAddress = emailAddress.text.toString()
        val password = password.text.toString()
        val countryCode = countryCode.text.toString()
        val mobileNumber = mobileNumber.text.toString()
        val companyName = companyName.text.toString()
        val currentDesignation = currentDesignation.text.toString()
        val postalAddress = postalAddress.text.toString()
        val city = city.text.toString()
        val country = country_id
        val pinCode = pinCode.text.toString()
        val selectIndustry = selectExperties_id.toString()
        val selectExpertise = selectIndustry_id.toString()
        val modal = PostData(
            fullName,
            mobileNumber,
            countryCode,
            password,
            emailAddress,
            currentDesignation,
            companyName,
            postalAddress,
            city,
            pinCode,
            country,
            1,
            listOf(selectIndustry),
            listOf(selectExpertise),
            true
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-15-206-100-11.ap-south-1.compute.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(RetrofitAPI::class.java)
            .posting(modal)
        retrofit.enqueue(object : Callback<AddUserResponse> {
            override fun onResponse(
                call: Call<AddUserResponse>,
                response: Response<AddUserResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("Success", response.body().toString())
                }
            }

            override fun onFailure(
                call: Call<AddUserResponse>,
                t: Throwable
            ) {
                Log.d("error", t.message.toString())
            }
        })


    }

}



