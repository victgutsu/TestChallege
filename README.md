# TestChallege

Requirements:
The goal is this exercise is to let you show your programming skills in Android.
You need to implement an app that:
- Is written in Kotlin.
- Reads a JSON feed from the Internet. Use the https://www.themealdb.com/ API.
- Uses Retrofit + RxJava to process network requests.
- 1st Screen contains a search field. When a user enters a new letter, the app
searches for meals and displays results in the list. Each list item has a name, a
picture, a category and an area of the meal.
- Clicking on a list item shows a 2nd screen containing selected meal details from the
json, especially ingredients with measures and a recipe.
- Persists the contents locally, so if a user opens the app it will show previously
opened meals as a history (they should appear in the 1st screen’s list if the search
field is empty).
- Uses Room + RxJava to work with a database.
- Compiles and runs from Android Studio
