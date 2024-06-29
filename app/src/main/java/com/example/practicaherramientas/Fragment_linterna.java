package com.example.practicaherramientas;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_linterna#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_linterna extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_linterna() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_linterna.
     */
    // Parametros de la aplicacion
    ImageButton lamp1,lamp2;
    Button btnEncender;
    View vista;
    // Para poder acceder a la camara del celular se crea una instancia para el manager.
    CameraManager cameraManager;
    String camaraid;
    boolean encendido;

    // TODO: Rename and change types and number of parameters
    public static Fragment_linterna newInstance(String param1, String param2) {
        Fragment_linterna fragment = new Fragment_linterna();
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
        vista = inflater.inflate(R.layout.fragment_linterna, container, false);

        btnEncender = (Button) vista.findViewById(R.id.btnEncender);
        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        lamp1 = (ImageButton) vista.findViewById(R.id.lamp1);
        lamp2 = (ImageButton) vista.findViewById(R.id.lamp2);

        try {
            camaraid = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException ex) {
            ex.printStackTrace();
        }

        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (encendido) {
                        apagarLuz();
                    } else {
                        encenderLuz();
                    }
                } catch (CameraAccessException x) {
                    Toast.makeText(getActivity(), "Problemas con la Luz " + x, Toast.LENGTH_SHORT).show();
                }
            }
        });

        lamp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(encendido){
                        apagarLuz();
                    }else{
                        encenderLuz();
                    }
                }catch (Exception ex){
                    Toast.makeText(getActivity(), "Problemas con la Camara " + ex,Toast.LENGTH_LONG).show();
                }
            }
        });

        lamp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(encendido){
                        apagarLuz();
                    }else{
                        encenderLuz();
                    }
                }catch (Exception ex){
                    Toast.makeText(getActivity(), "Problemas con la Camara " + ex,Toast.LENGTH_LONG).show();
                }
            }
        });

        return vista;
    }

    private void encenderLuz() throws CameraAccessException {
        cameraManager.setTorchMode(camaraid, true);
        encendido = true;
        lamp2.setVisibility(View.VISIBLE);
        lamp1.setVisibility(View.GONE);
    }

    private void apagarLuz() throws CameraAccessException {
        cameraManager.setTorchMode(camaraid, false);
        encendido = false;
        lamp2.setVisibility(View.GONE);
        lamp1.setVisibility(View.VISIBLE);
    }
}
