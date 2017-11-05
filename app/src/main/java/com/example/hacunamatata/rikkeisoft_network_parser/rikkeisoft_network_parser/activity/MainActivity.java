package com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.hacunamatata.rikkeisoft_network_parser.R;
import com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.adapter.UserAdapter;
import com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.decoration.MyItemDecorator;
import com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener{

    //private static final String DATA_URL = "https://jsonplaceholder.typicode.com/posts/1/comments";
    private static final String DATA_URL = "https://jsonplaceholder.typicode.com/posts";
    public static final String PARCEL_KEY = "PARCEL_KEY";

    public List<User> userList = new ArrayList<>();
    private RecyclerView rv_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize components
        rv_users = findViewById(R.id.rv_users);
        rv_users.setLayoutManager(new LinearLayoutManager(this));
        rv_users.addItemDecoration(new MyItemDecorator(4));

        if (isNetworkConnected()) {
            new GetDataAsyncTask().execute();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onItemClickListener(int position) {
        User user = userList.get(position);
        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra(PARCEL_KEY, user);
        startActivity(intent);
    }

    private class GetDataAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL(DATA_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();

                    Log.d("DataReceiver", sb.toString());
                    userList = convertFromJson(sb.toString());
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            UserAdapter adapter = new UserAdapter(userList);
            adapter.setClickListener(MainActivity.this);
            rv_users.setAdapter(adapter);
        }

        private ArrayList<User> convertFromJson(String json) throws JSONException {
            ArrayList<User> users = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int userID = jsonObject.optInt("userId");
                String title = jsonObject.optString("title");
                String body = jsonObject.optString("body");

                User user = new User(userID, title, body);
                users.add(user);
            }
            return users;
        }
    }
}
