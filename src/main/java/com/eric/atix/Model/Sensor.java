package com.eric.atix.Model;


import java.util.Random;

public class Sensor extends Thread{

    public Sensor(){
        super();
    }


    public void run(){
        startSimulating();
    }

    private void startSimulating(){
        long taskTime;
        long sleepTime = 1000;
        int processedTimes = 0;
        long acumulatedTime = 0;

        try {
            while (true) {
                taskTime = System.currentTimeMillis();
                this.messureAndSend();
                processedTimes ++;
                acumulatedTime += System.currentTimeMillis() - taskTime;
                if (acumulatedTime < sleepTime) {
                    if(processedTimes == 2){
                        Thread.sleep(sleepTime-acumulatedTime);
                        acumulatedTime = 0;
                        processedTimes = 0;
                    }
                }else{
                    acumulatedTime = 0;
                    processedTimes = 0;
                }
            }
        }catch (Exception ex){}
    }

    private void messureAndSend() {
        Random ran = new Random();
        int n = ran.nextInt() & Integer.MAX_VALUE;
        MonitoringSystem ms = MonitoringSystem.getInstance();
        ms.reciveMessage(n);
    }

}
