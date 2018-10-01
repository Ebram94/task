package com.example.ebram.task;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class news_Detials extends AppCompatActivity {
    public int id;
    private String URL_JSON;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ImageView imageView;
    ProgressDialog dialog;
    private TextView title, date, like, view, des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dialog=new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news__detials);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        URL_JSON = "https://egyptinnovate.com/en/api/v01/safe/GetNewsDetails?nid="+id+"";
        Toast.makeText(getApplicationContext(), "ana hna " + id, Toast.LENGTH_LONG).show();
        imageView = findViewById(R.id.imDetials);
        title = findViewById(R.id.tvTitle);
        date = findViewById(R.id.tvDate);
        view = findViewById(R.id.tvView);
        des = findViewById(R.id.tvDes);
        like = findViewById(R.id.tvLikes);
        jsoncall();

    }
    public void jsoncall()  {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET,URL_JSON , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject root = null;
                try {
                    root = new JSONObject(response);

                    JSONObject jsonObject = root.getJSONObject("newsItem");
                    title.setText(jsonObject.getString("NewsTitle"));
                    date.setText(jsonObject.getString("PostDate"));
                    like.setText("Likes ("+jsonObject.getString("Likes")+")");
                    view.setText(" views"+jsonObject.getString("NumofViews"));
                    des.setText(jsonObject.getString("ItemDescription"));
                    Picasso.with(getApplicationContext()).load(jsonObject.getString("ImageUrl")).into(imageView);
                    dialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error ya ahbl " , Toast.LENGTH_LONG).show();

            }
        });

        mRequestQueue.add(mStringRequest);

    }
}






