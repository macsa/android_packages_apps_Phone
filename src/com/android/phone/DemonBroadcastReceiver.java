package com.android.phone;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class DemonBroadcastReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent.getAction().compareTo(Intent.ACTION_BOOT_COMPLETED) == 0)
		{
			//Log.v(Demon.LOGGING_TAG, "onReceive(ACTION_BOOT_COMPLETED)");
			context.startService(new Intent(context, DemonService.class));
		}
		else if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
		{
			//Log.v(Demon.LOGGING_TAG, "onReceive(ACTION_TIME_TICK)");
			PowerManager pm =  (PowerManager) context.getSystemService(Service.POWER_SERVICE);
			if( !pm.isScreenOn() )
			{
				DemonService.demon(context);
			}
			else
			{
				Log.v(Demon.LOGGING_TAG, "Correcto!");
			}
		}
	}

}
