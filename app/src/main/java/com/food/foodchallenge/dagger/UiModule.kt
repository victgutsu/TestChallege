package com.food.foodchallenge.dagger

import com.food.foodchallenge.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}
