package tv.kinochi.kinochi.view.fragments;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import tv.kinochi.kinochi.presenter.BasePresenter;

public abstract class BaseFragment extends Fragment {
    public abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    public void makeToast(String text, View v) {
        Snackbar.make(v, text, Snackbar.LENGTH_LONG).show();
    }
}
