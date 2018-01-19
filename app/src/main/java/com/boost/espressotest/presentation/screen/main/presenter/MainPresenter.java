package com.boost.espressotest.presentation.screen.main.presenter;

import com.boost.espressotest.data.rest_tools.NoConnectivityException;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.BasePresenter;
import com.boost.espressotest.presentation.screen.main.view.MainView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private static final int PRODUCTS_PAGE = 1;
    private static final int PRODUCTS_PER_PAGE = 50;

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public MainPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProductList() {
        mView.showLoadingIndicator();
        Disposable productListDisposable = mProductRepository.getProductList(PRODUCTS_PAGE, PRODUCTS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::onProductsLoadSuccess, throwable -> {
                    if (throwable instanceof NoConnectivityException) {
                        mView.internetConnectionError();
                    } else {
                        mView.onProductsLoadError();
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
