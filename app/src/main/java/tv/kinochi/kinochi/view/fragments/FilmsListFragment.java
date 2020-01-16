package tv.kinochi.kinochi.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.BasePresenter;
import tv.kinochi.kinochi.presenter.FilmsPresenterList;
import tv.kinochi.kinochi.presenter.vo.BlockFilmsVO;
import tv.kinochi.kinochi.view.activities.MainActivityCallback;
import tv.kinochi.kinochi.view.adapters.BlockFilmsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmsListFragment extends BaseFragment implements FilmsListView {

    @Inject
    FilmsPresenterList presenter;

    @BindView(R.id.rvFilms)
    RecyclerView rvFilms;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private BlockFilmsAdapter adapter;
    private MainActivityCallback activityCallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (MainActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass() + " must implements "
                    + MainActivityCallback.class);
        }
    }

    public FilmsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        App.getComponent().inject(this);
        presenter.onCreate(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_films_list, container, false);
        ButterKnife.bind(this, view);

        refresh.setColorSchemeResources(R.color.colorAccent);
        rvFilms.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        adapter = new BlockFilmsAdapter(new ArrayList<>(), presenter);
        rvFilms.setAdapter(adapter);

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showFilmsList(List<BlockFilmsVO> filmVOList) {
        checkAdapter();
        adapter.setBlockFilmsVOList(filmVOList);
    }

    @Override
    public void openFilmCard(String uid) {
        activityCallback.openFilmCard(uid);
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }

    private void checkAdapter() {
        if (adapter == null) {
            adapter = new BlockFilmsAdapter(new ArrayList<>(), presenter);
        }
    }

    private void makeToast(String message) {
        makeToast(message, Objects.requireNonNull(getActivity()).findViewById(android.R.id.content));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
