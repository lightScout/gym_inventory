package com.britishbroadcast.gyminventory.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.britishbroadcast.gyminventory.R;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;
import com.britishbroadcast.gyminventory.presenter.GymEquipmentContract;
import com.britishbroadcast.gyminventory.presenter.GymEquipmentPresenter;

import java.util.List;


public class AddEquipmentToStockFragment extends Fragment {


    TextView titleTextView;
    EditText quantityEditText, descriptionEditText;
    Button addButton, cancelButton;
    GymEquipmentContract.GymEquipmentPresenter gymEquipmentPresenter;

    public AddEquipmentToStockFragment(GymEquipmentContract.GymEquipmentPresenter gymEquipmentPresenter) {
        this.gymEquipmentPresenter = gymEquipmentPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_stock_fragment_layout, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quantityEditText = view.findViewById(R.id.quantity_edittext);
        descriptionEditText = view.findViewById(R.id.description_edittext);
        addButton = view.findViewById(R.id.add_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        titleTextView = view.findViewById(R.id.title_textview);



        Bundle bundle = getArguments();
        if(bundle != null){
            GymEquipmentItem gymEquipmentItem = (GymEquipmentItem) bundle.getSerializable("EQUIPMENT_KEY");
            titleTextView.setText(getString(R.string.title_text, "Add "+ gymEquipmentItem.getType().toString()));
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addItemToStock(gymEquipmentItem);
                }
            });


        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });


    }

    private void addItemToStock(GymEquipmentItem gymEquipmentItem) {

        if(quantityEditText.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Quantity input missing", Toast.LENGTH_SHORT).show();
        }else{
            if(!descriptionEditText.getText().toString().isEmpty()){
                GymEquipmentStock gymEquipmentStock = new GymEquipmentStock(gymEquipmentItem.getType(), descriptionEditText.getText().toString().trim(), Integer.parseInt(quantityEditText.getText().toString().trim()));
                descriptionEditText.setText("");
                quantityEditText.setText("");
                gymEquipmentPresenter.insertGymEquipmentStock(gymEquipmentStock);
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }else{
                Toast.makeText(getActivity(), "Description input missing", Toast.LENGTH_SHORT).show();
            }
        }






    }


}
