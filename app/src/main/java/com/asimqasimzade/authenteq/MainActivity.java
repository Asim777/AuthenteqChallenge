package com.asimqasimzade.authenteq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import static androidx.navigation.Navigation.findNavController;


public class MainActivity extends AppCompatActivity {
    private NavController navigationController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigationController = findNavController(findViewById(R.id.navigationHostFragment));
        NavigationUI.setupActionBarWithNavController(this, navigationController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navigationController.navigateUp();
    }
}
