package io.github.armijosbrandon.dev_route.view.area__movil;
import io.github.armijosbrandon.dev_route.view.BaseAreaLayout;
import io.github.armijosbrandon.dev_route.view.Utils;
import io.github.armijosbrandon.dev_route.view.area__movil.area_camino_inicial.AreaCaminoInicial;
import io.github.armijosbrandon.dev_route.view.area__movil.area_interfaces_modernas.AreaInterfacesModernas;
import io.github.armijosbrandon.dev_route.view.area__movil.area_multiplataforma.AreaMultiplataforma;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;

public class AreaMovil extends BaseAreaLayout{
	/**
	 * Representa la interfaz visual y lógica de un Área Tecnológica principal (Nivel 2) de Desarrollo Movil.
	 * <p>
	 * Esta clase sirve como contenedor intermedio que organiza un menú lateral izquierdo 
	 * con botones de especialidades y delega el despliegue de información detallada de 
	 * nivel 3 (hojas de ruta, herramientas, lenguajes) a la {@code pilaEspecialidades} heredada.
	 * </p>
	 * <p>
	 * <b>Nota de replicación:</b> Esta estructura de nombres, métodos de inicialización 
	 * y bindeo de eventos puede copiarse tal cual para implementar las áreas de Ciencia de Datos, 
	 * Desarrollo Móvil y Backend.
	 * </p>
	 * * @author Armijos Brandon
	 * @version 1.0
	 * @see BaseAreaLayout
	 */
	
	private VBox menuEspecialidadesLateral; // Menu Lateral Nivel 2 de Desarrollo Movil
	
    private final Button btnEspecialidad1;
    private final Button btnEspecialidad2;
    private final Button btnEspecialidad3;
    
    private final AreaCaminoInicial panelEspecialidad1;
    private final AreaInterfacesModernas panelEspecialidad2;
    private final AreaMultiplataforma panelEspecialidad3;
    
    
    
    
    
    public AreaMovil() {
    	// CAPA 2 - DESARROLLO WEB
    	btnEspecialidad1 = Utils.boton("1. Camino Inicial", "fas-map-signs","icono-generico");                 // Contiene: Camino Nativo Android (Kotlin) o Nativo iOS (Swift)
    	btnEspecialidad2 = Utils.boton("2. Desarrollo Moderno de Interfaces", "fas-palette","icono-generico"); // Contiene: Jetpack Compose (Android) y SwiftUI (iOS)
    	btnEspecialidad3 = Utils.boton("3. Alternativa Multiplataforma", "fas-sync-alt","icono-generico");     // Contiene: Flutter (Dart) y React Native (JS/TS)
        
        // SUBRAMAS CAPA 3 EN ADELANTE
        panelEspecialidad1= new AreaCaminoInicial();
        panelEspecialidad2= new AreaInterfacesModernas();
        panelEspecialidad3= new AreaMultiplataforma();
        
        buildUI();
        inicializarEventos();
        
    }
    
    /**
     * Constructor por defecto del área tecnológica.
     * <p>
     * Se encarga de instanciar los controles de navegación específicos del área, 
     * inicializar los paneles detallados del siguiente nivel jerárquico, ensamblar 
     * la interfaz gráfica y enlazar los flujos de eventos de ida y vuelta.
     * </p>
     */
	public void buildUI(){
    	// CAPA 2 - VBox Desarrollo web
    	menuEspecialidadesLateral = new VBox(10,btnEspecialidad1,btnEspecialidad2,btnEspecialidad3,botonVolver);
    	
    	//agrego al siguiente nivel las especialidades
    	this.pilaEspecialidades.getChildren().addAll(panelEspecialidad1, panelEspecialidad2, panelEspecialidad3);
    	
    	//ensamblo a la fila horizontal de la area web general, menu y especialidades
    	this.getChildren().addAll(menuEspecialidadesLateral,pilaEspecialidades);
    	
    	this.ocultarTodasLasEspecialidades();
    }
    
	/**
     * Construye y organiza la distribución espacial del área.
     * <p>
     * Acopla el menú de especialidades y el botón de regreso general junto al contenedor
     * dinámico de intercambio {@code pilaEspecialidades}. Al finalizar, oculta todos 
     * los paneles para asegurar un estado inicial limpio en la pantalla derecha.
     * </p>
     */
	private void inicializarEventos() {
    	Button[] botonesMenu= getBotonesEspecialidades();
		Pane[] panelesDetalle= getContenedoresSubramas();
		
		for (int i = 0; i < botonesMenu.length; i++) {
		    Button boton = botonesMenu[i];
		    Pane contenedor = panelesDetalle[i];
		    
		    boton.setOnAction(e->{
		    	mostrarNuevaSpecialidad(contenedor);
		    	if (onSolicitudDesplazamiento != null) {
		    	    onSolicitudDesplazamiento.accept(contenedor.getWidth()+menuEspecialidadesLateral.getSpacing());
		    	}
		    });
		}
		
		Button[] botonesVolverInternos = {
		        panelEspecialidad1.getBotonVolver(),
		        panelEspecialidad2.getBotonVolver(),
		        panelEspecialidad3.getBotonVolver()
		    };
		    
		for (Button btnVolver : botonesVolverInternos) {
	        btnVolver.setOnAction(e ->{
		        if (onSolicitudDesplazamiento != null) {
		    	    onSolicitudDesplazamiento.accept(0.0);
		    	}
		        PauseTransition pausa = new PauseTransition(Duration.millis(500));
		        pausa.setOnFinished(evento -> {
		            ocultarTodasLasEspecialidades();
		        });
		        pausa.play();
	        });  
	    }
		
	}
	
	/**
     * Agrupa y expone los controles interactivos del menú de especialidades en forma de arreglo.
     * Utilizado para simplificar y automatizar el recorrido de asignación de eventos.
     * * @return Un arreglo de objetos {@link Button} pertenecientes al menú del Nivel 2.
     */
    public Button[] getBotonesEspecialidades() {
		return new Button[] {btnEspecialidad1,btnEspecialidad2,btnEspecialidad3};
    	
    }
    
    /**
     * Agrupa y expone los paneles de información detallada en forma de arreglo.
     * Utilizado para simplificar y automatizar los cambios de estado en la interfaz de usuario.
     * * @return Un arreglo de objetos {@link Pane} correspondientes a los contenedores del Nivel 3.
     */
    public Pane[] getContenedoresSubramas() {
		return new Pane[] {panelEspecialidad1,panelEspecialidad2,panelEspecialidad3};
    	
    }
}	


