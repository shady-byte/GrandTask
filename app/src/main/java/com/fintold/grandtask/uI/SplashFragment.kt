package com.fintold.grandtask.uI

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.R
import com.fintold.grandtask.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SplashFragment : Fragment() {
    private var binding: FragmentSplashBinding?= null
    private val viewModel by sharedViewModel<MainActivityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash,container,false)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopics()
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTopicsListFragment())
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        (requireActivity() as MainActivity).supportActionBar?.show()
    }
}