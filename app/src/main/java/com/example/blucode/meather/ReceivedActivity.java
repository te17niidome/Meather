package com.example.blucode.meather;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ReceivedActivity extends IntentService {

    final static String TAG = "ReceivedActivity";

    public ReceivedActivity() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }
}
