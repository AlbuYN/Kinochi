package tv.kinochi.kinochi.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.BasePresenter;
import tv.kinochi.kinochi.presenter.FilterFilmsPresenter;
import tv.kinochi.kinochi.presenter.vo.BlockFiltersVO;
import tv.kinochi.kinochi.view.adapters.BlockFiltersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationViewFragment extends BaseFragment implements NavigationView {

    @Inject
    FilterFilmsPresenter presenter;

    @BindView(R.id.editFab)
    FloatingActionButton editFab;



    @BindView(R.id.clFilter)
    ConstraintLayout clFilter;

    @BindView(R.id.cvPrimaryNav)
    ConstraintLayout cvPrimaryNav;

    @BindView(R.id.ibCategory)
    ImageButton ibCategory;
    @BindView(R.id.ibHome)
    ImageButton ibHome;
    @BindView(R.id.rvBlockFilters)
    RecyclerView rvBlockFilters;

    private BlockFiltersAdapter adapter;




    public NavigationViewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_navigation_view, container, false);
        ButterKnife.bind(this, view);

        rvBlockFilters.setHasFixedSize(true);

        adapter = new BlockFiltersAdapter(new ArrayList<>(), presenter);
        rvBlockFilters.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rvBlockFilters.setAdapter(adapter);

        presenter.onCreateView(savedInstanceState);

        ibCategory.setOnClickListener(v -> clFilter.setVisibility(View.VISIBLE));
        ibHome.setOnClickListener(v -> clFilter.setVisibility(View.GONE));


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showBlockFilters(List<BlockFiltersVO> blockFiltersVOList) {
        checkAdapter();
        adapter.setBlockFiltersVOList(blockFiltersVOList);
    }

    private void checkAdapter() {
        if (adapter == null) {
            adapter = new BlockFiltersAdapter(new ArrayList<>(), presenter);
        }
    }

    @Override
    public void showError(String error) {
        makeToast(error);
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
