package com.example.springtak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.springtak.databinding.ActivityHomeBinding
import com.example.springtak.utils.EmployeeAdapter
import com.example.springtak.utils.room.RoomFactory
import com.example.springtak.utils.room.RoomViewModel
import com.example.springtak.utils.room.Table

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var roomViewModel: RoomViewModel


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        roomViewModel.getAll()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val viewModelFactory = RoomFactory(application)
        roomViewModel = ViewModelProvider(this,viewModelFactory)[RoomViewModel::class.java]

        setObserver()
        setListener()
        roomViewModel.getAll()
    }

    private fun setListener() {
        binding.idFab.setOnClickListener {
            Intent(this, AddEmployeeActivity::class.java).apply {
                launcher.launch(this)
            }
        }
    }

    private fun setObserver() {
        roomViewModel.allEmp.observe(this) {
            Log.d("AxE","${it.first().fname}")
            if (it.isNotEmpty()) {
                setAdapter(it)
            }
        }
    }


    private fun setAdapter(table: List<Table>) {
        EmployeeAdapter(table).apply {
            binding.idRecycler.adapter = this
        }
    }

}