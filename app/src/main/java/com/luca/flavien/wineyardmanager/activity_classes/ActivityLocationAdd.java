package com.luca.flavien.wineyardmanager.activity_classes;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.luca.flavien.wineyardmanager.db.object.Orientation;
import com.luca.flavien.wineyardmanager.db.object.WineLot;
import com.luca.flavien.wineyardmanager.db.object.WineVariety;
import com.luca.flavien.wineyardmanager.MainActivity;
import com.luca.flavien.wineyardmanager.R;

import java.util.List;

public class ActivityLocationAdd extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextNumber;
    private EditText editTextSurface;

    private Spinner spinnerOrientation;
    private Spinner spinnerVariety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);

        initObjects();
        updateSpinner();


        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab_confirm_location);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEntries()){
                    Orientation orientation = (Orientation) spinnerOrientation.getSelectedItem();
                    WineVariety wineVariety = (WineVariety) spinnerVariety.getSelectedItem();

                    WineLot wineLot = new WineLot();
                    wineLot.setWineVariety(wineVariety);
                    wineLot.setName(editTextName.getText().toString());
                    wineLot.setNumberWineStock(Integer.parseInt(editTextNumber.getText().toString()));
                    wineLot.setSurface(Float.parseFloat(editTextSurface.getText().toString()));
                    wineLot.setOrientationid(orientation.getId());

                    MainActivity.wineLotDataSource.createWineLot(wineLot);
                    finish();
                }
            }
        });

    }

    private boolean checkEntries(){
        if (editTextName.getText().toString().matches("")){
            Toast.makeText(this, R.string.problem_add_location_name, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTextNumber.getText().toString().matches("")){
            Toast.makeText(this, R.string.problem_add_location_number , Toast.LENGTH_LONG).show();
            return false;
        }
        if (editTextSurface.getText().toString().matches("")){
            Toast.makeText(this, R.string.problem_add_location_surface , Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initObjects(){
        spinnerOrientation = (Spinner) findViewById(R.id.spinner_orientation);
        spinnerVariety = (Spinner) findViewById(R.id.spinner_variety);

        editTextName = (EditText)findViewById(R.id.edit_vineyard_name);
        editTextNumber = (EditText)findViewById(R.id.edit_number_vine_stock);
        editTextSurface = (EditText)findViewById(R.id.edit_size);
    }

    private void updateSpinner(){
        ArrayAdapter<Orientation> adapterOrientation = new ArrayAdapter<>
                (this, R.layout.row_simple, MainActivity.orientationList);
        spinnerOrientation.setAdapter(adapterOrientation);

        List<WineVariety> varietyList = MainActivity.wineVarietyDataSource.getAllWineVarieties();
        ArrayAdapter<WineVariety> adapterVariety = new ArrayAdapter<>
                (this, R.layout.row_simple, varietyList);
        spinnerVariety.setAdapter(adapterVariety);
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