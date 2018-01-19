package com.boost.espressotest.presentation.screen.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boost.espressotest.R;
import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.main.adapter.ProductAdapter;
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter;
import com.boost.espressotest.presentation.tools.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, ProductAdapter.ProductInteractionListener {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.rv_products) RecyclerView mProductsRecyclerView;

    private List<Product> mProductList = new ArrayList<>();
    private ProductAdapter mProductAdapter;

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
        if (mProductList.isEmpty()) {
            mPresenter.getProductList();
        }

        mProductAdapter = new ProductAdapter(mProductList, this);
        mProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsRecyclerView.setHasFixedSize(true);
        mProductsRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    public void onProductItemClick(int position) {

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
        mProductList.clear();
        mProductList.addAll(productList);
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductsLoadError() {

    }

    @Override
    public void internetConnectionError() {
        Utils.showToast(this, getString(R.string.error_connection_toast));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        MainApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
