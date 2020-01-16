package tv.kinochi.kinochi.other;

import android.app.Application;

import tv.kinochi.kinochi.other.di.AppComponent;
import tv.kinochi.kinochi.other.di.AppModule;
import tv.kinochi.kinochi.other.di.DaggerAppComponent;
import tv.kinochi.kinochi.other.di.ModelModule;
import tv.kinochi.kinochi.other.di.PresenterModule;
import tv.kinochi.kinochi.other.di.ViewModule;

public class App extends Application {
    private static AppComponent component;


    public static AppComponent getComponent() {return component;}

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule())
                .build();
    }

}
