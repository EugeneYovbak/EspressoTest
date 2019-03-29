package com.boost.espressotest.presentation.screen.main.view

import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.base.BaseView

interface MainView : BaseView {

    fun showProducts(productList: List<Product>)

    fun navigateToDetailScreen(productId: Long)

    fun productsLoadError(error: String? = null)

    fun internetConnectionError()
}
