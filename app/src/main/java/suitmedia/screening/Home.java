package suitmedia.screening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final EditText et_name = (EditText) findViewById(R.id.et_name);
        Button btn_next = (Button) findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_name.getText().toString().trim().equals("")){
                    et_name.setError("Masukkan nama terlebih dahulu");
                }else{
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.putExtra("NAMA",et_name.getText().toString());
                    startActivity(i);
                }
            }
        });


    }
}
