package tv.kinochi.kinochi.other.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tv.kinochi.kinochi.presenter.ErrorProcessing;

@Module
public class AppModule {
    private Context appContext;

    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;
    }


    @Provides
    @Singleton
    Context provideContext() {return appContext;}

    @Provides
    @Singleton
    ErrorProcessing errorProcessing() {return new ErrorProcessing(appContext);}
}
