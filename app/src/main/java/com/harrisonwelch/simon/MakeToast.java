package com.harrisonwelch.simon;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Suzanne on 3/8/2018.
 * Holds a static 'toast" function that makes sure only one toast is displayed on screen
 * at a time, preventing a long toast-queue buildup
 */

public class MakeToast {
    //makes a toast and cancels a previous toast if there was one.
    private static Toast toast;
    public static void toast(Context context, String text){
        if (toast != null){
            toast.cancel();
        }

        toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();

    }
}
