package com.boost.espressotest.presentation.screen.detail.view

import android.os.Bundle
import android.view.View
import com.boost.espressotest.R
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.screen.detail.presenter.DetailPresenter
import com.boost.espressotest.presentation.tools.Utils
import com.bumptech.glide.Glide
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_detail.*
import javax.inject.Inject

class DetailActivity : DaggerActivity(), DetailView {

    companion object {
        const val ARG_PRODUCT_ID = "ARG_PRODUCT_ID"
    }

    private var mProductId: Long = 0

    @Inject
    lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mPresenter.onAttach(this)

        mProductId = intent.getLongExtra(ARG_PRODUCT_ID, 0)
    }

    override fun onStart() {
        super.onStart()
        setViewListeners()
        mPresenter.getProduct(mProductId)
    }

    private fun setViewListeners() {
        iv_favorite.setOnClickListener { mPresenter.changeFavoriteStatus() }
        iv_back.setOnClickListener { onBackPressed() }
    }

    private fun setProductInfo(product: Product) {
        Glide.with(this).load(product.imageUrl).centerCrop().into(iv_product_image)
        tv_product_title.text = product.name
        tv_product_price.text = product.priceInCents.toString()
        tv_product_description.text = product.producerName
        iv_favorite.isSelected = product.isFavorite
    }

    override fun showLoadingIndicator() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = View.GONE
    }

    override fun showProduct(product: Product) {
        setProductInfo(product)
    }

    override fun productLoadError() {
        Utils.showToast(this, getString(R.string.error_database))
    }

    override fun updateProductStatus(isFavorite: Boolean) {
        iv_favorite.isSelected = isFavorite
    }

    override fun productStatusUpdateError() {
        Utils.showToast(this, getString(R.string.error_database))
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }
}
