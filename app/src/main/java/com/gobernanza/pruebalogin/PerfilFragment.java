package com.gobernanza.pruebalogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class PerfilFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button btnEditPerfil, btnGuardarPerfil;
    EditText name, lastName, Cp, email;

    //String EmCorreo = emaill;
    String ValueName, ValueLastName, ValueCp;

    public PerfilFragment() {
        super(R.layout.fragmen_perfil);
    }
    String correEmail;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Bundle arguments = getArguments();
        if (arguments != null) {
             correEmail = arguments.getString("correoEmail");
             ValueName = arguments.getString("NameValues");
             ValueLastName = arguments.getString("LastNameValues");
            ValueCp = arguments.getString("CpValues");
            // Usa el correo electrónico recibido como desees en el Fragment
        }

        btnEditPerfil = requireView().findViewById(R.id.buttonEditPerfil);
        btnGuardarPerfil = requireView().findViewById(R.id.buttonSalvarPerfil);
        name = requireView().findViewById(R.id.etNamePerfil);
        lastName = requireView().findViewById(R.id.etLastNamePerfil);
        Cp = requireView().findViewById(R.id.editCpPerfil);
        email = requireView().findViewById(R.id.Correo);

        name.setText(ValueName);
        lastName.setText(ValueLastName);
        Cp.setText(ValueCp);
        email.setText(correEmail);

        btnEditPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuardarPerfil.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                lastName.setEnabled(true);
                Cp.setEnabled(true);
                email.setText(correEmail);
                email.setEnabled(true);
                btnGuardarPerfil.setEnabled(true);
                btnEditPerfil.setVisibility(View.INVISIBLE);

            }
        });

        btnGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", name.getText().toString());
                data.put("lastName", lastName.getText().toString());
                data.put("Cp",Cp.getText().toString());

                db.collection("users").document(correEmail).set(data);

                btnGuardarPerfil.setVisibility(View.INVISIBLE);
                name.setEnabled(false);
                lastName.setEnabled(false);
                Cp.setEnabled(false);
                btnEditPerfil.setVisibility(View.VISIBLE);
            }
        });

 }

    }