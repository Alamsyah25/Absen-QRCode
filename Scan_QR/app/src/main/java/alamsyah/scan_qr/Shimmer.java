package alamsyah.scan_qr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.shimmer.ShimmerFrameLayout;

public class Shimmer extends AppCompatActivity {
    Button stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);

        stop = findViewById(R.id.stop);
        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer);
        container.startShimmerAnimation();
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
