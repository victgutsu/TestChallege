package com.food.foodchallenge.database

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MealRepository @Inject constructor(private val mealDao: MealDao) {
    fun insertCompletable(meal: Meal): Completable {
        return mealDao.insertCompletable(meal)
    }

    fun insert(meal: Meal) {
        mealDao.insert(meal)
    }

    fun insertAll(meals: List<Meal>): Completable {
        return mealDao.insertAllCompletable(meals)
    }

    fun getAll(): Single<List<Meal>> {
        return mealDao.getAlphabetizedSingle()
    }

    fun getMeal(id: Int): Single<Meal?> {
        return mealDao.getSingle(id)
    }
}
