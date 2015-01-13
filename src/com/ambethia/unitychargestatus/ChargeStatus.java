package com.ambethia.unitychargestatus;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

class ChargeStatus {

    private Context context;
    private IntentFilter intentFilter;

    private static ChargeStatus instance;

    public ChargeStatus() {
        this.instance = this;
    }

    public static ChargeStatus instance() {
        if(instance == null) {
            instance = new ChargeStatus();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isCharging() {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }
}
