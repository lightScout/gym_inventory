package com.britishbroadcast.gyminventory.model.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.britishbroadcast.gyminventory.util.EquipmentType;

@Entity(tableName = "gym_equipment_stock")
public
class GymEquipmentStock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "type")
    private EquipmentType type;
    @ColumnInfo(name = "info")
    private String info;
    @ColumnInfo(name = "quantity")
    private int quantity;


    public GymEquipmentStock(int id, EquipmentType type, String info, int quantity) {
        this.id = id;
        this.type = type;
        this.info = info;
        this.quantity = quantity;
    }

    @Ignore
    public GymEquipmentStock(EquipmentType type, String info, int quantity) {
        this.type = type;
        this.info = info;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
