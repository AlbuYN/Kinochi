package tv.kinochi.kinochi.other.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import tv.kinochi.kinochi.model.Model;
import tv.kinochi.kinochi.model.ModelImp;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideData() {
        return new ModelImp();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
