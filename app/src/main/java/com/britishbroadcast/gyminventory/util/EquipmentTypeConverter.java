package com.britishbroadcast.gyminventory.util;

import androidx.room.TypeConverter;

public class EquipmentTypeConverter {

    /**
     * Convert EquipmentType to an integer
     */
    @TypeConverter
    public static int fromEquipmentTypeToInt(EquipmentType value) {
        return value.ordinal();
    }

    /**
     * Convert an integer to EquipmentType
     */
    @TypeConverter
    public static EquipmentType fromIntToEquipmentType(int value) {
        return (EquipmentType.values()[value]);
    }
}
