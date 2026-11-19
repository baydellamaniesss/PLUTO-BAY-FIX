package com.example.pluto_baydella.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pluto_baydella.AuthActivity
import com.example.pluto_baydella.Home.pertemuan_5.FifthActivity
import com.example.pluto_baydella.Home.pertemuan_7.SeventhActivity
import com.example.pluto_baydella.Home.pertemuan_10.TenthActivity
import com.example.pluto_baydella.Home.pertemuan_11.EleventhActivity
import com.example.pluto_baydella.Home.photo.PhotoAdapter
import com.example.pluto_baydella.data.api.CatFactApiClient
import com.example.pluto_baydella.data.api.PhotoApiClient
import com.example.pluto_baydella.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // FIFTH ACTIVITY
        binding.btnToFifth.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        // SEVENTH ACTIVITY
        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // TENTH ACTIVITY (PERTEMUAN 10)
        binding.btnToNinth.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // ELEVENTH ACTIVITY (PERTEMUAN 11)
        binding.btnToEleventh.setOnClickListener {
            startActivity(Intent(requireContext(), EleventhActivity::class.java))
        }

        // LOGOUT
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()

                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        // REFRESH CAT FACT
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        loadCatFact()
        loadPhoto()
    }

    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                binding.rvGallery.adapter = PhotoAdapter(photos)

                /** List tampil vertikal */
                //binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List tampil horizontal */
                //binding.rvGallery.layoutManager =
               // LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List tampil grid */
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}