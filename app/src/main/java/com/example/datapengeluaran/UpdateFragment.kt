package com.example.datapengeluaran

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.datapengeluaran.data.User
import com.example.datapengeluaran.data.UserViewModel
import com.example.datapengeluaran.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.etTanggal.setText(args.currentUser.tanggal)
        binding.etKategori.setText(args.currentUser.kategori)
        binding.etJumlah.setText(args.currentUser.jumlah.toString())
        binding.etKeterangan.setText(args.currentUser.keterangan)


        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        //button update
        binding.btnUpdate.setOnClickListener {
            updateDatatoDatabase(args.currentUser.id)
        }

        binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Anda yakin data ini dihapus ?").setCancelable(false)
                .setPositiveButton("ya") { dialog, id ->
                    deleteDatatoDatabase(args.currentUser.id)
                }
                .setNegativeButton("Tidak") { dialog, id ->
                }
            val alert = builder.create()
            alert.show()
        }

        return view
        }
    private fun updateDatatoDatabase(id: Int) {
        //masukan variabel editText dengan nama text
        val tanggal = binding.etTanggal.text.toString()
        val kategori = binding.etKategori.text.toString()
        val jumlah = Integer.parseInt(binding.etJumlah.text.toString())
        val keterangan = binding.etKeterangan.text.toString()

        val newUser = User(args.currentUser.id, tanggal, kategori, jumlah, keterangan)

        mUserViewModel.updateUser(newUser)
        findNavController()
            .navigate(R.id.action_updateFragment_to_listFragment)
    }

    fun deleteDatatoDatabase(id: Int) {
        val user = User(args.currentUser.id, args.currentUser.tanggal, args.currentUser.kategori, args.currentUser.jumlah, args.currentUser.keterangan)
        mUserViewModel.deleteUser(user)
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

}