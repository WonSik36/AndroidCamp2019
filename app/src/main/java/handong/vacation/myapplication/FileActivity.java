package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity implements View.OnClickListener{
    Button btSave;
    Button btLoad;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        btSave = findViewById(R.id.btnSave);
        btSave.setOnClickListener(this);
        btLoad = findViewById(R.id.btnLoad);
        btLoad.setOnClickListener(this);

        filename = getFilesDir() + "data.txt";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoad:
                readFile();
                break;
            case R.id.btnSave:
                writeFile();
                break;
            case R.id.btnLoadRaw:
                readRawFile();
                break;
        }
    }

    private void readFile() {
        String content = "";
        String str;

        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while((str = br.readLine()) != null){
                content += str;
            }
            br.close();
            displayToast(content);
        }catch(IOException e){
            Log.e("fileio",e.getMessage());
        }

    }

    private void writeFile() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write("Hello World!");
            bw.close();
            displayToast("saved");
        }catch (IOException e){
            Log.e("fileio",e.getMessage());
        }
    }

    private void readRawFile() {
        String str;
        String content = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.data)));
        try{
            while((str = br.readLine()) != null){
                content += str;
            }
            br.close();
            displayToast(content);
        }catch(IOException e){
            Log.e("fileio",e.getMessage());
        }

    }

    private void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
