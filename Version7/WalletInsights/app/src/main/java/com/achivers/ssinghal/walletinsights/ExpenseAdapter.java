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
import com.achivers.ssinghal.walletinsights.model.ExpenseModel;


import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    Context context;
    List<ExpenseModel> expenseModels;

    public ExpenseAdapter(Context context, List<ExpenseModel> expenseModels) {
        this.context = context;
        this.expenseModels = expenseModels;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modal_expense, viewGroup, false);
        ExpenseAdapter.ExpenseViewHolder expenseViewHolder= new ExpenseAdapter.ExpenseViewHolder(v);
        v.setOnClickListener(expenseViewHolder);
        return expenseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder expenseViewHolder, int i) {
        expenseViewHolder.categoryName.setText(expenseModels.get(i).getCategory());
        expenseViewHolder.amount.setText(expenseModels.get(i).getAmount());

        expenseViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent ud = new Intent(context, AddExpense.class);
                ud.putExtra("key", expenseModels.get(pos).getExpenseid());
                context.startActivity(ud);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseModels.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView categoryName, amount;
        private ItemClickListener itemClickListener;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewExpense);
            categoryName = (TextView) itemView.findViewById(R.id.categoryNameBudget);
            amount = (TextView) itemView.findViewById(R.id.amtount);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

    }
}
