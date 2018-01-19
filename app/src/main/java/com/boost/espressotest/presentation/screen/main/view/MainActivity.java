package com.boost.espressotest.presentation.screen.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.boost.espressotest.R;
import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().initMainComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getProductList("", "", 1);
    }

    @Override
    public void showLoadingIndicator() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProductsLoadSuccess(List<Product> productList) {

    }

    @Override
    public void onProductsLoadError() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        MainApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
