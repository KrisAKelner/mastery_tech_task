package com.example.masterytechtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.masterytechtask.R
import com.example.masterytechtask.databinding.FragmentLoginBinding
import com.example.masterytechtask.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private lateinit var loginViewModel: LoginViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.userData.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    findNavController().navigate(R.id.action_LoginFragment_to_DriverListFragment)
                }
            }
        )

        loginViewModel.userErrorData.observe(viewLifecycleOwner, Observer {
                if (it != null && it.error.isNotEmpty()) {
                   displayError(it.error)
                }
            }
        )

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login() {
        val username = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (username.isBlank() || password.isBlank()) {
            displayError(getString(R.string.login_fill_error))
        } else {
            loginViewModel.login(username, password)
        }

    }

    private fun displayError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}