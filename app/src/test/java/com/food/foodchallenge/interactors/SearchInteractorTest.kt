package com.food.foodchallenge.interactors

import com.food.foodchallenge.database.MealRepository
import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.testutils.KotlinTestRunner
import com.food.foodchallenge.ui.searchflow.SearchInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SearchInteractorTest : KotlinTestRunner() {
    lateinit var interactor: SearchInteractor
    lateinit var mealRepository: MealRepository
    lateinit var networkClient: NetworkClient

    @Before
    fun before() {
        mealRepository = mockk(relaxed = true)
        networkClient = mockk(relaxed = true)
        interactor = spyk(SearchInteractor(networkClient, mealRepository))
    }

    @Test
    fun `should query meals`() {
        //befero
        every { interactor.serverMeal(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        every { interactor.savedMeals() } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        // when
        val test = interactor.queryMeal("query").test()
        test.await(1000, TimeUnit.MILLISECONDS)
        // result
        test.assertValueCount(1)
        test.assertNoErrors()
    }

    @Test
    fun `should not get any server meals`() {
        val test = interactor.serverMeal("").test()
        test.await(1000, TimeUnit.MILLISECONDS)
        test.assertValueCount(0)
    }

    @Test
    fun `should query only from db`() {
        //when
        interactor.queryMeal("")
        //then
        verify(exactly = 1) {
            interactor.savedMeals()
        }
        verify(exactly = 0) {
            interactor.serverMeal(any())
        }
    }

    @Test
    fun `should query only from db2`() {
        // when
        interactor.queryMeal("  ")
        // then
        verify(exactly = 1) {
            interactor.savedMeals()
        }
        verify(exactly = 0) {
            interactor.serverMeal(any())
        }
    }

    @Test
    fun `should query only from server`() {
        //when
        interactor.queryMeal("query")
        //then
        verify(exactly = 0) {
            interactor.savedMeals()
        }
        verify(exactly = 1) {
            interactor.serverMeal(any())
        }
    }

}
