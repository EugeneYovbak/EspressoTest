package com.boost.espressotest.presentation.screen.main.presenter;

import com.annimon.stream.Stream;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.exceptions.NoConnectivityException;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.base.BasePresenter;
import com.boost.espressotest.presentation.screen.main.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private static final int PRODUCTS_PAGE = 1;
    private static final int PRODUCTS_PER_PAGE = 50;

    private ProductRepository mProductRepository;
    private CompositeDisposable mCompositeDisposable;

    private List<Product> mProductList = new ArrayList<>();

    @Inject
    public MainPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProductList() {
        if (mProductList.isEmpty()) {
            getView().showLoadingIndicator();
            Disposable productListDisposable = mProductRepository.getProductList(PRODUCTS_PAGE, PRODUCTS_PER_PAGE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(getView()::hideLoadingIndicator)
                    .subscribe(this::handleProductsLoadSuccess, this::handleProductsLoadError);
            mCompositeDisposable.add(productListDisposable);
        } else {
            getView().showProducts(mProductList);
        }
    }

    private void handleProductsLoadSuccess(List<Product> products) {
        mProductList = products;
        getView().showProducts(products);
    }

    private void handleProductsLoadError(Throwable throwable) {
        if (throwable instanceof NoConnectivityException) {
            getView().internetConnectionError();
        } else {
            getView().productsLoadError();
        }
    }

    public void filterList(String searchText) {
        if (searchText.isEmpty()) {
            getView().showProducts(mProductList);
        } else {
            List<Product> searchList = Stream.of(mProductList)
                    .filter(value -> value.getName().toLowerCase().contains((searchText.toLowerCase())))
                    .toList();
            getView().showProducts(searchList);
        }
    }

    public void onProductItemClick(int position) {
        getView().navigateToDetailScreen(mProductList.get(position).getId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
