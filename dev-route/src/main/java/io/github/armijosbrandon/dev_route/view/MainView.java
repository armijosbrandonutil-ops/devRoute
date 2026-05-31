package io.github.armijosbrandon.dev_route.view;



import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;

import io.github.armijosbrandon.dev_route.view.Utils;
import io.github.armijosbrandon.dev_route.view.area__backend.AreaBackend;
import io.github.armijosbrandon.dev_route.view.area__movil.AreaMovil;
import io.github.armijosbrandon.dev_route.view.area_ciencia_datos.AreaCienciaDatos;
import io.github.armijosbrandon.dev_route.view.area_herramientas_extras.AreaHerramientasExtras;
import io.github.armijosbrandon.dev_route.view.area_web.AreaWeb;

/**
 * Vista de navegación coordinadora principal de la aplicación.
 * <p>
 * Se encarga de construir la estructura macro de la interfaz de usuario, dividiendo la
 * pantalla en un menú lateral de selección (Nivel 1) y un contenedor dinámico en pila 
 * que reacciona e intercambia los detalles de las áreas seleccionadas (Nivel 2).
 * </p>
 * * @author Armijos Brandon
 * @version 1.0
 * @see BaseAreaLayout
 */

public class MainView {

    private ScrollPane root; // contenedor principal que contiene todo
    
    private  HBox contenedorGeneral; //Contenedor de todo el arbol
    private  VBox menuAreasLateral; //Contenedor de la primera rama PADRE
    private  StackPane contenedorDetalles; //Contiene toda las subramas y el cuerpo del arbol
    
    //SUBRAMAS
    private AreaWeb areaWeb;
    private AreaCienciaDatos areaCienciaDatos;
    private AreaMovil areaMovil;
    private AreaBackend areaBackend;
    private  AreaHerramientasExtras areaHerramientasExtras;
    
    //Botones Lateral Izquierdo
    private final Button btnAreaWeb;
    private final Button btnAreaCienciaDatos;
    private final Button btnAreaMovil;
    private final Button btnAreaBackend;
    private final Button btnAreaHerramientasExtras;
    
    /** Región utilizada para el centrado dinámico del menú lateral. */
    private Region espaciadorCentro = new Region();
    
    /**
     * Constructor por defecto de la clase.
     * <p>
     * Se encarga de inicializar el contenedor raíz, instanciar los componentes visuales
     * de los botones y las vistas de especialidades, para luego disparar secuencialmente
     * la construcción de la UI y el bindeo de eventos.
     * </p>
     */
    public MainView(){
        root = new ScrollPane();
        
        root.setPannable(false);//permite scroll?
        
        //Botones de capa 1 inicializacion
        btnAreaWeb = Utils.boton("DESARROLLO WEB", "fas-laptop-code","icono-generico");
        btnAreaCienciaDatos = Utils.boton("CIENCIA DE DATOS", "fas-database","icono-generico");
        btnAreaMovil = Utils.boton("DESARROLLO MÓVIL", "fas-mobile-alt","icono-generico");
        btnAreaBackend = Utils.boton("DESARROLLO BACKEND", "fas-server","icono-generico");
        
        btnAreaHerramientasExtras = Utils.boton("HERRAMIENTAS INDUSTRIALES", "fas-toolbox","icono-generico");
        
        //inicializacion de vistas de area
        areaWeb = new AreaWeb();
        areaCienciaDatos = new AreaCienciaDatos();
        areaMovil = new AreaMovil();
        areaBackend = new AreaBackend();
        areaHerramientasExtras= new AreaHerramientasExtras();
        
        buildUI();
        inicializarEventos();
      
    }
    
