package com.boost.espressotest.presentation.screen.main.presenter;

import com.annimon.stream.Stream;
import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.data.rest_tools.NoConnectivityException;
import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.BasePresenter;
import com.boost.espressotest.presentation.screen.main.view.MainView;

import java.util.ArrayList;
import java.util.List;

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

    private List<ProductContent> mProductList = new ArrayList<>();

    @Inject
    public MainPresenter(ProductRepository productRepository) {
        mProductRepository = productRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getProductList() {
        if (mProductList.isEmpty()) {
            mView.showLoadingIndicator();
            Disposable productListDisposable = mProductRepository.getProductList(PRODUCTS_PAGE, PRODUCTS_PER_PAGE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(mView::hideLoadingIndicator)
                    .subscribe(productContents -> {
                        mProductList = productContents;
                        mView.onProductsLoadSuccess(productContents);
                    }, throwable -> {
                        if (throwable instanceof NoConnectivityException) {
                            mView.internetConnectionError();
                        } else {
                            mView.onProductsLoadError();
                        }
                    });
            mCompositeDisposable.add(productListDisposable);
        } else {
            mView.onProductsLoadSuccess(mProductList);
        }
    }

    public void filterList(String searchText) {
        if (searchText.isEmpty()) {
            mView.onListFiltered(mProductList);
        } else {
            List<ProductContent> searchList = Stream.of(mProductList).filter(value -> value.getName().toLowerCase().contains((searchText))).toList();
            mView.onListFiltered(searchList);
        }
    }

    public void onProductItemClick(int position) {
        mView.navigateToDetailScreen(mProductList.get(position).getId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
