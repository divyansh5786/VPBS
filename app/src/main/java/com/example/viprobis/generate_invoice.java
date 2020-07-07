package com.example.viprobis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class generate_invoice extends AppCompatActivity implements Adapter.OnClicker{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_invoice);
        Toolbar toolbar = findViewById(R.id.in_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invoice Generation");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.details);
        String[] transfer = {"remote","mouse"};
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this,transfer));
        FloatingActionButton add_prod = findViewById(R.id.add_prod);
        add_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inttoadd_prod = new Intent(generate_invoice.this,add_invoice_prod.class);
                startActivity(inttoadd_prod);
            }
        });


    FloatingActionButton barcode = findViewById(R.id.barcode);
        barcode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            IntentIntegrator integrator = new IntentIntegrator(generate_invoice.this);

            integrator.setPrompt("Scan");
            integrator.setCameraId(0);
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        }
    });

        FloatingActionButton confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inttoconfirm = new Intent(generate_invoice.this,Confirm.class);
                startActivity(inttoconfirm);
            }
        });

}


    @Override
    public void onItemClick(int position) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {

                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}