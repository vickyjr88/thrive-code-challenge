package com.example.challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.R
import com.example.challenge.data.DataHolder
import com.example.challenge.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var viewModel: DataHolder
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(DataHolder::class.java)
        binding.buttonSecond.setOnClickListener { handleSubmit() }
    }

    private fun handleSubmit() {
        val favoriteFood = binding.favoriteFood.text.toString()
        if (favoriteFood.isNotEmpty()) {
            val label = getString(R.string.favorite_food)
            viewModel.sendMessage("$label $favoriteFood")
            binding.favoriteFood.setText("")
        } else {
            Toast.makeText(context, "Please enter your favorite food.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}