package turmaformacao.br.agenda.controller.adatper;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import turmaformacao.br.agenda.R;
import turmaformacao.br.agenda.model.entities.Contact;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ContactListAdapter extends BaseAdapter implements Filterable{

    Activity context;
    List<Contact> contacts;

    public ContactListAdapter (Activity context, List<Contact>contacts){
        this.context = context;
        this.contacts = contacts;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact =  getItem(position);

        View v = context.getLayoutInflater().inflate(R.layout.contact_list_item,parent,false);

        TextView textViewName = (TextView) v.findViewById(R.id.textViewItem);
        textViewName.setText(contact.getName());


        return textViewName;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            List<String> arrayList = new ArrayList<>();
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                arrayList = (List<String>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<String> FilteredArrList = new ArrayList<String>();

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = contacts.size();
                    results.values = contacts;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < contacts.size(); i++) {
                        String data = contacts.get(i).getName();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}

