package thel0neshadow.daijoubou_clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Jordan on 07/01/2017.
 */

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.e("In receiver.", "yay!");

        //fetch extra strings from the intent
        String getIntentExtra = intent.getExtras().getString("extra");
        Log.e("intent key: ", getIntentExtra);

        //fetch extra ids from the intent
        //tells the app what sound to play
        int getSoundId = intent.getExtras().getInt("sound");
        Log.e("SoundId is", "" + String.valueOf(getSoundId));

        //create an intent to the RingtonePlaying Service
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        //pass extra string from receiver to the RingtonePlayingService
        serviceIntent.putExtra("extra", getIntentExtra);

        //pass extra int from receiver to ringtone playing service
        serviceIntent.putExtra("sound", getSoundId);

        //start the ringtone service
        context.startService(serviceIntent);
    }
}
