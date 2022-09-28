package com.example.saturnaliausers.ui.createreview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.system.Os.remove
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentCreateReviewBinding

class CreateReviewFragment : Fragment() {

    private lateinit var createReviewViewModel: CreateReviewViewModel
    private lateinit var createReviewBinding: FragmentCreateReviewBinding

    private val args: CreateReviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createReviewBinding = FragmentCreateReviewBinding.inflate(inflater, container, false)
        createReviewViewModel = ViewModelProvider(this)[CreateReviewViewModel::class.java]

        val view = createReviewBinding.root

        var disco = args.disco

        createReviewViewModel.msg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }

        createReviewViewModel.createReviewSuccess.observe(viewLifecycleOwner){
            findNavController().navigate(CreateReviewFragmentDirections.actionNavigationCreateReviewToNavigationDiscotecas(disco))
        }

        with(createReviewBinding){
            buttonSubmitReview.setOnClickListener {
                createReviewViewModel.checkFields(ratingBarReview.rating, editTextAuthor.text.toString(), editTextComment.text.toString(), disco.uid.toString())
            }
        }

        return view
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}