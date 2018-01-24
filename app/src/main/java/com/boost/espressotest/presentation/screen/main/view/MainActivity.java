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
import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.data.content.ProductContent;
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

public class MainActivity extends AppCompatActivity implements MainView, ProductAdapter.ProductInteractionListener {

    @BindView(R.id.sv_search) SearchView mSearchView;
    @BindView(R.id.rv_products) RecyclerView mProductsRecyclerView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private ProductAdapter mProductAdapter;

    @Inject
    MainPresenter mPresenter;

    //TODO: 1/23/18 filtering logic should be in presenter
    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
//            if (newText.isEmpty()) {
//                mProductAdapter.setProductList(mProductList);
//            } else {
//                List<ProductContent> searchList = Stream.of(mProductList).filter(value -> value.getName().toLowerCase().contains((newText))).toList();
//                mProductAdapter.setProductList(searchList);
//            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().initMainComponent().inject(this);
        mPresenter.onAttach(this);

        mProductAdapter = new ProductAdapter(this);
        mProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsRecyclerView.setHasFixedSize(true);
        mProductsRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getProductList();
    }

    @Override
    public void onProductItemClick(int position) {
        mPresenter.onProductItemClick(position);
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
    public void onProductsLoadSuccess(List<ProductContent> productList) {
        mProductAdapter.setProductList(productList);
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);
    }

    @Override
    public void onProductsLoadError() {
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
    protected void onStop() {
        mSearchView.setOnQueryTextListener(null);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        MainApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
