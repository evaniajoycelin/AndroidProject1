package com.pertemuan6;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pertemuan6.adapter.InventoryAdapter;
import com.pertemuan6.db.InventoryHelper;
import com.pertemuan6.model.Inventory;

import java.util.Vector;

public class AddActivity extends AppCompatActivity {

    private InventoryAdapter inventoryAdapter;
    private InventoryHelper inventoryHelper;
    private Vector<Inventory> inventories;

    private EditText etName, etDesc, etQty;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inventoryHelper = new InventoryHelper(this);
        inventoryAdapter = new InventoryAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
        // Add Inventory
        btnAdd.setOnClickListener(view -> {

            String itemName = etName.getText().toString();
            String itemDesc = etDesc.getText().toString();
            String qty = etQty.getText().toString();

            if(itemName.isEmpty() && itemDesc.isEmpty() && qty.isEmpty()){
                etName.setError("This field can not be empty");
                etDesc.setError("This field can not be empty");
                etQty.setError("This field can not be empty");
            }else if(itemDesc.isEmpty() && qty.isEmpty()){
                etDesc.setError("This field can not be empty");
                etQty.setError("This field can not be empty");
            }else if(itemName.isEmpty() && itemDesc.isEmpty()){
                etName.setError("This field can not be empty");
                etDesc.setError("This field can not be empty");
            }else if(itemName.isEmpty() && qty.isEmpty()){
                etName.setError("This field can not be empty");
                etQty.setError("This field can not be empty");
            }else{
                if(itemName.isEmpty()){
                    etName.setError("This field can not be empty");
                }else if(itemDesc.isEmpty()){
                    etDesc.setError("This field can not be empty");
                }else if (qty.isEmpty()){
                    etQty.setError("This field can not be empty");
                }else{
                    Integer itemQty = Integer.parseInt(qty);
                    inventoryHelper.insertInventory(new Inventory(itemName, itemDesc, itemQty));
                    inventories = inventoryHelper.getInventory();
                    inventoryAdapter.setInventory(inventories);
                    inventoryAdapter.notifyDataSetChanged();

                    Toast.makeText(AddActivity.this, "Success add new item", Toast.LENGTH_LONG).show();
                    Intent mainActivity = new Intent(AddActivity.this, MainActivity.class);
                    mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainActivity);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_item_back){
            Intent mainAct = new Intent(AddActivity.this, MainActivity.class);
            mainAct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainAct);
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        etName = findViewById(R.id.add_item_et_name);
        etDesc = findViewById(R.id.add_item_et_desc);
        etQty = findViewById(R.id.add_item_et_qty);
        btnAdd = findViewById(R.id.add_item_btn);

    }
}