package com.android.inventoryapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Product extends Fragment {

    ListView productListView;
    private ArrayList<Products> mProductsArrayList;
    private ProductAdapter pAdapter;
    private FloatingActionButton fabButton;
    private ProductDataBase mProductDataBase;
    private int noOfSales;

    public Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        mProductsArrayList = new ArrayList<>();

        productListView = (ListView) view.findViewById(R.id.product_list);
        fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);

        // mProductsArrayList.add(new Products("Andyy", 12, 10));


        getProduct();
        addProduct();

        return view;
    }

    public void getProduct() {
        mProductDataBase = new ProductDataBase(getActivity().getApplicationContext());
        mProductsArrayList = mProductDataBase.getProduct();
        pAdapter = new ProductAdapter(mProductsArrayList, getActivity());


        productListView.setAdapter(pAdapter);
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sales");
                builder.setTitle("");
                builder.setPositiveButton("Sales", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        View vieww = LayoutInflater.from(getActivity()).inflate(R.layout.addsale_layout, null);
                        final EditText editSale = (EditText) vieww.findViewById(R.id.add_sales);
                        alert.setView(vieww);
                        alert.setTitle("Enter Sale?");
                        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id = mProductsArrayList.get(position).getId();
                                noOfSales = mProductsArrayList.get(position).getpQuantity() - Integer.valueOf(editSale.getText().toString());
                                Products product = new Products(mProductsArrayList.get(position).getProdutName(),noOfSales, mProductsArrayList.get(position).getpPrice(), 0);
                                mProductsArrayList.set(id,product);
                                mProductDataBase.update(id, noOfSales);
                                pAdapter.notifyDataSetChanged();
                            }
                        });
                        alert.create().show();
                    }
                });


                builder.create().show();
            }
        });

        // pAdapter.notifyDataSetChanged();

    }

    public void addProduct() {

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Product");
                builder.setMessage("Add you PRoduct Name, Quantity , and PRice!");
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.product_alertview, null);
                final EditText peditName = (EditText) view1.findViewById(R.id.edit_pName);
                final EditText peditQuantity = (EditText) view1.findViewById(R.id.edit_pQuantity);
                final EditText peditPrice = (EditText) view1.findViewById(R.id.edit_pPrice);
                builder.setView(view1);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Products products = new Products(peditName.getText().toString(), Integer.valueOf(peditQuantity.getText().toString()), Integer.valueOf(peditPrice.getText().toString()), 0);
                        mProductsArrayList.add(products);
                        mProductDataBase.saveProduct(products);
                        pAdapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
            }
        });

    }
}
