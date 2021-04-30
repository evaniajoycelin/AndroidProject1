package com.pertemuan6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.pertemuan6.adapter.InventoryAdapter;
import com.pertemuan6.db.InventoryHelper;
import com.pertemuan6.model.Inventory;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private InventoryAdapter inventoryAdapter;
    private InventoryHelper inventoryHelper;
    private Vector<Inventory> inventories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler View
        RecyclerView recyclerView = findViewById(R.id.main_rv_inventory);

        //Helper
        inventoryHelper = new InventoryHelper(MainActivity.this);
        inventories = inventoryHelper.getInventory();
        if (inventories.size() == 0){
            inventoryHelper.generateInventory();
            inventories = inventoryHelper.getInventory();
        }

        inventoryAdapter = new InventoryAdapter(MainActivity.this);
        inventoryAdapter.setInventory(inventories);
        inventoryAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        recyclerView.setAdapter(inventoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_item_add){
            Intent addActivity = new Intent(MainActivity.this, AddActivity.class);
            addActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(addActivity);
        }
        return super.onOptionsItemSelected(item);
    }
}