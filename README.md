# WeatherApp

The Weather App consists of two screens - Main View that displays the current date, time, weather and high and low temperatures, and Detail View that displays an hourly forecast. The app utilizes the Dark Sky API to display the weather forecast.




### Architecture - Model View Presenter
I decided to use MVP for the following reasons -
* It has a cleaner architecture and helps in creating a clear separation of concerns between all the layers as opposed to MVC in which the Model View and the Controller are closely coupled. 
* In MVC, the Activity is closely coupled to both the UI and the data access mechanisms. MVP overcomes this problem with the help of a Presenter which is nothing but an Interface and the Model and the View communicate only through this interface. So any change in the backend would not require as much refactoring as would be required in MVC thus increasing the scalability. 
* Also it is easy to read and maintain the code and write unit tests thus making the app more robust.



### Brief overview of the files

**MainActivity/DetailActivity** acts as the View which is responsible for making changes to the UI. The MainActivity implements the MainView interface that holds all the method declarations that acts as a contract. 

**MainPresenter/DetailPresenter** is the Presenter that holds all the business logic and passes data between View and the Model.

**DataManager** acts as the Model in this case. It is responsible for fetching the data from the server or a local database and notifying the presenter.

**PreferencesManager** holds an instance of the SharedPreferences that is shared throughout the app and saves the Weather Forecast object and user's current location 

### OpenSource Libraries Used

**Retrofit** for making network calls

**RxJava2** for enabling reactive programming

**ButterKnife** to create the boilerplate necessary to wire up views and events in the activity.

**GSON** to convert Java objects into JSON and vice versa




### Notes

**Error View**

I have added an error view card to display an error message incase of any network failure. I feel it is very important to show useful error messages to the user in case of any failure. Right now the app only shows a static error message for all the different scenarios. But if given more time I would handle each case separately with a useful error message. 

**Error View Click**

Clicking the error view will re-try the network access so the user doesn’t remain stuck on the same screen. As a side note, I can also ask check if the WiFi is active before making the network request and if not, display the Forecast from the SharedPreferences instance from the PreferencesManager. But I chose to not do that as I wanted to make sure I have the right location.

**Utils Package - Constants.java and TimeUtils.java**

Added Constants.java file to keep all the final static constant strings in one file that could be used at multiple places. Similarly added TimeUtils.java class for time formatting.

**Loading Icon**

Added progress bar to display the loading circle for better UX so the user knows the data is still loading 

**String resources file**

Moved all the strings to the string resources file which is useful for localization and also if you have to use the same string at multiple places. It makes the app look cleaner and easier to maintain.

**Completed bonus feature**
Filtering out items before current timestamp on the Detail screen. The times that have already passed have a dark blue background where as the other ones have a light blue background







