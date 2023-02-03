# Bananagrams

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Cloning](#cloning)

## General info
#### Rules for playing: [https://bananagrams.com/blogs/news/how-to-play-bananagrams-instructions-for-getting-started](https://bananagrams.com/blogs/news/how-to-play-bananagrams-instructions-for-getting-started)
This desktop application will allow a user to play a mimic of the Bananagrams word game.
	
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
