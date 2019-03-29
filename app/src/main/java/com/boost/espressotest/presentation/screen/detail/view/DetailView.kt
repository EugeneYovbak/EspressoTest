package com.boost.espressotest.presentation.screen.detail.view

import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.base.BaseView

interface DetailView : BaseView {
    fun showProduct(product: Product)

    fun productLoadError(error: String? = null)

    fun updateProductStatus(isFavorite: Boolean)

    fun productStatusUpdateError(error: String? = null)
}
