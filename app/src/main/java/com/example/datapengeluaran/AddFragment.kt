package com.example.datapengeluaran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.datapengeluaran.data.User
import com.example.datapengeluaran.data.UserViewModel
import com.example.datapengeluaran.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this)
            .get(UserViewModel::class.java)

        binding.btnSimpan.setOnClickListener {
            insertDataToDatabase()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        return view
    }

    private fun insertDataToDatabase() {

        val tanggal = binding.inputTanggal.text.toString()
        val kategori = binding.inputKategori.text.toString()
        val jumlah = Integer.parseInt(binding.inputJumlah.text.toString())
        val keterangan = binding.inputKeterangan.text.toString()


        val user = User(0, tanggal, kategori, jumlah, keterangan)
        mUserViewModel.addUser(user)
    }
}