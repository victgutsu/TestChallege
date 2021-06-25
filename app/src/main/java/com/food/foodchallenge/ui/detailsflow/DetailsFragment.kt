package com.food.foodchallenge.ui.detailsflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.food.foodchallenge.databinding.FragmentDetailsBinding
import com.food.foodchallenge.ui.BaseFragment
import com.food.foodchallenge.ui.BaseViewModel

class DetailsFragment : BaseFragment() {
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private val args = navArgs<DetailsFragmentArgs>()

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition() // is used for delay transition between fragments
        //added icon to navigation
        viewModel.detailsItemState.observe(viewLifecycleOwner) {
            Glide.with(this)
                .load(it.thumb)
                .into(binding.motionLayout.imageViewBackground)
            binding.motionLayout.textViewHeaderTitle.text = it.meal
            binding.scrollable.textViewTitleMeal.text = it.meal
            binding.scrollable.textViewTitleSubMeal.text = "${it.area} / ${it.category}"
            binding.scrollable.textViewIngredients.text =
                it.ingredientMeasureList.joinToString("\n") { item -> item.first }
            binding.scrollable.textViewMeasures.text =
                it.ingredientMeasureList.joinToString("\n") { item -> item.second }
            binding.scrollable.textViewInstructions.text = it.instructions
            startPostponedEnterTransition() // is used for delay transition between fragments
        }
        viewModel.messageStringMessageEvent.observe(viewLifecycleOwner) {
            startPostponedEnterTransition() // is used for delay transition between fragments
        }
        viewModel.detailsMeal(args.value.searchId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}