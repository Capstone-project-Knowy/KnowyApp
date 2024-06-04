package com.capstone.knowy.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.knowy.ui.forum.newdiscussion.NewDiscussionFragment
import com.capstone.knowy.ui.forum.trending.TrendingFragment


class FragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrendingFragment()
            1 -> NewDiscussionFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}