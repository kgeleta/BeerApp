package com.vastmoths.beerapp.ui.listBeers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.vastmoths.beerapp.R;
import com.vastmoths.beerapp.database.DatabaseCRUD;
import com.vastmoths.beerapp.database.model.Beer;

import java.util.ArrayList;

public class ListBeerJavaFragment extends Fragment {

    private DatabaseCRUD databaseCRUD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_beer, container, false);
        this.databaseCRUD  = new DatabaseCRUD(rootView.getContext());

//        databaseCRUD.insertBeer(new Beer(0, "tyskie", 2, "jasne", "pic"));

        try
        {
            final ListView listView = (ListView)rootView.findViewById(R.id.beerList);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // this is called after user click on item in list
                    System.out.println("you clicked " + listView.getItemAtPosition(position));
                }
            });

            ArrayList beerNames = new ArrayList<String>();
            for (Beer beer :
                    databaseCRUD.getGetAllBeers()) {
                beerNames.add(beer.getName());
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, beerNames);
            listView.setAdapter(arrayAdapter);

        } catch(Exception e)
        {
            System.out.println("jeblo");
            System.out.println(e.getMessage());
        }

        return rootView;
    }

}
