# ImageFavoriteKeeper
A lightweight Android repository that handles favorite images with the SQLite database as the backend. It provides functionality to add or remove images from favorites, which is smoothly reflected in a RecyclerView. It will be ideal for developers looking to handle image favorites in their apps efficiently, using strong local storage.
## Features

- Add images to favorites.
- Remove images from favorites.
- Display favorite images in a RecyclerView.
- Persistent storage of favorite images using SQLite.
- Simple and efficient database operations.

## Requirements

- Android Studio Arctic Fox or later.
- Minimum SDK: 21 (Android 5.0 Lollipop).
- SQLite (pre-installed in Android).

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ImageFavKeeper.git
   ```

2. Open the project in Android Studio.

3. Sync Gradle to install dependencies.

4. Build and run the project on an emulator or a physical device.

## Usage

### Adding an Image to Favorites
When a user selects an image, it can be added to the favorites list. This action saves the image details in the SQLite database.

### Removing an Image from Favorites
Users can remove an image from favorites with a single click, which deletes the image record from the SQLite database.

### Viewing Favorite Images
Favorite images are displayed in a RecyclerView, dynamically updated as items are added or removed.
