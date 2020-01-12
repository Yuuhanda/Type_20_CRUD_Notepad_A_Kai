package com.example.tugasnyusahin

import Users
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    //deklarasi firebase database
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//untuk membuat data base dan membuat tambel dengan nama users
        ref = FirebaseDatabase.getInstance().getReference("USERS")
// btn save ketika kita klik akan menyimpan dan di tampilkan pada class show
        btn_save.setOnClickListener {
            savedata()
            val intent = Intent (this, show::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {

        //mendeklarasikan nama sama dengan inputjudul dengan tipe data string
        //mendeklarasikan status sama dengan inputjudul dengan tipe data string
        val nama = inputJudul.text.toString()
        val status = inputData.text.toString()
//mendeklarasi kan userid agar data base memiliki tipe data string
        val userId = ref.push().key.toString()
        //mengambil nilai untuk di import ke dalam data base
        val user = Users(userId, nama,status)
//memasukan data kedatabase sekaligus menjadikan tampilan menjadi normal kembali
        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputJudul.setText("")
            inputData.setText("")
        }
    }
}
