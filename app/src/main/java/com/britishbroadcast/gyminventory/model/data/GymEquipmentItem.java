package com.britishbroadcast.gyminventory.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.britishbroadcast.gyminventory.util.EquipmentType;

import java.io.Serializable;

@Entity(tableName = "gym_equipment_item")
public
class GymEquipmentItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "type")
    private EquipmentType type;
    @ColumnInfo(name = "info")
    private String info;



    // Used by Room
    public GymEquipmentItem(int id, EquipmentType type, String info) {
        this.id = id;
        this.type = type;
        this.info = info;
    }

    // Used for data entry
    @Ignore
    public GymEquipmentItem(EquipmentType type, String info) {
        this.type = type;
        this.info = info;
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


    @Override
    public String toString() {
        return "GymEquipment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