    /**
     * Organiza, acopla y ensambla las jerarquías de componentes en el contenedor global.
     * <p>
     * Define el estado inicial seguro de la aplicación limpiando los paneles de la
     * pantalla derecha para que no inicien sobrepuestos.
     * </p>
     */
    private void buildUI() {
		contenedorGeneral= new HBox();
		contenedorGeneral.setSpacing(30);
		contenedorGeneral.setAlignment(Pos.CENTER_LEFT);//asignado vertical y horizontal
		
		
		//Menu Izquierdo
		menuAreasLateral = new VBox(30,btnAreaWeb,btnAreaCienciaDatos,btnAreaMovil,btnAreaBackend,btnAreaHerramientasExtras);
				
		//Estructuracion de las subramas y sus areas, stack pane apila en capas las vistas
		contenedorDetalles= new StackPane(areaWeb, areaCienciaDatos, areaMovil, areaBackend, areaHerramientasExtras);
		
		//Ocupen el espacio  vertical de sus componentes no mas
		menuAreasLateral.setMaxHeight(VBox.USE_PREF_SIZE);
		contenedorDetalles.setMaxHeight(VBox.USE_PREF_SIZE);
		
		// Espacio vacio para empujar el inicio del arbol
	    espaciadorCentro = new Region();
	    espaciadorCentro.prefWidthProperty().bind(		//ancho preferido
	    	    root.widthProperty() 					//ancho del root
	    	    .divide(2)								// dividelo para 2
	    	    .subtract(menuAreasLateral.widthProperty().divide(2))//resta una mitad del ancho deel menu lateral para centrarlo
	    );
	    //usa el ancho y preferido establecido, no crescas mas
	    espaciadorCentro.setMinWidth(Region.USE_PREF_SIZE);
	    espaciadorCentro.setMaxWidth(Region.USE_PREF_SIZE);
	    
		
		//Estructura general
		contenedorGeneral.getChildren().addAll(espaciadorCentro,menuAreasLateral, contenedorDetalles);
		contenedorGeneral.widthProperty().addListener((obs, oldVal, newVal) -> { //cuando haiga un cambio de ancho, cuando se calcule el ancho
            if (newVal.doubleValue() > 0) {
                root.setHvalue(0.0); // desplazate al punto inicial 0,0
            }
        });
		
		root.setContent(contenedorGeneral);
		root.setFitToHeight(true);//ocupamos todo el alto del stage
		
		limpiarPantallaDetalles();
    }
    
    /**
     * Inicializa y bindea los manejadores de eventos (ActionListeners) de la aplicación.
     * <p>
     * Configura tanto las acciones de entrada (ir del menú de nivel 1 a los detalles de nivel 2)
     * como las acciones de salida, mapeando polimórficamente los botones "Volver" de cualquier
     * hijo que implemente {@link BaseAreaLayout}.
     * </p>
     */
    private void inicializarEventos() {
    	
    	
    	// Navegacion Nivel 1->Nivel 2
    	btnAreaWeb.setOnAction(e->{
			mostrarAreaDetallada(areaWeb);
			scrollHacia(calcularEscalaDestino());
			areaWeb.setOnSolicitudDesplazamiento(extra -> scrollHacia(calcularEscalaDestino(extra)));
		});
    	btnAreaCienciaDatos.setOnAction(e->{
			mostrarAreaDetallada(areaCienciaDatos);
			scrollHacia(calcularEscalaDestino());
			areaCienciaDatos.setOnSolicitudDesplazamiento(extra -> scrollHacia(calcularEscalaDestino(extra)));
		});
    	btnAreaMovil.setOnAction(e->{
			mostrarAreaDetallada(areaMovil);
			scrollHacia(calcularEscalaDestino());
			areaMovil.setOnSolicitudDesplazamiento(desplazamientoAdicionalArea -> scrollHacia(calcularEscalaDestino(desplazamientoAdicionalArea)));
		});
    	btnAreaBackend.setOnAction(e->{
			mostrarAreaDetallada(areaBackend);
			scrollHacia(calcularEscalaDestino());
			areaBackend.setOnSolicitudDesplazamiento(extra -> scrollHacia(calcularEscalaDestino(extra)));
		});
    	btnAreaHerramientasExtras.setOnAction(e->{
			mostrarAreaDetallada(areaHerramientasExtras);
			scrollHacia(calcularEscalaDestino(menuAreasLateral.getWidth()+contenedorGeneral.getSpacing()));
		});
    	
    	// Salida Nivel 2-> Nivel 1
    	for (Node nodo : contenedorDetalles.getChildren()) {
            // Verificamos que heredan de BaseAreaLayout 
            if (nodo instanceof BaseAreaLayout) {
                BaseAreaLayout subrama = (BaseAreaLayout) nodo; // casteamos
                // Le asignamos la acción a su botón volver
                subrama.botonVolver.setOnAction(e ->{
                	limpiarPantallaDetalles();
                	scrollHacia(0.0);
                });      
            }
        }
    	
    	// no hereda de  BaseAreaLayout por lo que  configuramos manual
    	areaHerramientasExtras.getBotonVolver().setOnAction(e ->{
        	
        	scrollHacia(0.0);
        	PauseTransition pausa = new PauseTransition(Duration.millis(500));
	        pausa.setOnFinished(evento -> {
	        	limpiarPantallaDetalles();
	        });
        });
	}
    
