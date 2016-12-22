package suitmedia.screening;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import layout.EventListFragment;

public class pilih_event extends AppCompatActivity {

    ListView lv_select_event;
    Toolbar toolbar_event;
    List<Event> eventLists;
    Boolean fragment_aktif = false;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_event);

        toolbar_event = (Toolbar) findViewById(R.id.toolbar_event);
        setSupportActionBar(toolbar_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Event e;
        eventLists = new ArrayList<Event>();
        e = new Event(R.mipmap.ic_launcher,"ABC Event",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 2",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 3",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 4",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 5",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 6",new Date());
        eventLists.add(e);
        e = new Event(R.mipmap.ic_launcher,"Event 7",new Date());
        eventLists.add(e);

        lv_select_event = (ListView) findViewById(R.id.lv_event);

        EventAdapter adapter = new EventAdapter(this,eventLists);
        lv_select_event.setAdapter(adapter);

        lv_select_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ri = new Intent();
                ri.putExtra("event",eventLists.get(i).nama);
                setResult(Activity.RESULT_OK, ri);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.opsi_add:

                FrameLayout fl = (FrameLayout) findViewById(R.id.event_list_fragment);
                if(!fragment_aktif){
                    fragment_aktif = true;
                    lv_select_event.setVisibility(View.GONE);
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.event_list_fragment, new EventListFragment());
                    ft.commit();
                    fl.setVisibility(View.VISIBLE);
                }else{
                    fragment_aktif = false;
                    fl.setVisibility(View.GONE);
                    lv_select_event.setVisibility(View.VISIBLE);
                }

                return true;
            case R.id.opsi_search:
                Toast.makeText(getApplicationContext(), "on going" ,
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
