package nvdhai2003.mobileapp.foodmaina.ui.screens;

import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_MAIN;

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

public class LoginScreenActivity extends AppCompatActivity {

    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btn_login_account);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("ScreenState", SCREEN_STATE_MAIN);
                    editor.apply();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                startActivity(new Intent(this, MainActivity.class));
//                finish();
            }
        });
    }
}