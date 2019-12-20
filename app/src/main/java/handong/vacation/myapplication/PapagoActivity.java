package handong.vacation.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class PapagoActivity extends AppCompatActivity {
    TextView tvKo;
    TextView tvJa;
    TextView tvFr;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papago);

        et = findViewById(R.id.editText1);
        tvKo = findViewById(R.id.tvKo);
        tvJa = findViewById(R.id.tvJa);
        tvFr = findViewById(R.id.tvFr);
    }

    public void onClickPapago(View view) {
        String input = et.getText().toString();
        String resKo = translate("ko|"+input);
        String resJa = translate("ja|"+input);
        String resFr = translate("fr|"+input);

        tvKo.setText(resKo);
        tvJa.setText(resJa);
        tvFr.setText(resFr);
    }

    private String translate(String input){
        try{
            PapagoDao papago = new PapagoDao();
            AsyncTask<String, Void, String> res = papago.execute(input);
            return res.get();
        }catch(ExecutionException | InterruptedException e){
            Log.e("papago",e.getMessage());
            return "Error Occured";
        }
    }

    private class PapagoDao extends AsyncTask<String, Void, String> {
        static final String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        static final String clientId = "flnXCMrbEFb56GhhJegD";//애플리케이션 클라이언트 아이디값";
        static final String clientSecret = "dkvueM1UgD";//애플리케이션 클라이언트 시크릿값";

        @Override
        protected String doInBackground(String... strings) {
            String[] inputs = strings[0].split("\\|");
            String target = inputs[0];
            String text = inputs[1];

            Log.d("papago",strings[0]);
            Log.d("papago",inputs[0]);
            Log.d("papago",inputs[1]);

            JSONParser parser = new JSONParser();
            try {
                /***** send request *****/
                String textEncoded = URLEncoder.encode(text, "UTF-8");
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                initConnection(con);
                String postParams = "source=en&target="+target+"&text=" + textEncoded;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                /***** get response *****/
                int responseCode = con.getResponseCode();
                BufferedReader br;
                String inputLine;
                StringBuffer response = new StringBuffer();

                if(responseCode!=200) { // error state
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }else{  // normal state: 200
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }

                br.close();
                con.disconnect();

                if(responseCode!=200){ // error state
                    Log.e("papago",response.toString());
                    return "Error Occured";
                }

                /***** parse json *****/
                JSONObject obj = (JSONObject)parser.parse(response.toString());
                JSONObject msg = (JSONObject) obj.get("message");
                JSONObject result = (JSONObject)msg.get("result");

                return (String)result.get("translatedText");

            } catch (Exception e) {
                /*
                 *   UnsupportedEncodingException
                 *   MalformedURLException
                 *   IOException
                 *   ProtocolException
                 *   ParseException
                 *
                 * */

                e.printStackTrace();
                Log.e("papago",e.getMessage());

                return "Error Occured";
            }
        }

        private void initConnection(HttpURLConnection con) throws ProtocolException {
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        }
    }
}
