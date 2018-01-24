package com.boost.espressotest.presentation.screen.main.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boost.espressotest.R;
import com.boost.espressotest.data.content.ProductContent;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductContent> mProductList = new ArrayList<>();
    private ProductInteractionListener mProductInteractionListener;

    public ProductAdapter(ProductInteractionListener productInteractionListener) {
        mProductInteractionListener = productInteractionListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(mProductList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void setProductList(List<ProductContent> productList) {
        this.mProductList = productList;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_product_image) ImageView mProductImageView;
        @BindView(R.id.tv_product_title) TextView mProductNameTextView;
        @BindView(R.id.tv_product_price) TextView mProductPriceTextView;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ProductContent product) {
            Glide.with(itemView.getContext()).load(product.getImageUrl()).centerCrop().into(mProductImageView);
            mProductNameTextView.setText(product.getName());
            mProductPriceTextView.setText(String.valueOf(product.getPriceInCents()));
        }

        @OnClick(R.id.root_view)
        void onItemClick() {
            mProductInteractionListener.onProductItemClick(getAdapterPosition());
        }
    }

    public interface ProductInteractionListener {
        void onProductItemClick(int position);
    }
}
