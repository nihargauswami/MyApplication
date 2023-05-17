package com.example.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplication.notification.Notification
import com.example.myapplication.notification.channelID
import com.example.myapplication.notification.messageExtra
import com.example.myapplication.notification.notificationID
import com.example.myapplication.notification.titleExtra


class LoginFragment : Fragment() {

    private lateinit var countryCode: TextView
    private lateinit var button: Button
    private lateinit var mobileNumber: EditText
    private lateinit var password: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val continueButton: Button = view.findViewById(R.id.Continue)
        mobileNumber = view.findViewById(R.id.Mobile_Number)
        password = view.findViewById(R.id.Password)
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
        createNotificationChannel()
        button = view.findViewById(R.id.Continue)
        button.setOnClickListener { scheduleNotification() }
        return view
    }

    private fun scheduleNotification() {
        val intent = Intent(context?.applicationContext, Notification::class.java)
        val title = mobileNumber.text.toString()
        val massage = password.text.toString()
        intent.putExtra(titleExtra,title)
        intent.putExtra(messageExtra,massage)
        val pendingIntent = PendingIntent.getBroadcast(
            context?.applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        pendingIntent
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif channel"
        val desc = "A Description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
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