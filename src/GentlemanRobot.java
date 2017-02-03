import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 15.08.2016.
 */
public class GentlemanRobot extends Robot implements Runnable {
    GentlemanRobot(int id, ArrayList<Detail> details) {
        this.id = id;
        this.battery = 50;
        this.details = details;
        this.haveLeftDetail = false;
        this.haveRightDetail = false;
       // this.leftNeighbor = leftNeighbour;
       // this.rightNeighbor = rightNeighbour;
    }   //конструктор получает значение батареи,id , и ссылку на хранилище деталей

    @Override
    void charging() {
        takeLeftDetail(details);
        takeRightDetail(details);
        if (battery > leftNeighbor.battery) {       //уступаем левому соседу
            putLeftDetail(details);
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("kk:mm:ss:S ");
            System.out.println(format1.format(d.getTime()));
            System.out.println("Robot number " + (id) + " gives his left detail");
            try{ Thread.sleep(200);}catch (Exception e){}
        }
        if (battery > rightNeighbor.battery) {      //уступаем правому соседу
            putRightDetail(details);
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("kk:mm:ss:S ");
            System.out.println(format1.format(d.getTime()));
            System.out.println("Robot number " + (id ) + " gives his right detail");
            try{ Thread.sleep(200);}catch (Exception e){}
        }
        if (haveLeftDetail && haveRightDetail == true && battery < 100) {       //заряжаемся сами
            battery += 10;
            putRightDetail(details);
            putLeftDetail(details);
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("kk:mm:ss:S ");
            System.out.println(format1.format(d.getTime()));
            System.out.println("Battery of robot number " + (id) + " = " + battery + "%");
            try{ Thread.sleep(500);}catch (Exception e){}
        }
        putRightDetail(details);
        putLeftDetail(details);
    }

    @Override
    public void run() {
        while(true) {
            if(battery <= 0) {
                System.out.println("Battery of robot number " + (id) + " = 0 %!!!!!!! Robot turned OFF");
                condition = 0;
                break;
            }
            this.charging();

            if(battery == 100)
            {
                condition = 1;
            }
            if(battery > 0 && battery < 100)
            {
                condition = 2;
            }
        }
    }
}
