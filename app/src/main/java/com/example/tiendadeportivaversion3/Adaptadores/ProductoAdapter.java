package com.example.tiendadeportivaversion3.Adaptadores;

import android.widget.BaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiendadeportivaversion3.DB.DBFirebase;
import com.example.tiendadeportivaversion3.DetallesActivity;
import com.example.tiendadeportivaversion3.Entidades.Producto;
import com.example.tiendadeportivaversion3.R;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> arrayProductos;
    private DBFirebase dbFirebase;

    public ProductoAdapter(Context context, ArrayList<Producto> arrayProductos) {
        this.context = context;
        this.arrayProductos = arrayProductos;
        dbFirebase = new DBFirebase();
    }

    public ArrayList<Producto> getArrayProductos() {
        return arrayProductos;
    }

    public void setArrayProductos(ArrayList<Producto> arrayProductos) {
        this.arrayProductos = arrayProductos;
    }

    @Override
    public int getCount() {
        return arrayProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.product_template, null);

        Producto producto = arrayProductos.get(i);
        LinearLayout linearLayoutItemProducto = (LinearLayout) view.findViewById(R.id.linearLayoutItemProducto);
        ImageView imgProducto = (ImageView) view.findViewById(R.id.imgProduct);
        TextView textName = (TextView) view.findViewById(R.id.textNameProduct);
        TextView textDescription = (TextView) view.findViewById(R.id.textDescriptionProduct);
        TextView textPrice = (TextView) view.findViewById(R.id.textPriceProduct);
        Button btnEliminarProducto = (Button) view.findViewById(R.id.btneliminarproducto);

        imgProducto.setImageResource(Integer.parseInt(producto.getImage()));
        textName.setText(producto.getName());
        textDescription.setText(producto.getDescription());
        textPrice.setText(new StringBuilder().append("$").append(String.valueOf(producto.getPrice())));

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbFirebase.deleteData(producto.getId());
                for(Iterator<Producto> iterator = arrayProductos.iterator(); iterator.hasNext(); ) {
                    if(iterator.next().getId().equals(producto.getId()))
                        iterator.remove();
                }
                notifyDataSetChanged();
            }
        });

        linearLayoutItemProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetallesActivity.class);
                intent.putExtra("id", producto.getId());
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", producto.getPrice());
                intent.putExtra("image", producto.getImage());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
