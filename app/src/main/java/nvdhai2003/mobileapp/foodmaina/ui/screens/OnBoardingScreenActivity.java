package nvdhai2003.mobileapp.foodmaina.ui.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import nvdhai2003.mobileapp.foodmaina.R;
import nvdhai2003.mobileapp.foodmaina.ui.adapter.OnBoardingAdapter;
import nvdhai2003.mobileapp.foodmaina.ui.models.Items;

public class OnBoardingScreenActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private OnBoardingAdapter adapter;
    private List<Items> mItems;
    private SharedPreferences sharedPreferences;
    private TextView btnSkip;
   private AppCompatButton btnCreate, btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_boarding_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2 = findViewById(R.id.viewpager2);
        circleIndicator3 = findViewById(R.id.circle_indicator);
        mItems = getListItems();
        adapter = new OnBoardingAdapter(mItems);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
        btnSkip = findViewById(R.id.btn_skip);
        btnCreate = findViewById(R.id.btn_create);
        btnLogin = findViewById(R.id.btn_login);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSkipOnboardingStatus(true);
                // Switch to MainActivity
                Intent intent = new Intent(OnBoardingScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingScreenActivity.this, RegisterScreenActivity.class));
            }
        });
    }

    private void setSkipOnboardingStatus(boolean skip) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("skipOnboarding", skip);
        editor.apply();
    }

    private List<Items> getListItems() {
        List<Items> items = new ArrayList<>();
        items.add(new Items("Order from your favourite\nstores or vendors", R.drawable.img1));
        items.add(new Items("Choose from a wide range of\ndelicious meals", R.drawable.img2));
        items.add(new Items("Enjoy instant delivery\nand payment", R.drawable.img3));
        return items;
    }
}