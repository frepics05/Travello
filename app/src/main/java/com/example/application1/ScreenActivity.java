package com.example.application1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenActivity extends AppCompatActivity {

    UserModel userModel;
    SaveShared saveShared;
    TextView tvTime, tvTanggal, tvUsername;
    ImageView ivBackground;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        tvTime = findViewById(R.id.tvClock);
        tvTanggal = findViewById(R.id.tvCalender);
        tvUsername = findViewById(R.id.tvUsername);

        ivBackground = findViewById(R.id.ivBg);

        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite three = new ThreeBounce();
        progressBar.setIndeterminateDrawable(three);

        saveShared = new SaveShared(this);
        userModel = new UserModel();
        userModel = saveShared.getUser();
        tvUsername.setText("Hi, " + userModel.getName());

        showDynamisTime();
    }

    private void showDynamisTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyy");
        tvTanggal.setText(simpleDateFormat.format(new Date()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                userModel = saveShared.getUser();
                userModel.setStatusLogin(false);
                saveShared.setUser(userModel);
                startActivity(new Intent(ScreenActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.accountSetting:
                startActivity(new Intent(ScreenActivity.this, SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Penerbangan(View view) {
        startActivity(new Intent(ScreenActivity.this, PenerbanganActivity.class));
    }

    public void Hotel(View view) {
        startActivity(new Intent(ScreenActivity.this, HotelActivity.class));
    }

    public void Kereta(View view) {
        startActivity(new Intent(ScreenActivity.this, KeretaActivity.class));
    }

    public void Kuliner(View view) {
        startActivity(new Intent(ScreenActivity.this, KulinerActivity.class));
    }

    public void TiketBus(View view) {
        startActivity(new Intent(ScreenActivity.this, BusActivity.class));
    }
}
