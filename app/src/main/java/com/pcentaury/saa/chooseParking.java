//  chooseParking.java
//  Project:    Saa
//  Created by SDE. Alfredo Jiménez Miguel on 08/08/19.
//  Copyright © 2019 com.pcentaury All rights reserved.
//  Source Code to Geolocation: https://academiaandroid.com/proyecto-geolocalizacion-android/
//  Adapted by Author
package com.pcentaury.saa;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class chooseParking extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//region atributes

    private String tvLatitud;
    private String tvLongitud;
    private LocationManager locManager;
    private LocationListener locListener;
    private Location loc;
    private EditText etLatitud, etLongitud;

//endregion

//region Properties

    public String getTvLatitud() {
        return tvLatitud;
    }
    public void setTvLatitud(String tvLatitud) {
        this.tvLatitud = tvLatitud;
    }
    public String getTvLongitud() {
        return tvLongitud;
    }
    public void setTvLongitud(String tvLongitud) {
        this.tvLongitud = tvLongitud;
    }

    public Location [] estaciones;
    public int origenRuta;
    public int finalRuta;
    public double datosParadaOrigen;
    public double datosParadaDestino;
//endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_parking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fabLocation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Geolocalizando tu Ubicación", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                GPSLocation();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        etLatitud = (EditText)findViewById(R.id.latitudValue);
        etLongitud = (EditText)findViewById(R.id.longitudValue);
        etLatitud.setEnabled(false);
        etLongitud.setEnabled(false);
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
        getMenuInflater().inflate(R.menu.choose_parking, menu);
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void GPSLocation() {
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder alertActuacionGPS1 = new AlertDialog.Builder(this);
            alertActuacionGPS1.setTitle("GPS");
            alertActuacionGPS1.setMessage("No se tienen permisos para tu geolocalización").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // FIRE ZE MISSILES!
                }
            });
            alertActuacionGPS1.show();
            return;
        } else {
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc != null) {
                AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(this);
                alertActuacionGPS.setTitle("GPS");
                alertActuacionGPS.setMessage("Geolocalizando tu Ubicación").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etLatitud.setText(String.valueOf(loc.getLatitude()));
                        etLongitud.setText(String.valueOf(loc.getLongitude()));
                    }
                });
                alertActuacionGPS.show();
            } else {
                //Si el primer Proveedor falla se buscara el de Red telefónica
                loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (loc != null) {
                    AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(this);
                    alertActuacionGPS.setTitle("GPS");
                    alertActuacionGPS.setMessage("Geolocalizando tu Ubicación").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            etLatitud.setText(String.valueOf(loc.getLatitude()));
                            etLongitud.setText(String.valueOf(loc.getLongitude()));
                        }
                    });
                    alertActuacionGPS.show();
                } else {
                    //Si el segundo Proveedor falla se buscaran los valores recueprados de otra instancia previa
                    loc = locManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    if (loc != null) {
                        AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(this);
                        alertActuacionGPS.setTitle("GPS");
                        alertActuacionGPS.setMessage("Geolocalizando tu Ubicación").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                etLatitud.setText(String.valueOf(loc.getLatitude()));
                                etLongitud.setText(String.valueOf(loc.getLongitude()));
                            }
                        });
                        alertActuacionGPS.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No tengo Acceso a tu Geolocalización", Toast.LENGTH_SHORT).show();
                        double latitudGPS = 0, longitudGPS = 0, altitudGPS = 0, precisionGPS = 0;
                        etLatitud.setText("" + latitudGPS);
                        etLongitud.setText("" + longitudGPS);
                    }
                }

            }
        }

    }

    //region CalculateRoutes
    public void distancias (Location origen, Location destino){
        datosParadaOrigen = origen.distanceTo(estaciones[0]);
        datosParadaDestino = destino.distanceTo(estaciones[0]);

        for (int i= 1; i< estaciones.length; i++){
            if (origen.distanceTo(estaciones[i]) < datosParadaOrigen){
                origenRuta = i;
                datosParadaOrigen = origen.distanceTo(estaciones[i]);
            }
            if (destino.distanceTo(estaciones[i]) < datosParadaDestino){
                finalRuta = i;
                datosParadaDestino = destino.distanceTo(estaciones[i]);
            }

        }
    }

    public double sumaDistMetros(){
        return datosParadaOrigen + datosParadaDestino;
    }
//endregion
}
