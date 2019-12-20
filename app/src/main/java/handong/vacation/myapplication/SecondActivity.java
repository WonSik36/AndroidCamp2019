package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");

        TextView tv = findViewById(R.id.textView1);
        if(msg == null)
            tv.setText("Activity Call");
        else
            tv.setText(msg);

        Button bt1 = findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText et = findViewById(R.id.editText1);
        Button bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret = et.getText().toString();
                Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                intent.putExtra("ret",ret);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
