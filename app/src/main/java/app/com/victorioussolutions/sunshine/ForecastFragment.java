package app.com.victorioussolutions.sunshine;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set some test data
        String[] forcastArray = {"Test 1", "Test 2"};

        URL zURL = null;
        try {
            zURL = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
        } catch (MalformedURLException e) {

        }
        String zJSON = null;
        new FetchWeatherTask().execute(zURL);

        // Convert array to string list.
        List<String> weekForecase = new ArrayList<String>(Arrays.asList(forcastArray));

        // Create an array adapter
        ArrayAdapter<String> zAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forcast, R.id.list_item_forecast_textview, forcastArray);

        // The create normally would jus return a View. We need to use tihs View reference to find the List View before we do that though. So assign to temp View variable.
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Finf the ListView that we're really interested in.
        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);

        // Set it's adapter to be the Array Adapter created above.
        listView.setAdapter(zAdapter);
        return rootView;
    }


    private class FetchWeatherTask extends AsyncTask<URL, Integer, String> {
        private String JSON;

        protected void onPostExecute(String json) {

        }

        protected String doInBackground(URL... urls) {
            Log.e("PlaceholderFragment", "Init");

            // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            Log.e("PlaceholderFragment", "After Init");

// Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {

                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are available at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                Log.e("PlaceholderFragment", "After Connect");

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    forecastJsonStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                Log.e("PlaceholderFragment", "After Read");

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    forecastJsonStr = null;
                }
                forecastJsonStr = buffer.toString();
                Log.e("PlaceholderFragment", "Done HTTP Call");
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                forecastJsonStr = null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            JSON = forecastJsonStr;
            return forecastJsonStr;
        }

    }

}
