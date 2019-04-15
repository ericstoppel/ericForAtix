package com.eric.atix.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonitoringSystem extends Thread{

    private final LogFile log = new LogFile(this.getClass());
    List<Integer> stackedValues = new ArrayList<Integer>();
    private static MonitoringSystem instance = new MonitoringSystem();
    private static final int MIN_DIFF = Integer.valueOf(Util.getProperty("MIN_DIFF"));
    private static final int PROM_VAL = Integer.valueOf(Util.getProperty("PROM_VAL"));

    private MonitoringSystem(){
        super();
        this.start();
    }

    public void run() {
        this.startMonitoring();
    }
    public static MonitoringSystem getInstance(){
        return instance;
    }

    public void reciveMessage(int value){
        stackedValues.add(value);
    }

    private void processStack(){
        log.logInfo("#######Processing stack########");
        List<Integer> newStack = new ArrayList<Integer>(stackedValues);
        stackedValues.clear();
        int minValue = Integer.MAX_VALUE;
        int maxValue = 0;
        long sum = 0;
        log.logInfo("number of items stacked:["+newStack.size()+"]");
        for (int val : newStack){
            log.logInfo("Processing message val["+val+"]");
            sum += val;
            if(val < minValue) minValue = val;
            if(val > maxValue) maxValue = val;
        }
        long prom = sum / newStack.size();
        log.logInfo("PROMEDIO: "+String.valueOf(prom)+ ", VALOR MINIMO: "+ minValue+", VALOR MAXIMO: "+maxValue);
        validate(minValue,maxValue,prom);
    }

    private void validate(int minValue, int maxValue, long prom){
        if(maxValue - minValue < MIN_DIFF){
            log.logWarning("La diferencia entre el valor minimo y el maximo debe ser mayor a "+MIN_DIFF);
        }
        if(prom < PROM_VAL){
            log.logWarning("El valor promedio debe ser superior a "+PROM_VAL);
        }
    }

    private void startMonitoring(){
        try {
            while (true) {
                this.processStack();
                Thread.sleep(50000);
            }
        }catch (Exception ex){}
    }
}
