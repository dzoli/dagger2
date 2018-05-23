package dmi.pmf.novica.mvpwiseass.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/*
    This is low level module

*/

@Module
public class ContextModule {

    private Context context;


    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context(){
        return context.getApplicationContext();
    }
}
