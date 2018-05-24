package dmi.pmf.novica.mvpwiseass.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import dmi.pmf.novica.mvpwiseass.RandomUsersApi;
import dmi.pmf.novica.mvpwiseass.annotation.RandomUserApplicationScope;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    *   Modules will provide under the hood dependencies
    *   for outermost dependencies - RandomUserApi and Picasso
    *
    *   Modules will move the code that instantiate classes
    *   to Module from Activity! This is Dependency injection strategy
    *
    */

@Module(includes = OkHttpModule.class) // RandomUserModule needs OkHttpModule for OkHttpClient!
public class RandomUserModule {

    @Provides
    public RandomUsersApi randomUsersApi(Retrofit retrofit) {
        return retrofit.create(RandomUsersApi.class);
    }

    @RandomUserApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory,
                             Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create();
    }


}
