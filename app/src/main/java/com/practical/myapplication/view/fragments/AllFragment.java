package com.practical.myapplication.view.fragments;

import android.app.Service;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.practical.myapplication.R;
import com.practical.myapplication.adapter.AllDataAdapter;
import com.practical.myapplication.common.Commn;
import com.practical.myapplication.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment implements SearchView.OnQueryTextListener {

    List<Employee> employeeList=new ArrayList<>();

    public AllFragment(List<Employee> employeeList) {
        // Required empty public constructor
        this.employeeList=employeeList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView recyclerView;
    AllDataAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_all, container, false);

        setHasOptionsMenu(true);
        recyclerView=v.findViewById(R.id.rvAll);

        if(employeeList!=null)
        setAdapter(employeeList);
        return v;
    }
    SearchView searchView ;
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.app_bar_search);
         searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onPrepareOptionsMenu(menu);
    }


    public void setAdapter(List<Employee> employeeList) {
       adapter=new AllDataAdapter(getContext(),employeeList,"");
        if(recyclerView!=null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        Boolean search=false;
        for(int i=0;i<employeeList.size();i++){
            if(employeeList.get(i).getCity().equalsIgnoreCase(query)||employeeList.get(i).getFirstName().equalsIgnoreCase(query)||employeeList.get(i).getLastName().equalsIgnoreCase(query)){
                search=true;
            }
        }

        if(search)
        {
            adapter.getFilter().filter(query);
            
        }
        else {
            Toast.makeText(getContext(), "No Match found", Toast.LENGTH_LONG).show();
            adapter.setEmployeelist(employeeList);
        }

        Commn.hideKeyboard(getContext(),searchView);
        return true;
    }



    @Override
    public boolean onQueryTextChange(String query) {

        return false;
    }
}