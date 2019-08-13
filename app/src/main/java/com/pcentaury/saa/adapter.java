package com.pcentaury.saa;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    String [][]datos;
    int[] distancias;
    private ArrayList <parkings> itemList;


    public adapter(Context contexto, ArrayList<parkings> itemList) {
        this.contexto = contexto;
        this.itemList = itemList;
    }

    public adapter(Context context, String[][] datos) {
        this.contexto = context;
        this.datos = datos;
    }

    //region Constructor invalido
    /*public adapter(Context contexto, String [][]datos, int[] distancias) {
        this.contexto = contexto;
        this.datos = datos;
        this.distancias = distancias;

        //inflater = (LayoutInflater)contexto.getSystemServiceName(contexto.LAYOUT_INFLATER_SERVICE);
     public adapter(Context contexto, ArrayList<parkings> itemList) {
        this.contexto = contexto;
        this.itemList = itemList;
    }
    }*/
//endregion
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        parkings item = (parkings)getItem(i);
        convertView = LayoutInflater.from(contexto).inflate(R.layout.parking_description, null);
        TextView nombreEst = (TextView)convertView.findViewById(R.id.tvParkingName);
        TextView direcEst = (TextView)convertView.findViewById(R.id.tvDireccionValue);
        TextView distEst = (TextView)convertView.findViewById(R.id.tvDistanceValue);
        RatingBar calif = (RatingBar)convertView.findViewById(R.id.ratingBarParking);


        nombreEst.setText(item.getParkingName());
        direcEst.setText(item.getParkingAdress());
        distEst.setText(item.getDistance()+" km");
        calif.setRating((float)item.getParkingRating());
        calif.setEnabled(false);

        return convertView;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        for(int i=0; i<itemList.size();i++){

        }
        return position;
    }


}
