package com.example.android.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

//This constant string will be used to store the content of the TextView used to display
    //the list of callbacks.
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY="callbacks";

    //constant values for the names of each respective lifecycle callback

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START="onStart";
    private static final String ON_RESUME="onResume";
    private static final String ON_PAUSE="onPause";
    private static final String ON_STOP="onStop";
    private static final String ON_RESTART="onRestart";
    private static final String ON_DESTROY="onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE="onSaveInstanceState";

    private TextView mLifeCycleDisplay;

//here  we're creating arraylist to append onStop() and onDestroy()

    private static final ArrayList<String>mLifecycleCallBacks = new ArrayList<>();
    //first of all onCreate activity will work
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifeCycleDisplay=(TextView) findViewById(R.id.tv_display_lifecycle);

        /**
         * if savedInstances is not null, that means our activity is not being started
         * for the first time. Even if the savedInstanceState is not null,it is smart to check
         * if the bundle contains the key we are looking for. In our cas, the key we are
         * looking for. In our case, the key we are looking for maps
         * contains that key, we set the contains of the textview accordingly
         */

        if(savedInstanceState!=null)
        {
            if(savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY))
            {
              String allPreviousLifecycleCallbacks = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
              mLifeCycleDisplay.setText(allPreviousLifecycleCallbacks  );
            }
        }



        for(int i=mLifecycleCallBacks.size()-1; i>=0; i--)
        {
            mLifeCycleDisplay.append(mLifecycleCallBacks.get(i)+"\n");
        }

        mLifecycleCallBacks.clear();

        logAndAppend(ON_CREATE);
    }





    /**
 * called when the activity is becoming visible to the user
 * followed by onResume() if the activity comes to the foreground, or onStop() if it becomes
 * hidden.
 */

@Override
protected void onStart()
{
    super.onStart();

    logAndAppend(ON_START);
}
/**
 * called when the activity will start interacting with the user
 */

protected void onResume()
{
    super.onResume();
    logAndAppend(ON_RESUME);
}


protected void onPause()
{
    super.onPause();
    logAndAppend(ON_PAUSE);
}
protected void onStop()
{
    super.onStop();

    mLifecycleCallBacks.add(0, ON_STOP);


    logAndAppend(ON_STOP);
}

    protected void onRestart()
    {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }
    protected void onDestroy() {

    super.onDestroy();

    mLifecycleCallBacks.add(0,ON_DESTROY);
    logAndAppend(ON_DESTROY);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
       super.onSaveInstanceState(outState);

       logAndAppend(ON_SAVE_INSTANCE_STATE);

       String lifecycleDisplayTextViewContents = mLifeCycleDisplay.getText().toString();

       outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY,lifecycleDisplayTextViewContents);

    }

    /**
     * logs to the console and appends the lifecycle method name to the TextView so that you can
     * view the series of method callbacks that are called both from the app and from within
     * android studio's logcat
     */

    private void logAndAppend(String lifecycleEvent)
    {
        Log.d(TAG,"Lifecycle Event:"+lifecycleEvent);

        mLifeCycleDisplay.append(lifecycleEvent+"\n");
    }

    /**
     * This method resets the contents of the TextView to its default text of "lifecycle callback
     */


    public void resetLifecycleDisplay(View view)
    {
        mLifeCycleDisplay.setText("Lifecycle Callbacks :\n");
    }
}