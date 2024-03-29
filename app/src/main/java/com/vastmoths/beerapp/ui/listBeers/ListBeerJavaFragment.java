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
import com.vastmoths.beerapp.ui.detailBeer.DetailBeerJavaFragment;

import java.util.ArrayList;
import java.util.List;

public class ListBeerJavaFragment extends Fragment implements AdapterView.OnItemClickListener {

    private DatabaseCRUD databaseCRUD;

    private List<Beer> beers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_beer, container, false);
        this.databaseCRUD  = new DatabaseCRUD(rootView.getContext());

//        databaseCRUD.insertBeer(new Beer(0, "tyskie", 2, "jasne", "pic"));

        try
        {
            final ListView listView = (ListView)rootView.findViewById(R.id.beerList);
            listView.setOnItemClickListener(this);
            beers = databaseCRUD.getGetAllBeers();

            ArrayList beerNames = new ArrayList<String>();
            for (Beer beer :
                    beers) {
                System.out.println(beer.getType());
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        System.out.println("you clicked " + listView.getItemAtPosition(position));

        DetailBeerJavaFragment nextFrag= new DetailBeerJavaFragment(beers.get(position));
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(this.getId(), nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}