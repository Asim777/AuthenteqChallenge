package com.asimqasimzade.authenteq;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    private NavController navigationController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationController = findNavController(findViewById(R.id.navigationHostFragment));
        NavigationUI.setupActionBarWithNavController(this, navigationController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navigationController.navigateUp();
    }
}
