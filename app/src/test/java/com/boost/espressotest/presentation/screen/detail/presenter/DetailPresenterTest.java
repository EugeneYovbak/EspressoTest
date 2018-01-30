package com.boost.espressotest.presentation.screen.detail.presenter;


import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.detail.view.DetailView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class DetailPresenterTest {

    private DetailPresenter mDetailPresenter;

    @Mock
    private ProductRepository mProductRepository;

    @Mock
    private DetailView mDetailView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        mDetailPresenter = new DetailPresenter(mProductRepository);
        mDetailPresenter.onAttach(mDetailView);
    }

    @Test
    public void getProductWhenSuccess_returnProduct() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(0))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(0);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).showProduct(product);
    }

    @Test
    public void getProductWhenError_returnError() {
        Exception exception = new Exception();

        when(mProductRepository.getProduct(0))
                .thenReturn(Observable.error(exception));

        mDetailPresenter.getProduct(0);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).productLoadError();
    }

    @Test
    public void getProductSeveralTimes_returnProduct() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(0))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(0);
        mDetailPresenter.getProduct(0);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(2)).showProduct(product);
    }

    @Test
    public void changeFavoriteStatusSuccess_returnProductWithNewStatus() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(0))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(0);

        when(mProductRepository.updateProductStatus(0, true))
                .thenReturn(Completable.complete());

        mDetailPresenter.changeFavoriteStatus();

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).showProduct(product);
        Mockito.verify(mDetailView, times(1)).updateProductStatus(true);
    }

    @Test
    public void changeFavoriteStatusError_returnError() {
        Product product = generateProduct();
        Exception exception = new Exception();

        when(mProductRepository.getProduct(0))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(0);

        when(mProductRepository.updateProductStatus(0, true))
                .thenReturn(Completable.error(exception));

        mDetailPresenter.changeFavoriteStatus();

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).showProduct(product);
        Mockito.verify(mDetailView, times(1)).productStatusUpdateError();
    }

    @After
    public void cleanUp() {
        mDetailPresenter.onDetach();
    }

    private Product generateProduct() {
        return new Product(
                0,
                "Name" + 0,
                100,
                "Producer" + 0,
                "https://picsum.photos/" + 100,
                false);
    }
}
