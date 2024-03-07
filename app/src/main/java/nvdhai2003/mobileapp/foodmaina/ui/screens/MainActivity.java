package nvdhai2003.mobileapp.foodmaina.ui.screens;

import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_LOGIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_MAIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_REGISTER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nvdhai2003.mobileapp.foodmaina.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            int screenState = prefs.getInt("ScreenState", 0);

            switch (screenState) {
                case SCREEN_STATE_MAIN:
                    break;
                case SCREEN_STATE_LOGIN:
                    startActivity(new Intent(this, LoginScreenActivity.class));
                    finish();
                    break;
                case SCREEN_STATE_REGISTER:
                    startActivity(new Intent(this, RegisterScreenActivity.class));
                    finish();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}