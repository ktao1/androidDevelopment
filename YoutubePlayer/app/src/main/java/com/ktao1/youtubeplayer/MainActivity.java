package com.ktao1.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ktao1.youtubeplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnSingle = binding.mainBtnPlaySingle;
        Button btnStandalon = binding.mainBtnStandalone;
        btnSingle.setOnClickListener(this);
        btnStandalon.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.main_btnPlaySingle:
                intent = new Intent(this, YoutubeActivity.class);
                break;
            case R.id.main_btnStandalone:
                intent = new Intent(this, StandaloneActivity.class);
                break;
            default:
        }

        if(intent!= null){
            startActivity(intent);
        }

    }
}