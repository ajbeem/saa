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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChooseParking extends Fragment {
//region atributes

    private String tvLatitud;
    private String tvLongitud;
    private LocationManager locManager;
    private LocationListener locListener;
    private Location loc;
    private EditText etLatitud, etLongitud;

//endregion

    public FragmentChooseParking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View chooseParking = inflater.inflate(R.layout.fragment_fragment_choose_parking, container, false);
        etLatitud = chooseParking.findViewById(R.id.fragmentlatitudValue);
        etLongitud = chooseParking.findViewById(R.id.fragmentlongitudTag);

        /*FloatingActionButton fab = chooseParking.findViewById(R.id.fragmentFabLocation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Geolocalizando tu Ubicación", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                GPSLocation();
            }
        });*/

       /* btEncontrarEstacionamiento = (Button) InicioView.findViewById(R.id.btnFragmentFindParking);
        btEncontrarEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // FindParking();
                Toast.makeText(getActivity(),"ENCONTRAR ESTACIONAMIENTO", Toast.LENGTH_SHORT).show();
            }
        });

        btAdministrarMetodosPago = (Button) InicioView.findViewById(R.id.btnFragmentPayManagement);
        btAdministrarMetodosPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PayManagement();
                Toast.makeText(getActivity(), "METODOS DE PAGO", Toast.LENGTH_SHORT).show();
            }
        });

        btCerrarSesion = (Button) InicioView.findViewById(R.id.btnFragmentCloseSession);
        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // CloseSession();
            }
        });*/

        return chooseParking;
    }

    private void GPSLocation() {
        locManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder alertActuacionGPS1 = new AlertDialog.Builder(getActivity());
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
                AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(getActivity());
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
                    AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(getActivity());
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
                        AlertDialog.Builder alertActuacionGPS = new AlertDialog.Builder(getActivity());
                        alertActuacionGPS.setTitle("GPS");
                        alertActuacionGPS.setMessage("Geolocalizando tu Ubicación").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                etLatitud.setText(String.valueOf(loc.getLatitude()));
                                etLongitud.setText(String.valueOf(loc.getLongitude()));
                            }
                        });
                        alertActuacionGPS.show();
                    } else {
                        Toast.makeText(getActivity(), "No tengo Acceso a tu Geolocalización", Toast.LENGTH_SHORT).show();
                        double latitudGPS = 0, longitudGPS = 0, altitudGPS = 0, precisionGPS = 0;
                        etLatitud.setText("" + latitudGPS);
                        etLongitud.setText("" + longitudGPS);
                    }
                }

            }
        }

    }

}
