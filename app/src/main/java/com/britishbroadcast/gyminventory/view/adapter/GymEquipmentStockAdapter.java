package com.britishbroadcast.gyminventory.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.britishbroadcast.gyminventory.R;
import com.britishbroadcast.gyminventory.model.data.GymEquipmentStock;

import java.util.List;

public class GymEquipmentStockAdapter extends RecyclerView.Adapter<GymEquipmentStockAdapter.GymEquipmentStockHolder> {

    List<GymEquipmentStock> gymEquipmentStockList;

    public GymEquipmentStockAdapter(List<GymEquipmentStock> gymEquipmentStockList) {
        this.gymEquipmentStockList = gymEquipmentStockList;
    }

    @NonNull
    @Override
    public GymEquipmentStockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gym_equipment_stock_item_layout, parent, false);

        return new GymEquipmentStockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GymEquipmentStockHolder holder, int position) {

        holder.quantityTextView.setText(holder.itemView.getContext().getString(R.string.stock_quantity_text, gymEquipmentStockList.get(position).getQuantity()+""));

        holder.descriptionTextView.setText(holder.itemView.getContext().getString(R.string.stock_description_text, gymEquipmentStockList.get(position).getType().toString() + " - " + gymEquipmentStockList.get(position).getInfo()));



    }

    @Override
    public int getItemCount() {
        return gymEquipmentStockList.size();
    }

    public void updateData(List<GymEquipmentStock> gymEquipmentStockList) {
        this.gymEquipmentStockList = gymEquipmentStockList;
        notifyDataSetChanged();
    }

    public class GymEquipmentStockHolder extends RecyclerView.ViewHolder {

        TextView quantityTextView, descriptionTextView;

        public GymEquipmentStockHolder(@NonNull View itemView) {
            super(itemView);

            quantityTextView = itemView.findViewById(R.id.quantity_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);

        }
    }
}
