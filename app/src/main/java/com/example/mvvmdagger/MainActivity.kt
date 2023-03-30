package com.example.mvvmdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdagger.Adapter.ProductAdapter
import com.example.mvvmdagger.models.Product
import com.example.mvvmdagger.viewmodels.MainViewModel
import com.example.mvvmdagger.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    //var textView: TextView = findViewById<TextView>(R.id.products)

    private val recyclerView : RecyclerView
    get() = findViewById(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as FakerApplication).applicationComponent.Inject(this)
        mainViewModel = ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)


        mainViewModel.productLiveData.observe(this) {
//           products.text =  it.joinToString { x -> x.title + "\n\n" }
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ProductAdapter(it,this)

        }

    }
}