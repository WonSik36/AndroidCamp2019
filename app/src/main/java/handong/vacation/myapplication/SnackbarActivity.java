package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarActivity extends AppCompatActivity {
    private View mainLayout;
    private View imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        mainLayout = findViewById(R.id.main_layout);
        imageView = findViewById(R.id.imageView3);
    }

    public void onClickSnackbar(View v){
        Snackbar.make(mainLayout, "Snackbar 메시지입니다.",Snackbar.LENGTH_SHORT).show();
    }

    public void onClickSnackbarAction(View v){
        final Snackbar sb = Snackbar.make(mainLayout, "Snackbar 메시지입니다.",Snackbar.LENGTH_INDEFINITE);
        sb.setAction("Ok",new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sb.dismiss();
            }
        });
        sb.show();
    }

    public void onClickSnackbarImage(View v){
        Snackbar.make(imageView, "Snackbar 메시지입니다.",Snackbar.LENGTH_SHORT).show();
    }
}
