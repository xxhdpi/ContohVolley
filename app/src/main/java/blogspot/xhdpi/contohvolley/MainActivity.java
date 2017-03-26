package blogspot.xhdpi.contohvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

  private static final String DATA_FROM_NET = "https://httpbin.org/get";
  private static final String TAG = "MainActivity";
  private Button btnGetData;
  private TextView tvHasil;

  private RequestQueue requestQueue;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btnGetData = (Button) findViewById(R.id.btnGetData);
    tvHasil = (TextView) findViewById(R.id.tvHasil);

    requestQueue = Volley.newRequestQueue(this);

    btnGetData.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        getDataFromURL(DATA_FROM_NET);
      }
    });
  }

  /**
   * ambil data dari internet menggunakan StringRequest dari volley
   */
  private void getDataFromURL(String url) {
    StringRequest stringRequest =
        new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
          @Override public void onResponse(String response) {
            // logging
            Log.d(TAG, "onResponse: " + response);
            tvHasil.setText(response);
          }
        }, new Response.ErrorListener() {
          @Override public void onErrorResponse(VolleyError error) {
            // jika proses http rquest gagal
            tvHasil.setText(error.getLocalizedMessage());
          }
        });

    // menambahkan request ke queue
    requestQueue.add(stringRequest);
  }
}
