package com.achivers.ssinghal.walletinsights;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achivers.ssinghal.walletinsights.intefacesclasses.ItemClickListener;
import com.achivers.ssinghal.walletinsights.model.Category;
import java.util.ArrayList;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.MyViewHolder> {
    Context context;
    ArrayList<Category> categories;

    public BudgetAdapter(Context c, ArrayList<Category> cat){
        context=c;
        categories=cat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(context).inflate(R.layout.modal_create_budget,viewGroup,false);

        MyViewHolder holder= new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {


        myViewHolder.catName.setText(categories.get(i).getCategoryName());
        myViewHolder.perName.setText(categories.get(i).getPercenatge());

        myViewHolder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) {

                Intent ud= new Intent(context, UpdateDeleteBudget.class);

                ud.putExtra("categoryName",categories.get(pos).getCategoryName());
                ud.putExtra("percentage",categories.get(pos).getPercenatge());
                ud.putExtra("key",categories.get(pos).getCategoryId());

                context.startActivity(ud);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    //MyViewHolder Class

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView catName, perName;
        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catName= (TextView) itemView.findViewById(R.id.catName);
            perName= (TextView) itemView.findViewById(R.id.perText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}
