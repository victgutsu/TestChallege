package com.food.foodchallenge.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.food.foodchallenge.utility.SchedulersProvider
import com.food.foodchallenge.utility.SingleLiveEvent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel(val schedulers: SchedulersProvider) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    val messageStringMessageEvent = SingleLiveEvent<String>()
    val progressEvent = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

    fun showError(error: Throwable) {
        error.printStackTrace()
        when (error) {
            is NetworkErrorException -> {
                messageStringMessageEvent.value = "Network error"
            }
            else -> {
                val show = error.localizedMessage?.contains("resolve host", true) ?: false
                if (show) messageStringMessageEvent.value = "Network error"
                if (!show) messageStringMessageEvent.value = error.message ?: "Some error occurred"
            }
        }
    }

    fun showError(errorMessage: String) {
        showMessage(errorMessage)
    }

    fun showMessage(message: String) {
        messageStringMessageEvent.value = message
    }

    fun showProgress(show: Boolean) {
        progressEvent.value = show
    }

    protected fun Completable.subscribeAsUsual(callback: (() -> Unit)? = null): Disposable {
        return this
            .subscribe(
                {
                    callback?.invoke()
                },
                { error ->
                    showError(error)
                }
            )
            .connectToSubscriptions()
    }

    protected fun <T> Single<T>.subscribeAsUsual(callback: (T) -> Unit): Disposable {
        return this
            .subscribe(
                { value ->
                    callback(value)
                },
                { error ->
                    showError(error)
                }
            )
            .connectToSubscriptions()
    }

    protected fun <T> Observable<T>.subscribeAsUsual(callback: (T) -> Unit): Disposable {
        return this
            .subscribe(
                { value ->
                    callback(value)
                },
                { error ->
                    showError(error)
                }
            )
            .connectToSubscriptions()
    }

    protected fun Disposable.connectToSubscriptions(): Disposable {
        subscriptions.add(this)
        return this
    }

    protected fun Completable.applySchedulers(
        subscribeOn: Scheduler = schedulers.ioScheduler(),
        observeOn: Scheduler = schedulers.mainScheduler()
    ): Completable {
        return this
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
    }

    protected fun <T> Single<T>.applySchedulers(
        subscribeOn: Scheduler = schedulers.ioScheduler(),
        observeOn: Scheduler = schedulers.mainScheduler()
    ): Single<T> {
        return this
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
    }

    protected fun <T> Observable<T>.applySchedulers(
        subscribeOn: Scheduler = schedulers.ioScheduler(),
        observeOn: Scheduler = schedulers.mainScheduler()
    ): Observable<T> {
        return this
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
    }
}
