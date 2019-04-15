Nombre: Eric Stoppel

Word: https://docs.google.com/document/d/11cg43DPfuJKbimRiKIdhA1rDGGLAN8-XTcjZ8GptdrM/edit?usp=sharing

CONSIDERACIONES IMPORTANTES:

    - Para correr los tests se debe correr la api primero.
    - Crear la carpeta logs adentro de src/main/resources
    

REQUISITOS: 

-Tener instalado maven localmente

-Configurar las variables en el archivo Sensitive.conf

-Instalar el proyecto con maven (pararse en la ruta raiz del proyecto y ejecutar mvn install)

SIMULAR:

Para simular simplemente abrir una terminar en la raiz del proyecto y ejecutar el Shell Script "runSimulation.sh" [sh runSimulation.sh]

Decisiones de diseño:

    - Cada sensor corre en un thread, son inicializados en la clase RunSimulation, por otro lado, el monitor es un singleton que corre en otro thread 
    el cual procesa datos cada 30 segundos aproximadamente ya que hacerlo de corrido dos veces en 1 minuto tomaria una gran parte del tiempo durmiendo
    y otra muy chica procesando el stack.
    
