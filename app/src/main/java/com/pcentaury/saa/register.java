package com.pcentaury.saa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {

    //region Atributes
    private EditText etCNames, etClastName, etCBirthDate, etCUserMail, etCUser, etCPassword;
    private RequestQueue requestQueue;
    private String urlLogin = "http://www.diot.esy.es/php/altaUsuariosJSON.php?";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FloatingActionButton fab = findViewById(R.id.fabRegisterSaa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegisterData();
            }
        });
        //region properties
        etCNames = (EditText) findViewById(R.id.etNames);
        etClastName = (EditText) findViewById(R.id.etlastName);
        etCBirthDate = (EditText) findViewById(R.id.etlastName);
        etCUserMail = (EditText) findViewById(R.id.etUserMail);
        etCUser = (EditText) findViewById(R.id.etUser);
        etCPassword = (EditText) findViewById(R.id.etPassword);
        //endregion
    }

    private void userRegisterData() {
        if (etCNames.getText().toString().isEmpty() || etClastName.getText().toString().isEmpty()
                || etCBirthDate.getText().toString().isEmpty() || etCUserMail.getText().toString().isEmpty()
                || etCUser.getText().toString().isEmpty() || etCPassword.getText().toString().isEmpty()) {
            AlertDialog.Builder camposVacios = new AlertDialog.Builder(this);
            camposVacios.setTitle("Advertencia");
            camposVacios.setMessage("Debes llenar todos los campos, revisa y vuelve a intentarlo").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                // Establecer acciones para Aceptar
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            camposVacios.show();

        }else {
            sendData(etCNames.getText().toString(), etClastName.getText().toString(), etCBirthDate.getText().toString(),
                    etCUserMail.getText().toString(), etCUser.getText().toString(), etCPassword.getText().toString());
        }
    }

    private void sendData(String nombres , String apellidos, String fechaNac, String eMail, String nickName, String pswd) {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("userName", nombres);
            jsonParams.put("lastName", apellidos);
            jsonParams.put("birthDate", fechaNac);
            jsonParams.put("userMail", eMail);
            jsonParams.put("user", nickName);
            jsonParams.put("pass", pswd);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error al convertir los datos", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Toast savedToast = Toast.makeText(getApplicationContext(),
                "Enviando datos", Toast.LENGTH_SHORT);
        savedToast.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, urlLogin, jsonParams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("answer").equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(),"" + response.getString("user")+"\n Utiliza tu contraseña para Ingresar", Toast.LENGTH_LONG).show();
                                goLogin();
                            } else {
                                Toast.makeText(getApplicationContext(),""+response.getString("answer")+"\n"+ response.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Error Al recibir los Datos, intentalo nuevamente" + e.getMessage(), Toast.LENGTH_LONG);
                            unsavedToast.show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "La comunicación con el servicio ha fallado Intentalo nuevamente" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void goLogin() {
            Intent goN = new Intent(this, ingresar.class);
            startActivity(goN);
            this.finish();
    }
}
