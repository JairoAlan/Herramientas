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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_musica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_musica extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_musica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_musica.
     */
    View vista;
    ImageButton musica1, musica2, btnprev, btnpause, btnplay, btnnext, btnstop;
    TextView tvNomCan;
    boolean reproduciendo = false;
    ArrayList<MediaPlayer> canciones = new ArrayList<>();
    ArrayList<String> nombresCanciones = new ArrayList<>();
    int currentIndex = 0;

    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_musica, container, false);

        musica1 = (ImageButton) vista.findViewById(R.id.musica1);
        musica2 = (ImageButton) vista.findViewById(R.id.musica2);
        btnprev = (ImageButton) vista.findViewById(R.id.btnprev);
        btnpause = (ImageButton) vista.findViewById(R.id.btnpause);
        btnplay = (ImageButton) vista.findViewById(R.id.btnplay);
        btnnext = (ImageButton) vista.findViewById(R.id.btnnext);
        btnstop = (ImageButton) vista.findViewById(R.id.btnstop);
        tvNomCan = (TextView) vista.findViewById(R.id.tvNomCan);

        // Initialize songs
        canciones.add(MediaPlayer.create(getContext(), R.raw.judith));
        canciones.add(MediaPlayer.create(getContext(), R.raw.replica));
        canciones.add(MediaPlayer.create(getContext(), R.raw.the_shooting_star));

        // Initialize song names
        nombresCanciones.add("Judith");
        nombresCanciones.add("Replica");
        nombresCanciones.add("The Shooting Star");

        // Display the name of the first song
        updateSongName();

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

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        return vista;
    }

    private void play() {
        MediaPlayer mp = canciones.get(currentIndex);
        if (!mp.isPlaying()) {
            musica2.setVisibility(View.VISIBLE);
            musica1.setVisibility(View.GONE);
            mp.start();
        }
    }

    private void pause() {
        MediaPlayer mp = canciones.get(currentIndex);
        if (mp.isPlaying()) {
            musica2.setVisibility(View.GONE);
            musica1.setVisibility(View.VISIBLE);
            mp.pause();
        }
    }

    private void stop() {
        MediaPlayer mp = canciones.get(currentIndex);
        if (mp.isPlaying()) {
            mp.stop();
            try {
                mp.prepare();
                mp.seekTo(0);
                musica2.setVisibility(View.GONE);
                musica1.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void next() {
        stop();
        musica2.setVisibility(View.GONE);
        musica1.setVisibility(View.VISIBLE);
        currentIndex = (currentIndex + 1) % canciones.size();
        play();
        updateSongName();
    }

    private void previous() {
        stop();
        currentIndex = (currentIndex - 1 + canciones.size()) % canciones.size();
        play();
        updateSongName();
    }

    private void updateSongName() {
        tvNomCan.setText(nombresCanciones.get(currentIndex));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (MediaPlayer mp : canciones) {
            if (mp != null) {
                mp.release();
            }
        }
    }
}
