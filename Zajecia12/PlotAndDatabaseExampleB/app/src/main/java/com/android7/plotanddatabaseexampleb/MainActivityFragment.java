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

    GraphView mGraphView_1;
    GraphView mGraphView_2;

    APIInterface apiInterface;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mGraphView_1 = view.findViewById(R.id.graph_1);
        mGraphView_2 = view.findViewById(R.id.graph_2);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        int limit = 100;

        //mGraphView_1.addSeries(getTemperatureData(Color.BLUE,limit,1,1));
        //mGraphView_1.addSeries(getTemperatureData(Color.RED,limit,2,2));
        mGraphView_1.addSeries(getTemperatureData(Color.RED,limit,3,true));
        mGraphView_1.addSeries(getTemperatureData(Color.BLUE,limit,4,true));
        //mGraphView_1.addSeries(getTemperatureData(getResources().getColor(R.color.plotGreen),limit,3,true));
        //mGraphView_1.addSeries(getTemperatureData(getResources().getColor(R.color.plotOrange),limit,4,true));

        mGraphView_2.addSeries(getTemperatureData(Color.BLUE,limit,1,true));
        mGraphView_2.addSeries(getHumidityData(Color.RED,limit,1,true));

        //mGraphView_2.addSeries(getHumidityData(Color.BLUE,limit,1,1));
        //mGraphView_2.addSeries(getHumidityData(Color.RED,limit,2,1));
        //mGraphView_2.addSeries(getHumidityData(getResources().getColor(R.color.plotGreen),limit,1,3));
        //mGraphView_2.addSeries(getHumidityData(getResources().getColor(R.color.plotOrange),limit,2,4));

        return view;
    }

    public double maxTemperatureToNorm(List<Temperature> temperatureList){

        if(temperatureList.size()<0)
            return 1;

        double maxTemperature = temperatureList.get(0).temperature;

        for(Temperature dataElement: temperatureList)
            if(maxTemperature>Math.abs(dataElement.temperature))
                maxTemperature = Math.abs(dataElement.temperature);

        if(maxTemperature==0)
            return 0;

        return maxTemperature;
    }

    public double maxHumidityToNorm(List<Humidity> humidityList){

        if(humidityList.size()<0)
            return 1;

        double maxHumidity = humidityList.get(0).humidity;

        for(Humidity dataElement: humidityList)
            if(maxHumidity>dataElement.humidity)
                maxHumidity = dataElement.humidity;

        if(maxHumidity==0)
            return 0;

        return maxHumidity;
    }


    public LineGraphSeries<DataPoint> getTemperatureData(int color, int limit, int sensor, final boolean normData){
        Call<List<Temperature>> call = apiInterface.getTemperature(limit,sensor);

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

    public LineGraphSeries<DataPoint> getHumidityData(int color, int limit, int sensor, final boolean normData){
        Call<List<Humidity>> call = apiInterface.getHumidity(limit,sensor);

        final LineGraphSeries<DataPoint> temperature = new LineGraphSeries<DataPoint>();
        temperature.setColor(color);

        call.enqueue(new Callback<List<Humidity>>() {
            @Override
            public void onResponse(Call<List<Humidity>> call, Response<List<Humidity>> response) {
                List<Humidity> data = response.body();

                for(int i=0;i<data.size();i++)
                    temperature.appendData(new DataPoint(i++,data.get(i).humidity),false,data.size());

            }

            @Override
            public void onFailure(Call<List<Humidity>> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();

                Log.v("Retrofit",t.toString());
            }
        });

        return temperature;
    }
}
