package com.eric.atix.Model;

import java.io.*;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.*;

public class LogFile {
    private static final String defaultName = "LogAtix";
    private String name = "";
    private static final String path = "./src/main/resources/logs/";
    private String definitivePath = "";
    private Logger uniqueLog = null;
    Handler fileHandler = null;
    Formatter simpleFormatter = null;

    /*Permitir nombre default*/

    private void createDirectory(String directoryName) {
        File directory = new File("./src/main/resources/logs/" + directoryName + "/");
        directory.mkdir();
    }

    private InputStream getProperties() throws Exception {
        //OBTENGO LAS PROPIEDADES DE LOGGER
        InputStream properties = null;
        File file = new File("./src/main/resources/logging.properties");
        properties = new FileInputStream(file);
        return properties;
    }

    private String getNameWithPrefix(String name) {
        try {
            // se cambia nombre del archivo como yyyyMMdd_nombreArchivo.log
            String fileName;
            SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
            String stFecha = format2.format(new Date());
            fileName = stFecha + "_" + name + ".log";
            return fileName;
        } catch (Throwable t) {
            return name;
        }
    }


    public LogFile(String aName) {
        try {
            if (aName.equals("")) {
                createDirectory(defaultName);
                name = getNameWithPrefix(defaultName);
            } else {
                createDirectory(aName);
                name = getNameWithPrefix(aName);
            }

            this.instanciar();
        } catch (Throwable t) {

        }

    }

    public LogFile(Class<?> clase) {
        String aName = clase.getSimpleName();

        try {
            if (aName.equals("")) {
                name = getNameWithPrefix(defaultName);
            } else {
                createDirectory(aName);
                name = getNameWithPrefix(aName);
            }

            this.instanciar();
        } catch (Throwable t) {

        }
    }

    //Obtengo el nombre sin formato
    private String getCleanName(String formatName) {
        String cleanName = "";
        cleanName = formatName.substring(9, formatName.length() - 4);
        return cleanName;
    }


    private void instanciar() throws Exception {
        InputStream properties = null;
        uniqueLog = Logger.getLogger(name);
        String cleanName = getCleanName(name);
        //obtengo el path donde va a estar ubicado el archivo segun que robot sea
        definitivePath = path + cleanName + "/" + name;

        //Asigna formato configurado en resources
        properties = getProperties();
        if (properties != null) {
            LogManager.getLogManager().readConfiguration(properties);
        }
        ConsoleHandler consoleHandler = new ConsoleHandler();
        uniqueLog.addHandler(consoleHandler);
    }

    private void asignarFileHandler() {
        // Instancia del formateador
        try {
            simpleFormatter = new SimpleFormatter();
            // Instancia del File Handler apuntando al Path final
            fileHandler = new FileHandler(definitivePath, true);
            // Asignando el formateador al File Handler
            fileHandler.setFormatter(simpleFormatter);
            // Asignando el File Handler al Logger
            uniqueLog.addHandler(fileHandler);
        } catch (IOException exception) {

        }
    }

    private void cerrarFileHandler() {
        if (uniqueLog != null && fileHandler != null) {
            //Console handler removed
            fileHandler.close();
            fileHandler.flush();
            uniqueLog.removeHandler(fileHandler);
        }
    }

    public void logInfo(String message) {
        // Logea un mensaje [INFORMACION]/[INFO]
        asignarFileHandler();
        uniqueLog.info(message);
        cerrarFileHandler();
    }

    public void logWarning(String message) {
        // Logea un mensaje [ADVERTENCIA]/[WARNING]
        asignarFileHandler();
        uniqueLog.warning(message);
        cerrarFileHandler();
    }

    public void logError(String message) {
        // Logea un mensaje [GRAVE]/[SEVERE]
        asignarFileHandler();
        uniqueLog.severe(message);
        cerrarFileHandler();
    }

    public void logError(Exception e) {
        // Logea un mensaje [GRAVE]/[SEVERE]
        asignarFileHandler();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        uniqueLog.severe(e.getMessage() + "\n" + sStackTrace);
        cerrarFileHandler();
    }

}


