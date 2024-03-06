package nvdhai2003.mobileapp.foodmaina.ui.screens;

import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_LOGIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_MAIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_REGISTER;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nvdhai2003.mobileapp.foodmaina.R;

public class RegisterScreenActivity extends AppCompatActivity {

    private AppCompatButton btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegister = findViewById(R.id.btn_create_account);
        try {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("ScreenState", SCREEN_STATE_REGISTER);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}