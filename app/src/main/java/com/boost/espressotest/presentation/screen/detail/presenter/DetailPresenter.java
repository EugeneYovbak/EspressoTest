package com.boost.espressotest.presentation.screen.detail.presenter;

import com.boost.espressotest.data.rest_tools.NoConnectivityException;
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

    public void getProduct(long id) {
        mView.showLoadingIndicator();
        Disposable productListDisposable = mProductRepository.getProduct(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::onProductLoadSuccess, throwable -> {
                    if (throwable instanceof NoConnectivityException) {
                        mView.internetConnectionError();
                    } else {
                        mView.onProductLoadError();
                    }
                });
        mCompositeDisposable.add(productListDisposable);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
