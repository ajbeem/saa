package com.pcentaury.saa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ingresar extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin, btnForgotPassword, btnRegister;
    private EditText etUser, etPass;
    private TextView tvInfoArea;
    private RequestQueue requestQueue;
    private String urlLogin = "http://www.diot.esy.es/php/usersLoginJSON.php?";
    private SharedPreferences savedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //region Edit Text
        etUser = (EditText) findViewById(R.id.cmpUsernameIngresar);
        etPass = (EditText) findViewById(R.id.cmpPasswordIngresar);
        //end region

        //region Buttons
        btnLogin = (Button) findViewById(R.id.btnLoginIngresar);
        btnLogin.setOnClickListener(this);
        btnForgotPassword = (Button) findViewById(R.id.btnForgotPass);
        btnForgotPassword.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btnRegisterNew);
        btnRegister.setOnClickListener(this);
        //end region

        //region Text Views
        tvInfoArea = (TextView) findViewById(R.id.infoArea);
        //end region

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnForgotPass:
                AlertDialog.Builder forgotPassAlert = new AlertDialog.Builder(this);
                forgotPassAlert.setTitle("Forgot password");
                forgotPassAlert.setMessage("Olvidaste Tu contraseña?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                forgotPassAlert.show();
                break;
            case R.id.btnRegisterNew:
                AlertDialog.Builder newUser = new AlertDialog.Builder(this);
                newUser.setTitle("Register");
                newUser.setMessage("Deseas Registrarte?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                newUser.show();

                break;
            case R.id.btnLoginIngresar:
                if (Services.NetworkConnection(this)) {
                    //Toast.makeText(getApplicationContext(), "Conexión Estable", Toast.LENGTH_SHORT).show();
                    /*AlertDialog.Builder loginUser = new AlertDialog.Builder(this);
                    loginUser.setTitle("Login");
                    loginUser.setMessage("Iniciar Sesion?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    loginUser.show();*/
                    autenticarUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Verifica tu conexión a internet e intentalo nuevamente", Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }

    private void autenticarUser() {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("nUsuario", etUser.getText().toString());
            jsonParams.put("pswd", etPass.getText().toString());
        } catch (Exception e) {
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
                                tvInfoArea.setText("Bienvenido " + response.getString("user"));
                                startMainActivity(response.getString("user"));
                                Toast.makeText(getApplicationContext(), "Usuario Autenticado", Toast.LENGTH_SHORT).show();
                            } else {
                                tvInfoArea.setText("" + response.getString("answer"));
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

    private void startMainActivity(String user) {
        Intent goN = new Intent(this, MainActivity.class);
        goN.putExtra("userName", user);
        startActivity(goN);
        this.finish();
    }
}
