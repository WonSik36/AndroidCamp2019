package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private WebView wv;
    private EditText et;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wv = findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d("web view","error occured");
                if (view.canGoBack()) {
                    view.goBack();
                }
                Toast toast = Toast.makeText(getBaseContext(), description,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        // wv.setWebChromeClient(new WebChromeClient());
        wv.loadUrl("https://m.naver.com/");

        et = findViewById(R.id.editText2);
        bt = findViewById(R.id.button10);
    }

    public void go2URL(android.view.View view){
        String url = et.getText().toString();
        wv.loadUrl(url);
    }
}
