/**
 * SafeSnooze class: making the ability to sleep your threads not a death sentence.
 * Author: Marcus Eliason (Choooooooooooooooooooooooooooooooooooooooooooooooood)
 * Created 10/21/15
 */

package com.astronuts.library.chudsCode;
//Import
import android.util.Log;

//Main class
public class SafeSnooze {

    //sleep method
    public static void snooze(long num, char unit) {

        switch(unit){
            case 'm':

                //num = num

                break;

            case 'M':

                //num = num

                break;

            case 's':

                num = num * 1000;

                break;

            case  'S':

                num = num * 1000;

                break;

        }

        try{

            Thread.sleep(num);

        } catch (InterruptedException e) {

            Log.i("Chud.Sleep","Sleep thread was interrupted. Ignoring...");

        }

    }

}
