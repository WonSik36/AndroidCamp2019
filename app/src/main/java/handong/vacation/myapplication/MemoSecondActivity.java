package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import handong.vacation.myapplication.object.Memo;

public class MemoSecondActivity extends AppCompatActivity {
    private TextView memoNum;
    private EditText title;
    private EditText content;
    private Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_second);

        memoNum = findViewById(R.id.textView1);
        title = findViewById(R.id.editText1);
        content = findViewById(R.id.editText2);

        Intent intent = getIntent();
        memo = (Memo)intent.getSerializableExtra("memo");

        memoNum.setText("Memo"+(memo.getId()+1));
        title.setText(memo.getTitle());
        content.setText(memo.getContent());
    }

    public void onSavedButtonClick(View v){
        memo.setTitle(title.getText().toString());
        memo.setContent(content.getText().toString());

        Intent intent = new Intent(getApplicationContext(),MemoFirstActivity.class);
        intent.putExtra("ret",memo);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void onResetButtonClick(View v){
        title.setText("");
        content.setText("");
        memo.reset();
    }
}
