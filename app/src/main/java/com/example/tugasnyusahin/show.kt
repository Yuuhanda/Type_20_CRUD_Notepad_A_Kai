package com.example.tugasnyusahin

import Users
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_show.*

class show : AppCompatActivity() {
//deklarasi variabel ref sebagai firebase data base
    //deklarasi varibel lis sebagai mutableList
    //deklarasi list view sebagai list view
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Users>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
//untuk membuat data base dan membuat tambel dengan nama users
        ref = FirebaseDatabase.getInstance().getReference("USERS")
        //membuat data berbentuk list dengan mengambil data darii data base
        list = mutableListOf()
        listView = findViewById(R.id.listView)
//untuk menambah data baru
        btn_add.setOnClickListener {
            val intent = Intent (this, Dashboard::class.java)
            startActivity(intent)
        }

        info_button.setOnClickListener(){
            intent = Intent(this, InfoPage::class.java)
            startActivity(intent)
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
//update data
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(Users::class.java)
                        list.add(user!!)
                    }
                    val adapter = Adapter(this@show,R.layout.data_out,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
