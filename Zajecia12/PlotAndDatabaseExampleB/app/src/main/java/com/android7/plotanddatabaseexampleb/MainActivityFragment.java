package com.android7.plotanddatabaseexampleb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    GraphView mGraphView;
    APIInterface apiInterface;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mGraphView = (GraphView) view.findViewById(R.id.graph);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        mGraphView.addSeries(getTemperatureData(Color.BLUE, 100,1));
        mGraphView.addSeries(getTemperatureData(Color.RED, 100,2));

        return view;
    }

    public LineGraphSeries<DataPoint> getTemperatureData(int color, int limit, int sensor){
        Call<List<Temperature>> call = apiInterface.getWSIZTemperature(limit,sensor);

        final LineGraphSeries<DataPoint> temperature = new LineGraphSeries<DataPoint>();
        temperature.setColor(color);

        call.enqueue(new Callback<List<Temperature>>() {
            @Override
            public void onResponse(Call<List<Temperature>> call, Response<List<Temperature>> response) {
                List<Temperature> data = response.body();


                for(int i=0;i<data.size();i++)
                    temperature.appendData(new DataPoint(i++,data.get(i).temperature),false,data.size());

            }

            @Override
            public void onFailure(Call<List<Temperature>> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();

                Log.v("Retrofit",t.toString());
            }
        });

        return temperature;
    }
}
