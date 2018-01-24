package com.boost.espressotest.presentation.screen.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boost.espressotest.R;
import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.presentation.screen.detail.presenter.DetailPresenter;
import com.boost.espressotest.presentation.tools.Utils;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

public class DetailActivity extends AppCompatActivity implements DetailView {

    public static final String ARG_PRODUCT_ID = "ARG_PRODUCT_ID";

    @BindView(R.id.iv_favorite) ImageView mAddToFavoriteImageView;
    @BindView(R.id.iv_product_image) ImageView mProductImageView;
    @BindView(R.id.tv_product_title) TextView mProductNameTextView;
    @BindView(R.id.tv_product_price) TextView mProductPriceTextView;
    @BindView(R.id.tv_product_description) TextView mProductDescriptionTextView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private long mProductId;

    @Inject
    DetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().initDetailComponent().inject(this);
        mPresenter.onAttach(this);

        mProductId = getIntent().getLongExtra(ARG_PRODUCT_ID, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getProduct(mProductId);
    }

    private void setProductInfo(ProductContent product) {
        Glide.with(this).load(product.getImageUrl()).centerCrop().into(mProductImageView);
        mProductNameTextView.setText(product.getName());
        mProductPriceTextView.setText(String.valueOf(product.getPriceInCents()));
        mProductDescriptionTextView.setText(product.getProducerName());
        mAddToFavoriteImageView.setSelected(product.isFavorite());
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.iv_favorite)
    void onFavoriteClick() {
        mPresenter.changeFavoriteStatus();
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
    public void onProductLoadSuccess(ProductContent product) {
        setProductInfo(product);
    }

    @Override
    public void onProductLoadError() {
        Utils.showToast(this, getString(R.string.error_database));
    }

    @Override
    public void onProductStatusUpdateSuccess(ProductContent product) {
        mAddToFavoriteImageView.setSelected(product.isFavorite());
    }

    @Override
    public void onProductStatusUpdateError() {
        Utils.showToast(this, getString(R.string.error_database));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        MainApp.getDependencyGraph().releaseDetailComponent();
        super.onDestroy();
    }
}
