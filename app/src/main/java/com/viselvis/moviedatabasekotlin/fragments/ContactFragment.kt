package com.viselvis.moviedatabasekotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import com.viselvis.moviedatabasekotlin.R
import com.viselvis.moviedatabasekotlin.database.AppDatabase
import com.viselvis.moviedatabasekotlin.databinding.FragmentContactBinding
import com.viselvis.moviedatabasekotlin.viewmodels.ContactViewModel

class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var inputString: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        initUIComponents()
        return binding.root
    }

    private fun initUIComponents() {
        // button for adding contacts
        binding.btnAddContact.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        // check first if text input is empty or not
        if (!binding.tieInput.text.isNullOrEmpty()) {
            // if not empty, get the text input
            Toast.makeText(context, "The string should be added!", Toast.LENGTH_LONG).show()
        } else {
            // else, show prompt
            Toast.makeText(context, "Text is empty!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // create an instance of the database

    }

}