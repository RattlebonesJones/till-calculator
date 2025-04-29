package com.tillcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    BillsFragment firstFragment = new BillsFragment();
    CoinsFragment secondFragment = new CoinsFragment();
    RollsFragment thirdFragment = new RollsFragment();
    BigDecimal miscBills = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal tensSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal fivesSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal onesSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal quartersSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal dimesSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal nickelsSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal penniesSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal quartersRollSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal dimesRollSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal nickelsRollSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal penniesRollSum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.billsFragment);

    }

    private BigDecimal createBigDecimal(double n) {
        return new BigDecimal(n).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getField(int id, double scalar) {
        String asString = ((EditText) findViewById(id)).getText().toString();
        int asInt = 0;
        try {
            asInt = Integer.parseInt(asString);
        } catch (NumberFormatException ignored) {
        }
        return createBigDecimal(asInt).multiply(createBigDecimal(scalar)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private void setSumView(int id, BigDecimal val){
        ((TextView) findViewById(id)).setText(val.toString());
    }

    public void getSum(View view) {
        switch (bottomNavigationView.getSelectedItemId()) {
            case R.id.billsFragment:
                miscBills = getField(R.id.miscBills, 1);
                setSumView(R.id.miscBillsTotal, miscBills);
                tensSum = getField(R.id.tens, 10);
                setSumView(R.id.tensTotal, tensSum);
                fivesSum = getField(R.id.fives, 5);
                setSumView(R.id.fivesTotal, fivesSum);
                onesSum = getField(R.id.ones, 1);
                setSumView(R.id.onesTotal, onesSum);
                break;
            case R.id.coinsFragment:
                quartersSum = getField(R.id.quarters, 0.25);
                setSumView(R.id.quartersTotal, quartersSum);
                dimesSum = getField(R.id.dimes, 0.1);
                setSumView(R.id.dimesTotal, dimesSum);
                nickelsSum = getField(R.id.nickels, 0.05);
                setSumView(R.id.nickelsTotal, nickelsSum);
                penniesSum = getField(R.id.pennies, 0.01);
                setSumView(R.id.penniesTotal, penniesSum);
                break;
            case R.id.rollsFragment:
                quartersRollSum = getField(R.id.quartersRolls, 10);
                setSumView(R.id.quartersRollsTotal, quartersRollSum);
                dimesRollSum = getField(R.id.dimesRolls, 5);
                setSumView(R.id.dimesRollsTotal, dimesRollSum);
                nickelsRollSum = getField(R.id.nicklesRolls, 2);
                setSumView(R.id.nickelsRollsTotal, nickelsRollSum);
                penniesRollSum = getField(R.id.penniesRolls, 0.5);
                setSumView(R.id.penniesRollsTotal, penniesRollSum);
                break;
        }
        BigDecimal[] values = {miscBills, tensSum, fivesSum, onesSum, quartersSum, dimesSum, nickelsSum, penniesSum, quartersRollSum, dimesRollSum, nickelsRollSum, penniesRollSum};
        BigDecimal sum = createBigDecimal(0);
        for (BigDecimal i : values) {
            sum = sum.add(i);
        }
        setSumView(R.id.totalView, sum);
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