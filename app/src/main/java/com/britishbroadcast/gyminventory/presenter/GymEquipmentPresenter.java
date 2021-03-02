package com.britishbroadcast.gyminventory.presenter;

import androidx.room.Room;

import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;
import com.britishbroadcast.gyminventory.model.db.DataBase;

import java.util.List;

import static com.britishbroadcast.gyminventory.model.db.DataBase.DATABASE_NAME;

public class GymEquipmentPresenter implements GymEquipmentContract.GymEquipmentPresenter{

    private GymEquipmentContract.GymEquipmentView view;
    private DataBase dataBase;


    public GymEquipmentPresenter(GymEquipmentContract.GymEquipmentView view) {
        this.view = view;
        // Initializing Data Base
        dataBase = Room.databaseBuilder(
               view.getContext(),
               DataBase.class,
                DATABASE_NAME
        ).build();

    }

    @Override
    public void insertGymEquipmentItem(GymEquipmentItem gymEquipmentItem) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    dataBase.gymEquipmentDAO().addGymEquipmentItem(gymEquipmentItem);
                    view.displaySuccess("Equipment " + gymEquipmentItem.getType() +
                            " created successfully." );
                    getGymEquipmentItems();
                } catch (Exception e) {
                    view.displayError("Failed to create gym equipment " + gymEquipmentItem.getType() + ".");
                }
            }
        }.start();




    }

    @Override
    public void insertGymEquipmentStock(GymEquipmentStock gymEquipmentStock) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    dataBase.gymEquipmentDAO().addGymEquipmentStock(gymEquipmentStock);
                    view.displaySuccess("Equipment " + gymEquipmentStock.getType().toString() +
                            " added to stock successfully." );
                    getGymEquipmentsStock();
                } catch (Exception e) {
                    view.displayError("Failed to add gym equipment " + gymEquipmentStock.getType().toString() + " to stock.");
                }
            }
        }.start();


    }

    @Override
    public void getGymEquipmentItems() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    List<GymEquipmentItem> gymEquipmentItemList = dataBase.gymEquipmentDAO().getGymEquipmentItems();
                    if(gymEquipmentItemList.isEmpty())
                        view.displayError("No equipments found.");
                    else
                        view.displayEquipmentItems(gymEquipmentItemList);
                } catch (Exception e) {
                    view.displayError("Failed to read data base. Tray again later.");
                }
            }
        }.start();
    }

    @Override
    public void getGymEquipmentsStock() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    List<GymEquipmentStock> gymEquipmentStockList = dataBase.gymEquipmentDAO().getGymEquipmentStock();
                    if(gymEquipmentStockList.isEmpty())
                        view.displayError("No equipments in stock.");
                    else
                        view.displayEquipmentStock(gymEquipmentStockList);
                } catch (Exception e) {
                    view.displayError("Failed to read data base. Tray again later.");
                }
            }
        }.start();
    }

    @Override
    public void deleteAllGymEquipments() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                dataBase.clearAllTables();
            }
        }.start();


    }
}
