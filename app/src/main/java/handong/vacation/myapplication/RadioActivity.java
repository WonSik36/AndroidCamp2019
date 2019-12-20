package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class RadioActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private RadioButton r1;
    private RadioButton r2;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        iv = findViewById(R.id.imageView2);

        r1.setOnCheckedChangeListener(this);
        r2.setOnCheckedChangeListener(this);

//        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                    iv.setImageResource(R.drawable.apple);
//            }
//        });
//
//        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                    iv.setImageResource(R.drawable.kiwi);
//            }
//        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("radio","onCheckedChanged :"+isChecked);
        if(!isChecked)
            return;

        if(buttonView == r1)
            iv.setImageResource(R.drawable.apple);

        else if(buttonView == r2)
            iv.setImageResource(R.drawable.kiwi);
    }
}
