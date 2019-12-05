package com.vastmoths.beerapp.ui.createBeer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.vastmoths.beerapp.R;
import com.vastmoths.beerapp.database.DatabaseCRUD;
import com.vastmoths.beerapp.database.model.Beer;
import com.vastmoths.beerapp.ui.listBeers.ListBeerJavaFragment;

public class CreateBeerJavaFragment extends Fragment implements View.OnClickListener {

    private EditText beerName;
    private EditText beerComment;
    private EditText beerRate;
    private Spinner beerType;

    private DatabaseCRUD databaseCRUD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_create_beer, container, false);

        this.databaseCRUD  = new DatabaseCRUD(rootView.getContext());


        beerName = (EditText)rootView.findViewById(R.id.beerName);
        beerComment = (EditText)rootView.findViewById(R.id.comment);
        beerRate = (EditText)rootView.findViewById(R.id.rate);
        beerType = (Spinner)rootView.findViewById(R.id.spinner);

        final Button saveButton = (Button)rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (!isValidString(beerName.getText().toString())) {
            this.beerName.setError("Name can't be empty");
            return;
        }
        if (!isValidString(beerComment.getText().toString())) {
            this.beerComment.setError("Comment can't be empty");
            return;
        }
        if (beerRate.getText().toString().isEmpty() || !isValidRate(Integer.valueOf(beerRate.getText().toString()))) {
            this.beerRate.setError("Rate should be in rage 0 to 10");
            return;
        }

        System.out.println(beerName.getText().toString());
        System.out.println(beerComment.getText().toString());
        System.out.println(beerRate.getText().toString());
        System.out.println(beerType.getSelectedItem().toString());

        Beer beer = new Beer(0, beerName.getText().toString(), Integer.valueOf(beerRate.getText().toString()), "type", beerComment.getText().toString());
        this.databaseCRUD.insertBeer(beer);

        ListBeerJavaFragment nextFrag= new ListBeerJavaFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(this.getId(), nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }

    private boolean isValidRate(int rate) {
        return rate >= 0.0 && rate <= 10.0;
    }

    private boolean isValidString(String string) {
        return string.length() > 0;
    }
}
