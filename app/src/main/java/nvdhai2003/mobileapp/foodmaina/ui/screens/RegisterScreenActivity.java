package nvdhai2003.mobileapp.foodmaina.ui.screens;

import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_LOGIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_MAIN;
import static nvdhai2003.mobileapp.foodmaina.ui.screens.SplashScreenActivity.SCREEN_STATE_REGISTER;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import nvdhai2003.mobileapp.foodmaina.R;

public class RegisterScreenActivity extends AppCompatActivity {
    private int backPressCount = 0;
    private EditText edtEmail, edtPassword, edtConfPass;
    private TextView tvLogin;
    private RelativeLayout resLayout;
    private LinearLayout linearLayout;
    private FirebaseAuth mAuth;
    private AppCompatButton btnCreateAccount;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        viewMapping();
        resLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                edtEmail.clearFocus();
                edtPassword.clearFocus();
                edtConfPass.clearFocus();
                return false;
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterScreenActivity.this, LoginScreenActivity.class));
            }
        });
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfPass.getText().toString().trim();

                if (validateInput(email, password, confirmPassword)) {
                    createUserAccount(email, password);
                    SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("ScreenState", SCREEN_STATE_LOGIN);
                    editor.apply();

                } else {
                    // Display appropriate error messages to the user
                    Toast.makeText(RegisterScreenActivity.this, "Please address validation issues.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private boolean validateInput(String email, String password, String confirmPassword) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email address format.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Password fields cannot be empty.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true; // If all validations pass
    }

    private void createUserAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Account creation successful
                            Toast.makeText(RegisterScreenActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                            sendEmailVerification();
                            // Handle successful account creation (e.g., navigate to main activity)
                            Intent intent = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class); // Replace with your main activity class
                            startActivity(intent);
                            finish();
                        } else {
                            // Account creation failed
                            Toast.makeText(RegisterScreenActivity.this, "Account creation failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendEmailVerification() {
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterScreenActivity.this, "Verification email sent, please check your email", Toast.LENGTH_SHORT).show();
                            // You can navigate to another activity or show a message here
                        } else {
                            Toast.makeText(RegisterScreenActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void viewMapping() {
        edtEmail = findViewById(R.id.edt_email_res);
        edtPassword = findViewById(R.id.edt_pass_res);
        edtConfPass = findViewById(R.id.edt_conf_pass);
        resLayout = findViewById(R.id.res_layout);
        linearLayout = findViewById(R.id.linear1_res);
        mAuth = FirebaseAuth.getInstance();
        btnCreateAccount = findViewById(R.id.btn_create_account);
        tvLogin = findViewById(R.id.tv_login);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressCount < 1) {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            backPressCount++;
        } else {
            super.onBackPressed();
        }
    }
}