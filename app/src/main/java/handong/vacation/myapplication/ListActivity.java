package handong.vacation.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity{
    List<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listview1);

        list.add("사과");
        list.add("키위");
        list.add("배");
        list.add("오렌지");
        list.add("포도");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),list.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                displayDeleteDialog(position);
                return false;
            }
        });

        et = findViewById(R.id.editText1);
    }

    public void displayDeleteDialog(final int pos){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("삭제")
                .setMessage("삭제하시겠습니까?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteOk(pos);
                    }
                })
                .setNegativeButton("CANCEL",null)
                .show();
    }

    public void deleteOk(int pos){
        list.remove(pos);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"삭제되었습니다",Toast.LENGTH_SHORT).show();
    }

    public void addFruitClick(View view){
        String fruitName = et.getText().toString();
        et.setText("");
        if(fruitName.length() == 0)
            return;

        list.add(fruitName);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,fruitName+"이 추가되었습니다",Toast.LENGTH_SHORT).show();
    }
}
