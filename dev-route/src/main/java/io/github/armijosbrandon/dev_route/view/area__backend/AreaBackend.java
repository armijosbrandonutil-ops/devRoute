package io.github.armijosbrandon.dev_route.view.area__backend;
import io.github.armijosbrandon.dev_route.view.BaseAreaLayout;
import io.github.armijosbrandon.dev_route.view.Utils;
import io.github.armijosbrandon.dev_route.view.area__backend.area_arquitectura_avanzada.AreaArquitecturaAvanzada;
import io.github.armijosbrandon.dev_route.view.area__backend.area_core_servidor.AreaCoreServidor;
import io.github.armijosbrandon.dev_route.view.area__backend.area_frameworks_persistencia.AreaFrameworksPersistencia;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;

public class AreaBackend extends BaseAreaLayout{
	/**
	 * Representa la interfaz visual y lógica de un Área Tecnológica principal (Nivel 2) de Backend.
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
	
	private VBox menuEspecialidadesLateral; // Menu Lateral Nivel 2 de Backend
	
    private final Button btnEspecialidad1;
    private final Button btnEspecialidad2;
    private final Button btnEspecialidad3;
    
    private final AreaCoreServidor panelEspecialidad1;
    private final AreaFrameworksPersistencia panelEspecialidad2;
    private final AreaArquitecturaAvanzada panelEspecialidad3;
    
    
    
    
    
    public AreaBackend() {
    	// CAPA 2 - DESARROLLO WEB
    	btnEspecialidad1 = Utils.boton("1. Lenguaje-Core del Servidor", "fas-microchip","icono-generico");     // Contiene: Elección de Lenguaje (Java, Python o C#)
    	btnEspecialidad2 = Utils.boton("2. Frameworks y Persistencia", "fas-layer-group","icono-generico");    // Contiene: Frameworks (Spring Boot/Django/.NET), Bases de Datos y ORM
    	btnEspecialidad3 = Utils.boton("3. Arquitectura Avanzada", "fas-project-diagram","icono-generico");     // Contiene: Seguridad (JWT/OAuth2) y Patrones de Diseño
        
        // SUBRAMAS CAPA 3 EN ADELANTE
        panelEspecialidad1= new AreaCoreServidor();
        panelEspecialidad2= new AreaFrameworksPersistencia();
        panelEspecialidad3= new AreaArquitecturaAvanzada();
        
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


