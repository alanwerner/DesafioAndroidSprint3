package com.example.catalagofilmes.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.catalagofilmes.databinding.FragmentFilmesFavoritosBinding

class FilmesFavoritosFragment : Fragment() {

    private var _binding: FragmentFilmesFavoritosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(FilmesFavoritosViewModel::class.java)

        _binding = FragmentFilmesFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}