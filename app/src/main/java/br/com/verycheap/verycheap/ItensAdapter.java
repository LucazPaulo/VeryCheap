package br.com.verycheap.verycheap;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.PessoaViewHolder> {

    List<Itens> itens;

    ItensAdapter(List<Itens> itens) {
        this.itens = itens;
    }


    public static class PessoaViewHolder extends RecyclerView.ViewHolder {
        TextView nomRazaoSocial;
        TextView valUltimaVenda;
        TextView nomBairro;
        TextView dscProduto;
        TextView dthEmissaoUltimaVenda;
        ImageView imageView;


        PessoaViewHolder(View itemView) {
            super(itemView);
            nomRazaoSocial = itemView.findViewById(R.id.nomRazaoSocial);
            valUltimaVenda = itemView.findViewById(R.id.valUltimaVenda);
            nomBairro = itemView.findViewById(R.id.nomBairro);
            dscProduto = itemView.findViewById(R.id.dscProduto);
            dthEmissaoUltimaVenda = itemView.findViewById(R.id.dthEmissaoUltimaVenda);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        PessoaViewHolder pvh = new PessoaViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder viewHolder, int i) {

        viewHolder.nomRazaoSocial.setText(itens.get(i).getNomRazaoSocial());
        viewHolder.valUltimaVenda.setText(itens.get(i).getValUltimaVenda());
        viewHolder.nomBairro.setText(itens.get(i).getNomBairro());
        viewHolder.dscProduto.setText(itens.get(i).getDscProduto());
        viewHolder.dthEmissaoUltimaVenda.setText(itens.get(i).getDthEmissaoUltimaVenda());


        if (viewHolder.dthEmissaoUltimaVenda.getText().subSequence(0, 4).equals("Hoje")) {

            Drawable drawable = viewHolder.imageView.getResources().getDrawable(R.drawable.bol_verde);
            viewHolder.imageView.setImageDrawable(drawable);

        } else if (viewHolder.dthEmissaoUltimaVenda.getText().subSequence(0, 5).equals("Ontem")) {

            Drawable drawable = viewHolder.imageView.getResources().getDrawable(R.drawable.bol_queiroz);
            viewHolder.imageView.setImageDrawable(drawable);

        } else {

            Drawable drawable = viewHolder.imageView.getResources().getDrawable(R.drawable.bol_vermelha);
            viewHolder.imageView.setImageDrawable(drawable);

        }


    }

    @Override
    public int getItemCount() {
        return itens.size();
    }


    public Itens getItem(int position) {
        return itens.get(position);
    }

}
