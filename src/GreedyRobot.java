import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 15.08.2016.
 */
public class GreedyRobot extends Robot implements Runnable{

    GreedyRobot(int id, ArrayList<Detail> details) {
        this.id = id;
        this.battery = 50;
        this.details = details;
        this.haveLeftDetail = false;
        this.haveRightDetail = false;
       // this.leftNeighbor = leftNeighbour;
      //  this.rightNeighbor = rightNeighbour;
    }   //конструктор получает значение батареи,id , и ссылку на хранилище деталей

    @Override
    void charging() {
        if (battery < 60) {
            leftNeighbor.putRightDetail(details);
            takeLeftDetail(details);
            rightNeighbor.putLeftDetail(details);
            takeRightDetail(details);
            if(haveLeftDetail && haveRightDetail == true)
            while (battery < 100) {
                battery += 10;
                Date d = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("kk:mm:ss:S ");
                System.out.println(format1.format(d.getTime()));
                System.out.println("Battery of robot number " + (id) + " = " + battery + "%");
                try{ Thread.sleep(500);}catch (Exception e){}
            }
            putLeftDetail(details);
            putRightDetail(details);
        }
        if (battery > 60 ) {
            try{ Thread.sleep(500);}catch (Exception e){}
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
