//  MainActivity.java
//  Project:    Saa
//  Created by SDE. Alfredo Jiménez Miguel on 08/07/19.
//  Copyright © 2019 com.pcentaury All rights reserved.
//
package com.pcentaury.saa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button btEncontrarEstacionamiento, btAdministrarMetodosPago, btCerrarSesion;
    //btnFindParking, btnMainPayManagement, btnMainCloseSession

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btEncontrarEstacionamiento = (Button)findViewById(R.id.btnMainFindParking);
        btEncontrarEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindParking();
            }
        });

        btAdministrarMetodosPago = (Button)findViewById(R.id.btnMainPayManagement);
        btAdministrarMetodosPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PayManagement();
            }
        });
        btCerrarSesion = (Button)findViewById(R.id.btnMainCloseSession);
        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CloseSession();
            }
        });
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Toast errorVersion = Toast.makeText(getApplicationContext(),
                    "Ir a Home"
                    , Toast.LENGTH_LONG);
            errorVersion.show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "Galeria de Fotos", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(this);
            alertActuacionGPS.setTitle("SLIDE SHOW");
            alertActuacionGPS.setMessage("IR A GALERÍA?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // FIRE ZE MISSILES!
                }
            });
            alertActuacionGPS.show();
        } else if (id == R.id.nav_tools) {
            //Inicialización
            AlertDialog.Builder nuevaAlerta = new AlertDialog.Builder(this);
            //establecer título
            nuevaAlerta.setTitle("Herramientas");
            //Mensaje de Cuerpo
            nuevaAlerta.setMessage("Mostrar Herramientas?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                // Establecer acciones para Aceptar
                public void onClick(DialogInterface dialog, int id) {
                    //Acciones para el botón Aceptar

                }
                //establecer acciones para botón cancelar
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Acciones para el botón cancel
                } });
            //Mostrar diálogo
            nuevaAlerta.show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnMainFindParking:

                break;
            case R.id.btnMainPayManagement:
                PayManagement();
                break;
            case  R.id.btnMainCloseSession:
                CloseSession();
                break;
        }
    }*/

    private void FindParking(){
        Intent goChooseParking = new Intent(this, chooseParking.class);
        startActivity(goChooseParking);
        // this.finish();
    }

   /* private void PayManagement(){
        Intent goPayManager = new Intent(this, payManager.class);
        //goPayManager.putExtra("userName", user);
        startActivity(goPayManager);
        // this.finish();
    }*/

    private void CloseSession(){
        Intent goCloseSession = new Intent(this, ingresar.class);
        startActivity(goCloseSession);
        this.finish();
    }
}
