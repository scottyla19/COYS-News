package com.example.android.coys;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.empty;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class MainActivity extends AppCompatActivity {
    private  ArticleAdapter mAdapter;
    private static final int[] mThemes = {R.style.HomeKit, R.style.HomeKeeper, R.style.AwayKeeper, R.style.ThirdKit};
    private TextView mEmptyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_theme_key),
                getString(R.string.settings_theme_default));


        int index = Integer.parseInt(minMagnitude);

        setTheme(mThemes[index]);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.my_list_view);



        mEmptyText = (TextView) findViewById(R.id.empty_view);
        mEmptyText.setText(R.string.no_news);
        mEmptyText.setVisibility(View.GONE);

        mAdapter = new ArticleAdapter(this, R.layout.list_item, new ArrayList<Result>());
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Result currentStory = mAdapter.getItem(position);
                Uri uri = Uri.parse(currentStory.getWebUrl());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://content.guardianapis.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Date today = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = outFormat.format(today);



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            GuardianService guardianService = retrofit.create(GuardianService.class);
            Call<Article> call = guardianService.listArticles("football%20AND%20spurs", todayStr,"newest", "06ff3810-5916-4724-9bea-5a2b930c6207");

            call.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);

                    // Set empty state text to display "No earthquakes found."
                    mEmptyText.setVisibility(View.GONE);

                    // Clear the adapter of previous earthquake data
                    mAdapter.clear();


                    Article articles = response.body();
                    ArrayList<Result> stories = new ArrayList<Result>();
                    mAdapter.clear();

                    for (int i = 0; articles.getResponse().getResults().size() > i; i++){
                        stories.add(articles.getResponse().getResults().get(i));

                    }
                    if (stories != null && !stories.isEmpty()) {
                        mAdapter.addAll(stories);
                    }else {
                        mEmptyText.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Log.e(call.toString(), t.toString());
                    mEmptyText.setVisibility(View.VISIBLE);
                }

            });
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyText.setText(R.string.no_network);
            mEmptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent settingsIntent = new Intent(this, ThemeActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
