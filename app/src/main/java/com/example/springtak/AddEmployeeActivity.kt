package com.example.springtak

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.springtak.databinding.ActivityAddEmployeeBinding
import com.example.springtak.utils.room.RoomFactory
import com.example.springtak.utils.room.RoomViewModel
import com.example.springtak.utils.room.Table

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var roomViewModel: RoomViewModel
    private lateinit var binding: ActivityAddEmployeeBinding

    private lateinit var firstName: String
    private lateinit var lastName: String
    private var age: Int = 0
    private lateinit var address: String
    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_employee)

        val viewModelFactory = RoomFactory(application)
        roomViewModel = ViewModelProvider(this,viewModelFactory)[RoomViewModel::class.java]

        setOnClick()
    }

    private fun setOnClick() {
        binding.idButton.setOnClickListener {
            firstName = binding.idEditFirstName.text.toString()
            if (firstName.isEmpty()) {
                showToast("Please enter first name")
                return@setOnClickListener
            }
            lastName = binding.idEditLastName.text.toString()
            if (lastName.isEmpty()) {
                showToast("Please enter last name")
                return@setOnClickListener
            }

            val a = binding.idEditAge.text.toString()
            if (a.isEmpty()) {
                showToast("Please enter age")
                return@setOnClickListener
            }
            age = a.toInt()

            address = binding.idEditAddress.text.toString()
            if (address.isEmpty()) {
                showToast("Please enter address")
                return@setOnClickListener
            }
            city = binding.idEditCity.text.toString()
            if (city.isEmpty()) {
                showToast("Please enter city")
                return@setOnClickListener
            }

            val table = Table(0,firstName, lastName, age,address,city)
            roomViewModel.insert(table)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}