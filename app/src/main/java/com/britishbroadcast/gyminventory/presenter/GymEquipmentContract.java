package com.britishbroadcast.gyminventory.presenter;

import android.content.Context;

import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;

import java.util.List;

public interface GymEquipmentContract {

    interface GymEquipmentPresenter{
        void insertGymEquipmentItem(GymEquipmentItem gymEquipmentItem);
        void insertGymEquipmentStock(GymEquipmentStock gymEquipmentStock);
        void getGymEquipmentItems();
        void getGymEquipmentsStock();
        void deleteAllGymEquipments();
    }

    interface GymEquipmentView{
        void displayEquipmentItems(List<GymEquipmentItem> gymEquipmentItemList);
        void displayEquipmentStock(List<GymEquipmentStock> gymEquipmentStockList);
        void displayError(String errorMessage);
        void displaySuccess(String successMessage);
        Context getContext();
    }


}
