package app.com.victorioussolutions.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set some test data
        String[] forcastArray = {"Test 1", "Test 2"};

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
}
