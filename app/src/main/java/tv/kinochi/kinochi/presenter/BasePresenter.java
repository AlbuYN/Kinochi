package tv.kinochi.kinochi.presenter;

import android.content.Context;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import tv.kinochi.kinochi.model.Model;
import tv.kinochi.kinochi.other.App;

public abstract class BasePresenter implements Presenter {

    @Inject
    public ErrorProcessing errorProcessing;
    @Inject
    public Context context;
    @Inject
    public Model model;
    @Inject
    CompositeSubscription compositeSubscription;

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    public Context getContext() {
        return context;
    }
}
