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
        //fetch extra strings from the intent
        String getIntentExtra = intent.getExtras().getString("extra");

        //fetch extra ids from the intent
        //tells the app what sound to play
        int getIntentId = intent.getExtras().getInt("intent");

        //create an intent to the RingtonePlaying Service
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        //pass extra string from receiver to the RingtonePlayingService
        serviceIntent.putExtra("extra", getIntentExtra);

        //pass extra int from receiver to ringtone playing service
        serviceIntent.putExtra("intent", getIntentId);

        //start the ringtone service
        context.startService(serviceIntent);
    }
}
