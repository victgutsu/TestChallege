package com.food.foodchallenge.interactors

import com.food.foodchallenge.database.MealRepository
import com.food.foodchallenge.network.NetworkClient
import com.food.foodchallenge.testutils.KotlinTestRunner
import com.food.foodchallenge.ui.detailsflow.DetailsInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class DetailsInteractorTest : KotlinTestRunner() {
    lateinit var interactor: DetailsInteractor
    lateinit var mealRepository: MealRepository
    lateinit var networkClient: NetworkClient

    @Before
    fun before() {
        mealRepository = mockk(relaxed = true)
        networkClient = mockk(relaxed = true)
        interactor = spyk(DetailsInteractor(networkClient, mealRepository))
    }

    @Test
    fun `should return details`() {
        // before
        every { interactor.detailsServer(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        every { interactor.detailsStorageSingle(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        // when
        val test = interactor.details(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        // result
        test.assertValueCount(1)
        test.assertNoErrors()
    }

    @Test
    fun `should fail return details`() {
        val error = IllegalStateException("some error")
        // before
        every { interactor.detailsServer(any()) } returns Single.create {
            it.onError(error)
        }
        every { interactor.detailsStorageSingle(any()) } returns Single.create {
            it.onError(error)
        }
        // when
        val test = interactor.details(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        // result
        test.assertValueCount(0)
        test.assertError(error)
    }

    @Test
    fun `should return details from db if server error`() {
        // before
        every { interactor.detailsServer(any()) } returns Single.create {
            it.onError(mockk(relaxed = true))
        }
        every { interactor.detailsStorageSingle(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        // when
        val test = interactor.details(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        // result
        test.assertValueCount(1)
        test.assertNoErrors()
    }

    @Test
    fun `should save details to db`() {
        //before
        every { networkClient.detailsMeal(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        //when
        val test = interactor.detailsServer(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        test.assertValueCount(1)
        // result
        test.assertNoErrors()
        verify(exactly = 1) {
            mealRepository.insert(any())
        }
    }

    @Test
    fun `should not save details to db`() {
//        before
        every { networkClient.detailsMeal(any()) } returns Single.create {
            it.onError(mockk(relaxed = true))
        }
        //when
        val test = interactor.detailsServer(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
//        result
        test.assertValueCount(0)
        verify(exactly = 0) {
            mealRepository.insert(any())
        }
    }

    @Test
    fun `should get saved details to db`() {
        // before
        every { mealRepository.getMeal(any()) } returns Single.create {
            it.onSuccess(mockk(relaxed = true))
        }
        //when
        val test = interactor.detailsStorageSingle(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        //result
        test.assertValueCount(1)
        test.assertNoErrors()
    }

    @Test
    fun `should fail saved details to db`() {
        //before
        val error = Exception("Test error")
        every { mealRepository.getMeal(any()) } returns Single.create {
            it.onError(error)
        }
        //when
        val test = interactor.detailsStorageSingle(1).test()
        test.await(1000, TimeUnit.MILLISECONDS)
        //result
        test.assertValueCount(0)
        test.assertError(error)
    }
}
