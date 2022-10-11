package com.example.catalagofilmes.ui.filmes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catalagofilmes.databinding.FragmentListaFilmesBinding
import com.example.catalagofilmes.model.Filme
import com.example.catalagofilmes.ui.activity.FilmeActivity
import com.example.catalagofilmes.ui.adapter.ListaFilmesAdapter
import com.example.catalagofilmes.webclient.filmeWebClient
import kotlinx.coroutines.launch
import java.io.Serializable

class ListaFilmesFragment : Fragment() {

    private var _binding: FragmentListaFilmesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter by lazy {
        ListaFilmesAdapter()
    }

    private val dataSource by lazy {
        filmeWebClient()
    }

    private var page = 1
    private val arrayListFilmes: ArrayList<Filme> = arrayListOf()
    private lateinit var escutaLista: RecyclerView.OnScrollListener




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ListaFilmesViewModel::class.java)

        _binding = FragmentListaFilmesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                buscaFilmesComPaginacao()
                configuraRecyclerView()
            }
        }
    }

    private suspend fun buscaFilmesComPaginacao() {
        try {
            val response = dataSource.buscaTodos(page)
            if (response != null) {
                arrayListFilmes.addAll(response.results!!)
                adapter.atualiza(arrayListFilmes)
                page++
                removeScrollListener()
                addScrollListener()
            }
            binding.activityListaFilmeProgressBar.visibility = View.GONE
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Conexão com a internet não encontrada!",
                Toast.LENGTH_SHORT
            ).show()
            binding.activityListaFilmeProgressBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        removeScrollListener()
        addScrollListener()
    }

    private fun addScrollListener() {
        escutaLista = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemVisible = visibleItemCount + pastVisibleItems
                    val totalItemCount = layoutManager.itemCount
                    if (totalItemVisible >= totalItemCount) {
                        lifecycleScope.launch {
                            removeScrollListener()
                            buscaFilmesComPaginacao()
                        }
                    }
                }
            }
        }
        binding.activityListaFilmesRecyclerView.addOnScrollListener(escutaLista)
    }

    private fun removeScrollListener() {
        if (::escutaLista.isInitialized){
            binding.activityListaFilmesRecyclerView.removeOnScrollListener(escutaLista)
        }
    }

    override fun onPause() {
        super.onPause()
        removeScrollListener()
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