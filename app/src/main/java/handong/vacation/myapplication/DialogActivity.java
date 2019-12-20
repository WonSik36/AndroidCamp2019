package handong.vacation.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {
    String[] fruit = {"사과","오렌지","포도","배"};
    boolean[] checked = new boolean[fruit.length];
    int checkedNum = 0;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button1:
                dialog1();
                break;
            case R.id.button2:
                dialog2();
                break;
            case R.id.button3:
                dialog3();
                break;
            case R.id.button4:
                dialog4();
                break;
        }
    }

    private void dialog1(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Basic Dialog")
                .setIcon(R.drawable.kiwi)
                .setMessage("Do yout want to delete?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        deleteOk();
                    }
                })
                .setNegativeButton("CANCEL",null)
                .show();
    }

    private void dialog2(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Radio Dialog")
                .setSingleChoiceItems(fruit, checkedNum, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedNum = which;
//                        Toast.makeText(getApplicationContext(), fruit[which]+"가 선택됨",Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        selectOk();
                    }
                })
                .setNegativeButton("CANCEL",null)
                .show();
    }

    private void dialog3(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Checkbox Dialog")
                .setMultiChoiceItems(fruit, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                            checked[which] = true;
                        else
                            checked[which] = false;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        selectMulti();
                    }
                })
                .setNegativeButton("CANCEL",null)
                .show();
    }

    private void dialog4(){
        View customView = View.inflate(this, R.layout.custom_dialog,null);
        et = customView.findViewById(R.id.etfruit);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("과일 입력")
                .setView(customView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        customSelect();
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    private void deleteOk(){
        Toast.makeText(this,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
    }

    private void selectOk(){
        Toast.makeText(this,fruit[checkedNum]+"가 선택되었습니다.",Toast.LENGTH_SHORT).show();
    }

    private void selectMulti(){
        String output = "";
        for(int i=0;i<checked.length;i++){
            if(checked[i]) {
                output += fruit[i];
                output += ", ";
            }
        }
        Toast.makeText(this,output.substring(0,output.length()-2)+"가 선택되었습니다.",Toast.LENGTH_SHORT).show();
    }

    private void customSelect(){
        Toast.makeText(this, et.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
