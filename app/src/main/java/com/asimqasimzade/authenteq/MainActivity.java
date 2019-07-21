package com.asimqasimzade.authenteq;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    private NavController navigationController;
    private ViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationController = findNavController(findViewById(R.id.navigationHostFragment));
        NavigationUI.setupActionBarWithNavController(this, navigationController);
    }

/*    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        return parent;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        return navigationController.navigateUp();
    }


}
