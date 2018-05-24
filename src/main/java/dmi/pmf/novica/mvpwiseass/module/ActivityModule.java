package dmi.pmf.novica.mvpwiseass.module;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dmi.pmf.novica.mvpwiseass.annotation.RandomUserApplicationScope;

@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }


    @Named("activity_context") // dagger don't know which context provide when (app or activity)
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}

