package com.food.foodchallenge.utility

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SchedulersProvider @Inject constructor() {

    fun ioScheduler(): Scheduler = Schedulers.io()

    fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}