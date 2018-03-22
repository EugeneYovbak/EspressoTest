package com.boost.espressotest.presentation.screen.detail.presenter


import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.screen.detail.view.DetailView
import io.reactivex.Completable
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
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnit

private const val IGNORED_LONG: Long = 0

@RunWith(JUnit4::class)
class DetailPresenterTest {

    @Mock
    private lateinit var mProductRepository: ProductRepository

    @Mock
    private lateinit var mDetailView: DetailView

    @InjectMocks
    private lateinit var mDetailPresenter: DetailPresenter

    @Rule @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        mDetailPresenter = DetailPresenter(mProductRepository)
        mDetailPresenter.onAttach(mDetailView)
    }

    @Test
    fun getProductWhenSuccess_returnProduct() {
        val product = Mockito.mock(Product::class.java)

        `when`(mProductRepository.getProduct(anyLong()))
                .thenReturn(Single.just(product))

        mDetailPresenter.getProduct(IGNORED_LONG)

        Mockito.verify<DetailView>(mDetailView, times(1)).showLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).hideLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).showProduct(product)
    }

    @Test
    fun getProductWhenError_returnError() {
        val exception = Exception()

        `when`(mProductRepository.getProduct(anyLong()))
                .thenReturn(Single.error(exception))

        mDetailPresenter.getProduct(IGNORED_LONG)

        Mockito.verify<DetailView>(mDetailView, times(1)).showLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).hideLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).productLoadError()
    }

    @Test
    fun getProductSeveralTimes_returnProduct() {
        val product = Mockito.mock(Product::class.java)

        `when`(mProductRepository.getProduct(anyLong()))
                .thenReturn(Single.just(product))

        mDetailPresenter.getProduct(IGNORED_LONG)
        mDetailPresenter.getProduct(IGNORED_LONG)

        Mockito.verify<DetailView>(mDetailView, times(1)).showLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).hideLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(2)).showProduct(product)
    }

    @Test
    fun changeFavoriteStatusSuccess_returnProductWithNewStatus() {
        val product = Mockito.mock(Product::class.java)

        `when`(mProductRepository.getProduct(anyLong()))
                .thenReturn(Single.just(product))

        mDetailPresenter.getProduct(IGNORED_LONG)

        `when`(mProductRepository.updateProductStatus(anyLong(), anyBoolean()))
                .thenReturn(Completable.complete())

        mDetailPresenter.changeFavoriteStatus()

        Mockito.verify<DetailView>(mDetailView, times(1)).showLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).hideLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).showProduct(product)
        Mockito.verify<DetailView>(mDetailView, times(1)).updateProductStatus(anyBoolean())
    }

    @Test
    fun changeFavoriteStatusError_returnError() {
        val product = Mockito.mock(Product::class.java)
        val exception = Exception()

        `when`(mProductRepository.getProduct(anyLong()))
                .thenReturn(Single.just(product))

        mDetailPresenter.getProduct(IGNORED_LONG)

        `when`(mProductRepository.updateProductStatus(anyLong(), anyBoolean()))
                .thenReturn(Completable.error(exception))

        mDetailPresenter.changeFavoriteStatus()

        Mockito.verify<DetailView>(mDetailView, times(1)).showLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).hideLoadingIndicator()
        Mockito.verify<DetailView>(mDetailView, times(1)).showProduct(product)
        Mockito.verify<DetailView>(mDetailView, times(1)).productStatusUpdateError()
    }

    @After
    fun cleanUp() {
        mDetailPresenter.onDetach()
    }
}
