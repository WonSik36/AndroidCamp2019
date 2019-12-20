package handong.vacation.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import handong.vacation.myapplication.object.Memo;

public class MemoFirstActivity extends AppCompatActivity {
    List<Memo> memoList = new ArrayList<Memo>();
    static final int REQUEST_MEMO = 100;
    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_first);

        for(int i=0;i<3;i++){
            memoList.add(new Memo(i));
        }

        bt1 = findViewById(R.id.button1);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Memo ret = (Memo)data.getSerializableExtra("ret");
            Memo tmp = memoList.get(ret.getId());
            tmp.setTitle(ret.getTitle());
            tmp.setContent(ret.getContent());

            int idx = ret.getId()+1;
            if(!tmp.isEmpty())
                highlightButton(idx);
            else
                grayButton(idx);
        }
    }

    public void onClick1(View view){
        Intent intent = new Intent(getApplicationContext(), MemoSecondActivity.class);
        intent.putExtra("memo",memoList.get(0));
        startActivityForResult(intent, REQUEST_MEMO);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void onClick2(View view){
        Intent intent = new Intent(getApplicationContext(), MemoSecondActivity.class);
        intent.putExtra("memo",memoList.get(1));
        startActivityForResult(intent, REQUEST_MEMO);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void onClick3(View view){
        Intent intent = new Intent(getApplicationContext(), MemoSecondActivity.class);
        intent.putExtra("memo",memoList.get(2));
        startActivityForResult(intent, REQUEST_MEMO);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    private void highlightButton(int idx){
        switch (idx){
            case 1:
                bt1.setBackgroundColor(getResources().getColor(R.color.highlight));
                break;
            case 2:
                bt2.setBackgroundColor(getResources().getColor(R.color.highlight));
                break;
            case 3:
                bt3.setBackgroundColor(getResources().getColor(R.color.highlight));
                break;
        }
    }

    private void grayButton(int idx){
        switch (idx){
            case 1:
                bt1.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 2:
                bt2.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case 3:
                bt3.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
        }
    }
}
