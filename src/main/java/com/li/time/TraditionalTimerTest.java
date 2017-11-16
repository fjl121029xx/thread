package com.li.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {


    private static int x = 0;

    public static void main(String[] args) {

        new Timer().schedule(new MyTimeTask(), 2000);
    }

    static class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            x = (x + 1) % 2;
            System.out.println("___BOOM~!____" + x + "\t" + new Date().getSeconds());
            new Timer().schedule(new MyTimeTask(), x == 0 ? 2000 : 4000);
        }
    }
}
