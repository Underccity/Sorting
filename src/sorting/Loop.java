package sorting;

import main.Controller;
import main.Main;

import java.util.Arrays;

/**
 * Created by Леонид on 08.05.2016.
 */
//Бесконечный цикл(для проверки на то, какие потоки запущены или поставлены на паузу и notifyAll для всех сортировок)
//Запускается в отдельном потоке, т.к. я не смог найти куда в JavaFX передается управление после launch(args) в Main
public class Loop implements Runnable {

    @Override
    public void run() {
        Controller[] controllers = Main.controllers;
        boolean[] waitingFlags = new boolean[controllers.length];//Хранит какие из сортировок закончили итерацию и ожидают другие сортировки
        boolean[] startedSortings=new boolean[controllers.length];//Хранит какие из сортировок запущены

        while (true) {
            if(!Main.mainThread.isAlive())System.exit(0);//Закрытие программы при закрытии окна
                                                         // (Иначе, из за бесконечного цикла, программа продолжает работать)
            for (int i = 0; i < controllers.length; i++) {
                waitingFlags[i] = controllers[i].getWaitingFlag();
                if(controllers[i].getTh()!=null)
                startedSortings[i]=!controllers[i].isPause();
                else startedSortings[i]=false;
            }
            if (Arrays.equals(waitingFlags,startedSortings))
                synchronized (Main.monitor) {
                    try {
                        Main.monitor.wait(Main.PAUSE_BETWEEN_REDRAW);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Main.monitor.notifyAll();
                }

        }
    }
}
