package io.github.armijosbrandon.dev_route.controller;


import io.github.armijosbrandon.dev_route.view.MainView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**
 * MainController is responsible for initializing the primary
 * view of the application and configuring the main Stage.
 * 
 * It acts as the entry point of the presentation layer
 * after the JavaFX runtime bootstrap.
 */
public class MainController {
    private MainView view;
   

    public MainController(Stage stage) {
        this.view = new MainView();
   
        Scene scene = new Scene(view.getRoot(), 800, 600);
        //estilos
        String cssPath = getClass().getResource("/estilos.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        
        //establecer scene principal
        stage.setScene(scene);
        stage.setTitle("DevRoute");
        // bloquea el agrandamiento o redimensionamiento de la ventana
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/app_icon.png")));
        //arranque de gui
        stage.show();

    }


}
