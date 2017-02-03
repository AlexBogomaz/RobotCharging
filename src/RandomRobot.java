import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 15.08.2016.
 */
public class RandomRobot extends Robot implements Runnable{

    RandomRobot( int id, ArrayList<Detail> details) {
        this.id = id;
        this.battery = 50;
        this.details = details;
        this.haveLeftDetail = false;
        this.haveRightDetail = false;
        this.condition = 2;
    }

    @Override
    void charging() {
        if (battery < 100) {    //если уровень заряда не полный
            takeLeftDetail(details);
            takeRightDetail(details);
            if (haveLeftDetail && haveRightDetail == true) {
                battery += 10;
                System.out.println("Battery of robot number " + (id) + " = " + battery + "%");
                putRightDetail(details);
                putLeftDetail(details);
                Date d = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("kk:mm:ss:S ");
                System.out.println(format1.format(d.getTime()));         //Время события
               //System.out.println("Battery of robot number " + (id) + " = " + battery + "%");
               try{ Thread.sleep(500);;}catch (Exception e){}
                //System.out.println("Robot number " + (this.id + 1) + " go to sleep");
                int rand = 100 + (int) (Math.random() * ((300 - 100) + 1));     //засыпаем га (100-300)мс случайно
                try{ Thread.sleep(rand);;}catch (Exception e){}
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            this.charging();
            if(battery == 100)
            {
                condition = 1;
            }
            if(battery > 0 && battery < 100)
            {
                condition = 2;
            }
            if(battery <= 0) {
                System.out.println("Battery of robot number " + (id) + " = 0 %!!!!!!! Robot turned OFF");
                condition = 0;
                break;
            }

        }

    }


}
