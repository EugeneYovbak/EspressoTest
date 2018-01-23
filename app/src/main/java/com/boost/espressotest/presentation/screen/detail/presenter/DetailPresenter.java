package com.boost.espressotest.presentation.screen.detail.presenter;

import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.domain.ProductRepository;
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

    @Inject
    public DetailPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProduct(long productId) {
        mView.showLoadingIndicator();
        Disposable productListDisposable = mProductRepository.getProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::onProductLoadSuccess, throwable -> mView.onProductLoadError());
        mCompositeDisposable.add(productListDisposable);
    }

    public void changeFavoriteStatus(ProductContent product) {
        Disposable productFavoriteStatusDisposable = mProductRepository.updateProductStatus(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::onProductStatusUpdateSuccess, throwable -> mView.onProductStatusUpdateError());
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
