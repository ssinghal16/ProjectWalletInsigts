package com.achivers.ssinghal.walletinsights;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.achivers.ssinghal.walletinsights.model.AddIncomeModel;

import java.util.List;

public class AddIncomeList extends ArrayAdapter<AddIncomeModel> {

    private Activity context;
    List<AddIncomeModel>  incomeModels;

    public AddIncomeList( Activity context, List<AddIncomeModel> incomeModels) {
        super(context,R.layout.layout_addincome_list, incomeModels);
        this.context = context;
        this.incomeModels = incomeModels;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_addincome_list, null, true);

        TextView textViewAmount = (TextView) listViewItem.findViewById(R.id.textViewAmount);
        TextView textViewSource = (TextView) listViewItem.findViewById(R.id.textViewSource);
        TextView textViewLabel= (TextView) listViewItem.findViewById(R.id.textViewLabel);
        TextView textViewDate= (TextView) listViewItem.findViewById(R.id.textViewDate);
        AddIncomeModel addIncomeModel = incomeModels.get(position);
        textViewAmount.setText(addIncomeModel.getAmount());
        textViewSource.setText(addIncomeModel.getSource());
        textViewLabel.setText(addIncomeModel.getLabel());
        textViewDate.setText(addIncomeModel.getDateCreation());


        return listViewItem;
    }
}
