package dmi.pmf.novica.mvpwiseass.module;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dmi.pmf.novica.mvpwiseass.annotation.RandomUserApplicationScope;

/*
    This is low level module

*/

@Module
public class ContextModule {

    private Context context;


    public ContextModule(Context context) {
        this.context = context;
    }

    @Named("application_context")
    @RandomUserApplicationScope
    @Provides
    public Context context(){
        return context.getApplicationContext();
    }
}
