package com.britishbroadcast.gyminventory.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;
import com.britishbroadcast.gyminventory.util.EquipmentTypeConverter;

@Database(version = 1, entities = {GymEquipmentItem.class, GymEquipmentStock.class})
@TypeConverters(EquipmentTypeConverter.class)
public abstract class  DataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "gym.db";
    public abstract GymEquipmentDAO gymEquipmentDAO();
}
