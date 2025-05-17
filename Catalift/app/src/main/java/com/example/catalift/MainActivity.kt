package com.example.catalift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.catalift.R
import com.example.catalift.databinding.ActivityMainBinding
import com.example.catalift.ui.InterestsFragment

class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding?=null
    private val binding
        get()=_binding!!

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
