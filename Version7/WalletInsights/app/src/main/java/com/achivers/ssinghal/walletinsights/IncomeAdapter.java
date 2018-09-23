package com.achivers.ssinghal.walletinsights;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achivers.ssinghal.walletinsights.intefacesclasses.ItemClickListener;
import com.achivers.ssinghal.walletinsights.model.IncomeModel;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
    Context context;
    List<IncomeModel> incomeModels;


    public class IncomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView sourceText, amtText;
        private ItemClickListener itemClickListener;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewIncome);
            sourceText = (TextView) itemView.findViewById(R.id.sourceText);
            amtText = (TextView) itemView.findViewById(R.id.amt);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public IncomeAdapter(Context c, List<IncomeModel> incomeModels) {
        this.context = c;
        this.incomeModels = incomeModels;
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modal_income, viewGroup, false);
        IncomeViewHolder ivh = new IncomeViewHolder(v);
        v.setOnClickListener(ivh);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder incomeViewHolder, int i) {

        incomeViewHolder.sourceText.setText(incomeModels.get(i).getSource());
        incomeViewHolder.amtText.setText(incomeModels.get(i).getAmount());

        incomeViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent ud = new Intent(context, AddIncome.class);
                ud.putExtra("key", incomeModels.get(pos).getId());
                context.startActivity(ud);
            }
        });


    }

    @Override
    public int getItemCount() {
        return incomeModels.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
