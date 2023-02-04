# Bananagrams

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Cloning](#cloning)

## General info
This desktop application will allow a user to play a mimic of the Bananagrams word game.
#### Rules for playing: [https://bananagrams.com/blogs/news/how-to-play-bananagrams-instructions-for-getting-started](https://bananagrams.com/blogs/news/how-to-play-bananagrams-instructions-for-getting-started)
	
## Technologies
Project created with:
* Java
* JavaFX

## Cloning
1. Clone the repository
2. [Download](https://gluonhq.com/products/javafx) the JavaFX SDK onto your machine and take note of the file path
3. Include the following in the VM arguments:
``` 
--module-path "PATH/TO/YOUR/JAVAFX/LIB/FOLDER" --add-modules javafx.controls,javafx.fxml
```
&nbsp;&nbsp;&nbsp;&nbsp;  -If you see "Error: JavaFX runtime components are missing, and are required to run this application", it means that you didn't include \
&nbsp;&nbsp;&nbsp;&nbsp; these VM arguments properly.\
&nbsp;&nbsp;&nbsp;&nbsp;  -If you see a version error, it means you are running an outdated JDK version.\
4. Everything should be up and running!

## Sample Images

#### Starting a game
<img width="1440" alt="StartingGame" src="https://user-images.githubusercontent.com/90576219/216736305-b9825cf8-399c-4dca-8c92-3132e876ab47.png">

#### Playing a game
<img width="1440" alt="PlayingGame" src="https://user-images.githubusercontent.com/90576219/216736315-ab60712f-54eb-460b-8711-5501585b3071.png">

#### Finishing a game
<img width="1440" alt="FinishedGame" src="https://user-images.githubusercontent.com/90576219/216736807-1574f41a-c320-4b0d-a8b3-6831c9e04d2b.png">

