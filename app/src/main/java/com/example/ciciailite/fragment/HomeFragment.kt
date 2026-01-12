package com.example.ciciailite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ciciailite.R
import com.example.ciciailite.databinding.ActivityHomeFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: ActivityHomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cari Bottom Navigation yang ada di MainActivity
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Aksi klik tombol Mulai Chat
        binding.cardChat.setOnClickListener {
            // Pindah ke tab Data/Chat (Ganti R.id.nav_data sesuai ID di menu Anda)
            bottomNav?.selectedItemId = R.id.nav_data
        }

        // Aksi klik tombol Riwayat
        binding.cardHistory.setOnClickListener {
            // Pindah ke tab Data/Chat
            bottomNav?.selectedItemId = R.id.nav_data
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}