package dmi.pmf.novica.mvpwiseass.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dmi.pmf.novica.mvpwiseass.annotation.RandomUserApplicationScope;
import okhttp3.OkHttpClient;



// needs OkHttpModule for OkHttp3Downloader and OkHttpClient, but
// also needs Context, but Context is included in OkHttpModule!
@Module(includes = OkHttpModule.class)
public class PicassoModule {

    @RandomUserApplicationScope
    @Provides
    public Picasso picasso(@Named("application_context") Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

}
