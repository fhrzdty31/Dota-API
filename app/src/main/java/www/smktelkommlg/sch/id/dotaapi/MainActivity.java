package www.smktelkommlg.sch.id.dotaapi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<HeroModel> heroList = new ArrayList<>();
    private final String url = "https://api.opendota.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_hero);
        recyclerView.setAdapter(adapter);
        getVolley();
    }
    private void getVolley() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "/api/herostats", this::getData, error -> Toast.makeText(MainActivity.this, "Eror Data", Toast.LENGTH_LONG));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getData(String request) {
        try {
            JSONArray jsonArray = new JSONArray(request);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(i);
                String att = data.getString("primary_attr");
                switch (att) {
                    case "int" :
                        att = "Intelegent";
                        break;
                    case "str" :
                        att = "Strength";
                        break;
                    case "agi" :
                        att = "Agility";
                        break;
                }
                HeroModel hero = new HeroModel(
                        data.getString("localized_name"),
                        att,
                        data.getString("attack_type"),
                        url + data.getString("img")
                );
                heroList.add(hero);
                adapter = new HeroAdapter(heroList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}