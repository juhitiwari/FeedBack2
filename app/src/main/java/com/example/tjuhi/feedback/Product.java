package com.example.tjuhi.feedback;

/**
 * Created by tjuhi on 6/2/2017.
 */

public class Product {
    private String mProduct;
    private String mRating;
    private String mProductID;
private int length;
    public String getmProductID() {
        return mProductID;
    }

    public void setmProductID(String mProductID) {
        this.mProductID = mProductID;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Product(){


}
    public Product(String mProduct,String mRating,String mProductID,int length){
        super();
        this.mProduct=mProduct;
        this.mRating=mRating;
        this.mProductID=mProductID;
        this.length=length;
    }
    public String getmProduct() {
        return mProduct;
    }

    public void setmProduct(String mProduct) {
        this.mProduct = mProduct;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }
}
