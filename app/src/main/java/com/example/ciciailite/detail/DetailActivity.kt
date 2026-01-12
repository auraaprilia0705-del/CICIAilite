package com.example.ciciailite.detail

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.example.ciciailite.R // Pastikan ini mengarah ke package aplikasi Anda

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PERBAIKAN: Pastikan nama layout XML-nya benar (activity_detail)
        setContentView(R.layout.activity_detail)

        val etEdit = findViewById<EditText>(R.id.etEditData)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // Mengambil data dari Intent (CPMK 3)
        val id = intent.getStringExtra("DATA_ID") ?: ""
        val currentText = intent.getStringExtra("DATA_TEXT") ?: ""
        etEdit.setText(currentText)

        val dbRef = FirebaseDatabase.getInstance().getReference("chats").child(id)

        // UPDATE Data (CPMK 4)
        btnUpdate.setOnClickListener {
            val newText = etEdit.text.toString().trim()
            if (newText.isNotEmpty()) {
                dbRef.child("text").setValue(newText).addOnSuccessListener {
                    Toast.makeText(this, "Data diperbarui", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke halaman sebelumnya
                }
            }
        }

        // DELETE Data (CPMK 4)
        btnDelete.setOnClickListener {
            dbRef.removeValue().addOnSuccessListener {
                Toast.makeText(this, "Data dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}