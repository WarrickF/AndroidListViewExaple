package app.com.victorioussolutions.sunshine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            /*
            Activity zActivity = getActivity();
            Context context = zActivity.getApplicationContext() ;
            Intent settingsIntent = new Intent()
            */
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        if (id == R.id.action_myLocation) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        // This ends up calling the Fragments onOptionsItemSelected method which is where were handle the refresh
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle", "Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle", "Stop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "Start");
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("LifeCycle", "Create");
    }

    

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "Destroy");
    }
}
