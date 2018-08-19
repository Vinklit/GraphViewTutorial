package com.example.sylvain.graphviewtutorial;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import static android.R.attr.start;
import static com.example.sylvain.graphviewtutorial.R.id.graph;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    LineGraphSeries<DataPoint> series, series2;
    public int lastX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graphView = (GraphView)findViewById(graph);
        series = new LineGraphSeries<DataPoint>();
        series.setColor(Color.rgb(255,51,17));
        //series2 = new LineGraphSeries<DataPoint>();
        //series2.setColor(Color.rgb(255,170,17));
        series.setThickness(3);
        graphView.addSeries(series);
        //graphView.addSeries(series2);
        //graphView.setTitle("MyGraph");
        //graphView.setTitleColor(Color.rgb(255,255,255));
        graphView.getLegendRenderer().setVisible(false);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "", ""});
        staticLabelsFormatter.setVerticalLabels(new String[] {"", "", ""});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMaxY(117);
        viewport.setMinY(0);
        viewport.setMaxX(200);;
        viewport.setXAxisBoundsManual(true);


        viewport.setScrollable(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void addEntry(){
        series.appendData(new DataPoint(lastX++, RANDOM.nextDouble()*100d),true, 200);
        //series2.appendData(new DataPoint(lastX++, RANDOM.nextDouble()*100d),true, 400);

    }
}
