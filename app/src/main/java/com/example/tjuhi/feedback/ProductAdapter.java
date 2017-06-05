package com.example.tjuhi.feedback;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tjuhi on 6/2/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    FeedbackAdapterOnClickHandler mClickHandler;

    public interface FeedbackAdapterOnClickHandler {
        void onClick(int rate);
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        holder.mProduct.setText(products.get(position).getmProduct());
       holder.mRating.setRating(Float.parseFloat(products.get(position).getmRating()));
        if (products.get(position).getLength()==1) {
            holder.mReview.setText(products.get(position).getLength() + " Review");
        }
        else{
            holder.mReview.setText(products.get(position).getLength() + " Reviews");
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mProduct,mReview;
        public RatingBar mRating;

        public ViewHolder(View itemView) {
            super(itemView);
            mProduct = (TextView) itemView.findViewById(R.id.product);
            mRating = (RatingBar) itemView.findViewById(R.id.rating);
            mReview=(TextView)itemView.findViewById(R.id.review_link);
            mReview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mReview) {
                int adapterPostition = getAdapterPosition();

                mClickHandler.onClick(adapterPostition);
            }
        }
    }

    public void SetOnItemClickListener(final FeedbackAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }
}
