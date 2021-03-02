package com.britishbroadcast.gyminventory.model.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;
import com.britishbroadcast.gyminventory.util.EquipmentType;

import java.util.List;

@Dao
public interface GymEquipmentDAO {

    @Insert
    void addGymEquipmentItem(GymEquipmentItem... gymEquipmentItem);

    @Insert
    void addGymEquipmentStock(GymEquipmentStock... gymEquipmentStocks);

    @Query("SELECT * FROM gym_equipment_item")
    List<GymEquipmentItem> getGymEquipmentItems();

    @Query("SELECT * FROM gym_equipment_stock")
    List<GymEquipmentStock> getGymEquipmentStock();


    @Query("SELECT * FROM gym_equipment_item WHERE type = :equipmentType")
    GymEquipmentItem getGymEquipmentItemByType(EquipmentType equipmentType);

}
