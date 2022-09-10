package com.example.catalagofilmes.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme
import com.example.catalagofilmes.ui.adapter.ListaFilmesAdapter
import com.example.catalagofilmes.webclient.filmeWebClient

class ListaFilmesActivity : AppCompatActivity() {

    private val adapter by lazy {
        ListaFilmesAdapter(this)
    }

    private val dataSource by lazy {
        filmeWebClient()
    }

    private val arrayListFilmes: ArrayList<Filme> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                try {
                    val response = dataSource.buscaTodos()
                    if (response != null) {
                        arrayListFilmes.addAll(response.results!!)
                    }
                } catch (e: Exception) {
                    Log.e("ErroArrayList", "onCreate: $e")
                }
                configuraRecyclerView()
            }
        }
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_filmes_recyclerView)
        recyclerView.adapter = adapter
        adapter.atualiza(arrayListFilmes)
    }
}