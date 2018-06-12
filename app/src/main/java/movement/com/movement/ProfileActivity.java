package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mBarChart = findViewById(R.id.bar_chart);

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f, 8));
        barEntries.add(new BarEntry(1f, 1));
        barEntries.add(new BarEntry(2f, 2));
        barEntries.add(new BarEntry(3f, 3));
        barEntries.add(new BarEntry(4f, 4));
        barEntries.add(new BarEntry(6f, 5));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Months");

        final List<String> months = new ArrayList<>();
        months.add("Mon");
        months.add("Tue");
        months.add("Wed");
        months.add("Thu");
        months.add("Fri");
        months.add("Sat");
        months.add("Sun");

        BarData barData = new BarData(barDataSet);
        mBarChart.setData(barData);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months.get((int) value);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_share_profile:
                break;
            case R.id.menu_edit_profile:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
