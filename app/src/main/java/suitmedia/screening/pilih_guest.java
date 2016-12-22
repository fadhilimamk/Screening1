package suitmedia.screening;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class pilih_guest extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    GridView gv_select_guest;
    SwipeRefreshLayout srl_guest_grid;
    List<Person> guesttLists;
    private static String url = "http://dry-sierra-6832.herokuapp.com/api/people";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_guest);
        gv_select_guest = (GridView) findViewById(R.id.gridView);
        srl_guest_grid = (SwipeRefreshLayout) findViewById(R.id.srl_guest_grid);
                guesttLists = new ArrayList<Person>();

        srl_guest_grid.setOnRefreshListener(this);
        srl_guest_grid.post(new Runnable() {
            @Override
            public void run() {
                srl_guest_grid.setRefreshing(true);
                new GetGuests().execute();
            }
        });


        gv_select_guest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person choosen = guesttLists.get(i);
                Calendar cal = Calendar.getInstance();
                cal.setTime(choosen.birthday);
                int bulan = cal.get(Calendar.MONTH);
                Toast.makeText(getApplicationContext(), choosen.getPhone() +" | "+ ( (isPrima(bulan))?"Bulan prima":"Bulan komposit" ),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean isPrima(int val){
        Boolean prima = (val>1);
        int i = 2;
        while((i*i<=val) && (prima)){
            if(val % i == 0)
                prima = false;
            else
                i++;
        }
        return prima;
    }

    @Override
    public void onRefresh() {
        new GetGuests().execute();
    }

    private class GetGuests extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("JSON", "Mengambil JSON");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonstr = sh.makeServiceCall(url);
            Log.e("JSON", "Respon: " + jsonstr);

            if(jsonstr != null){
                try{
                    JSONArray jsonarray = new JSONArray(jsonstr);
                    for (int i =0 ;i<jsonarray.length();i++){
                        JSONObject guest = jsonarray.getJSONObject(i);
                        String tgllahir = guest.getString("birthdate");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthdate = sdf.parse(tgllahir);
                        Person p = new Person(R.mipmap.ic_launcher,guest.getString("name"),birthdate);
                        guesttLists.add(p);
                    }
                } catch (final JSONException e) {
                    Log.e("JSON", "Kesalahan pembacaan data");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Kesalahan pembacaan data: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                } catch (final ParseException e) {
                    Log.e("JSON", "Kesalahan pembacaan data");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Kesalahan pembacaan data: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }else{
                Log.e("JSON", "Gagal mendapatkan JSON.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Gagal mendapatkan data dari server",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            srl_guest_grid.setRefreshing(false);
            GuestAdapter adapter = new GuestAdapter(pilih_guest.this,guesttLists);
            gv_select_guest.setAdapter(adapter);
        }

    }
}
