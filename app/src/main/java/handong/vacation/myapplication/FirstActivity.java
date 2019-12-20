package handong.vacation.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    static final int REQEUST_MSG = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et = findViewById(R.id.editText1);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tv = findViewById(R.id.textView1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQEUST_MSG && resultCode == RESULT_OK){
            String ret = data.getStringExtra("ret");
            tv.setText(ret);
        }
    }

    public void onClick1(View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }

    public void onClick2(View view){
        String value = et.getText().toString();
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("msg",value);
        startActivity(intent);
    }

    public void onClick3(View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("msg","give return!");
        startActivityForResult(intent, REQEUST_MSG);
    }
}
