package io.github.armijosbrandon.dev_route.view.area_web;
import io.github.armijosbrandon.dev_route.view.BaseAreaLayout;
import io.github.armijosbrandon.dev_route.view.Utils;
import io.github.armijosbrandon.dev_route.view.area_web.area_frameworks.AreaFrameworks;
import io.github.armijosbrandon.dev_route.view.area_web.area_fronted_basico.AreaFrontedBasico;
import io.github.armijosbrandon.dev_route.view.area_web.area_tooling.AreaTooling;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;

public class AreaWeb extends BaseAreaLayout{
	/**
	 * Representa la interfaz visual y lógica de un Área Tecnológica principal (Nivel 2).
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
	
	private VBox menuEspecialidadesLateral; // Menu Lateral Nivel 2 de Web
	
    private final Button btnEspecialidad1;
    private final Button btnEspecialidad2;
    private final Button btnEspecialidad3;
    
    private final AreaFrontedBasico panelEspecialidad1;
    private final AreaFrameworks panelEspecialidad2;
    private final AreaTooling panelEspecialidad3;
    
    
    
    
    
    public AreaWeb() {
    	// CAPA 2 - DESARROLLO WEB
        btnEspecialidad1 = Utils.boton("1. Cimientos - Frontend Básico", "fas-code","icono-generico");              // Contiene: HTML5, CSS3, JavaScript básico y manipulación del DOM
        btnEspecialidad2 = Utils.boton("2. Evolucion- Frameworks y Escabilidad", "fab-react","icono-generico");        // Contiene: React.js / Vue.js / Angular, TypeScript y manejo de estados
        btnEspecialidad3 = Utils.boton("3. Manejo de Entorno- Tooling & Construcción", "fas-tools","icono-generico");     // Contiene: Empaquetadores (Vite, Webpack) y Testing básico (Jest, Vitest)
        
        // SUBRAMAS CAPA 3 EN ADELANTE
        panelEspecialidad1= new AreaFrontedBasico();
        panelEspecialidad2= new AreaFrameworks();
        panelEspecialidad3= new AreaTooling();
        
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
    	menuEspecialidadesLateral = new VBox(btnEspecialidad1,btnEspecialidad2,btnEspecialidad3,botonVolver);
    	
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


