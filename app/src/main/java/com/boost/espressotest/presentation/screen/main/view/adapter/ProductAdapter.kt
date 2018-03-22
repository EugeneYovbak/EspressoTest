package com.boost.espressotest.presentation.screen.main.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boost.espressotest.R
import com.boost.espressotest.domain.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.*
import java.util.*

class ProductAdapter(private val mProductInteractionListener: ProductInteractionListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val mProductList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // TODO: 3/22/18 layoutinflater as extension for context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mProductList[position])
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    fun setProductList(productList: List<Product>) {
        mProductList.clear()
        mProductList.addAll(productList)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(product: Product) {
            Glide.with(itemView.context).load(product.imageUrl).centerCrop().into(iv_product_image)
            tv_product_title.text = product.name
            tv_product_price.text = product.priceInCents.toString()
            root_view.setOnClickListener {
                mProductInteractionListener.onProductItemClick(mProductList[adapterPosition], adapterPosition)
            }
        }
    }

    interface ProductInteractionListener {
        fun onProductItemClick(product: Product, position: Int)
    }
}
