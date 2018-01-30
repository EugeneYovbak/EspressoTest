package com.boost.espressotest.presentation.screen.main.presenter;

import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.domain.exceptions.NoConnectivityException;
import com.boost.espressotest.domain.model.Product;
import com.boost.espressotest.presentation.screen.main.view.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MainPresenterTest {

    private MainPresenter mMainPresenter;

    @Mock
    private ProductRepository mProductRepository;

    @Mock
    private MainView mMainView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        mMainPresenter = new MainPresenter(mProductRepository);
        mMainPresenter.onAttach(mMainView);
    }

    @Test
    public void getProductListWhenSuccess_returnList() {
        List<Product> productList = generateProductList();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).showProducts(productList);
    }

    @Test
    public void getProductListWhenError_returnError() {
        Exception exception = new Exception();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.error(exception));

        mMainPresenter.getProductList();

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).productsLoadError();
    }

    @Test
    public void getProductListWhenConnectionError_returnConnectionError() {
        NoConnectivityException noConnectivityException = new NoConnectivityException();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.error(noConnectivityException));

        mMainPresenter.getProductList();

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).internetConnectionError();
    }

    @Test
    public void getProductListSeveralTimes_returnList() {
        List<Product> productList = generateProductList();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();
        mMainPresenter.getProductList();

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(2)).showProducts(productList);
    }

    @Test
    public void filterListWithEmptyText_returnList() {
        List<Product> productList = generateProductList();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();

        mMainPresenter.filterList("");

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(2)).showProducts(productList);
    }

    @Test
    public void filterListWithItemName_returnListWithOnlyOneItem() {
        List<Product> productList = generateProductList();

        List<Product> searchList = new ArrayList<>();
        searchList.add(productList.get(10));

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();

        mMainPresenter.filterList("Name10");

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).showProducts(productList);
        Mockito.verify(mMainView, times(1)).showProducts(searchList);
    }

    @Test
    public void filterListWithExampleText_returnEmptyList() {
        List<Product> productList = generateProductList();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();

        mMainPresenter.filterList("Lorem Ipsum");

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).showProducts(productList);
        Mockito.verify(mMainView, times(1)).showProducts(new ArrayList<>());
    }

    @Test
    public void filterListSeveralTimes_returnDifferentListsSeveralTimes() {
        List<Product> productList = generateProductList();

        List<Product> firstSearchList = new ArrayList<>();
        firstSearchList.add(productList.get(1));
        firstSearchList.addAll(productList.subList(10, 20));

        List<Product> secondSearchList = new ArrayList<>();
        secondSearchList.add(productList.get(10));

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();

        mMainPresenter.filterList("Name");
        mMainPresenter.filterList("Name1");
        mMainPresenter.filterList("Name10");
        mMainPresenter.filterList("Name101");
        mMainPresenter.filterList("");

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(3)).showProducts(productList);
        Mockito.verify(mMainView, times(1)).showProducts(firstSearchList);
        Mockito.verify(mMainView, times(1)).showProducts(secondSearchList);
        Mockito.verify(mMainView, times(1)).showProducts(new ArrayList<>());
    }

    @Test
    public void clickOnItem_navigateToDetailScreen() {
        List<Product> productList = generateProductList();

        when(mProductRepository.getProductList(1, 50))
                .thenReturn(Observable.just(productList));

        mMainPresenter.getProductList();
        mMainPresenter.onProductItemClick(2);

        Mockito.verify(mMainView, times(1)).showLoadingIndicator();
        Mockito.verify(mMainView, times(1)).hideLoadingIndicator();
        Mockito.verify(mMainView, times(1)).showProducts(productList);
        Mockito.verify(mMainView, times(1)).navigateToDetailScreen(productList.get(2).getId());
    }

    @After
    public void cleanUp() {
        mMainPresenter.onDetach();
    }

    private List<Product> generateProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Product product = new Product(
                    i,
                    "Name" + i,
                    i * 100,
                    "Producer" + i,
                    "https://picsum.photos/" + i,
                    i % 2 == 0);
            productList.add(product);
        }
        return productList;
    }
}