package com.fintold.grandtask.uI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.R
import com.fintold.grandtask.adapters.OnClickListener
import com.fintold.grandtask.adapters.TopicsRecyclerViewAdapter
import com.fintold.grandtask.databinding.FragmentTopicsListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopicsListFragment : Fragment() {
    private var binding: FragmentTopicsListBinding?= null
    private val viewModel by sharedViewModel<MainActivityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_topics_list,container,false)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel

        binding?.topicsListRecyclerView?.adapter = TopicsRecyclerViewAdapter(OnClickListener {
            viewModel.selectedTopic.value = it
            findNavController().navigate(TopicsListFragmentDirections.actionTopicsListFragmentToTopicDetailsFragment())
        },viewModel)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = null
    }
}