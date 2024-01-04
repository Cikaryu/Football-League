package com.uts.uasmobprogug214;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.uts.uasmobprogug214.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ResultFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch  (item.getItemId()){
                case R.id.result:
                    replaceFragment(new ResultFragment());
                    break;
                case R.id.league:
                    replaceFragment(new LeagueFragment());
                    break;
                case R.id.goalsking:
                    replaceFragment(new GoalsKingFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout.fragment);
        fragmentTransaction.commit();
    }
}