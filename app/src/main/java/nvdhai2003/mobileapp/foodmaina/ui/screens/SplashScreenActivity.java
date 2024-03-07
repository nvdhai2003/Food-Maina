package nvdhai2003.mobileapp.foodmaina.ui.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nvdhai2003.mobileapp.foodmaina.R;

public class SplashScreenActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    public static final int SCREEN_STATE_MAIN = 1;
    public static final int SCREEN_STATE_LOGIN = 2;
    public static final int SCREEN_STATE_REGISTER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    int screenState = prefs.getInt("ScreenState", 0);
                    switch (screenState) {
                        case SCREEN_STATE_MAIN:
                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                            break;
                        case SCREEN_STATE_LOGIN:
                            startActivity(new Intent(SplashScreenActivity.this, LoginScreenActivity.class));
                            break;
                        case SCREEN_STATE_REGISTER:
                            startActivity(new Intent(SplashScreenActivity.this, RegisterScreenActivity.class));
                            break;
                        default:
                            startActivity(new Intent(SplashScreenActivity.this, OnBoardingScreenActivity.class));
                            break;
                    }
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }
}