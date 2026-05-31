package io.github.armijosbrandon.dev_route.view;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;

public class Utils {

	public static Button boton(String texto, String icon_name, String... estilos) { //metodo Helper para crear botones con misma logica y diseño
	    Button b = new Button(texto);
	    FontIcon icon=new FontIcon(icon_name);
	    b.setGraphic(icon);
	    b.setContentDisplay(ContentDisplay.TOP);
	    
	    //estilos de icono
	    if (estilos != null) {
	        icon.getStyleClass().addAll(estilos);
	    }
	   	return b;
	}
}

