#Sistema de Averías - Manual de Desarrollador
REST es una técnica de arquitectura de software para sistemas hipermedia distribuidos como la World Wide Web.

En este sistema se utilizará la librería Jersey para implementar un servicio RESTful. Esta librería permite generar la definición del servicio, y ejecutar el servicio al mismo tiempo.

##Clases planas
Inicialmente se requiere definir una serie de clases planas mayormente conocidos como beans, estas serán serializadas por el servicio web. Estas clases realmente corresponden a objetos tipo DTO (Data Transfer Object). Residiran en el directorio ../src/service/beans.

##Definición del servicio
Con Jersey basta con definir una clase que se encargará de contener los métodos a ser consultados. La clase puede ser cualquier tipo y los parámetros de sus métodos serán serializados automáticamente. Se debe ubicar en el directorio ../src/service/resources. Otra clase necesaria es aquella que define el recurso tipo objeto. Este archivo especifica los métodos necesarios para agregar nuevos obejtos y recuperar datos. Residirá en el mismo directorio.

##Clase de parámetros
Para realizar el paso de parámetros es necesario utilizar una clase, residente en el directorio ../src/service/util.

##Compilación
Para compilar esta clase se debe utilizar un archivo residente en el directorio ../

##Interfaz
Para evaluar el correcto funcionamiento del servicio se desarrollará una interfaz web mediante javascript. Adicionalmente, se utilizará una librería javascript que facilite la manipulación del DOM. En este caso se utilizará D3.js pero podría ser cualquier otra como jquery, prototype ó extjs.

###Pagina principal
El código de la página principal incluye el llamado a todas las librerías Javascript necesarias. Este archivo llamado index.html se ubica en el directorio ../reporteaverias

Importante: Note que es necesario que en este mismo directorio exista la librería d3.v2.min.js la cual se puede descargar desde el sitio http://d3js.org

###Listados
El primer archivo javascript que se presenta es aquel utilizado para generar el listado de objetos, reside en el mismo directorio.

###Detalles
El otro archivo javascript permite generar el detalle de los datos del objeto, reside en el directorio ../reporteaverias

###Actualización
El archivo javascript que permite actualizar los datos de los objetos, se ubica en el mismo directorio ../reporteaverias

Note que aquí se hay que crear un reemplazo para la función xhr existente en D3. Esto se hace debido a que la función original no acepta métodos POST, DELETE ó PUT.

###Hoja de estilo (CSS)
Para mejorar la apariencia de esta interfaz se utiliza una hoja de estilo llamada style.css, que se ubica en el mismo directorio ../reporteaverias

##Ejecución de servicio
Este proyecto se puede ejecutar bajo cualquier contenedor de Servlet. Para ello se requiere de la creación del archivo de configuración Web y descargar las librerías que componen Jersey.

###Archivo de configuración Web
El servidor de servlets requiere del archivo de configuración de la aplicación para conocer en donde se ubica la clase a ejecutar. Además este archivo permite indicar la ubicación y nombre de clase que carga los servicios web. Estos archivos de configuración del servlet siempre se llaman web.xml y deben residir en el directorio ../reporteaverias/WEB-INF

##Ejecución del tutorial

Para realizar la ejecución se puede utilizar un producto como el Winstone Servlet Container que permite ejecutar servlets de forma muy sencilla. Se debe descargar el programa winstone-0.9.10.jar y copiarlo en el directorio ../

Adicionalmente, es necesario descargar las librerías de Jersey y copiarlas al directorio ../reporteaverias/WEB-INF/lib:

+ asm-3-1.3.1.jar
+ jersey-bundle-1.13.jar
+ jersey-core-1-1.13.jar
+ jersey-server-1-1.13.jar
+ servlet-api-2.5.jar