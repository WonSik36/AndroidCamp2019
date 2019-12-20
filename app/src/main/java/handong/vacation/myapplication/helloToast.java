package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class helloToast extends AppCompatActivity {
    private int cnt;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_toast);

        tv = findViewById(R.id.textView3);
    }

    public void toastHello(android.view.View view){
        toastStr("Hello");
    }

    public void toastHi(android.view.View view){
        toastStr("Hi");
    }

    public void toastCnt(android.view.View view){
        toastStr(cnt+"");
    }

    public void toastStr(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void countNumber(View view) {
        cnt++;
        tv.setText(cnt+"");
    }

    public void resetNumber(View view) {
        cnt = 0;
        tv.setText(cnt+"");
    }
}
