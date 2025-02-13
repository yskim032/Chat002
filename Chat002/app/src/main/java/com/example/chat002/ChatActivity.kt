package com.example.chat002

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat002.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//class ChatActivity : AppCompatActivity() {
//
//    private lateinit var receiverName: String
//    private lateinit var receiverUid: String
//
//    private lateinit var binding: ActivityChatBinding
//
//
//    lateinit var mAuth: FirebaseAuth
//    lateinit var mDbRef: DatabaseReference
//
//    private lateinit var receiverRoom: String
//    private lateinit var senderRoom: String
//
//    private lateinit var messageList:ArrayList<Message>
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding= ActivityChatBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        messageList = ArrayList()
//        val messageAdapter : MessageAdapter = MessageAdapter(this, messageList)
//
//        binding.chatRecyclerView.layoutManager=LinearLayoutManager(this)
//        binding.chatRecyclerView.adapter = messageAdapter
//
//        receiverName = intent.getStringExtra("name").toString()
//        receiverUid= intent.getStringExtra("uId").toString()
//
//        mAuth = FirebaseAuth.getInstance()
//        mDbRef = FirebaseDatabase.getInstance().reference
//
//        val senderUid = mAuth.currentUser?.uid
//
//        senderRoom = receiverUid + senderUid
//        receiverRoom = senderUid + receiverUid
//
//        supportActionBar?.title=receiverName
//
//        binding.sendBtn.setOnClickListener {
//
//            val message = binding.messageEdit.text.toString()
//            val messageObject = Message(message, senderUid)
//
//                //보낸이, 받는이 대화 내용 저장
//            mDbRef.child("chats").child(senderRoom).child("messages").push()
//                .setValue(messageObject).addOnSuccessListener {
//                    mDbRef.child("chats").child(receiverRoom).child("messages").push()
//                        .setValue(messageObject)
//                }
//            binding.messageEdit.setText("")
//        }
//
//        mDbRef.child("chats").child(senderRoom).child("messages")
//            .addValueEventListener(object:ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    messageList.clear()
//
//                    for (postSnapshat in snapshot.children){
//
//                        val message = postSnapshat.getValue(Message::class.java)
//                        messageList.add(message!!)
//                    }
//                    messageAdapter.notifyDataSetChanged()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//
//    }
//
//
//}
class ChatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receiverUid: String

    private lateinit var binding: ActivityChatBinding

    lateinit var mAuth: FirebaseAuth
    lateinit var mDbRef: DatabaseReference

    private lateinit var receiverRoom: String
    private lateinit var senderRoom: String

    private lateinit var messageList:ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageList = ArrayList()
        val messageAdapter : MessageAdapter = MessageAdapter(this, messageList)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true // 최신 메시지가 보이도록 설정
        binding.chatRecyclerView.layoutManager = layoutManager
        binding.chatRecyclerView.adapter = messageAdapter

        receiverName = intent.getStringExtra("name").toString()
        receiverUid= intent.getStringExtra("uId").toString()

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference

        val senderUid = mAuth.currentUser?.uid

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title=receiverName

        binding.sendBtn.setOnClickListener {
            val message = binding.messageEdit.text.toString()
            if (message.isNotEmpty()) {
                val messageObject = Message(message, senderUid)

                // 보낸이, 받는이 대화 내용 저장
                mDbRef.child("chats").child(senderRoom).child("messages").push()
                    .setValue(messageObject).addOnSuccessListener {
                        mDbRef.child("chats").child(receiverRoom).child("messages").push()
                            .setValue(messageObject)
                    }
                binding.messageEdit.setText("")

                // 새 메시지가 추가될 때 최신 메시지로 스크롤
                binding.chatRecyclerView.scrollToPosition(messageList.size - 1)
            }
        }

        mDbRef.child("chats").child(senderRoom).child("messages")
            .addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()

                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        if (message != null) {
                            messageList.add(message)
                        }
                    }
                    messageAdapter.notifyDataSetChanged()

                    // 데이터 변경 시 최신 메시지로 스크롤
                    binding.chatRecyclerView.scrollToPosition(messageList.size - 1)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}


