package com.fintold.grandtask.uI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.R
import com.fintold.grandtask.databinding.FragmentTopicDetailsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopicDetailsFragment : Fragment() {
    private var binding: FragmentTopicDetailsBinding?= null
    private val viewModel by sharedViewModel<MainActivityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_topic_details,container,false)
        binding?.lifecycleOwner = viewLifecycleOwner

        viewModel.selectedTopic.observe(viewLifecycleOwner) {
            it?.let {
                binding?.redditTopic = it
                (requireActivity() as MainActivity).setActionBarTitle(it.title)
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}