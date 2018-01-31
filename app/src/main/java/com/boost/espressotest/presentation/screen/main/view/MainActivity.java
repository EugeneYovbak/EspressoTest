package com.boost.espressotest.presentation.screen.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.boost.espressotest.R;
import com.boost.espressotest.app.EspressoTestApp;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity;
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter;
import com.boost.espressotest.presentation.screen.main.view.adapter.ProductAdapter;
import com.boost.espressotest.presentation.tools.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.sv_search) SearchView mSearchView;
    @BindView(R.id.rv_products) RecyclerView mProductsRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private ProductAdapter mProductAdapter;

    @Inject
    MainPresenter mPresenter;

    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            mPresenter.filterList(newText);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EspressoTestApp.getDependencyGraph().initMainComponent().inject(this);
        mPresenter.onAttach(this);
        initList();
        initSearch();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getProductList();
    }

    private void initList() {
        mProductAdapter = new ProductAdapter((product, position) -> mPresenter.onProductItemClick(position));
        mProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsRecyclerView.setHasFixedSize(true);
        mProductsRecyclerView.setAdapter(mProductAdapter);
    }

    private void initSearch() {
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);
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
    public void showProducts(List<Product> productList) {
        mProductAdapter.setProductList(productList);
    }

    @Override
    public void productsLoadError() {
        Utils.showToast(this, getString(R.string.error_server));
    }

    @Override
    public void navigateToDetailScreen(long productId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_PRODUCT_ID, productId);
        startActivity(intent);
    }

    @Override
    public void internetConnectionError() {
        Utils.showToast(this, getString(R.string.error_connection_toast));
    }

    @Override
    protected void onDestroy() {
        mSearchView.setOnQueryTextListener(null);
        mPresenter.onDetach();
        EspressoTestApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
