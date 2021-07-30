package com.projects.BookSearch;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDownload extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private List<DownDetails> down;
    private RecyclerView mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_download);
        mList = findViewById(R.id.idDlBooks);
        extractBooks();
    }

    private void extractBooks() {
        down = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(BookDownload.this);
        mRequestQueue.getCache().clear();
        final String JSON_URL = "https://jrnkbucket.s3.ap-south-1.amazonaws.com/book.json";
        RequestQueue queue = Volley.newRequestQueue(BookDownload.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray itemsArray = response.getJSONArray("books");
                    for (int i = 0; i < Objects.requireNonNull(itemsArray).length(); i++) {
                        JSONObject jsonObject = itemsArray.getJSONObject(i);
                        String title = jsonObject.optString("title");
                        String author = jsonObject.optString("author");
                        String publisher = jsonObject.optString("publisher");
                        String year = jsonObject.optString("year");
                        String url = jsonObject.optString("url");
                        DownDetails downDetails = new DownDetails(title,author,publisher,year,url);
                        down.add(downDetails);
                        DownAdapter adapter = new DownAdapter((ArrayList<DownDetails>) down,BookDownload.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookDownload.this, RecyclerView.VERTICAL, false);
                        RecyclerView mRecyclerView = findViewById(R.id.idDlBooks);
                        //in below line we are setting layout manager and adapter to our recycler view.
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setAdapter(adapter);

                        }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookDownload.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}