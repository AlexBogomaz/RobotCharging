import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alex on 15.08.2016.
 */
public class ChargingStation extends Robot  {

    public boolean allCharged(Robot b1, Robot b2, Robot b3, Robot b4, Robot b5, Robot b6) {
        if (b1.condition == 1 && b2.condition == 1 && b3.condition == 1 && b4.condition == 1 && b5.condition == 1 && b6.condition == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean allDischarged(Robot b1, Robot b2, Robot b3, Robot b4, Robot b5, Robot b6) {
        if (b1.condition == 0 && b2.condition == 0 && b3.condition == 0 && b4.condition == 0 && b5.condition == 0 && b6.condition == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean charg3And3discharge(Robot b1, Robot b2, Robot b3, Robot b4, Robot b5, Robot b6) {
        int countDis = 0;
        int countDon = 0;
        for (int i = 0; i < 6; i++) {
            if (condition == 0) {
                countDis++;
            }
            if (condition == 1) {
                countDon++;
            }
        }
        if (countDis == 3 && countDon == 3) {
            return true;
        } else {
            return false;
        }
    }


    public void Discharger(Robot b1, Robot b2, Robot b3, Robot b4, Robot b5, Robot b6) {
        if (b1.battery > 0)
            b1.battery -= 10;
        if (b2.battery > 0)
            b2.battery -= 10;
        if (b3.battery > 0)
            b3.battery -= 10;
        if (b4.battery > 0)
            b4.battery -= 10;
        if (b5.battery > 0)
            b5.battery -= 10;
        if (b6.battery > 0)
            b6.battery -= 10;
    }



}



