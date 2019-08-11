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
import android.support.v4.app.FragmentManager;
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
    //region Artibutes
    private Button btEncontrarEstacionamiento, btAdministrarMetodosPago, btCerrarSesion;
    private FragmentManager fragMg;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btEncontrarEstacionamiento = (Button)findViewById(R.id.btnMainFindParking);
        btEncontrarEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FindParking();
                Toast.makeText(getApplicationContext(),"ENCONTRAR ESTACIONAMIENTO", Toast.LENGTH_SHORT).show();
            }
        });

        btAdministrarMetodosPago = (Button)findViewById(R.id.btnMainPayManagement);
        btAdministrarMetodosPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayManagement();
                Toast.makeText(getApplicationContext(), "METODOS DE PAGO", Toast.LENGTH_SHORT).show();
            }
        });

        btCerrarSesion = (Button)findViewById(R.id.btnMainCloseSession);
        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CloseSession();
            }
        });*/
        fragMg = getSupportFragmentManager();
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
//nav_configuracion, nav_balance, nav_tiempo, nav_tools

        if (id== R.id.main_nav_barCode) {
            fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentInicio()).commit();
        }else if (id == R.id.main_nav_findParking) {
            fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentChooseParking()).commit();

        }else if(id == R.id.main_nav_managePayMethods){
            fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentPayManager()).commit();

        }else if (id == R.id.main_nav_closeSession) {
            //fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentInicio()).commit();
            this.finish();

        }else if (id == R.id.nav_configuracion) {
            Toast.makeText(getApplicationContext(),"Configuracion", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_balance) {
            Toast.makeText(getApplicationContext(), "Balance", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tiempo) {
            Toast.makeText(getApplicationContext(), "Tiempo", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tools) {
            Toast.makeText(getApplicationContext(), "Herramientas", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void FindParking(){
        fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentChooseParking()).commit();
        // this.finish();
    }
    private void PayManagement() {
        fragMg.beginTransaction().replace(R.id.mainContainer, new FragmentPayManager()).commit();
    }
    private void CloseSession(){
        this.finish();
    }


}

