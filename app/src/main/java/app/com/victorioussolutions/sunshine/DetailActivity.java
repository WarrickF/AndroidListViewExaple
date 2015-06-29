package app.com.victorioussolutions.sunshine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        TextView zView = (TextView)findViewById(R.id.Detail_Text);
        zView.setText(intent.getStringExtra(intent.EXTRA_TEXT));

        //zView.sette
        //zView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
            //Intent settingsIntent = new Intent(this, SettingsActivity.class);
            //startActivity(settingsIntent);
            return true;
        }

        if(id == R.id.menu_item_share) {
            // Fetch and store ShareActionProvider
            // mShareActionProvider = (ShareActionProvider) item.getActionProvider();

            // Return true to display menu
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
