package com.boost.espressotest.presentation.screen.main.presenter;


import com.boost.espressotest.base.BasePresenter;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.screen.main.view.MainView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

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

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
