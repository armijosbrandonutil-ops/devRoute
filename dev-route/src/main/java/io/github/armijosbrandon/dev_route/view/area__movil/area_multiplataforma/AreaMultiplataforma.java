package io.github.armijosbrandon.dev_route.view.area__movil.area_multiplataforma;

import io.github.armijosbrandon.dev_route.view.Utils;

import javafx.scene.layout.*;
import javafx.scene.text.Text;    	//Text permite controlar texto de forma avanzada y en diferentes formatos
import javafx.scene.text.TextFlow;  //TextFlow usa componentes Text para crear un parraro y otorgarles salto de linea inteligente
import javafx.scene.control.*;

/**
 * Representa la interfaz visual y lógica de un Panel de Contenido Detallado (Nivel 3).
 * <p>
 * Esta clase se encarga de estructurar y desplegar la hoja de ruta definitiva mediante
 * filas horizontales que emparejan un botón interactivo con un bloque de lectura responsivo.
 * Utiliza {@link TextFlow} y {@link ScrollPane} para ofrecer un renderizado de texto 
 * enriquecido con saltos de línea inteligentes y soporte para desbordamientos.
 * </p>
 * <p>
 * <b>Nota de replicación:</b> Esta estructura de variables genéricas, métodos constructores
 * de texto y formateadores es completamente reutilizable para cualquier otra especialidad final
 * (como Frontend Básico, Frameworks, Fundamentos de Python, etc.).
 * </p>
 * * @author Armijos Brandon
 * @version 1.0
 * @see TextFlow
 * @see ScrollPane
 */
public class AreaMultiplataforma extends VBox{
	
	private final Button btnTecnologia1;
    private final Button btnTecnologia2;
    
    private final ScrollPane scrollInfo1;
    private final ScrollPane scrollInfo2;
    
    private final Button botonVolver;
    
    /**
     * Constructor por defecto del panel de contenido detallado.
     * <p>
     * Se encarga de instanciar los componentes interactivos, disparar los métodos
     * de creación de texto enriquecido, estructurar el layout global y definir espacios visuales.
     * </p>
     */
    public AreaMultiplataforma() {
    	
    	this.setSpacing(15);
    	
    	//Inicializacion de los botones
    	btnTecnologia1 = Utils.boton("1. Flutter", "fas-cloud","icono-generico","logo-cloud ");     
    	btnTecnologia2 = Utils.boton("2. React Native", "fab-react","icono-generico","logo-react");       
       	
    	// Inicializacion del contenido explicativo detallado
    	scrollInfo1 = crearContenidoInformativo(
		    "Flutter\n",
		    "• Qué aprender: Programación orientada a objetos en Dart, arquitectura de widgets (Stateless y Stateful Widgets), maquetación adaptativa multiplataforma, inyección de dependencias y renderizado de alto rendimiento.\n• Herramientas clave: Flutter SDK y lenguaje Dart."
		);
		scrollInfo2 = crearContenidoInformativo(
		    "React Native\n",
		    "• Qué aprender: Creación de aplicaciones móviles utilizando JavaScript o TypeScript, comunicación interactiva a través de componentes nativos del sistema mediante puentes de comunicación (bridges), y despliegue rápido.\n• Herramientas clave: Expo CLI o React Native CLI."
		);
         	
		botonVolver= Utils.boton("Regresar", "fas-arrow-circle-left","icono-generico");
    			
        buildUi();
        
    }
    
    /**
     * Construye la estructura visual acoplando los botones y bloques de texto en contenedores horizontales.
     * <p>
     * Utiliza políticas de crecimiento horizontal (HGrow) para forzar a los bloques de texto
     * a ocupar todo el espacio remanente en la pantalla de forma elástica.
     * </p>
     */
	private void buildUi() {
		//construccion de filas con tecnologia y lo que se necesita aprender
		HBox contenedorSeccion1= new HBox(10,btnTecnologia1,scrollInfo1);
		HBox contenedorSeccion2= new HBox(10,btnTecnologia2,scrollInfo2);
		
		HBox.setHgrow(scrollInfo1, Priority.ALWAYS);//expandase Horizontalmente con el contenedor
        HBox.setHgrow(scrollInfo2, Priority.ALWAYS);
		
		this.getChildren().addAll(contenedorSeccion1,contenedorSeccion2,botonVolver);
	}
	
	/**
     * Construye, estiliza y empaqueta un bloque de texto enriquecido dentro de un ScrollPane adaptable.
     * <p>
     * Este método centraliza toda la lógica de diseño: aplica fuentes, activa el ajuste de 
     * ancho elástico para el salto de línea inteligente y remueve los bordes nativos de JavaFX.
     * </p>
     * @param titulo Cadena de texto que actuará como encabezado de la sección.
     * @param cuerpo Cadena de texto detallada con las viñetas y descripciones de la ruta.
     * @return Un control {@link ScrollPane} transparente listo para añadirse al layout.
     */
    private ScrollPane crearContenidoInformativo(String titulo, String cuerpo) {
    	

        TextFlow textFlow = new TextFlow();
        textFlow.setPrefWidth(400);

        Text tituloInfo = new Text(titulo);
        tituloInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");
        Text cuerpoInfo = new Text(cuerpo);

        textFlow.getChildren().addAll(tituloInfo, cuerpoInfo);
        return formatearScroll(textFlow);
    }

    
    private ScrollPane formatearScroll(TextFlow contenido) {
        ScrollPane scroll = new ScrollPane(contenido);
        scroll.setFitToWidth(true); //ajusta tu contenido a tu tamaño
        return scroll;
    }
    
    /**
     * Proporciona acceso público al botón de regreso para permitir que el menú del área 
     * capture su evento de clic de forma automatizada.
     * @return El control {@link Button} encargado de accionar el regreso al nivel superior.
     */
	public Button getBotonVolver() {
		return botonVolver;
	}
	
}
