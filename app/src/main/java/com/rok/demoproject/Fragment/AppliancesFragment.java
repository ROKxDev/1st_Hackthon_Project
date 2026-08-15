package com.rok.demoproject.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rok.demoproject.AdapterClass.AppliancesListAdapterClass;
import com.rok.demoproject.PojoClass.AppliancesListPojoCalss;
import com.rok.demoproject.R;
import com.rok.demoproject.Urls.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AppliancesFragment extends Fragment {
    TextView tvAppliancesNotFound;
    ListView lvAppliancesList;
    List<AppliancesListPojoCalss> appliancesListPojoCalsses;
    AppliancesListAdapterClass appliancesListAdapterClass;
    SearchView svAppliances;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appliances, container, false);

        tvAppliancesNotFound = view.findViewById(R.id.tvSearchAppliancesNotFound);
        lvAppliancesList = view.findViewById(R.id.lvAppliancesList);
        svAppliances = view.findViewById(R.id.svAppliancesSrarch);
        appliancesListPojoCalsses = new ArrayList<>();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("All Appliances List");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        getAllDonorsList();


        svAppliances.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                searchDonors(query);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchDonors(query);
                return false;
            }
        });

        return view;

    }

    private void searchDonors(String query) {

        List<AppliancesListPojoCalss> tempSearchDnors = new ArrayList<>();
        tempSearchDnors.clear();

        for (AppliancesListPojoCalss obj:appliancesListPojoCalsses)
        {
            if (obj.getName().toUpperCase().contains(query.toUpperCase()))
            {
                tempSearchDnors.add(obj);
            }
            appliancesListAdapterClass = new AppliancesListAdapterClass(tempSearchDnors,getActivity());
            lvAppliancesList.setAdapter(appliancesListAdapterClass);
        }
    }

    private void getAllDonorsList() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Url.URL_ALL_APPIANCES,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("getAllApplince");

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String applianceName = jsonObject.getString("username");
                        String model = jsonObject.getString("bloodgroup");
                        String nextServiceDate = jsonObject.getString("");


                        appliancesListPojoCalsses.add(new AppliancesListPojoCalss(id,applianceName,model,nextServiceDate));
                    }

                    appliancesListAdapterClass = new AppliancesListAdapterClass(appliancesListPojoCalsses,getActivity());
                    lvAppliancesList.setAdapter(appliancesListAdapterClass);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
