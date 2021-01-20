# ITunesSearch
ITunesSearch is a small application based on modern android development and MVVM architecture.

This project focuses on the use of Coroutines to asynchronously fetch data using ITunes Search API and Retrofit.

The latest results will be stored in a local database using Room. Upon app relaunch, the data from the last search result will be retained along with the date and time when the search was executed.

# Tech Stack
* **Kotlin** language
* **Coroutines** for asynchronous
* **Hilt** for dependency injection
* **MVVM** architecture
  * **ViewModel** to hold data, lifecycle aware
  * **LiveData** to notify data change to view
* **Repository** pattern
  * **Retrofit** for remote data source, constructing RestAPI
  * **Room** for local data source, persistent data, updating LiveData support
* **Picasso** for loading images/thumbnail with support for placeholder and error
* **Timber** for logging
* **Navigation** and **Safe Args** for going from list to detail view and passing the data between destinations (in this case the music object)
* **Views**
  * **RecyclerView** for results list
  * **SearchView** for search placeholder
  
# Behind The Scenes
1. Upon first use of App, nothing is displayed yet because there is no data in the database.
2. Once user searches for a term, Retrofit does the query using ITunesAPI and gets the result.
3. The result contains the list of music object (List\<Music\>) which is then saved in the database using Room. (The DB is cleared first before saving new entries)
4. Room returns the LiveData (which it generates whenever the DB is updated, so convenient right?) and this is displayed in the RecyclerView.
5. When clicking an entry in the RecyclerView, the view goes from music list fragment to music details fragment, and the music object is passed in the viewModel (since it survives configuration changes) using navigation and safeargs.
6. The passed data is then used to populate the textViews in the Details screen. There is no need to do a query in the database at this point because the whole object is passed.
7. Clicking the artist, track name, and collection will redirect the user to a browser showing the details.
8. Upon exit and relaunch of the app, the last search items are saved in the database and is populated in the recyclerView along with the date and time of the last search result.
