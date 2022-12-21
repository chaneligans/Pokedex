# Pokedex
![pokedex](https://user-images.githubusercontent.com/37652117/208570738-18b8aa8a-4dfb-43f9-82ed-d0186b265485.png)

# Demo
Users can scroll through the Pokédex, click on a Pokémon to view detailed information, and search for a Pokémon.
<p align=center>
<img src=https://user-images.githubusercontent.com/37652117/207526997-5a6453b6-b1ae-44aa-891f-c96864bf1d6f.gif width=311>
</p>

# Built using
* [PokeAPI](https://pokeapi.co/) - RESTful API interface to objects built from thousands of lines of data related to Pokémon
* [Retrofit](https://square.github.io/retrofit/) - Type-safe REST client which aims to make it easier to consume RESTful web services
* [Moshi](https://github.com/square/moshi) - JSON library for Android which helps to parse JSON into Java/Kotlin objects
* [RxJava2](https://github.com/ReactiveX/RxJava) - A library for composing asynchronous and event-based programs by using observable sequences
* RecyclerView - Used to efficiently display large sets of Pokémon
* ListAdapter - Used to provide new or modified data to the RecycelerView adapter and handles all the diff computations. Used for when we retrieve more Pokémon from the API and update the data set.
* Single Activity Architecture - This app uses only one Activity, utilizing multiple Fragments to display the UI.
* ViewBinding - Used to programatically interact with views
