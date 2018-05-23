package dmi.pmf.novica.mvpwiseass;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import dmi.pmf.novica.mvpwiseass.component.DaggerRandomUserComponent;
import dmi.pmf.novica.mvpwiseass.component.RandomUserComponent;
import dmi.pmf.novica.mvpwiseass.model.RandomUsers;
import dmi.pmf.novica.mvpwiseass.module.ContextModule;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

//    Retrofit retrofit;
    RecyclerView recyclerView;
    RandomUserAdapter adapter;

    Context context;
    Picasso picasso;

    RandomUsersApi randomUsersApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        context = this;

//        beforeDagger2();

        afterDagger2();

        populateUsers();
    }

    private void afterDagger2() {
        RandomUserComponent component =
                DaggerRandomUserComponent.builder()
                        .contextModule(new ContextModule(this))
                        .build();
        picasso = component.getPicasso();
        randomUsersApi = component.getRandomUserApi();

    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = randomUsersApi.getRandomUsers(10);
        randomUsersCall.enqueue(new retrofit2.Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, retrofit2.Response<RandomUsers> response) {
                if (response.isSuccessful()) {
                    adapter = new RandomUserAdapter(picasso);
                    adapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

//    public RandomUsersApi getRandomUserService(){
//        return retrofit.create(RandomUsersApi.class);
//    }

//    private void beforeDagger2() {
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = new Gson();
//
//        File cacheFile = new File(this.getCacheDir(), "httpCache");
//        cacheFile.mkdirs();
//
//        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
//
//
//        // debugging
//        Timber.plant(new Timber.DebugTree());
//        HttpLoggingInterceptor httpLoggingInterceptor = new
//                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(@NonNull String message) {
//                Timber.i(message);
//            }
//        });
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHttpClient = new OkHttpClient()
//                .newBuilder()
//                .cache(cache)
//                .addInterceptor(httpLoggingInterceptor)
//                .build();
//
//        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);
//
//        picasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();
//
//        retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("https://randomuser.me/")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }
}
