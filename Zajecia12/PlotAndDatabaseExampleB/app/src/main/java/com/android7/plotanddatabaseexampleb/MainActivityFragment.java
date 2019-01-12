package com.android7.plotanddatabaseexampleb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    SwipeRefreshLayout mSwipeLayout;

    GraphView mGraphView_1;
    GraphView mGraphView_2;

    APIInterface apiInterface;

    public MainActivityFragment() {

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mSwipeLayout = view.findViewById(R.id.swipe_layout);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        mGraphView_1 = view.findViewById(R.id.graph_1);
        mGraphView_2 = view.findViewById(R.id.graph_2);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        loadData();

        return view;
    }

    private void loadData(){
        int limit = 100;

        mGraphView_1.addSeries(getTemperatureData(getResources().getColor(R.color.plotOrange),limit,1,false));
        mGraphView_1.addSeries(getTemperatureData(getResources().getColor(R.color.plotGreen),limit,2,false));
        mGraphView_1.addSeries(getTemperatureData(Color.RED,limit,3,false));
        mGraphView_1.addSeries(getTemperatureData(Color.BLUE,limit,4,false));

        mGraphView_2.addSeries(getTemperatureData(Color.BLUE,limit,1,true));
        mGraphView_2.addSeries(getHumidityData(Color.RED,limit,1,true));
    }

    private void clearPlot(){
        mGraphView_1.removeAllSeries();
        mGraphView_2.removeAllSeries();
    }

    private double getMaxTemperatureToNorm(List<Temperature> data){
        if(data.size()==0)
            return 1;

        double maxTemp = data.get(0).temperature;

        for(Temperature dataElement : data){
            if(Math.abs(dataElement.temperature)>maxTemp)
                maxTemp = dataElement.temperature;
        }

        return maxTemp;
    }

    private double getMaxHumidityToNorm(List<Humidity> data){
        if(data.size()==0)
            return 1;

        double maxHumidity = data.get(0).humidity;

        for(Humidity dataElement : data){
            if(Math.abs(dataElement.humidity)>maxHumidity)
                maxHumidity = dataElement.humidity;
        }

        return maxHumidity;
    }


    public LineGraphSeries<DataPoint> getTemperatureData(int color, int limit, int sensor, final boolean normData){
        Call<List<Temperature>> call = apiInterface.getTemperature(limit,sensor);

        final LineGraphSeries<DataPoint> temperature = new LineGraphSeries<DataPoint>();
        temperature.setColor(color);

        mSwipeLayout.setColorSchemeColors(Color.BLACK,color,Color.BLACK);
        mSwipeLayout.setRefreshing(true);

        call.enqueue(new Callback<List<Temperature>>() {
            @Override
            public void onResponse(Call<List<Temperature>> call, Response<List<Temperature>> response) {
                List<Temperature> data = response.body();

                double tempToNorm = 1;

                if(normData)
                    tempToNorm = getMaxTemperatureToNorm(data);

                for(int i=0;i<data.size();i++)
                    temperature.appendData(new DataPoint(i++,data.get(i).temperature/tempToNorm),false,data.size());

                mSwipeLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Temperature>> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();

                mSwipeLayout.setRefreshing(false);

                Log.v("Retrofit",t.toString());
            }
        });

        return temperature;
    }

    public LineGraphSeries<DataPoint> getHumidityData(int color, int limit, int sensor, final boolean normData){
        Call<List<Humidity>> call = apiInterface.getHumidity(limit,sensor);

        final LineGraphSeries<DataPoint> temperature = new LineGraphSeries<DataPoint>();
        temperature.setColor(color);

        mSwipeLayout.setColorSchemeColors(Color.BLACK,color,Color.BLACK);
        mSwipeLayout.setRefreshing(true);

        call.enqueue(new Callback<List<Humidity>>() {
            @Override
            public void onResponse(Call<List<Humidity>> call, Response<List<Humidity>> response) {
                List<Humidity> data = response.body();

                double humidityToNorm = 1;

                if(normData)
                    humidityToNorm = getMaxHumidityToNorm(data);

                for(int i=0;i<data.size();i++)
                    temperature.appendData(new DataPoint(i++,data.get(i).humidity/humidityToNorm),false,data.size());

                mSwipeLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Humidity>> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();

                mSwipeLayout.setRefreshing(false);

                Log.v("Retrofit",t.toString());
            }
        });

        return temperature;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_graph,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_reload:
                loadData();
                return true;
            case R.id.action_clear:
                clearPlot();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
