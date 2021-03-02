package com.britishbroadcast.gyminventory.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.britishbroadcast.gyminventory.R;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;
import com.britishbroadcast.gyminventory.presenter.GymEquipmentContract;
import com.britishbroadcast.gyminventory.presenter.GymEquipmentPresenter;
import com.britishbroadcast.gyminventory.view.adapter.GymEquipmentItemAdapter;
import com.britishbroadcast.gyminventory.view.adapter.GymEquipmentStockAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.britishbroadcast.gyminventory.util.EquipmentType.DUMBELL;
import static com.britishbroadcast.gyminventory.util.EquipmentType.EXERCISEBIKE;
import static com.britishbroadcast.gyminventory.util.EquipmentType.TREADMILL;

public class MainActivity extends AppCompatActivity implements GymEquipmentContract.GymEquipmentView, GymEquipmentItemAdapter.GymEquipmentItemDelegate {


    @BindView(R.id.available_equipment_recyclerview)
    RecyclerView availableEquipmentRecyclerView;

    @BindView(R.id.stock_equipment_recyclerview)
    RecyclerView stockEquipmentRecyclerView;

    SharedPreferences sharedPreferences;

    private GymEquipmentContract.GymEquipmentPresenter gymEquipmentPresenter;
    private GymEquipmentItemAdapter gymEquipmentItemAdapter;
    private GymEquipmentStockAdapter gymEquipmentStockAdapter;
    private AddEquipmentToStockFragment addEquipmentToStockFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);



        gymEquipmentPresenter = new GymEquipmentPresenter(this);
        addEquipmentToStockFragment = new AddEquipmentToStockFragment(gymEquipmentPresenter);

        gymEquipmentItemAdapter = new GymEquipmentItemAdapter(new ArrayList<>(), this);
        gymEquipmentStockAdapter = new GymEquipmentStockAdapter(new ArrayList<>());
        checkFirstAppLaunch();



    }

    private void checkFirstAppLaunch() {

        if(sharedPreferences.getBoolean("FIRST_TIME", true)){
                    gymEquipmentPresenter.insertGymEquipmentItem(new GymEquipmentItem(TREADMILL,"Hyper 2021"));
                    gymEquipmentPresenter.insertGymEquipmentItem(new GymEquipmentItem(DUMBELL,"10Kg"));
                    gymEquipmentPresenter.insertGymEquipmentItem(new GymEquipmentItem(EXERCISEBIKE,"Ninbo 99k"));
                    sharedPreferences.edit().putBoolean("FIRST_TIME", false).apply();
        }else{
            Toast.makeText(getContext(),"Welcome back", Toast.LENGTH_SHORT).show();
            gymEquipmentPresenter.getGymEquipmentItems();
            gymEquipmentPresenter.getGymEquipmentsStock();
        }
    }

    @Override
    public void displayEquipmentItems(List<GymEquipmentItem> gymEquipmentItemList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    gymEquipmentItemAdapter.updateData(gymEquipmentItemList);
                    availableEquipmentRecyclerView.setLayoutManager(linearLayoutManager);
                    availableEquipmentRecyclerView.setAdapter(gymEquipmentItemAdapter);
                }
            });
    }

    @Override
    public void displayEquipmentStock(List<GymEquipmentStock> gymEquipmentStockList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    gymEquipmentStockAdapter.updateData(gymEquipmentStockList);
                    stockEquipmentRecyclerView.setLayoutManager(linearLayoutManager);
                    stockEquipmentRecyclerView.setAdapter(gymEquipmentStockAdapter);
                }
            });
    }

    @Override
    public void displayError(String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), errorMessage,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void displaySuccess(String successMessage) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), successMessage,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void selectGymEquipmentItem(GymEquipmentItem gymEquipmentItem) {
        Log.d("TAG_X", "selectGymEquipmentItem: "+ gymEquipmentItem.getType().toString());


        Bundle bundle = new Bundle();
        bundle.putSerializable("EQUIPMENT_KEY", gymEquipmentItem);
        addEquipmentToStockFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                )
                .add(R.id.main_frame, addEquipmentToStockFragment)
                .addToBackStack(addEquipmentToStockFragment.getTag())
                .commit();
    }
}