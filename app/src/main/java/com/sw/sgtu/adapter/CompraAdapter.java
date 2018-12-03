package com.sw.sgtu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sw.sgtu.R;
import com.sw.sgtu.modelo.Compra;

import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.ViewHolder>{

    private List<Compra> listaCompra;
    private Context context;

    public CompraAdapter(Context context, List<Compra> listaCompra){
        this.context = context;
        this.listaCompra = listaCompra;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemCompraFecha, itemCompraLinea, itemCompraPrecio,
                itemCompraParaderoInicial, itemCompraParaderofinal;
        public ViewHolder(View itemView) {
            super(itemView);
            itemCompraFecha = itemView.findViewById(R.id.itemCompraFecha);
            itemCompraLinea = itemView.findViewById(R.id.itemCompraLinea);
            itemCompraParaderoInicial = itemView.findViewById(R.id.itemCompraParaderoInicial);
            itemCompraParaderofinal = itemView.findViewById(R.id.itemCompraParaderoFinal);
            itemCompraPrecio = itemView.findViewById(R.id.itemCompraPrecio);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Compra compra = listaCompra.get(position);
        String precio = "S/ " + compra.getCosto();
        holder.itemCompraFecha.setText(compra.getFecha());
        holder.itemCompraLinea.setText(compra.getLinea_transporte());
        holder.itemCompraParaderoInicial.setText(compra.getParadero_inicial());
        holder.itemCompraParaderofinal.setText(compra.getParadero_final());
        holder.itemCompraPrecio.setText(precio);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_compra, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return listaCompra.size();
    }
}
