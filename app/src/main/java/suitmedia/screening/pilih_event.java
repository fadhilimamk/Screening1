package suitmedia.screening;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class pilih_event extends AppCompatActivity {

    ListView lv_select_event;
    List<Event> eventLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_event);

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
}
