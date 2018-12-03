package com.sw.sgtu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sw.sgtu.R;
import com.sw.sgtu.request.ResultadosResponse;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ViewHolder>{

    private List<ResultadosResponse> listaResultados;
    private Context context;

    public ResultadosAdapter(Context context, List<ResultadosResponse> listaResultados){
        this.context = context;
        this.listaResultados = listaResultados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNroDescripcion, itemDescripcion;
        public ViewHolder(View itemView) {
            super(itemView);
            /*itemDescripcion = itemView.findViewById(R.id.itemDescripcion);
            itemNroDescripcion = itemView.findViewById(R.id.itemNroDescripcion);*/
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*Queja queja = listaQueja.get(position);
        int nro_queja = position + 1;
        String nro = "N° " + nro_queja ;
        holder.itemDescripcion.setText(queja.getDescripcion());
        holder.itemNroDescripcion.setText(nro);*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resultados, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }
}
