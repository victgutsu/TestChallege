package com.food.foodchallenge.viewmodels

import com.food.foodchallenge.testutils.KotlinTestRunner
import com.food.foodchallenge.testutils.observeForTesting
import com.food.foodchallenge.ui.searchflow.SearchInteractor
import com.food.foodchallenge.ui.searchflow.SearchItem
import com.food.foodchallenge.ui.searchflow.SearchViewModel
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

class SearchModelViewTest : KotlinTestRunner() {

    lateinit var schedulersProvider: SchedulersProvider
    lateinit var scheduler: TestScheduler
    lateinit var viewModel: SearchViewModel
    lateinit var interactor: SearchInteractor

    @Before
    fun before() {
        interactor = mockk(relaxed = true)
        scheduler = TestScheduler()
        schedulersProvider = mockk {
            every { ioScheduler() } returns scheduler
            every { mainScheduler() } returns scheduler
        }
        viewModel = spyk(SearchViewModel(interactor, schedulersProvider))
    }

    @Test
    fun `should update search list`() {
        // given
        val query = "some query"
        val mockSearchItem = SearchItem(1, "meal", "category", "area", "thumb")
        every { interactor.queryMeal(query) } returns Single.create {
            it.onSuccess(listOf(mockSearchItem))
        }
        // when
        viewModel.search(query)
        scheduler.triggerActions()
        // then
        viewModel.searchListState.observeForTesting {
            Assert.assertTrue(viewModel.searchListState.value?.isNotEmpty() ?: false)
        }
        verify(exactly = 1) {
            viewModel.updateSearchList(listOf(mockSearchItem))
        }
        verify(exactly = 1) {
            viewModel.updateQueryState(query)
        }
    }

    @Test
    fun `should Not update search list`() {
        // given
        every { interactor.queryMeal(any()) } returns Single.create {
            it.onError(Exception("Some error"))
        }
        // when
        viewModel.search("some")
        scheduler.triggerActions()
        // then
        verify(exactly = 0) {
            viewModel.updateSearchList(any())
        }
        verify(exactly = 0) {
            viewModel.updateQueryState(any())
        }
    }

    @Test
    fun `should show message on error`() {
        // given
        val error = Exception("message")
        every { interactor.queryMeal(any()) } returns Single.create {
            it.onError(error)
        }
        // when
        viewModel.search("some")
        scheduler.triggerActions()
        // then
        verify(exactly = 1) {
            viewModel.showError(error)
        }
        verify(exactly = 0) {
            viewModel.updateQueryState(any())
        }
        verify(exactly = 0) {
            viewModel.updateSearchList(any())
        }
    }

    @Test
    fun `refresh should call query `() {
        //  when
        viewModel.refresh()
        // then
        verify(exactly = 1) {
            viewModel.search(any())
        }
    }

    @Test
    fun `should update search list `() {
        //when
        viewModel.updateSearchList(listOf(mockk(relaxed = true)))
        // result
        viewModel.searchListState.observeForTesting {
            Assert.assertTrue(viewModel.searchListState.value?.isNotEmpty() ?: false)
        }
    }

    @Test
    fun `should update query state `() {
        //when
        viewModel.updateQueryState("query")
        //result
        viewModel.searchQueryState.observeForTesting {
            Assert.assertTrue(viewModel.searchQueryState.value?.isNotEmpty() ?: false)
        }
    }

    @Test
    fun `should open details`() {
        //before
        val mockSearchItem = SearchItem(1, "meal", "category", "area", "thumb")
        // when
        viewModel.openItem(mockSearchItem)
        //result
        viewModel.openDetailsEvent.observeForTesting {
            Assert.assertEquals(viewModel.openDetailsEvent.value?.searchId, mockSearchItem.id)
        }
    }
}
