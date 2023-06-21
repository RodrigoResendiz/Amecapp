package com.gobernanza.pruebalogin;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class ReporteFragment extends Fragment  {

    Button btnSiapame, btnObrasPublicas, btnAlumPub, btnAsePub, btnGarden;
    public ReporteFragment() {
        super(R.layout.fragment_reporte);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSiapame = requireView().findViewById(R.id.buttonSIAPAME);
        btnObrasPublicas = requireView().findViewById(R.id.buttonObrasPublicas);

        btnSiapame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.buttonSIAPAME){
                   // Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_reportesActivity);
                }
            }
        });

        btnObrasPublicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonObrasPublicas){
                    Navigation.findNavController(view).navigate(R.id.action_navigation_dashboard_to_obrasPublicasFragment);
                }
            }
        });
    }

}