package com.example.viprobis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class paked extends AppCompatActivity {
EditText Barcode ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paked);
        Toolbar toolbar = findViewById(R.id.pa_toolbar);
        Barcode = findViewById(R.id.edit_barcode_p);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Worker's Name");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final CharSequence[] options = { "With Barcode", "Without Barcode\n(Generate Barcode)" };
        AlertDialog.Builder builder = new AlertDialog.Builder(paked.this);
        builder.setTitle("Choose the type of product");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("With Barcode"))
                {
                    IntentIntegrator integrator = new IntentIntegrator(paked.this);

                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setOrientationLocked(false);
                    integrator.initiateScan();
                }
                if (options[item].equals("Without Barcode\n(Generate Barcode)"))
                    Toast.makeText(paked.this, "add the product", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Barcode.setText(result.getContents());
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}