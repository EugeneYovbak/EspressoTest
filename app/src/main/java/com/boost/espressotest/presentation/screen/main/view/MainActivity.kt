package com.boost.espressotest.presentation.screen.main.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.SearchView
import com.boost.espressotest.R
import com.boost.espressotest.domain.model.Product
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter
import com.boost.espressotest.presentation.screen.main.view.adapter.ProductAdapter
import com.boost.espressotest.presentation.tools.Utils
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainView {

    private lateinit var mProductAdapter: ProductAdapter

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.onAttach(this)
        initList()
        initSearch()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.getProductList()
    }

    private fun initList() {
        mProductAdapter = ProductAdapter(object : ProductAdapter.ProductInteractionListener {
            override fun onProductItemClick(product: Product, position: Int) {
                mPresenter.onProductItemClick(position)
            }

        })
        rv_products.layoutManager = LinearLayoutManager(this)
        rv_products.setHasFixedSize(true)
        rv_products.adapter = mProductAdapter
    }

    private fun initSearch() {
        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.filterList(newText)
                return false
            }

        })
    }

    override fun showLoadingIndicator() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = View.GONE
    }

    override fun showProducts(productList: List<Product>) {
        mProductAdapter.setProductList(productList)
    }

    override fun productsLoadError() {
        Utils.showToast(this, getString(R.string.error_server))
    }

    override fun navigateToDetailScreen(productId: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ARG_PRODUCT_ID, productId)
        startActivity(intent)
    }

    override fun internetConnectionError() {
        Utils.showToast(this, getString(R.string.error_connection_toast))
    }

    override fun onDestroy() {
        sv_search.setOnQueryTextListener(null)
        mPresenter.onDetach()
        super.onDestroy()
    }
}
