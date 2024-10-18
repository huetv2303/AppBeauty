package com.example.appbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appbeauty.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity   {
    private Fragment homeFragment, savedFragment, chatFragment, notifyFragment, profileFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private static int clickToLogout;
    private static int stackLayout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();

        stackLayout++;
        clickToLogout = 0;
        clickButtonNavigation();

        // từ Fragment trở đến Activity để lấy đối tượng User (Xác thực)
        Intent intent = getIntent();
        String request = intent.getStringExtra("request");
        if(request != null) {
            switch (request) {
                case "history": case "check": case "payment": case "cart":
                    loadFragment_replace(chatFragment, 2);
                    break;
                case "hint":
                    loadFragment_replace(notifyFragment, 3);
                    break;
                default:
                    loadFragment_replace(homeFragment, 0);
                    break;
            }
        } else {
            loadFragment_replace(homeFragment, 0);
        }
    }

    public void initializeUI() {
        homeFragment = new HomeFragment();
//        savedFragment = new SavedFragment();
//        chatFragment = new ChatFragment();
//        notifyFragment = new NotifyFragment();
//        profileFragment = new ProfileFragment();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentManager = getSupportFragmentManager();
    }

    public void clickButtonNavigation() {
        loadFragment(homeFragment); // setting: load mặc định - HomeFragment lên trên HomeActivity
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_home) {
                    loadFragment(homeFragment);
                } else if (item.getItemId() == R.id.ic_tym) {
                    loadFragment(savedFragment);
                } else if (item.getItemId() == R.id.ic_giohang) {
                    loadFragment(chatFragment);
                } else if (item.getItemId() == R.id.ic_thongbao) {
                    loadFragment(notifyFragment);
                } else if (item.getItemId() == R.id.ic_account) {
                    loadFragment(profileFragment);
                } else {
                    return true;
                }
                return true;
            }
        });
    }

    // load Fragment
    private void loadFragment(Fragment fragmentReplace) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, fragmentReplace)
                .commit();
    }

    private void loadFragment_replace(Fragment fragment, int indexItem) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);    // transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        System.out.println(stackLayout);
        if (stackLayout < 2) {
            clickToLogout++;

            if (clickToLogout > 1) {
                finish();
                stackLayout--;
            } else {
                Toast.makeText(this, "Click thêm lần nữa để đăng xuất!", Toast.LENGTH_SHORT).show();
            }

            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    clickToLogout = 0;
                }
            }.start();
        } else {
            stackLayout--;
            super.onBackPressed();
        }
    }

}