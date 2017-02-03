import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        TimerTask timerDischarging;         //Создаем задачу разрядки которая будет работать в фоновом режиме
        Timer timer = new Timer(true);       //Таймер для запуска задачи фонового режима
        Scanner sc = new Scanner(System.in);

        ArrayList<Detail> details = new ArrayList<Detail>(6);
        for (int i = 0; i < 6; i++) {
            details.add(new Detail(i));
        }
        System.out.println(details.toString());


        Robot[] mas = new Robot[6];
        for(int i = 0;i<6;i++)
        {
            System.out.println("Input robot type for robot number: "+i);
            int a = sc.nextInt();
            switch (a)
            {
                case 1:
                    mas[i] = new RandomRobot(i,details);
                    break;
                case 2:mas[i] = new GreedyRobot(i,details);
                    break;
                case 3:mas[i] = new GentlemanRobot(i,details);
                    break;
                default:
                   break ;
            }

        }

/////////////////////////////////
        final Robot rob1 = mas[0];//new RandomRobot(0, details);
        final Robot rob2 = mas[1];
        final Robot rob3 = mas[2];
        final Robot rob4 = mas[3];
        final Robot rob5 = mas[4];
        final Robot rob6 = mas[5];
/////////////////////////////////
        rob2.leftNeighbor = rob3;
        rob2.rightNeighbor = rob1;
        rob3.leftNeighbor = rob4;
        rob3.rightNeighbor = rob2;
        rob4.leftNeighbor = rob5;
        rob4.rightNeighbor = rob3;
        rob5.leftNeighbor = rob6;
        rob5.rightNeighbor = rob4;
        rob6.leftNeighbor = rob1;
        rob6.rightNeighbor = rob5;
/////////////////////////////////
        final ChargingStation r = new ChargingStation();
        timerDischarging = new TimerTask() {
            @Override
            public void run() {             //Описание потока разрядки
                try {
                    r.Discharger(rob1, rob2, rob3, rob4, rob5, rob6);
                    System.out.println("DISCHARGEE!!!!!");

                } catch (Exception e) {
                }

            }
        };
        timer.schedule(timerDischarging, 1000, 1000);
/////////////////////////////////

        try {

            final Thread robot1 = new Thread(rob1);
            final Thread robot2 = new Thread(rob2);
            final Thread robot3 = new Thread(rob3);
            final Thread robot4 = new Thread(rob4);
            final Thread robot5 = new Thread(rob5);
            final Thread robot6 = new Thread(rob6);
/////////////////////////////////
            final Thread checkAllCharged = new Thread(new Runnable() {
                @Override
                public void run() {                    //Поток проверки на зарядку всех роботов

                    while (true) {
                        if (r.allCharged(rob1, rob2, rob3, rob4, rob5, rob6)) {
                            robot1.stop();
                            robot2.stop();
                            robot3.stop();
                            robot4.stop();
                            robot5.stop();
                            robot6.stop();
                            System.out.println();
                            System.out.println("All robot are charged!!!!!!");
                            break;
                        }
                        if(r.allDischarged(rob1, rob2, rob3, rob4, rob5, rob6)) {
                            robot1.stop();
                            robot2.stop();
                            robot3.stop();
                            robot4.stop();
                            robot5.stop();
                            robot6.stop();
                            System.out.println();
                            System.out.println("All robot are discharged!!!!!!");
                            break;
                        }
                        if(r.charg3And3discharge(rob1, rob2, rob3, rob4, rob5, rob6))
                        {
                            robot1.stop();
                            robot2.stop();
                            robot3.stop();
                            robot4.stop();
                            robot5.stop();
                            robot6.stop();
                            System.out.println();
                            System.out.println("3 robots charged and 3 robot discharged!!!!");
                            break;
                        }
                    }

                }
            });

            robot1.start();
            robot2.start();
            robot3.start();
            robot4.start();
            robot5.start();
            robot6.start();
            checkAllCharged.start();

        } catch (Exception e) {

        }


    }
}
