package com.gobernanza.pruebalogin;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class ReporteFragment extends Fragment  {

    Button btnSiapame, btnObrasPublicas, btnAlumPub, btnAsePub, btnGarden;

   public String NameSiapame = "REPORTE SIAPAME";
    public String NameObraspublicas = "REPORTE OBRAS PUBLICAS";
    public String NameAlumPub = "REPORTE ALUMBRADO PUBLICO";
    public  String NameAseoPub = "REPORTE ASEO PUBLICO";
    public String NameGarden = "REPORTE PARQUES Y JARDINES";
    public ReporteFragment() {
        super(R.layout.fragment_reporte);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        btnSiapame = requireView().findViewById(R.id.buttonSIAPAME);
        btnObrasPublicas = requireView().findViewById(R.id.buttonObrasPublicas);
        btnAlumPub = requireView().findViewById(R.id.buttonaAlumbradoPublico);
        btnAsePub = requireView().findViewById(R.id.buttonAseoPublico);
        btnGarden = requireView().findViewById(R.id.buttonPark);

        btnSiapame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.buttonSIAPAME){

                    bundle.putString("NombreFrame", NameSiapame);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment,bundle);

                }
            }
        });

        btnObrasPublicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonObrasPublicas){
                    bundle.putString("NombreFrame", NameObraspublicas);
                   Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment, bundle);
                }
            }
        });

        btnAlumPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonaAlumbradoPublico){
                    bundle.putString("NombreFrame", NameAlumPub);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment,bundle);
                }
            }
        });

        btnAsePub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.buttonAseoPublico){
                    bundle.putString("NombreFrame", NameAseoPub);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment,bundle);
                }
            }
        });

        btnGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonPark){
                    bundle.putString("NombreFrame", NameGarden);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment,bundle);
                }
            }
        });
    }

}