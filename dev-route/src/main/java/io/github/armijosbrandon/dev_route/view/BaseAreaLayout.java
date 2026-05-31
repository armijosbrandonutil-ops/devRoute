package io.github.armijosbrandon.dev_route.view;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Clase abstracta que sirve como plantilla base para el diseño visual de cada
 * Área Tecnológica (Desarrollo Web, Ciencia de Datos, Móvil, Backend).
 * <p>
 * Hereda de {@link HBox} para organizar los componentes en formato de filas y columnas.
 * Proporciona de forma centralizada un contenedor para apilar las especialidades (Capa 3)
 * y la lógica para alternar su visibilidad mediante navegación interna.
 * </p>
 * * @author Armijos Brandon
 * @version 1.0
 */
public abstract class BaseAreaLayout extends HBox {
	protected final StackPane pilaEspecialidades = new StackPane(); //donde se ubicara elementos de la siguiente capa
	protected Button botonVolver= Utils.boton("Regresar", "fas-arrow-circle-left","icono-generico"); // boton para volver al anterior area
	
	/**
     * Callback de delegación que se dispara cuando este componente solicita un cambio 
     * en el scroll horizontal del contenedor padre.
     * <p>
     * El parámetro {@link Double} que recibe representa el desplazamiento adicional 
     * en píxeles que la subrama requiere para encuadrar su contenido correctamente.
     * </p>
     */
    protected Consumer<Double> onSolicitudDesplazamiento;

    /**
     * Registra un manejador de eventos (Callback) para interceptar y procesar las peticiones
     * de desplazamiento horizontal emitidas por esta subrama.
     * <p>
     * Permite acoplar la navegación interna de este componente con el sistema de scroll 
     * del contenedor macro (MainView) de forma asíncrona y desacoplada.
     * </p>
     *
     * @param manejador Acción lambda que procesará el desplazamiento. Debe aceptar 
     *                  un {@link Double} con los píxeles excedentes del layout.
     */
    public void setOnSolicitudDesplazamiento(Consumer<Double> manejador) {
        this.onSolicitudDesplazamiento = manejador;
    }
	
	/**
     * Recorre todos los nodos hijos contenidos en la {@code pilaEspecialidades} 
     * para ocultarlos del flujo visual de la interfaz. 
     * <p>
     * Modifica tanto la propiedad {@code setVisible(false)} como {@code setManaged(false)} 
     * para liberar espacio real en el layout de JavaFX.
     * </p>
     */
	public void ocultarTodasLasEspecialidades() {
        for (Node nodo : pilaEspecialidades.getChildren()) {
            nodo.setVisible(false);
        }
    }
	/**
     * Gestiona el cambio dinámico de visualización dentro de la misma subrama,
     * apagando los paneles activos y encendiendo únicamente el destino seleccionado.
     * * @param panelDestino El nodo visual (Pane) correspondiente a la especialidad 
     * que se desea mostrar en pantalla.
     */
    public void mostrarNuevaSpecialidad(Node panelDestino) {
    	ocultarTodasLasEspecialidades();
        if (panelDestino != null) {
            panelDestino.setVisible(true);
        }
    }
    
    /**
     * Proporciona acceso público al botón de regreso para permitir que contenedores
     * externos de mayor jerarquía intercepten y vinculen su comportamiento.
     * * @return El control {@link Button} encargado de accionar el regreso en la navegación.
     */
    public Button getBotonVolver() {
    	botonVolver.getStyleClass().add("boton-volver");
		return botonVolver;
	}


}