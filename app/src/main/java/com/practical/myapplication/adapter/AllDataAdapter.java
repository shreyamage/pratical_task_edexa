package com.practical.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.practical.myapplication.R;
import com.practical.myapplication.model.Employee;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AllDataAdapter extends RecyclerView.Adapter<AllDataAdapter.MyHolder> implements Filterable {


    Context context;
    List<Employee> employeeList;
    List<Employee> empoyeeFilterlist;
    String city;

    public AllDataAdapter(Context context, List<Employee> employeeList,String city) {
        this.context = context;
        this.employeeList = employeeList;
        this.empoyeeFilterlist = employeeList;
        this.city=city;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.raw_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        if(city.equalsIgnoreCase(context.getString(R.string.chicago)))
        {
            holder.tvName.setText(empoyeeFilterlist.get(position).getFirstName() + "  " + empoyeeFilterlist.get(position).getLastName());
            holder.txtCity.setVisibility(View.GONE);
            holder.tvName.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        }else if(city.equalsIgnoreCase(context.getString(R.string.newyork)))
        {
            holder.tvName.setText(empoyeeFilterlist.get(position).getFirstName() + "  " + empoyeeFilterlist.get(position).getLastName());
            holder.txtCity.setVisibility(View.GONE);
            holder.tvName.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        }else if(city.equalsIgnoreCase(context.getString(R.string.losangelos)))
        {
            holder.tvName.setText(empoyeeFilterlist.get(position).getFirstName() + "  " + empoyeeFilterlist.get(position).getLastName());
            holder.txtCity.setVisibility(View.GONE);
            holder.tvName.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        }
        else {
            holder.txtCity.setVisibility(View.VISIBLE);
            holder.txtCity.setText(empoyeeFilterlist.get(position).getCity());
            holder.tvName.setText(empoyeeFilterlist.get(position).getFirstName() + "  " + empoyeeFilterlist.get(position).getLastName());
        }

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // show popup

                BottomSheetDialog dialog=new BottomSheetDialog(context);

                dialog.setContentView(R.layout.item);
                TextView textView=dialog.findViewById(R.id.tvFLName);
                textView.setText(empoyeeFilterlist.get(position).getFirstName()+ "  "+"\n    "+empoyeeFilterlist.get(position).getLastName());
                dialog.setCancelable(true);
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return empoyeeFilterlist.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    empoyeeFilterlist = employeeList;
                } else {
                    List<Employee> filteredList = new ArrayList<>();
                    for (Employee row : employeeList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCity().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getFirstName().toLowerCase().contains(charString.toLowerCase())||
                                row.getLastName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);

                        }
                    }

                    empoyeeFilterlist = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = empoyeeFilterlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                empoyeeFilterlist = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setEmployeelist(List<Employee> employeeList) {
        empoyeeFilterlist=employeeList;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView txtCity;
        TextView tvName;
        CardView cardview;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtCity=itemView.findViewById(R.id.txtCity);
            tvName=itemView.findViewById(R.id.tvName);
            cardview=itemView.findViewById(R.id.cardview);

        }
    }
}
