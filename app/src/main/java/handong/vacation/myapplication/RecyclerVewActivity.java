package handong.vacation.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import handong.vacation.myapplication.object.Fruit;

public class RecyclerVewActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    List<Fruit> list = new ArrayList<Fruit>();
    CustomAdapter adapter;
    RecyclerView recyclerView;
    ImageView iv;
    RadioButton r0,r1,r2,r3;
    EditText etName, etDesc;
    int fId = R.drawable.apple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_vew);

        initRecyclerView();
    }

    public void onClickAdd(View v){
        View customView = View.inflate(this, R.layout.custom_dialog_add_fruit,null);

        r0 = customView.findViewById(R.id.radioButton0);
        r1 = customView.findViewById(R.id.radioButton1);
        r2 = customView.findViewById(R.id.radioButton2);
        r3 = customView.findViewById(R.id.radioButton3);
        r0.setOnCheckedChangeListener(this);
        r1.setOnCheckedChangeListener(this);
        r2.setOnCheckedChangeListener(this);
        r3.setOnCheckedChangeListener(this);

        etName = customView.findViewById(R.id.etfname);
        etDesc = customView.findViewById(R.id.etfdesc);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("과일 입력")
                .setView(customView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        customSelect(etName.getText().toString(),etDesc.getText().toString());
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    private void initRecyclerView(){
        initList();
        setListView();
    }

    private void initList() {
        list.add(new Fruit(R.drawable.apple, "사과", "빨강"));
    }

    private void setListView(){
        adapter = new CustomAdapter(this,list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void customSelect(String fname, String fdesc){
        list.add(new Fruit(fId, fname,fdesc));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("radio","onCheckedChanged :"+isChecked);

        if(!isChecked)
            return;

        switch(buttonView.getId()){
            case R.id.radioButton0:
                fId = R.drawable.apple;
                break;
            case R.id.radioButton1:
                fId = R.drawable.orange;
                break;
            case R.id.radioButton2:
                fId = R.drawable.grape;
                break;
            case R.id.radioButton3:
                fId = R.drawable.kiwi;
                break;
        }
    }
}
