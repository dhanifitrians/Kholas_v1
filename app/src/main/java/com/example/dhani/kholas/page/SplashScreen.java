package com.example.dhani.kholas.page;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;


/*import com.easyandroidanimations.library.BounceAnimation;*/
import com.example.dhani.kholas.R;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashScreen extends AppCompatActivity {

    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;
boolean MY_PERMISSIONS_REQUEST_READ_CONTACTS=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);

        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke Welcome activity activity
                Intent home=new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(home);
                finish();

            }
        },2000);

    }

}
