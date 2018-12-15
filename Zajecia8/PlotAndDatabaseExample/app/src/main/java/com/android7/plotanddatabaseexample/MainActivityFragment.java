package com.android7.plotanddatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);



        apiInterface = APIClient.getClient().create(APIInterface.class);

        getTemperatureData();

        return view;
    }

    public void getTemperatureData(){
        Call<Temperature> call = apiInterface.getWSIZTemperature(10);

        call.enqueue(new Callback<Temperature>() {
            @Override
            public void onResponse(Call<Temperature> call, Response<Temperature> response) {
                Temperature temperature = response.body();

            }

            @Override
            public void onFailure(Call<Temperature> call, Throwable t) {

            }
        });
    }
}
