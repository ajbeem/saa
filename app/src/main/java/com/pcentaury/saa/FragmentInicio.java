package com.pcentaury.saa;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment {
    //region Artibutes
        //private final int[] botonesFragmentInicio = {R.id.btnFragmentFindParking, R.id.btnFragmentPayManagement, R.id.btnFragmentCloseSession};
        //private Button btEncontrarEstacionamiento, btAdministrarMetodosPago, btCerrarSesion;
    //btnFindParking, btnMainPayManagement, btnMainCloseSession
    //endregion
    public FragmentInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InicioView = inflater.inflate(R.layout.fragment_fragment_inicio, container, false);
        return InicioView;
    }

}
