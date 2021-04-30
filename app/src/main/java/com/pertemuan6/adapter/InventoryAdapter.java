package com.pertemuan6.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pertemuan6.AddActivity;
import com.pertemuan6.EditActivity;
import com.pertemuan6.MainActivity;
import com.pertemuan6.R;
import com.pertemuan6.db.InventoryHelper;
import com.pertemuan6.model.Inventory;

import java.util.Vector;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder>{

    private Context context;
    private Vector<Inventory> inventories;
    private InventoryHelper inventoryHelper;

    public InventoryAdapter(Context context){
        this.context = context;
        this.inventories = new Vector<>();
        this.inventoryHelper = new InventoryHelper(context);
    }

    public void setInventory(Vector<Inventory> inventories){
        this.inventories = inventories;
    }

    @NonNull
    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.inventory_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(inventories.get(position).getName());
        holder.itemDesc.setText("Description: "+inventories.get(position).getDescription());
        holder.itemQty.setText("Quantity: "+inventories.get(position).getQty());

        holder.itemEditBtn.setOnClickListener(view -> {
            Intent editActivity = new Intent(context, EditActivity.class);
            editActivity.putExtra("Inventory", inventories.get(position));
            context.startActivity(editActivity);
//            this.inventoryHelper.updateInventory(inventories.get(position).getId(),
//                                                inventories.get(position).getName(),
//                                                inventories.get(position).getDescription(),
//                                                inventories.get(position).getQty());
        });
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName, itemDesc, itemQty;
        private Button itemEditBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.main_item_name);
            itemDesc = itemView.findViewById(R.id.main_item_desc);
            itemQty = itemView.findViewById(R.id.main_item_qty);
            itemEditBtn = itemView.findViewById(R.id.main_item_edit_btn);

        }
    }
}
