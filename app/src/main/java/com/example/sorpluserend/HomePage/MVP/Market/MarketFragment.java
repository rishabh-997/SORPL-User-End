package com.example.sorpluserend.HomePage.MVP.Market;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.Model.CartList;
import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.Comapany_list;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.SubCat_list;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketFragment extends Fragment implements MarketContract.view
{
    MarketContract.presenter presenter;
    MarketAdapter adapter;
    List<CartList> list=new ArrayList<>();

    @BindView(R.id.market_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.market_bar)
    ProgressBar progressBar;
    @BindView(R.id.market_spinner_category)
    Spinner spinner_category;
    @BindView(R.id.market_spinner_company)
    Spinner spinner_company;

    List<Comapany_list> comapany_list=new ArrayList<>();
    List<SubCat_list> subcat_list=new ArrayList<>();
    String CompanyNameFull="",CompanyNameShort="",SubCategory="";


    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_market, container, false);
        presenter=new MarketPresenter(this);
        ButterKnife.bind(this,view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar.setVisibility(View.VISIBLE);


        presenter.getCompany();
        return view;
    }

    @Override
    public void toast(String message)
    {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(CartResponse body) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getList();
        adapter=new MarketAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showCompanies(Comapny_response body) {
        comapany_list=body.getComapany_list();

        String[] companylistfinal=new String[comapany_list.size()];
        for(int i=0;i<comapany_list.size();i++){
            companylistfinal[i]=comapany_list.get(i).getComapanyfull();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, companylistfinal);
        spinner_company.setAdapter(adapter);
        spinner_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                progressBar.setVisibility(View.VISIBLE);
                CompanyNameFull=spinner_company.getSelectedItem().toString();
                setCategory(spinner_company.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCategory(String Company)
    {
        for(int i=0;i<comapany_list.size();i++)
        {
            if(Company.equals(comapany_list.get(i).getComapanyfull())) {
                CompanyNameShort = comapany_list.get(i).getCompanyshort();
                break;
            }
        }
        presenter.getSubCategory(CompanyNameShort);
    }

    @Override
    public void showSubCategories(SubCat_response body) {
        subcat_list.clear();
        subcat_list=body.getSubcat_list();

        if(subcat_list.size()!=0) {
            String[] categoryListfinla = new String[subcat_list.size()];
            for (int i = 0; i < subcat_list.size(); i++) {
                categoryListfinla[i] = subcat_list.get(i).getSubcategory();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categoryListfinla);
            spinner_category.setAdapter(adapter);
            spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    progressBar.setVisibility(View.VISIBLE);
                    SubCategory=spinner_category.getSelectedItem().toString();
                    presenter.getMarket("Client",CompanyNameShort,spinner_category.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else
        {
            String[] cat=new String[1];
            cat[0]="No Categories Found";
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cat);
            spinner_category.setAdapter(adapter);
        }
    }
}
