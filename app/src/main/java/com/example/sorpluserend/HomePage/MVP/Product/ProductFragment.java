package com.example.sorpluserend.HomePage.MVP.Product;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.MVP.Cart.CartFragment;
import com.example.sorpluserend.HomePage.Model.Comapany_list;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.ProductList;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecList;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_list;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.HomePage.Model.UnitList;
import com.example.sorpluserend.HomePage.Model.UnitResponse;
import com.example.sorpluserend.R;
import com.example.sorpluserend.Utilities.SharedPref;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragment extends Fragment implements ProductContract.view,ProductAdapter.onNoteClickListener
{
    ProductFragmentListener listener;
    ProductContract.presenter presenter;
    ProductAdapter adapter;
    SharedPref sharedPref;

    @BindView(R.id.cart_bar)
    ProgressBar progressBar;
    @BindView(R.id.spinner_company)
    Spinner spinner_company;
    @BindView(R.id.spinner_category)
    Spinner spinner_category;
    @BindView(R.id.prodlist_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.cart_name)
    TextView cart_name;
    @BindView(R.id.cart_id)
    TextView cart_id;
    @BindView(R.id.cart_image)
    ImageView cart_image;
    @BindView(R.id.cart_cross)
    ImageView cart_cross;
    @BindView(R.id.cart_description)
    TextView cart_desciption;
    @BindView(R.id.cart_price)
    EditText cart_price;
    @BindView(R.id.cart_size)
    Spinner cart_size;
    @BindView(R.id.cart_increase_quantity)
    TextView cart_increase;
    @BindView(R.id.cart_decrease_quantity)
    TextView cart_decrease;
    @BindView(R.id.cart_item_quantity)
    EditText cart_quantity;
    @BindView(R.id.cart_submit)
    Button cart_submit;
    @BindView(R.id.bottom_sheet_add_to_cart)
    RelativeLayout bottom_sheet;
    BottomSheetBehavior sheetBehavior;

    List<UnitList> unitList=new ArrayList<>();
    List<Comapany_list> comapany_list=new ArrayList<>();
    List<SubCat_list> subcat_list=new ArrayList<>();
    List<ProductList> list=new ArrayList<>();
    List<SpecList> specifications=new ArrayList<>();

    String CompanyNameFull="",CompanyNameShort="",SubCategory="";
    boolean isSheetClosed = true;
    String cost,size, unit;

    public ProductFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        presenter=new ProductPresenter(this);
        ButterKnife.bind(this,view);
        sharedPref=new SharedPref(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        presenter.getUnit();
        presenter.getCompany();
        sheetBehavior=BottomSheetBehavior.from(bottom_sheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        cart_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSheetClosed=true;
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        return view;
    }

    @Override
    public void showtaost(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setList(UnitResponse body) {
        unitList=body.getPUnitList();
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                message=message + Html.fromHtml("<b>"+specifications.get(i).getHeading()+"</b>",Html.FROM_HTML_MODE_LEGACY);
            }
            else{
                message=message + Html.fromHtml("<b>"+specifications.get(i).getHeading()+"</b>");
            }

            message=message+"\n"+specifications.get(i).getValue()+"\n\n";
        }

        if(specifications.size()==0)
            message=nospecs;

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Specifications");
        builder.setMessage(message.trim());
        builder.show();
    }



    @Override
    public void updateMyCart(String successfully_added_to_cart)
    {
        progressBar.setVisibility(View.GONE);
        cart_quantity.setText("");
        Toast.makeText(getContext(), "Added Successfully to Cart", Toast.LENGTH_SHORT).show();
        listener.UPdateCart();
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
    public void onProductClick(final int position)
    {
        String[] unitlistfinal=new String[unitList.size()];
        for(int i=0;i<unitList.size();i++)
            unitlistfinal[i]=unitList.get(i).getUnit();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, unitlistfinal);
        cart_size.setAdapter(adapter);
        cart_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ProductList productList=list.get(position);
        cart_name.setText(productList.getName());
        cart_id.setText(productList.getId());
        cart_desciption.setText(productList.getDescription());
        Picasso.get().load(productList.getImage_url()).into(cart_image);

        isSheetClosed = false;
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        unit =cart_quantity.getText().toString();

        cart_decrease.setVisibility(View.GONE);
        cart_increase.setVisibility(View.GONE);

        cart_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)+1);
                cart_quantity.setText(unit);
            }
        });
        cart_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)-1);
                cart_quantity.setText(unit);
            }
        });

        cart_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(cart_quantity.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please Enter The Quantity", Toast.LENGTH_SHORT).show();

                else {
                    addinCart(position);
                    if(!isSheetClosed)
                    {
                        isSheetClosed=true;
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }
        });
        cart_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSheetClosed)
                {
                    isSheetClosed=true;
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    private void addinCart(int position)
    {
        size=cart_size.getSelectedItem().toString();
        unit=cart_quantity.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        presenter.addCart(list.get(position).getId(),sharedPref.getMobile(),size,unit);
    }

    /**
     * below are functions to connect to CartFragment so we can refresh cart
     */

    public interface ProductFragmentListener
    {
        void UPdateCart();
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof CartFragment.CartFragmentListener)
        {
            listener=(ProductFragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()+"must be implemented");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}
