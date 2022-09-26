package com.example.catalagofilmes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme
import com.example.catalagofilmes.ui.adapter.ListaFilmesAdapter
import com.example.catalagofilmes.webclient.filmeWebClient
import kotlinx.coroutines.launch
import java.io.Serializable

class ListaFilmesActivity : AppCompatActivity() {

    private val adapter by lazy {
        ListaFilmesAdapter()
    }

    private val dataSource by lazy {
        filmeWebClient()
    }

    private var page = 1
    private val arrayListFilmes: ArrayList<Filme> = arrayListOf()
    private lateinit var escutaLista : RecyclerView.OnScrollListener
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                recyclerView = findViewById(R.id.activity_lista_filmes_recyclerView)
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
            findViewById<ProgressBar>(R.id.activity_lista_filme_ProgressBar).visibility = View.GONE
        } catch (e: Exception) {
            Toast.makeText(
                this@ListaFilmesActivity,
                "Conexão com a internet não encontrada!",
                Toast.LENGTH_SHORT
            ).show()
            findViewById<ProgressBar>(R.id.activity_lista_filme_ProgressBar).visibility = View.GONE
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
        recyclerView.addOnScrollListener(escutaLista)
    }

    private fun removeScrollListener() {
        if (::escutaLista.isInitialized){
            recyclerView.removeOnScrollListener(escutaLista)
        }
    }

    override fun onPause() {
        super.onPause()
        removeScrollListener()
    }

    private fun configuraRecyclerView() {
        recyclerView.adapter = adapter
        adapter.itemClickListener = {
            val intent = Intent(this, FilmeActivity::class.java)
            intent.putExtra("Filme", it as Serializable)
            startActivity(intent)
        }
    }
}