package movement.com.movement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    public void startMoving(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
}
