package io.github.armijosbrandon.dev_route;


import io.github.armijosbrandon.dev_route.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) {   
     
    	MainController controller = new MainController(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}

