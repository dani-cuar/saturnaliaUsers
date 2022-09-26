package com.example.saturnaliausers.ui.reseña

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.databinding.FragmentResenaBinding
import com.example.saturnaliausers.model.Review

class resenaFragment : Fragment() {

    private lateinit var resenaBinding: FragmentResenaBinding
    private lateinit var resenaViewModel: ResenaViewModel
    private lateinit var resenaAdapter: ResenaAdapter
    private var reviewList: ArrayList<Review> = ArrayList()
    private val args: resenaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resenaBinding = FragmentResenaBinding.inflate(inflater, container, false)
        resenaViewModel = ViewModelProvider(this)[ResenaViewModel::class.java]

        val disco = args.disco

        resenaViewModel.loadReviews(disco.uid.toString())

        resenaViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        resenaViewModel.reviewsList.observe(viewLifecycleOwner){list ->
            resenaAdapter.appendItems(list)
        }

        resenaAdapter = ResenaAdapter(reviewList)

        resenaBinding.reviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@resenaFragment.requireContext())
            adapter = resenaAdapter
            setHasFixedSize(false)
        }

        return resenaBinding.root
    }

    private fun showMsg(it: String?) {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}