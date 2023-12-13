# TV Shows Explorer App

The TV Shows Explorer App is an Android application that provides users with a seamless experience to discover and explore TV shows. It adheres to Clean Architecture principles, employs the MVVM (Model-View-ViewModel) architectural pattern, and leverages the power of Jetpack Compose for building modern native Android UIs. The app incorporates local data storage using Room, asynchronous programming with Coroutines and Flow, and dependency injection with Dagger Hilt.

## Key Features

- Browse and search a diverse collection of TV shows.
- Access detailed information about TV shows, including titles, overviews, release dates, and ratings.
- Mark TV shows as favorites for quick access.
- View a curated list of favorite TV shows.

## Technologies and Libraries Utilized

- **Android Jetpack Compose:** A modern UI toolkit for crafting native Android user interfaces.
- **Kotlin Coroutines:** Facilitates asynchronous and concurrent programming for smoother user experiences.
- **Room:** Android's SQLite database library, supporting local data storage.
- **ViewModel:** An essential part of Android Architecture Components, managing UI-related data in a lifecycle-aware manner.
- **Retrofit:** A robust HTTP client library for handling network requests.
- **Gson:** A versatile JSON serialization/deserialization library for parsing API responses.
- **Dagger Hilt:** A dependency injection framework tailored for Android development.
- **Coil:** An image loading library for displaying TV show posters and images.
- **Kotlin DSL:** Gradle build scripts enhanced with Kotlin.

## Architecture Overview

The app strictly follows Clean Architecture principles, promoting modular and maintainable development. The architecture comprises the following layers:

- **Presentation:** Hosts Jetpack Compose UI components, ViewModels, and UI-related logic.
- **Domain:** Encompasses the business logic, defining the use cases of the application.
- **Framework:** Implements concrete data source implementations, such as network and local database interactions.
- **Data:** Manages data operations, including fetching data from the network and accessing local databases via Room.
- **Dependency Injection:** Utilizes Dagger Hilt for dependency injection, fostering modular and testable code.

## Setup and Deployment

To run the TV Shows Explorer App on your local environment, follow these straightforward steps:

1. Clone the repository: `git clone https://github.com/your_username/TVShowsExplorer.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Development Workflow

The development workflow adheres to the standard MVVM pattern and Clean Architecture principles. Here's a concise overview of the workflow:

1. Jetpack Compose is utilized to implement UI components, screens, and layouts.
2. ViewModel classes are crafted to manage UI-related data and business logic.
3. Domain layer use cases are implemented to address the specific business requirements of the app.
4. Network requests are executed with Retrofit, and the resulting data is converted to domain models using Gson.
5. Room is employed to store data locally, ensuring offline availability.
6. Dependency injection via Dagger Hilt facilitates the provision of necessary dependencies.
7. Rigorous unit tests and UI tests are crafted to validate the correctness and reliability of the app.