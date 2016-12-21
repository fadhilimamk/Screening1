package suitmedia.screening;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String nama;
    TextView tv_nama;
    Button btn_pilih_event;
    Button btn_pilih_guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = getIntent().getStringExtra("NAMA");
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        btn_pilih_event = (Button) findViewById(R.id.btn_pilih_event);
        btn_pilih_guest = (Button) findViewById(R.id.btn_pilih_guest);

        tv_nama.setText(nama);
        btn_pilih_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),pilih_event.class);
                startActivityForResult(i,1);
            }
        });
        btn_pilih_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),pilih_guest.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                btn_pilih_event.setText(data.getStringExtra("event"));
            }
        }
    }
}
