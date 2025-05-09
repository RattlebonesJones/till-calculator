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
    BillsFragment firstFragment;
    CoinsFragment secondFragment;
    RollsFragment thirdFragment;
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
        firstFragment = new BillsFragment();
        secondFragment = new CoinsFragment();
        thirdFragment = new RollsFragment();
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

    private void setSumView(int id, BigDecimal val) {
        TextView view = ((TextView) findViewById(id));
        if (view != null) {
            view.setText(val.toString());
        }
    }

    private void setBillsSums() {
        setSumView(R.id.miscBillsTotal, miscBills);
        setSumView(R.id.tensTotal, tensSum);
        setSumView(R.id.fivesTotal, fivesSum);
        setSumView(R.id.onesTotal, onesSum);
    }

    private void setCoinsSums() {
        setSumView(R.id.quartersTotal, quartersSum);
        setSumView(R.id.dimesTotal, dimesSum);
        setSumView(R.id.nickelsTotal, nickelsSum);
        setSumView(R.id.penniesTotal, penniesSum);
    }

    private void setRollsSums() {
        setSumView(R.id.quartersRollsTotal, quartersRollSum);
        setSumView(R.id.dimesRollsTotal, dimesRollSum);
        setSumView(R.id.nickelsRollsTotal, nickelsRollSum);
        setSumView(R.id.penniesRollsTotal, penniesRollSum);
    }

    public void getSum(View view) {
        switch (bottomNavigationView.getSelectedItemId()) {
            case R.id.billsFragment:
                miscBills = getField(R.id.miscBills, 1);
                tensSum = getField(R.id.tens, 10);
                fivesSum = getField(R.id.fives, 5);
                onesSum = getField(R.id.ones, 1);
                setBillsSums();
                break;
            case R.id.coinsFragment:
                quartersSum = getField(R.id.quarters, 0.25);
                dimesSum = getField(R.id.dimes, 0.1);
                nickelsSum = getField(R.id.nickels, 0.05);
                penniesSum = getField(R.id.pennies, 0.01);
                setCoinsSums();
                break;
            case R.id.rollsFragment:
                quartersRollSum = getField(R.id.quartersRolls, 10);
                dimesRollSum = getField(R.id.dimesRolls, 5);
                nickelsRollSum = getField(R.id.nicklesRolls, 2);
                penniesRollSum = getField(R.id.penniesRolls, 0.5);
                setRollsSums();
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
                setBillsSums();
                return true;

            case R.id.coinsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMain, secondFragment).commit();
                setCoinsSums();
                return true;

            case R.id.rollsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMain, thirdFragment).commit();
                setRollsSums();
                return true;
        }
        return false;
    }
}