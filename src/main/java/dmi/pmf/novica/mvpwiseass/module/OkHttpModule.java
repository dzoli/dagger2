package dmi.pmf.novica.mvpwiseass.module;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class) // OkHttpModule needs ContextModule
public class OkHttpModule {

    @Provides
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        // then we also mst provide cache and HttpLoggingInterceptor,
        // but cache also has dependencies
    }

    @Provides
    public Cache cache(File file) {
        return new Cache(file, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    public File file(Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor hli = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }

        });
        hli.setLevel(HttpLoggingInterceptor.Level.BODY);
        return hli;
    }
}
