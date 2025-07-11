package com.example.uthrestapi250;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uthrestapi250.PersonAdapter;
import com.example.uthrestapi250.Config.Personas;
import com.example.uthrestapi250.Config.RestApiMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Personas> personList;
    PersonAdapter adapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listview);
        personList = new ArrayList<>();

        Personas p = new Personas();
        p.setId("1");
        p.setNombres("Juan");
        p.setApellidos("Perez");
        p.setTelefono("9999-9999");
        p.setFoto(""); // sin imagen
        personList.add(p);

        adapter = new PersonAdapter(this, personList);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        loadPersons();


    }

    private void loadPersons() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, RestApiMethods.EndpointGetPersons,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object = response.getJSONObject(i);

                            Personas person = new Personas();
                            person.setId(object.getString("id"));
                            person.setNombres(object.getString("nombres"));
                            person.setApellidos(object.getString("apellidos"));
                            person.setDireccion(object.getString("direccion"));
                            person.setTelefono(object.getString("telefono"));
                            person.setFechanac(object.getString("fechanac"));
                            person.setFoto(object.getString("foto"));

                            personList.add(person);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );

        requestQueue.add(request);
    }
}

