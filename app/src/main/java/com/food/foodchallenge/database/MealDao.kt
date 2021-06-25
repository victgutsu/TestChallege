package com.food.foodchallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MealDao {
    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM meal_table ORDER BY strMeal ASC")
    fun getAlphabetizedSingle(): Single<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompletable(meal: Meal): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCompletable(list: List<Meal>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Meal>)

    @Query("DELETE FROM meal_table")
    fun deleteAllCompletable(): Completable

    @Query("DELETE FROM meal_table")
    fun deleteAll()

    @Query("SELECT * FROM meal_table WHERE idMeal=:id")
    fun getSingle(id: Int): Single<Meal?>

    @Query("SELECT * FROM meal_table WHERE idMeal=:id")
    fun get(id: Int): Meal?
}
