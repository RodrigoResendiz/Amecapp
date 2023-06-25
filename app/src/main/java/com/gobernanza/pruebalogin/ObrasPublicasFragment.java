package com.gobernanza.pruebalogin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ObrasPublicasFragment extends Fragment {

    private Spinner CatObPub;
    private ArrayList<String> lista = new ArrayList<String>();

    public ObrasPublicasFragment() {
        super(R.layout.fragment_obras_publicas);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Para cambiar el titutlo de la barra superior cuando es fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("REPORTE OBRAS PUBLICAS ");
        iniciarSpinner();
        addValores();
        arrancarSpinner();

    }

    private void arrancarSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,lista);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CatObPub.setAdapter(adapter);

        CatObPub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //aqui con la instruccion siguiente indicas que item se selecciono y puedes hacer tu codigo siguiente.
                String Seleccion =  (String) CatObPub.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void addValores() {
        lista.add("Seleccione tipo de reporte:");
        lista.add("Reparacion de rejillas pluvial");
        lista.add("Repavimentacion");
        lista.add("Baches");
        lista.add("Topografia para pavimentacion");
        lista.add("Otros");
    }

    private void iniciarSpinner() {
        CatObPub = requireView().findViewById(R.id.spinnerFallas);
    }

}