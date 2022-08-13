package com.example.saturnaliausers.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnaliausers.R

class DeleteFragment : Fragment() {

    private lateinit var deleteviewModel: DeleteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        deleteviewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}