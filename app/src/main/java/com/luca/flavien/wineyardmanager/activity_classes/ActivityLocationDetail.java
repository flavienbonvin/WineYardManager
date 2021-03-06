package com.luca.flavien.wineyardmanager.activity_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.luca.flavien.wineyardmanager.db.object.WineLot;
import com.luca.flavien.wineyardmanager.MainActivity;
import com.luca.flavien.wineyardmanager.R;

/**
 * Created by Flavien and Luca on 24.04.2017.
 *
 * Project : WineYardManager
 * Package: activity_classes
 *
 * Description: Allow the creation and edit of the location
 */

public class ActivityLocationDetail extends AppCompatActivity {

    private WineLot wineLot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        wineLot = (WineLot) getIntent().getSerializableExtra("winelot");

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityLocationAdd.class);
                intent.putExtra("vine_lot", wineLot);

                Log.d("EDIT VINEYARD: ", wineLot.getId() +
                        " name: " + wineLot.getName() +
                        " variety: " + wineLot.getWineVariety().getName() +
                        " varietyID: " + wineLot.getWineVariety().getId());

                startActivity(intent);
                finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTextView(wineLot);
    }

    private void setTextView(WineLot wineLot){
        TextView textViewWineVariety = (TextView)findViewById(R.id.winevariety);
        TextView textViewnumberWineStock = (TextView)findViewById(R.id.number_of_wine_stock);
        TextView textViewSurface = (TextView)findViewById(R.id.surface);
        TextView textViewOrientation = (TextView)findViewById(R.id.orientation);

        setTitle(wineLot.getName());

        textViewWineVariety.setText(wineLot.getWineVariety().getName());
        textViewnumberWineStock.setText(Integer.toString(wineLot.getNumberWineStock()) + " " + getString(R.string.vines));
        textViewSurface.setText(Float.toString(wineLot.getSurface()) + " " + getString(R.string.square_meters));
        textViewOrientation.setText(MainActivity.orientationList.get(wineLot.getOrientationid()).toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.onBackPressed();
            return true;
        }
        return true;
    }
}