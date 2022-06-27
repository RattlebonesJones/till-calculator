package com.tillcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.billsFragment);

    }

    BillsFragment firstFragment = new BillsFragment();
    CoinsFragment secondFragment = new CoinsFragment();
    RollsFragment thirdFragment = new RollsFragment();

    public void getSum(View view) {
        double miscBills = Integer.parseInt(((EditText) findViewById(R.id.miscBills)).getText().toString());
        double tensSum = 10 * Integer.parseInt(((EditText) findViewById(R.id.tens)).getText().toString());
        double fivesSum = 5 *Integer.parseInt(((EditText) findViewById(R.id.fives)).getText().toString());
        double onesSum = Integer.parseInt(((EditText) findViewById(R.id.ones)).getText().toString());
        double quartersSum = 0;
        double dimesSum = 0;
        double nickelsSum = 0;
        double penniesSum = 0;
        double quartersRollSum = 0;
        double dimesRollSum = 0;
        double nickelsRollSum = 0;
        double penniesRollSum = 0;

        double sum = miscBills + tensSum + fivesSum + onesSum + quartersSum + dimesSum + nickelsSum + penniesSum + quartersRollSum + dimesRollSum + nickelsRollSum + penniesRollSum;
        ((TextView)findViewById(R.id.totalView)).setText(Double.toString(sum));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.billsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMain, firstFragment).commit();
                return true;

            case R.id.coinsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMain, secondFragment).commit();
                return true;

            case R.id.rollsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMain, thirdFragment).commit();
                return true;
        }
        return false;
    }
}