# DevRoute: Ruta de Aprendizaje en Desarrollo de Software

DevRoute es una aplicación de escritorio interactiva desarrollada en JavaFX. Funciona como un mapa de ruta visual y dinámico diseñado para guiar a estudiantes y entusiastas en su formación profesional dentro de la ingeniería de software.

## Vista General
| Menú Principal | Herramientas Industriales |
| :---: | :---: |
| <img src="./assets/vistas-generales/menu-principal.png" width="400"> | <img src="assets/vistas-generales/herramientas-industriales.png" width="400"> |

## Galerías de Rutas

### Desarrollo Web
| Menú Web | Sub-Rama A | Sub-Rama B | Sub-Rama C |
| :---: | :---: | :---: | :---: |
| <img src="assets/rama-desarrollo-web/web-menu-principal.png" width="200"> | <img src="assets/rama-desarrollo-web/web-subrama-a-cimiento.png" width="200"> | <img src="assets/rama-desarrollo-web/web-subrama-b-evolucion.png" width="200"> | <img src="assets/rama-desarrollo-web/web-subrama-c-entorno.png" width="200"> |

### Desarrollo Backend
| Menú Backend | Sub-Rama A | Sub-Rama B | Sub-Rama C |
| :---: | :---: | :---: | :---: |
| <img src="assets/rama-backend/backend-menu-principal.png" width="200"> | <img src="assets/rama-backend/backend-subrama-a-core.png" width="200"> | <img src="assets/rama-backend/backend-subrama-b-frameworks.png" width="200"> | <img src="assets/rama-backend/backend-subrama-c-arquitectura.png" width="200"> |

### Ciencia de Datos
| Menú Data | Sub-Rama A | Sub-Rama B | Sub-Rama C |
| :---: | :---: | :---: | :---: |
| <img src="assets/rama-ciencia-datos/ciencia-datos-menu-principal.png" width="200"> | <img src="assets/rama-ciencia-datos/data-subrama-a-fundamentos.png" width="200"> | <img src="assets/rama-ciencia-datos/data-subrama-b-manipulacion.png" width="200"> | <img src="assets/rama-ciencia-datos/data-subrama-c-ia.png" width="200"> |

### Desarrollo Móvil
| Menú Móvil | Sub-Rama A | Sub-Rama B | Sub-Rama C |
| :---: | :---: | :---: | :---: |
| <img src="assets/rama-desarrollo-movil/movil-menu-principal.png" width="200"> | <img src="assets/rama-desarrollo-movil/mobile-subrama-a-dilema.png" width="200"> | <img src="assets/rama-desarrollo-movil/mobile-subrama-b-interfaces.png" width="200"> | <img src="assets/rama-desarrollo-movil/mobile-subrama-c-multiplataforma.png" width="200"> |

## ¿Cómo funciona? (Interactividad y Navegación)
A diferencia de un documento estático, DevRoute está diseñado con una arquitectura de interfaz dinámica que permite al usuario explorar el conocimiento a través de niveles de profundidad:

1. **Menú Principal (Las Áreas):** Desde la pantalla inicial, el usuario selecciona una macro-área de la tecnología (ej. Desarrollo Web, Ciencia de Datos, Backend).
2. **Las Sub-Ramas (El Camino):** La vista principal transiciona de forma fluida hacia un esquema que divide el área seleccionada en etapas de aprendizaje estructuradas (desde los "Fundamentos" hasta "Arquitecturas Avanzadas").
3. **Las Tecnologías (Las Opciones):** Dentro de cada etapa, el usuario interactúa con botones que representan lenguajes, frameworks o conceptos específicos (ej. HTML5, React, Docker).
4. **Fichas de Aprendizaje (El Destino Final):** Al hacer clic en una tecnología, el interactivo despliega un panel de información detallada que explica exactamente **qué se debe estudiar** (conceptos clave, sintaxis, flujos) y cuáles son las **herramientas estándar** de la industria para dominar esa competencia en específico.

## Requisitos Previos
Para ejecutar el proyecto en tu entorno local, asegúrate de tener instalado:
* JDK 17 o superior.
* Apache Maven.
* Git.

## Instalación y Ejecución

### 1. Clonar el Repositorio
```bash
git clone https://github.com/armijosbrandonutil-ops/devRoute.git
cd devRoute
```

### 2. Compilar
El proyecto utiliza Maven para gestionar sus dependencias estructurales. Para compilarlo, ejecuta:
```bash
mvn clean compile
```

### 3. Ejecutar la Aplicación
Para iniciar la interfaz gráfica interactiva, lanza el siguiente comando:
```bash
mvn exec:java -Dexec.mainClass="io.github.armijosbrandon.dev_route.App"
```

## Rutas de Aprendizaje Disponibles
A través de la navegación interactiva, podrás explorar a fondo las siguientes disciplinas:
* **Desarrollo Web:** Desde maquetación básica (HTML/CSS) hasta empaquetadores y testing moderno.
* **Ciencia de Datos:** Fundamentos matemáticos, manipulación con Pandas y bases de Machine/Deep Learning.
* **Desarrollo Móvil:** Caminos nativos (Android con Kotlin / iOS con Swift) y alternativas multiplataforma (Flutter, React Native).
* **Desarrollo Backend:** Ecosistemas de servidores (Java, Python, C#), manejo de ORMs y seguridad (JWT).
* **Herramientas Industriales:** Módulo transversal que explica el uso de Git, Docker, APIs REST y Cloud Computing.


## Autor
Armijos Brandon

## Licencia
Este proyecto es de código abierto.
