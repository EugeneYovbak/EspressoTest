package com.boost.espressotest.presentation.screen.main.presenter

import com.annimon.stream.Stream
import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.domain.exceptions.NoConnectivityException
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.base.BasePresenter
import com.boost.espressotest.presentation.screen.main.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainPresenter
@Inject constructor(private val mProductRepository: ProductRepository) : BasePresenter<MainView>() {
    // TODO: 3/22/18 do you even need this as companion object?
    companion object {
        private const val PRODUCTS_PAGE = 1
        private const val PRODUCTS_PER_PAGE = 50
    }

    private val mCompositeDisposable = CompositeDisposable()

    private var mProductList: List<Product> = ArrayList()

    fun getProductList() {
        if (mProductList.isEmpty()) {
            // TODO: 3/22/18 FYI with moxy you don't need to do this ugly view!!., view?. checks
            view!!.showLoadingIndicator()
            val productListDisposable = mProductRepository.getProductList(PRODUCTS_PAGE, PRODUCTS_PER_PAGE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate({ view!!.hideLoadingIndicator() })
                    .subscribe(
                            { this.handleProductsLoadSuccess(it) },
                            { this.handleProductsLoadError(it) }
                    )
            mCompositeDisposable.add(productListDisposable)
        } else {
            view?.showProducts(mProductList)
        }
    }

    private fun handleProductsLoadSuccess(products: List<Product>) {
        mProductList = products
        view?.showProducts(products)
    }

    private fun handleProductsLoadError(throwable: Throwable) {
        if (throwable is NoConnectivityException) {
            view?.internetConnectionError()
        } else {
            view?.productsLoadError()
        }
    }

    fun filterList(searchText: String) {
        if (searchText.isEmpty()) {
            view?.showProducts(mProductList)
        } else {
            val searchList = Stream.of(mProductList)
                    .filter { (_, name) -> name.toLowerCase().contains(searchText.toLowerCase()) }
                    .toList()
            view?.showProducts(searchList)
        }
    }

    fun onProductItemClick(position: Int) {
        view?.navigateToDetailScreen(mProductList[position].id)
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}
