package com.example.lcarry.broadcastTest.util;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lcarry} on 2017/4/7.
 */

public class ActivityCollector {
    public static final String TAG = "ActivityCollector";

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public  static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        //kill进程
//        Log.w(TAG,"before kill进程");
//        android.os.Process.killProcess(android.os.Process.myPid());
//        Log.w(TAG,"after kill进程");
    }
}
