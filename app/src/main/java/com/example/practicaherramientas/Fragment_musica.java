package com.example.practicaherramientas;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment_musica extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    View vista;
    ImageButton musica1, musica2, btnprev, btnpause, btnplay, btnnext, btnstop;
    TextView tvNomCan;
    ArrayList<Integer> canciones = new ArrayList<>();
    int cancionActual = 0; // Índice de la canción actual
    MediaPlayer mediaPlayer;

    public Fragment_musica() {
        // Required empty public constructor
    }

    public static Fragment_musica newInstance(String param1, String param2) {
        Fragment_musica fragment = new Fragment_musica();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Inicialización de las canciones
        canciones.add(R.raw.judith);
        canciones.add(R.raw.replica);
        canciones.add(R.raw.the_shooting_star);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_musica, container, false);

        // Inicialización de botones u otros elementos de interfaz
        musica1 = vista.findViewById(R.id.musica1);
        musica2 = vista.findViewById(R.id.musica2);
        btnprev = vista.findViewById(R.id.btnprev);
        btnpause = vista.findViewById(R.id.btnpause);
        btnplay = vista.findViewById(R.id.btnplay);
        btnnext = vista.findViewById(R.id.btnnext);
        btnstop = vista.findViewById(R.id.btnstop);

        // Configuración de listeners para botones de reproducción
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        // Inicializar el MediaPlayer con la primera canción
        initializeMediaPlayer();

        return vista;
    }

    private void initializeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Liberar recursos del MediaPlayer actual
        }
        mediaPlayer = MediaPlayer.create(getContext(), canciones.get(cancionActual));
    }

    private void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void next() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        cancionActual++;
        if (cancionActual >= canciones.size()) {
            cancionActual = 0; // Vuelve al inicio si llega al final de la lista
        }
        initializeMediaPlayer();
        play(); // Reproducir la siguiente canción
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            initializeMediaPlayer(); // Reinicializar el MediaPlayer para que esté listo para la próxima reproducción
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Liberar recursos del MediaPlayer
        }
    }
}
