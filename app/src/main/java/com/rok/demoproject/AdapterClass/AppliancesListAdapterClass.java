package com.rok.demoproject.AdapterClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rok.demoproject.PojoClass.AppliancesListPojoCalss;
import com.rok.demoproject.R;

import java.util.List;

public class AppliancesListAdapterClass extends BaseAdapter {

    private List<AppliancesListPojoCalss> appliancesListPojoCalssses;
    private Activity activity;

    public AppliancesListAdapterClass(List<AppliancesListPojoCalss> appliancesListPojoCalssses, Activity activity) {
        this.appliancesListPojoCalssses = appliancesListPojoCalssses;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return appliancesListPojoCalssses != null ? appliancesListPojoCalssses.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return appliancesListPojoCalssses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.appliances_list, parent, false);

            holder.tvAppliancesName = view.findViewById(R.id.tvAppliancesName);
            holder.tvApplinaceModel = view.findViewById(R.id.tvAppliancesModel);
            holder.tvApplinaceNextService = view.findViewById(R.id.tvAppliancesNextServiceDate);
            holder.btnAppliancsDetails = view.findViewById(R.id.btnAppliancesDetails);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final AppliancesListPojoCalss obj = appliancesListPojoCalssses.get(position);

        if (obj != null) {
            if (holder.tvAppliancesName != null) {
                holder.tvAppliancesName.setText(obj.getName());
            }
            if (holder.tvApplinaceModel != null) {
                holder.tvApplinaceModel.setText(obj.getModel());
            }
            if (holder.tvApplinaceNextService != null) {
                holder.tvApplinaceNextService.setText("Service: " + obj.getNextServiceDate());
            }
        }

        return view;
    }

    static class ViewHolder {
        TextView tvAppliancesName, tvApplinaceModel, tvApplinaceNextService;
        Button btnAppliancsDetails;
    }
}