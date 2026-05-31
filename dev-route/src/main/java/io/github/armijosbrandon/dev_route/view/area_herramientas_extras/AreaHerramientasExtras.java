package io.github.armijosbrandon.dev_route.view.area_herramientas_extras;

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
public class AreaHerramientasExtras extends VBox{
	
	private final Button btnTecnologia1;
    private final Button btnTecnologia2;
    private final Button btnTecnologia3;
    private final Button btnTecnologia4;

    
    private final ScrollPane scrollInfo1;
    private final ScrollPane scrollInfo2;
    private final ScrollPane scrollInfo3;
    private final ScrollPane scrollInfo4;
    
    private final Button botonVolver;
    
    /**
     * Constructor por defecto del panel de contenido detallado.
     * <p>
     * Se encarga de instanciar los componentes interactivos, disparar los métodos
     * de creación de texto enriquecido, estructurar el layout global y definir espacios visuales.
     * </p>
     */
    public AreaHerramientasExtras() {
    	
    	this.setSpacing(15);
    	
    	//Inicializacion de los botones
    	btnTecnologia1 = Utils.boton("1. GitHub", "fab-github","icono-generico","icono-generico","logo-github");     
    	btnTecnologia2 = Utils.boton("2. Docker", "fab-docker","icono-generico","icono-generico","logo-docker");       
    	btnTecnologia3 = Utils.boton("3. APIs REST", "fas-plug","icono-generico","icono-generico");    
    	btnTecnologia4 = Utils.boton("4. Cloud Computing ", "fas-cloud","icono-generico","icono-generico","logo-cloud");

    	
    	// Inicializacion del contenido explicativo detallado
    	scrollInfo1 = crearContenidoInformativo(
    		    "Git / GitHub\n",
    		    "• Qué aprender: Control de versiones local (commits, log, checkout),ramificación (branches), resolución limpia de conflictos de código,peticiones de integración (Pull Requests) y publicación de proyectos en la nube.\n• Herramientas clave: Git CLI y GitHub."
		);
    	scrollInfo2 = crearContenidoInformativo(
		    "Docker\n",
		    "• Qué aprender: Creación de recetas de configuración de contenedores (Dockerfiles), descarga de imágenes de sistemas operativos y bases de datos desde Docker Hub, exposición de puertos de red y orquestación con Docker Compose.\n• Herramientas clave: Docker Desktop y Docker CLI."
		);
		scrollInfo3 = crearContenidoInformativo(
		    "APIs REST\n",
		    "• Qué aprender: Principios del protocolo HTTP, uso semántico de métodos (GET, POST, PUT, DELETE), códigos de estado de respuesta del servidor (200, 400, 404, 500) y formateo universal de intercambio de datos JSON.\n• Herramientas clave: Postman o Insomnia."
		);
		scrollInfo4 = crearContenidoInformativo(
		    "Cloud Computing\n",
		    "• Qué aprender: Conceptos básicos de infraestructura en la nube (IaaS, PaaS, Serverless), despliegue automatizado de aplicaciones backend y frontend, y configuración elemental de variables de entorno seguras.\n• Herramientas clave: AWS, Vercel, Firebase o Render."
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
		HBox contenedorSeccion3= new HBox(10,btnTecnologia3,scrollInfo3);
		HBox contenedorSeccion4= new HBox(10,btnTecnologia4,scrollInfo4);
		
		HBox.setHgrow(scrollInfo1, Priority.ALWAYS);//expandase Horizontalmente con el contenedor
        HBox.setHgrow(scrollInfo2, Priority.ALWAYS);
        HBox.setHgrow(scrollInfo3, Priority.ALWAYS);
        HBox.setHgrow(scrollInfo4, Priority.ALWAYS);
		
		this.getChildren().addAll(contenedorSeccion1,contenedorSeccion2,contenedorSeccion3,contenedorSeccion4,botonVolver);
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

        scroll.setMaxWidth(400);
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
