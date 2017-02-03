import java.util.ArrayList;

/**
 * Created by Alex on 15.08.2016.
 */
public abstract class Robot extends Thread {
    protected int id;//номер робота
    protected volatile int battery;// заряд батареи
    protected boolean haveLeftDetail = false; //наличие левой детали
    protected boolean haveRightDetail = false;    //наличие правой детали
    protected Robot rightNeighbor;     //правой сосед
    protected Robot leftNeighbor;    //левый сосед
    protected ArrayList<Detail> details;          //Хранилище данных о деталях
    protected volatile int condition;//состояние батареи робота

    void takeLeftDetail(ArrayList<Detail> details) {
        if(haveLeftDetail == false)
        if (details.get(id).status.compareAndSet(false,true) ) {
            haveLeftDetail = true;
            System.out.println("Robot number "+id+" is take left detail number "+details.get(id));
        } else {
            //System.out.println("Robot number "+id+" Detail number " + details.get(id).number + " is using now");
        }
    }

    void takeRightDetail(ArrayList<Detail> details) {
        if(haveRightDetail == false)
        if (id == 0) {
            if (details.get(5).status.compareAndSet(false,true)) {
                haveRightDetail = true;
                System.out.println("Robot number "+id+" is take right detail number "+details.get(5));
            } else {
               // System.out.println("Robot number "+id+" Detail number " + details.get(5).number + " is using now");
            }
        } else {
            if(haveRightDetail == false)
            if(id != 0)
            if (details.get(id - 1).status.compareAndSet(false,true)) {
                haveRightDetail = true;
                System.out.println("Robot number "+id+" is take right detail number "+details.get(id - 1));
            } else{}
                //System.out.println("Robot number "+id+" Detail number " + details.get(id - 1).number + " is using now");
        }
    }

    void putRightDetail(ArrayList<Detail> details) {
        if(haveRightDetail == true)
        if(id == 0)
        {
            details.get(5).status.compareAndSet(true,false);
            haveRightDetail = false;
            System.out.println("Robot number "+id+" is PUT right detail number "+details.get(5));
        }
       if(haveRightDetail == true)
       {
           details.get(id-1).status.compareAndSet(true,false);
           haveRightDetail = false;
           System.out.println("Robot number "+id+" is PUT right detail number "+details.get(id-1));
       }
    }

    void putLeftDetail(ArrayList<Detail> details) {
        if(haveLeftDetail == true)
        if (details.get(id).status.compareAndSet(true,false)) {
            haveLeftDetail = false;
            System.out.println("Robot number "+id+" is PUT left detail number "+details.get(id));
        }
    }

    void charging() {
    }

    void discharging() {
        if (battery != 0)
            battery -= 10;
    }
}
