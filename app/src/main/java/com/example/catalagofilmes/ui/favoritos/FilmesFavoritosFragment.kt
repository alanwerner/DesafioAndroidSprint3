package com.example.catalagofilmes.ui.favoritos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.catalagofilmes.database.AppDatabase
import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.databinding.FragmentFilmesFavoritosBinding
import com.example.catalagofilmes.repository.Repository
import com.example.catalagofilmes.ui.activity.FilmeActivity
import com.example.catalagofilmes.ui.adapter.ListaFilmesAdapter
import java.io.Serializable

class FilmesFavoritosFragment : Fragment() {

    private val dao: FilmeDao by lazy {
        AppDatabase.getInstance(requireContext()).filmeDao()
    }

    private val repository by lazy {
        Repository(dao)
    }

    private val adapter by lazy {
        ListaFilmesAdapter()
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        adapter.atualiza(repository.buscaFilmes() ?: emptyList())
        cliqueLongo()
    }

    fun cliqueLongo(){
        adapter.longClickListener = {
            val id = adapter.filmes[it].id
            dao.deletaFilme(id!!)
            adapter.deleta(it)
        }
    }

    private fun configuraRecyclerView() {
        binding.activityListaFilmesRecyclerView.adapter = adapter
        adapter.itemClickListener = {
            val intent = Intent(context, FilmeActivity::class.java)
            intent.putExtra("Filme", it as Serializable)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}