package com.example.ciciailite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ciciailite.databinding.FragmentDataBinding
import com.google.firebase.database.*

class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbRef: DatabaseReference
    private lateinit var chatAdapter: ChatAdapter
    private val chatList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Firebase ke node "chats"
        dbRef = FirebaseDatabase.getInstance().getReference("chats")

        // 2. Inisialisasi Custom ChatAdapter (Untuk tampilan Bubble)
        chatAdapter = ChatAdapter(chatList)
        binding.lvAiData.adapter = chatAdapter

        // 3. Ambil data dari Firebase secara Real-time
        fetchChatData()

        // 4. Logika Tombol Kirim
        binding.btnAdd.setOnClickListener {
            val pesanInput = binding.etInputChat.text.toString().trim()

            if (pesanInput.isNotEmpty()) {
                // Kirim pesan User
                val userMessage = "User: $pesanInput"
                kirimKeFirebase(userMessage)

                // Kosongkan input
                binding.etInputChat.text.clear()

                // Simulasi AI sedang berpikir (Delay 1 detik)
                binding.root.postDelayed({
                    val balasanAi = dapatkanResponAi(pesanInput)
                    kirimKeFirebase("Cici AI: $balasanAi")
                }, 1000)

            } else {
                Toast.makeText(requireContext(), "Ketik pesan dulu!", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Klik lama untuk hapus riwayat chat (Opsional)
        binding.lvAiData.setOnItemLongClickListener { _, _, _, _ ->
            dbRef.removeValue()
            Toast.makeText(requireContext(), "Riwayat chat dihapus", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun fetchChatData() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (data in snapshot.children) {
                    val text = data.child("text").value.toString()
                    chatList.add(text)
                }
                chatAdapter.notifyDataSetChanged()

                // Otomatis scroll ke pesan paling bawah
                if (chatList.isNotEmpty()) {
                    binding.lvAiData.setSelection(chatList.size - 1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Gagal: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun kirimKeFirebase(pesan: String) {
        val id = dbRef.push().key
        if (id != null) {
            dbRef.child(id).child("text").setValue(pesan)
        }
    }

    private fun dapatkanResponAi(input: String): String {
        val tanya = input.lowercase()
        return when {
            tanya.contains("halo") -> "Halo juga! Ada yang bisa Cici bantu?"
            tanya.contains("siapa") -> "Saya adalah Cici AI, asisten virtual kamu."
            tanya.contains("uas") -> "Fokus dan teliti, kodingan kamu pasti jalan!"
            tanya.contains("terima kasih") -> "Sama-sama, senang bisa membantu."
            else -> "Maaf, Cici belum mengerti. Bisa jelaskan lagi tentang '$input'?"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}