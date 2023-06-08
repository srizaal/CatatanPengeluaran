package com.example.datapengeluaran

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datapengeluaran.data.UserViewModel
import com.example.datapengeluaran.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Mneyiapkan adapter dan ryclclevIEW
        val adapter = UserListAdapter()
        val rvUser = binding.rvUser

        //Menambahkan adapter ke recyclerView
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(requireContext())


        mUserViewModel = ViewModelProvider(this). get(UserViewModel::class.java)
        //mengambil secara bertahap
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer{
                user -> adapter.setData(user)
            Log.d("User", user.toString())
        })

        //membuat aksi fab
        binding.floatingActionButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}