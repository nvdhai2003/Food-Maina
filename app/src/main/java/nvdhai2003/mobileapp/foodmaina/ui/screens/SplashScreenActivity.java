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
                checkSkipStatusAndTransition();
            }
        }, 2000);
    }

    private void checkSkipStatusAndTransition() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean skipOnboarding = preferences.getBoolean("skipOnboarding", false);
        Class<?> nextActivity = skipOnboarding ? MainActivity.class : OnBoardingScreenActivity.class;

        Intent intent = new Intent(SplashScreenActivity.this, nextActivity);
        startActivity(intent);
        finish();
    }
}