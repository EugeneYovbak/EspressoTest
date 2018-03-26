package com.boost.espressotest.presentation.screen.main.presenter

import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.exceptions.NoConnectivityException
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.screen.main.view.MainView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit
import java.util.*

@RunWith(JUnit4::class)
class MainPresenterTest {

    private val IGNORED_INT = 2

    @Mock
    private lateinit var mProductRepository: ProductRepository

    @Mock
    private lateinit var mMainView: MainView

    @InjectMocks
    private lateinit var mMainPresenter: MainPresenter

    @Rule @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        mMainPresenter.onAttach(mMainView)
    }

    @Test
    fun getProductListWhenSuccess_returnList() {
        val productList = generateProductList()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(productList)
    }

    @Test
    fun getProductListWhenError_returnError() {
        val exception = Exception()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.error(exception))

        mMainPresenter.getProductList()

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).productsLoadError()
    }

    @Test
    fun getProductListWhenConnectionError_returnConnectionError() {
        val noConnectivityException = NoConnectivityException()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.error(noConnectivityException))

        mMainPresenter.getProductList()

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).internetConnectionError()
    }

    @Test
    fun getProductListSeveralTimes_returnList() {
        val productList = generateProductList()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()
        mMainPresenter.getProductList()

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(2)).showProducts(productList)
    }

    @Test
    fun filterListWithEmptyText_returnList() {
        val productList = generateProductList()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()

        mMainPresenter.filterList("")

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(2)).showProducts(productList)
    }

    @Test
    fun filterListWithItemName_returnListWithOnlyOneItem() {
        val productList = generateProductList()

        val searchList = ArrayList<Product>()
        searchList.add(productList[10])

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()

        mMainPresenter.filterList("Name10")

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(productList)
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(searchList)
    }

    @Test
    fun filterListWithExampleText_returnEmptyList() {
        val productList = generateProductList()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()

        mMainPresenter.filterList("Lorem Ipsum")

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(productList)
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(ArrayList())
    }

    @Test
    fun filterListSeveralTimes_returnDifferentListsSeveralTimes() {
        val productList = generateProductList()

        val firstSearchList = ArrayList<Product>()
        firstSearchList.add(productList[1])
        firstSearchList.addAll(productList.subList(10, 20))

        val secondSearchList = ArrayList<Product>()
        secondSearchList.add(productList[10])

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()

        mMainPresenter.filterList("Name")
        mMainPresenter.filterList("Name1")
        mMainPresenter.filterList("Name10")
        mMainPresenter.filterList("Name101")
        mMainPresenter.filterList("")

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(3)).showProducts(productList)
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(firstSearchList)
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(secondSearchList)
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(ArrayList())
    }

    @Test
    fun clickOnItem_navigateToDetailScreen() {
        val productList = generateProductList()

        whenever(mProductRepository.getProductList(anyInt(), anyInt()))
                .thenReturn(Single.just(productList))

        mMainPresenter.getProductList()
        mMainPresenter.onProductItemClick(IGNORED_INT)

        Mockito.verify<MainView>(mMainView, times(1)).showLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).hideLoadingIndicator()
        Mockito.verify<MainView>(mMainView, times(1)).showProducts(productList)
        Mockito.verify<MainView>(mMainView, times(1)).navigateToDetailScreen(productList[IGNORED_INT].id)
    }

    @After
    fun cleanUp() {
        mMainPresenter.onDetach()
    }

    private fun generateProductList(): List<Product> {
        val productList = ArrayList<Product>()
        for (i in 0..49) {
            val product = Product(
                    i.toLong(),
                    "Name$i",
                    (i * 100).toLong(),
                    "Producer$i",
                    "https://picsum.photos/$i",
                    i % 2 == 0)
            productList.add(product)
        }
        return productList
    }
}