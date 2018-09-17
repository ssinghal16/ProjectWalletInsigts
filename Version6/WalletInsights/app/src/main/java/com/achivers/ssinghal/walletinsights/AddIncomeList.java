package com.achivers.ssinghal.walletinsights;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.achivers.ssinghal.walletinsights.model.IncomeModel;

import java.util.List;

public class AddIncomeList extends ArrayAdapter<IncomeModel> {

    private Activity context;
    List<IncomeModel>  incomeModels;

    public AddIncomeList( Activity context, List<IncomeModel> incomeModels) {
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
        IncomeModel addIncomeModel = incomeModels.get(position);
        textViewAmount.setText(addIncomeModel.getAmount());
        textViewSource.setText(addIncomeModel.getSource());
        textViewLabel.setText(addIncomeModel.getLabel());
        textViewDate.setText(addIncomeModel.getDateOfCreation());


        return listViewItem;
    }
}
