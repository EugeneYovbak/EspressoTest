package com.boost.espressotest.presentation.screen.main.presenter;

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

    private ProductRepository mProductRepository;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public MainPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProductList(String query, String where, int page) {
        mView.showLoadingIndicator();
        Disposable productListDisposable = mProductRepository.getProductList(query, where, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideLoadingIndicator)
                .subscribe(mView::onProductsLoadSuccess, throwable -> mView.onProductsLoadError());
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
