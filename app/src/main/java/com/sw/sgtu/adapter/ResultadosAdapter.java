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
        private TextView itemResultadoLinea;
        public ViewHolder(View itemView) {
            super(itemView);
            itemResultadoLinea = itemView.findViewById(R.id.itemResultadoLinea);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultadosResponse resultadosResponse = listaResultados.get(position);
        holder.itemResultadoLinea.setText(resultadosResponse.getLinea_transporte());
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
