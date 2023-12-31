package com.p47.reeltalk

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.p47.reeltalk.databinding.Welcome3Binding
import com.p47.reeltalk.ui.login.LoginActivity


class WelcomeFragment3 : Fragment() {

    private var _binding: Welcome3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Welcome3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set OnClickListener for the Button
        binding.continueButton.setOnClickListener {
            val intent = Intent(requireContext(), GenreSelect::class.java)
            startActivity(intent)
        }

        binding.loginClick3.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

