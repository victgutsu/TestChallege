package com.food.foodchallenge.viewmodels

import com.food.foodchallenge.testutils.KotlinTestRunner
import com.food.foodchallenge.testutils.observeForTesting
import com.food.foodchallenge.ui.detailsflow.DetailsInteractor
import com.food.foodchallenge.ui.detailsflow.DetailsItem
import com.food.foodchallenge.ui.detailsflow.DetailsViewModel
import com.food.foodchallenge.utility.SchedulersProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsModelViewTest : KotlinTestRunner() {

    lateinit var schedulersProvider: SchedulersProvider
    lateinit var scheduler: TestScheduler
    lateinit var viewModel: DetailsViewModel
    lateinit var interactor: DetailsInteractor

    @Before
    fun before() {
        interactor = mockk(relaxed = true)
        scheduler = TestScheduler()
        schedulersProvider = mockk {
            every { ioScheduler() } returns scheduler
            every { mainScheduler() } returns scheduler
        }
        viewModel = spyk(DetailsViewModel(interactor, schedulersProvider))
    }

    @Test
    fun `should get details`() {
        // before
        val id = 12
        val detailsItem = DetailsItem(id, "meal", "category", "area", "thumb", "instructuins", emptyList())
        every { interactor.details(id) } returns Single.create {
            it.onSuccess(detailsItem)
        }
        // when
        viewModel.detailsMeal(id)
        scheduler.triggerActions()
        // check
        viewModel.detailsItemState.observeForTesting {
            Assert.assertEquals(viewModel.detailsItemState.value?.id, detailsItem.id)
        }
        verify(exactly = 1) {
            viewModel.updateUI(detailsItem)
        }
    }

    @Test
    fun `should update ui`() {
        //before
        val detailsItem = DetailsItem(1, "meal", "category", "area", "thumb", "", emptyList())
        //when
        viewModel.updateUI(detailsItem)
        //check
        viewModel.detailsItemState.observeForTesting {
            Assert.assertEquals(viewModel.detailsItemState.value?.id, detailsItem.id)
        }
    }
}
