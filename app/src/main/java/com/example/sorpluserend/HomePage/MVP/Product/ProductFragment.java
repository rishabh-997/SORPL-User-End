package com.example.sorpluserend.HomePage.MVP.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import com.example.sorpluserend.HomePage.Model.Comapany_list;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.ProductList;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecList;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_list;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragment extends Fragment implements ProductContract.view,ProductAdapter.onNoteClickListener
{
    ProductContract.presenter presenter;
    ProductAdapter adapter;

    @BindView(R.id.cart_bar)
    ProgressBar progressBar;
    @BindView(R.id.spinner_company)
    Spinner spinner_company;
    @BindView(R.id.spinner_category)
    Spinner spinner_category;
    @BindView(R.id.prodlist_recycler_view)
    RecyclerView recyclerView;

    List<Comapany_list> comapany_list=new ArrayList<>();
    List<SubCat_list> subcat_list=new ArrayList<>();
    List<ProductList> list=new ArrayList<>();
    List<SpecList> specifications=new ArrayList<>();

    String CompanyNameFull="",CompanyNameShort="",SubCategory="";

    public ProductFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        presenter=new ProductPresenter(this);
        ButterKnife.bind(this,view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.getCompany();

        return view;
    }

    @Override
    public void showtaost(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
                    presenter.getList(spinner_category.getSelectedItem().toString(),CompanyNameShort);
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

    @Override
    public void showdata(Product_Response body)
    {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getProductListList();
        adapter=new ProductAdapter(list,getContext(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showSpecs(SpecResponse body) {
        progressBar.setVisibility(View.GONE);
        specifications=body.getSpecList();
        String nospecs="No Specification details";
        String indent= "                 ";
        String message="";
        for(int i=0;i<specifications.size();i++)
        {
            message=message+specifications.get(i).getHeading()+" - "+specifications.get(i).getValue()+"\n";
        }

        if(specifications.size()==0)
            message=nospecs;

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Specifications");
        builder.setMessage(message);
        builder.show();
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
    public void onSpecClick(int position)
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getSpecs(list.get(position).getName());
    }

    @Override
    public void onProductClick(int position) {
        progressBar.setVisibility(View.VISIBLE);
        presenter.addCart(list.get(position).getId(),"9935685103");
    }
}
