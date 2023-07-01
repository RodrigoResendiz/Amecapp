package com.gobernanza.pruebalogin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.File;
import java.util.ArrayList;

public class ObrasPublicasFragment extends Fragment {

    Button btnCamara, btnGaleria;

    Uri imagenUri;

    int tomarFoto = 100;
    int selecionarImagen =200;
    String CarpetaRaiz = "MisFotosApp";
    String CarpetaImagenes= "Imagenes";
    String RutaImagen = CarpetaRaiz + CarpetaImagenes;
    String path;

    private Spinner CatObPub;

    private ArrayList<String> lista = new ArrayList<String>();
    private FusedLocationProviderClient fusedLocationClient;

    public ObrasPublicasFragment() {
        super(R.layout.fragment_obras_publicas);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Para cambiar el titutlo de la barra superior cuando es fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("REPORTE OBRAS PUBLICAS ");
        btnCamara = requireView().findViewById(R.id.buttonCamera);
        btnGaleria = requireView().findViewById(R.id.buttonGaleria);
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) requireContext(),
                    new String[]{android.Manifest.permission.CAMERA,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        }

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagnen();
            }
        });
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomaFoto();
            }
        });

        iniciarSpinner();
        addValores();
        arrancarSpinner();

    }

    private void tomaFoto() {
        String nombreImagen = "";
        File fileimagen = new File(Environment.getExternalStorageDirectory(),RutaImagen);
        boolean isCreada = fileimagen.exists();

        if (isCreada == false){
            isCreada = fileimagen.mkdirs();
        }//si no funciona el else intentar con otro if
        else {
            nombreImagen = new long[]{System.currentTimeMillis() / 1000} + ".jpg";
        }

        path = Environment.getExternalStorageDirectory()+File.separator+RutaImagen+File.separator+nombreImagen;
        File imagen = new File(path);

        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            String authorities = this.getActivity().getPackageName() + ".provider";
            Uri imagenUri = FileProvider.getUriForFile(requireContext(), authorities, imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
        }else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagen));
        }
        startActivityForResult(intent, tomarFoto);
    }


    private void seleccionarImagnen() {
        Intent Galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Galeria, selecionarImagen);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == selecionarImagen){
            imagenUri = data.getData();

        }else if (resultCode == RESULT_OK && requestCode == tomarFoto){
            MediaScannerConnection.scanFile(requireContext(), new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {

                }
            });

            Bitmap bitmap = BitmapFactory.decodeFile(path);

        }

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