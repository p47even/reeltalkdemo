package com.p47.reeltalk

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WelcomePagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // Return the corresponding fragment based on the position
        return when (position) {
            0 -> WelcomeFragment1()
            1 -> WelcomeFragment2()
            2 -> WelcomeFragment3()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        // Return the total number of fragments
        return 3
    }
}
