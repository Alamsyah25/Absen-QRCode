package alamsyah.scan_qr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView tv;
    ImageView iv,dev;


    private final int SPLASH_TIMEOUT = 2200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Intro.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, SPLASH_TIMEOUT);



        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        dev = (ImageView) findViewById(R.id.dev);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.transition1);
        Animation animx = AnimationUtils.loadAnimation(this, R.anim.transitionx);

        tv.startAnimation(anim);
        iv.startAnimation(anim1);
        dev.startAnimation(animx);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        ColorBar();
//        final Intent i = new Intent(this,intro.class);
//        Thread timer = new Thread(){
//            public void run() {
//                try {
//                    sleep(3000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                finally {
//                    startActivity(i);
//                    finish();
//                }
//            }
//        }; timer.start();
    }

    private void ColorBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
