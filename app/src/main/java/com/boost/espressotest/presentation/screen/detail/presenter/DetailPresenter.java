package com.boost.espressotest.presentation.screen.detail.presenter;

import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.BasePresenter;
import com.boost.espressotest.presentation.screen.detail.view.DetailView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

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
            mView.showLoadingIndicator();
            Disposable productListDisposable = mProductRepository.getProduct(productId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(mView::hideLoadingIndicator)
                    // TODO: 1/24/18 check mainpresenter approach
                    .subscribe(product -> {
                        mProduct = product;
                        mView.onProductLoadSuccess(product);
                    }, throwable -> mView.onProductLoadError());
            mCompositeDisposable.add(productListDisposable);
        } else {
            mView.onProductLoadSuccess(mProduct);
        }
    }

    public void changeFavoriteStatus() {
        Disposable productFavoriteStatusDisposable = mProductRepository.updateProductStatus(mProduct.getId(), !mProduct.isFavorite())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    mProduct.setFavorite(!mProduct.isFavorite());
                    mView.onProductStatusUpdateSuccess(mProduct.isFavorite());
                }, throwable -> mView.onProductStatusUpdateError());
        mCompositeDisposable.add(productFavoriteStatusDisposable);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
