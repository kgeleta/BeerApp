package com.vastmoths.beerapp.ui.detailBeer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vastmoths.beerapp.R;
import com.vastmoths.beerapp.database.DatabaseCRUD;
import com.vastmoths.beerapp.database.model.Beer;

import org.w3c.dom.Text;

public class DetailBeerJavaFragment extends Fragment {

//    private TextView beerName;
//    private TextView beerRate;
//    private TextView beerTag;
//    private TextView beerComment;

    private Beer beer;

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public DetailBeerJavaFragment(Beer beer) {
        this.beer = beer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_beer, container, false);
//        this.databaseCRUD = new DatabaseCRUD(rootView.getContext());

        TextView name =(TextView)rootView.findViewById(R.id.beerNameTextView);
        name.setText(beer.getName());
        TextView rate = (TextView)rootView.findViewById(R.id.rateTextView);
        rate.setText(beer.getRate() + "/10");
        TextView tag = (TextView)rootView.findViewById(R.id.tagsTextView);
        tag.setText(beer.getType());
        TextView comment = (TextView)rootView.findViewById(R.id.commentTextView);
        comment.setText(beer.getPicturePath());

        System.out.println(beer.getType());
        System.out.println(beer.getPicturePath());




        return rootView;
    }
}
