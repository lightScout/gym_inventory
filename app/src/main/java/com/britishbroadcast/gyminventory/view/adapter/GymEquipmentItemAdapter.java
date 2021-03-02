package com.britishbroadcast.gyminventory.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.britishbroadcast.gyminventory.R;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentItem;
import com.britishbroadcast.gyminventory.util.EquipmentType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GymEquipmentItemAdapter extends RecyclerView.Adapter<GymEquipmentItemAdapter.GymEquipmentTypeHolder> {

    List<GymEquipmentItem> gymEquipmentItemList;
    private GymEquipmentItemDelegate gymEquipmentItemDelegate;

    public GymEquipmentItemAdapter(List<GymEquipmentItem> gymEquipmentItemList, GymEquipmentItemDelegate gymEquipmentItemDelegate) {
        this.gymEquipmentItemList = gymEquipmentItemList;
        this.gymEquipmentItemDelegate = gymEquipmentItemDelegate;
    }

    public void updateData(List<GymEquipmentItem> gymEquipmentItemList) {

        this.gymEquipmentItemList = gymEquipmentItemList;
        notifyDataSetChanged();
    }

    public interface GymEquipmentItemDelegate{
        void selectGymEquipmentItem(GymEquipmentItem gymEquipmentItem);
    }


    @NonNull
    @Override
    public GymEquipmentTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gym_equipment_items_item_layout, parent, false);

        return new GymEquipmentTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GymEquipmentTypeHolder holder, int position) {


        GymEquipmentItem gymEquipmentItem = gymEquipmentItemList.get(position);

        holder.equipmentTypeTextView.setText(holder.itemView.getContext().getString(R.string.equipment_type_text, gymEquipmentItem.getType()));

        int icon;

        if(gymEquipmentItem.getType() == EquipmentType.TREADMILL){
            icon = R.drawable.ic_treadmill;
        }else if (gymEquipmentItem.getType() == EquipmentType.DUMBELL){
            icon = R.drawable.ic_dumbell;
        }else
            icon = R.drawable.ic_exercisebike;


        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(" ")
                .placeholder(icon)
                .into(holder.equipmentImageView);


        holder.itemView.setOnClickListener(view -> {
                    gymEquipmentItemDelegate.selectGymEquipmentItem(gymEquipmentItem);
                }
        );

    }

    @Override
    public int getItemCount() {
        return gymEquipmentItemList.size();
    }

    public class GymEquipmentTypeHolder extends RecyclerView.ViewHolder {

        TextView equipmentTypeTextView;
        ImageView equipmentImageView;

        public GymEquipmentTypeHolder(@NonNull View itemView) {
            super(itemView);

            equipmentTypeTextView = itemView.findViewById(R.id.equipment_type_textview);
            equipmentImageView = itemView.findViewById(R.id.equipment_type_imageview);
        }
    }
}
