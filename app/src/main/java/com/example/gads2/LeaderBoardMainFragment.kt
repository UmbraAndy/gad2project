package com.example.gads2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_leader_board_main.*


class LeaderBoardMainFragment : Fragment(R.layout.fragment_leader_board_main) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bind tab to pager
        activity?.let {
            mainViewPager.adapter = MainLeaderBoardPageAdapter(it)
            TabLayoutMediator(tabLayout, mainViewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Learning Leaders"
                    1 -> tab.text = "Skill IQ Leaders"
                }
            }.attach()
        }

        submitButton.setOnClickListener {
            findNavController().navigate(R.id.action_leaderBoardMainFragment_to_submissionFragment)
        }
    }

    private class MainLeaderBoardPageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            Log.d("XXXX", "POS: $position")
            return when (position) {
                0 -> LearnerFragment()
                1 -> SkillFragment()
                else -> {
                    throw IllegalArgumentException("Invalid position($position")
                }
            }

        }


    }
}