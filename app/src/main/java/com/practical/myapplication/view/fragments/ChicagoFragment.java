package com.practical.myapplication.view.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.practical.myapplication.R;
import com.practical.myapplication.adapter.AllDataAdapter;
import com.practical.myapplication.common.Commn;
import com.practical.myapplication.databinding.FragmentAllBinding;
import com.practical.myapplication.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ChicagoFragment extends Fragment implements SearchView.OnQueryTextListener {


    List<Employee> employeeList=new ArrayList<>();

    public ChicagoFragment(List<Employee> employeeList) {
        this.employeeList=employeeList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentAllBinding binding;
    AllDataAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_all,container,false);

        setHasOptionsMenu(true);
        if(employeeList!=null)
            setAdapter(employeeList);
        return binding.getRoot();
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

        adapter=new AllDataAdapter(getContext(),employeeList,getResources().getString(R.string.chicago));
        if(binding.rvAll!=null) {
            binding.rvAll.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvAll.setAdapter(adapter);
            binding.rvAll.setHasFixedSize(true);
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