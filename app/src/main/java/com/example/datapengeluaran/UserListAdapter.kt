package com.example.datapengeluaran

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.datapengeluaran.data.User
import java.text.SimpleDateFormat
import java.util.*

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent,false))
    }

    override fun getItemCount(): Int {
        // size = ukuran dr list ada brp (record)
        Log.d("Size of User" , userList.toString())
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //kita attach = menaruh dr database ke form
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvId).text = (position+1).toString()
        holder.itemView.findViewById<TextView>(R.id.tvTanggal).text = getCurrentDate()
        holder.itemView.findViewById<TextView>(R.id.tvKategori).text = currentItem.kategori
        holder.itemView.findViewById<TextView>(R.id.tvJumlah).text = currentItem.jumlah.toString()
        holder.itemView.findViewById<TextView>(R.id.tvKeterangan).text = currentItem.keterangan

//        if (position % 2 == 0){
//            holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setBackgroundResource(Color:CYAN)
//        }

        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        Log.d("UserList", user.toString())
        notifyDataSetChanged()
    }

    private fun getCurrentDate():String{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}