package com.viselvis.moviedatabasekotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.viselvis.moviedatabasekotlin.databinding.FragmentAccountBinding
import com.viselvis.moviedatabasekotlin.viewmodels.AccountViewModel

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: AccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        binding.btnIncrement.setOnClickListener() {
            incrementScore()
        }

        updateScoreDisplay()
        return binding.root
    }

    private fun incrementScore() {
        viewModel.increment()
        updateScoreDisplay()
    }

    private fun updateScoreDisplay() {
        binding.tvNum.text = viewModel.score.toString()
    }

    companion object {

    }
}