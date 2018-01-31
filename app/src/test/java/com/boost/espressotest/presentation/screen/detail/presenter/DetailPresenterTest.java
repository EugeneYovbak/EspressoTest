package com.boost.espressotest.presentation.screen.detail.presenter;


import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.detail.view.DetailView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class DetailPresenterTest {

    private static final long IGNORED_LONG = 0;

    @Mock
    private ProductRepository mProductRepository;

    @Mock
    private DetailView mDetailView;

    @InjectMocks
    private DetailPresenter mDetailPresenter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        mDetailPresenter = new DetailPresenter(mProductRepository);
        mDetailPresenter.onAttach(mDetailView);
    }

    @Test
    public void getProductWhenSuccess_returnProduct() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(anyLong()))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(IGNORED_LONG);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).showProduct(product);
    }

    @Test
    public void getProductWhenError_returnError() {
        Exception exception = new Exception();

        when(mProductRepository.getProduct(anyLong()))
                .thenReturn(Observable.error(exception));

        mDetailPresenter.getProduct(IGNORED_LONG);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).productLoadError();
    }

    @Test
    public void getProductSeveralTimes_returnProduct() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(anyLong()))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(IGNORED_LONG);
        mDetailPresenter.getProduct(IGNORED_LONG);

        Mockito.verify(mDetailView, times(1)).showLoadingIndicator();
        Mockito.verify(mDetailView, times(1)).hideLoadingIndicator();
        Mockito.verify(mDetailView, times(2)).showProduct(product);
    }

    @Test
    public void changeFavoriteStatusSuccess_returnProductWithNewStatus() {
        Product product = generateProduct();

        when(mProductRepository.getProduct(anyLong()))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(IGNORED_LONG);

        when(mProductRepository.updateProductStatus(anyLong(), anyBoolean()))
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

        when(mProductRepository.getProduct(anyLong()))
                .thenReturn(Observable.just(product));

        mDetailPresenter.getProduct(IGNORED_LONG);

        when(mProductRepository.updateProductStatus(anyLong(), anyBoolean()))
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
