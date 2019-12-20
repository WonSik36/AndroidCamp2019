package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private CheckBox check;
    private EditText input1;
    private EditText input2;
    private EditText input3;

    private int americanoNum = 0;
    private int caffelatteNum = 0;
    private int caffemochaNum = 0;

    /*
        1. 품목 개수 계산
        2. 할인 여부
        3. 가격 계산
        4. 메세지 창
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        b1 = findViewById(R.id.button3);
        b2 = findViewById(R.id.button4);
        check = findViewById(R.id.checkBox2);
        input1 = findViewById(R.id.editText7);
        input2 = findViewById(R.id.editText8);
        input3 = findViewById(R.id.editText9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = check.isChecked();

                if(!(input1.getText().toString().length()==0))
                    americanoNum = Integer.parseInt(input1.getText().toString());
                if(!(input2.getText().toString().length()==0))
                    caffelatteNum = Integer.parseInt(input2.getText().toString());
                if(!(input3.getText().toString().length()==0))
                    caffemochaNum = Integer.parseInt(input3.getText().toString());

                int sum = americanoNum*1000 + caffelatteNum*1500 + caffemochaNum*1700;
                if(checked)
                    sum *= 0.9;

                String output = "가격: "+sum;
                Toast.makeText(Main3Activity.this, output, Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input1.setText("");
                input1.setHint("개수를 입력하세요");
                input2.setText("");
                input2.setHint("개수를 입력하세요");
                input3.setText("");
                input3.setHint("개수를 입력하세요");
            }
        });
    }
}
