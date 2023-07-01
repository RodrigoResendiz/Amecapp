package com.gobernanza.pruebalogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Correo, contraseña;
    Button Login;
    TextView registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Login app");
        Correo = findViewById(R.id.editTexEmail);
        contraseña = findViewById(R.id.editTextPassword);
        Login = findViewById(R.id.buttonLogin);
        registro = findViewById(R.id.textViewRegistro);

        Login.setOnClickListener(this);
        registro.setOnClickListener(this);


    }
        @Override
        public void onClick (View v) {
            int id = v.getId();
            switch (id) {
                case R.id.buttonLogin:
                    if (!Correo.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()) {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(Correo.getText().toString(), contraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    GoToHome(task.getResult().getUser().getEmail() != null ? task.getResult().getUser().getEmail() : "");
                                } else {
                                    showAlert();
                                }
                            }
                        });
                    }
                    break;
                case R.id.textViewRegistro:
                    GoToRegistro();
            }

        }

    private void GoToRegistro() {
    Intent i = new Intent(this,Registro.class);
    startActivity(i);
    finish();
    }

    private void showAlert() {
        Toast.makeText(this,"se ha producido un error al autenticar el usuario",Toast.LENGTH_LONG);
    }

    private void GoToHome(String email) {
        Intent intent = new Intent(this,HomeActivity2.class);
        intent.putExtra("correoEmail",email);
        startActivity(intent);
    }

}