package com.gobernanza.pruebalogin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText nombre, apellido, correo, password, CP;
    TextView Return;
    Button regis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        setTitle("REGISTRATE");

        nombre = findViewById(R.id.editTextName);
        apellido = findViewById(R.id.editTextLLastName);
        correo = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        CP = findViewById(R.id.editTextTextCP);
        Return = findViewById(R.id.textViewReturnSign);
        regis = findViewById(R.id.ButtonRegistro);

        Return.setOnClickListener(this);
        regis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.textViewReturnSign:
                GoToSignIn();
                break;
            case R.id.ButtonRegistro:
                if (!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty() && ! CP.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                GoToHome(task.getResult().getUser().getEmail() != null ? task.getResult().getUser().getEmail() : "");
                            }else {
                                showAlert();
                            }
                        }
                    });

                }
                break;


        }
    }

    private void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage("Se ha producido un error al autenticar el usuario")
                .setPositiveButton("Aceptar", null)
                .show();
      //  Toast.makeText(this,"se ha producido un error al autenticar el usuario",Toast.LENGTH_LONG);
    }

    private void GoToHome(String emaail) {
        Intent secundario = new Intent(this,HomeActivity2.class);
        secundario.putExtra("nombre", nombre.getText().toString());
       // secundario.putExtra("apellido", (CharSequence) apellido);
       // secundario.putExtra("cp", (CharSequence) CP);
        secundario.putExtra("correoEmail", correo.getText().toString());
        startActivity(secundario);
    }


    private void GoToSignIn() {
        Intent primario = new Intent(this,MainActivity.class);
        startActivity(primario);
    }
}