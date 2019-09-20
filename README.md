# news-app
The app uses Kotlin following recommended MVVM architecture by Google at https://developer.android.com/jetpack/docs/guide

## Steps to run:
1. Generate api key from https://newsapi.org/
2. Put the api key in ApiService.kt / API_KEY

## Code structure: 
- binding: BindingAdapter add some extension function so view layouts can access ViewModel directly
- data: contain user preference data 
- network: retrofit to fetch data and model to parse json 
- ui: ViewControl and ViewModel for each screen 

## Libraries and tools included:
- Support libraries
- Retrofit 2
- Moshi 
- Glide
- LiveData 
- ViewModel 
- Binding 
- SafeArgs 
- Coroutines

## Room to improve:

- Add DI
- Add repositories 
- Add UnitTest 
- Add Database 
- Add code comments 
- Update icon 
- Correct landscape layouts
