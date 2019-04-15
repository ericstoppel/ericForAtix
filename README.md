Nombre: Eric Stoppel

API: https://meliplanetsbyericstoppel.herokuapp.com


Consideraciones: 
    
    En mi opinion, hubiese separado la app que se encarga de realizar la simulacion de la api 
    dado que esta ultima unicamente se encarga de recuperar datos de la base de datos por
    lo que todo el codigo que simula podria abstraerse de este proyecto, en este caso decidi
    codear todo en el mismo proyecto para centralizar las clases.
    
    Por otro lado por un tema de performance se generaron los datos del clima para 10 años
    en una BD Mysql la cual fue hosteada en cloud junto con la api, un ejemplo de un curl
    para obtener el clima de un dia seria:
    
    curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET https://meliplanetsbyericstoppel.herokuapp.com/clima?dia=33
    
    Para probar la funcionalidad recomiendo generar la base de forma local y correrlo
    en ese entorno, dado que pegarle directamente a jawsdb demora mucho tiempo.

    

REQUISITOS: 

-Tener instalado maven localmente
	

-Para importar la Base de datos, abrir MySQL en una conexion local, click en Data Import, seleccionar "Import from Self-Contained File" y abrir el archivo createSQL.sql ubicado en la raiz del proyecto.

-Abrir el archivo Sensitive.conf ubicado en resources y cambiar las credenciales para conectarse a su mysql local

-Instalar el proyecto con maven (pararse en la ruta raiz del proyecto y ejecutar mvn clean install)

SIMULAR:


Para simular simplemente abrir una terminar en la raiz del proyecto y ejecutar el Shell Script "runSimulation.sh" [sh runSimulation.sh]

Decisiones de diseño:

	-Se decidio crear una clase civilizacion en caso de que esta pueda crecer y tener mas atributos, ademas, de esta manera los planetas podrian cambiar de civilizacion en cualquier momento si asi se quisiera. 

	-En la clase util para manejar operaciones aritmeticas se uso como tipo de dato BigDecimal por una cuestion de presicion.

	-En vez de manejar los climas con strings se creo la clase "Weathers" la cual maneja codigos de clima con sus descripciones.
