package com.food.foodchallenge.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.food.foodchallenge.dagger.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getViewModel(): BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().messageStringMessageEvent.observe(
            viewLifecycleOwner
        ) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    protected open fun showMessage(errorMessage: String) {
        val rootGroup = activity?.findViewById<View>(android.R.id.content) as ViewGroup
        Snackbar.make(rootGroup, errorMessage, Snackbar.LENGTH_SHORT).show()
    }
}
