package com.example.saturnaliausers.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.saturnaliausers.databinding.FragmentFavoritesBinding
import com.example.saturnaliausers.databinding.FragmentNotificationsBinding
import com.example.saturnaliausers.ui.favorites.FavoritesViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsBinding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsBinding = FragmentNotificationsBinding.inflate(inflater,container,false)
        notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]

        return notificationsBinding.root
    }


}