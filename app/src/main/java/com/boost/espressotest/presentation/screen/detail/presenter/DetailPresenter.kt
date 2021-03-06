package com.boost.espressotest.presentation.screen.detail.presenter

import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.base.BasePresenter
import com.boost.espressotest.presentation.screen.detail.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter
@Inject constructor(private val mProductRepository: ProductRepository) : BasePresenter<DetailView>() {
    private val mCompositeDisposable = CompositeDisposable()

    private lateinit var mProduct: Product

    fun getProduct(productId: Long) {
        if (!::mProduct.isInitialized) {
            view?.showLoadingIndicator()
            val productListDisposable = mProductRepository.getProduct(productId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate { view?.hideLoadingIndicator() }
                    .subscribe(
                            this::handleProductLoadSuccess,
                            this::handleProductLoadError
                    )
            mCompositeDisposable.add(productListDisposable)
        } else {
            view?.showProduct(mProduct)
        }
    }

    private fun handleProductLoadSuccess(product: Product) {
        mProduct = product
        view?.showProduct(product)
    }

    private fun handleProductLoadError(throwable: Throwable) {
        view?.productLoadError(throwable.message)
    }

    fun changeFavoriteStatus() {
        val productFavoriteStatusDisposable = mProductRepository.updateProductStatus(mProduct.id, !mProduct.isFavorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::handleFavoriteStatusChangeSuccess,
                        this::handleFavoriteStatusChangeError
                )
        mCompositeDisposable.add(productFavoriteStatusDisposable)
    }

    private fun handleFavoriteStatusChangeSuccess() {
        mProduct.isFavorite = !mProduct.isFavorite
        view?.updateProductStatus(mProduct.isFavorite)
    }

    private fun handleFavoriteStatusChangeError(throwable: Throwable) {
        view?.productStatusUpdateError(throwable.message)
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}
