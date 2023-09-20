package com.android.kakaobankproject.mainTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.kakaobankproject.R
import com.android.kakaobankproject.SearchFragment
import com.android.kakaobankproject.databinding.FragmentContactBinding


class ContactFragment : Fragment() {

    private val binding by lazy { FragmentContactBinding.inflate(layoutInflater) }
    private val searchFragment = SearchFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_contract, searchFragment)
        transaction.commit()
    }

    companion object {
        fun newInstance() = ContactFragment()
    }

}