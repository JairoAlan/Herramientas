package com.example.practicaherramientas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practicaherramientas.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Fragment_linterna fragmentLinterna = new Fragment_linterna();
    Fragment_musica fragmentMusica = new Fragment_musica();
    Fragment_nivel fragmentNivel = new Fragment_nivel();
    ImageView iv_engranes_sin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iv_engranes_sin = findViewById(R.id.iv_engranes_sin);

        bottomNavigationView = findViewById(R.id.menu);
        // getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragmentLinterna).commit();

        binding.menu.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.linterna){
                item.setIcon(getResources().getDrawable(R.drawable.linterna2));
                iv_engranes_sin.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragmentLinterna).commit();
            }

            if(item.getItemId() == R.id.musica){
                item.setIcon(getResources().getDrawable(R.drawable.musica2));
                iv_engranes_sin.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragmentMusica).commit();
            }

            if(item.getItemId() == R.id.nivel){
                item.setIcon(getResources().getDrawable(R.drawable.nivel2));
                iv_engranes_sin.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragmentNivel).commit();
            }
            return true;
        });

    }
}