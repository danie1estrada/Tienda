package com.example.tienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.tienda.databinding.ActivityMainBinding;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        setup();
    }

    private void init() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        navController = navHostFragment.getNavController();
    }

    private void setup() {
        setupAppBar();
        setupNavController();
        setupBottomNavigationView();
    }

    private void setupAppBar() {
        HashSet<Integer> destinations = new HashSet<>();
        destinations.add(R.id.productsListFragment);
        destinations.add(R.id.providersListFragment);
        destinations.add(R.id.transactionsFragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(destinations).build();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
    }

    private void setupNavController() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()) {
                case R.id.productsListFragment:
                case R.id.providersListFragment:
                case R.id.transactionsFragment:
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                    break;
                default:
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    break;
            }
        });
    }

    private void setupBottomNavigationView() {
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }
}