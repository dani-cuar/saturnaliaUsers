package com.example.saturnaliausers.ui.createreview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentCreateReviewBinding

class CreateReviewFragment : Fragment() {

    private lateinit var createReviewViewModel: CreateReviewViewModel
    private lateinit var createReviewBinding: FragmentCreateReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createReviewBinding = FragmentCreateReviewBinding.inflate(inflater, container, false)
        createReviewViewModel = ViewModelProvider(this)[CreateReviewViewModel::class.java]

        val view = createReviewBinding.root



        return view
    }

}