    /**
     * Apaga y oculta absolutamente todos los nodos secundarios alojados en el panel
     * dinámico de detalles. Restablece la UI ocultando los flujos del layout.
     */
    private void limpiarPantallaDetalles() {
    	// Recorremos todos los hijos del StackPane para apagarlos
        for (javafx.scene.Node nodo : contenedorDetalles.getChildren()) {
            nodo.setVisible(false);
        }
    }
    
    /**
     * Oculta cualquier subárea activa en la pila lateral derecha y enciende 
     * de forma exclusiva la sección de destino seleccionada.
     * * @param areaSeleccionada El contenedor {@link Pane} correspondiente a la ruta 
     * tecnológica que el usuario desea explorar.
     */
    private void mostrarAreaDetallada(Pane areaSeleccionada) {
    	limpiarPantallaDetalles();
        
        // Encendemos solo el contenedor que nos interesa
        if (areaSeleccionada != null) {
            areaSeleccionada.setVisible(true);
        }
    }
    
    /**
     * Realiza una transición animada hacia una posición horizontal específica del ScrollPane.
     * @param valorH La posición objetivo en el eje horizontal (0.0 a 1.0).
     */
    private void scrollHacia(double valorH) {

        Timeline desplazamientoAnimacion= new Timeline();// aplica transicion en algunos metodos de desplazamiento
    	KeyValue desplazarA = new KeyValue(root.hvalueProperty(), valorH); //que valor cambiar y al cual cambiar
    	KeyFrame fotograma = new KeyFrame(Duration.millis(500), desplazarA); //duracion de ese cambio de valor
    	
    	desplazamientoAnimacion.getKeyFrames().add(fotograma);//añado a la animacion
    	desplazamientoAnimacion.play();
    }
    
    /**
     * Calcula la escala de desplazamiento para centrar el área deseada.
     * @return El valor {@code hvalue} calculado.
     */
    private double calcularEscalaDestino() {
    	return calcularEscalaDestino(0.0);
    }
    
    /**
     * Sobrecarga de cálculo de escala permitiendo añadir un margen adicional.
     * @param ancho_adicional Valor de píxeles extra a considerar en el cálculo.
     * @return El valor {@code hvalue} calculado para el desplazamiento.
     */
    private double calcularEscalaDestino(double ancho_adicional) {
        double anchoComponente = espaciadorCentro.getWidth() + contenedorGeneral.getSpacing()+ancho_adicional;//espacio del separador
        double anchoContenidoTotal = root.getContent().getBoundsInLocal().getWidth();//el ancho de todo el contenedor con todo su contenido
        double anchoVisibleScrollPane = root.getViewportBounds().getWidth(); //area visible del scroll
        double maximoPixelesRecorribles = anchoContenidoTotal - anchoVisibleScrollPane; // cuantos pixeles se peude desplazar desde lo ya ocupado

        return anchoComponente / maximoPixelesRecorribles; // cuanto equivale nuestro compoentne en las escalas dedesplazamiento, valores de 0.0 a 1.0
        
        
    }
    
    /**
     * Obtiene el contenedor raíz administrado por esta vista principal para ser
     * inyectado directamente en la escena (Scene) del Stage inicial de JavaFX.
     * * @return El nodo contenedor raíz casteado como {@link Parent}.
    */
    public Parent getRoot() {
        return root;
    }

}

