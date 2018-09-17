package com.achivers.ssinghal.walletinsights;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achivers.ssinghal.walletinsights.model.IncomeModel;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
List<IncomeModel> incomeModels;
    public static class IncomeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView sourceText, amtText;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.cardViewIncome);
            sourceText= (TextView) itemView.findViewById(R.id.sourceText);
            amtText=(TextView) itemView.findViewById(R.id.amt);
        }
    }

    public IncomeAdapter(IncomeActivity incomeActivity, List<IncomeModel> incomeModels) {
        this.incomeModels=incomeModels;
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modal_income, viewGroup, false);
        IncomeViewHolder ivh = new IncomeViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder incomeViewHolder, int i) {

        incomeViewHolder.sourceText.setText(incomeModels.get(i).getSource());
        incomeViewHolder.amtText.setText(incomeModels.get(i).getAmount());

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
