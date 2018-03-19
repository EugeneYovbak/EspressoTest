package com.boost.espressotest.presentation.screen.detail.presenter;

import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.base.BasePresenter;
import com.boost.espressotest.presentation.screen.detail.view.DetailView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter extends BasePresenter<DetailView> {

    private ProductRepository mProductRepository;
    private CompositeDisposable mCompositeDisposable;

    private Product mProduct;

    @Inject
    public DetailPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProduct(long productId) {
        if (mProduct == null) {
            getView().showLoadingIndicator();
            Disposable productListDisposable = mProductRepository.getProduct(productId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(getView()::hideLoadingIndicator)
                    .subscribe(this::handleProductLoadSuccess, this::handleProductLoadError);
            mCompositeDisposable.add(productListDisposable);
        } else {
            getView().showProduct(mProduct);
        }
    }

    private void handleProductLoadSuccess(Product product) {
        mProduct = product;
        getView().showProduct(product);
    }

    private void handleProductLoadError(Throwable throwable) {
        getView().productLoadError();
    }

    public void changeFavoriteStatus() {
        Disposable productFavoriteStatusDisposable = mProductRepository.updateProductStatus(mProduct.getId(), !mProduct.isFavorite())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleFavoriteStatusChangeSuccess, this::handleFavoriteStatusChangeError);
        mCompositeDisposable.add(productFavoriteStatusDisposable);
    }

    private void handleFavoriteStatusChangeSuccess() {
        mProduct.setFavorite(!mProduct.isFavorite());
        getView().updateProductStatus(mProduct.isFavorite());
    }

    private void handleFavoriteStatusChangeError(Throwable throwable) {
        getView().productStatusUpdateError();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
