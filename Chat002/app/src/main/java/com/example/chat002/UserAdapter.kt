package com.example.chat002

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val context: Context, private val userList:ArrayList<User>):
        RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

            //화면설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view: View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent, false)

        return UserViewHolder(view)

    }

            //데이터 설정
    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val currentUser = userList[position]
                holder.nameText.text = currentUser.name

                holder.itemView.setOnClickListener{
                    val intent = Intent(context, ChatActivity::class.java)

                    intent.putExtra("name", currentUser.name)
                    intent.putExtra("uld", currentUser.uld)

                    context.startActivity(intent)
                }
    }

        //데이터  갯수 가져오기

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val nameText: TextView = itemView.findViewById((R.id.name_text))
    }


}


