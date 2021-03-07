package com.viselvis.moviedatabasekotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.viselvis.moviedatabasekotlin.R
import android.app.Activity
import androidx.lifecycle.Observer
import com.viselvis.moviedatabasekotlin.adapters.ContactAdapter
import com.viselvis.moviedatabasekotlin.application.MainApplication
import com.viselvis.moviedatabasekotlin.database.AppDatabase
import com.viselvis.moviedatabasekotlin.databinding.FragmentContactBinding
import com.viselvis.moviedatabasekotlin.model.Contact
import com.viselvis.moviedatabasekotlin.viewmodels.ContactViewModel
import com.viselvis.moviedatabasekotlin.viewmodels.ContactViewModelFactory

class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory( (activity?.application as MainApplication).contactRepo )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactBinding.inflate(inflater, container, false)
        // viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        initUIComponents()
        return binding.root
    }

    private fun initUIComponents() {
        // button for adding contacts
        binding.btnAddContact.setOnClickListener {
            addContact()
        }

        // initialize the recyclerview here
        val adapter = ContactAdapter()
        binding.rvContacts.adapter = adapter
        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.rvContacts.layoutManager = manager

        // observer for LiveData
        contactViewModel.allContacts.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let { adapter.submitList(it) }
        })
    }

    private fun addContact() {
        // check first if text input is empty or not
        if (!binding.tieInput.text.isNullOrEmpty()) {
            // if not empty, get the text input
            // Toast.makeText(context, "The string should be added!", Toast.LENGTH_LONG).show()
            val cont1 = Contact(binding.tieInput.text.toString(), "039892")
            contactViewModel.insert(cont1)
        } else {
            // else, show prompt
            Toast.makeText(context, "Text is empty!", Toast.LENGTH_LONG).show()
        }
    }
}