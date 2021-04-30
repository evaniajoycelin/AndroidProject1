package com.pertemuan6;

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

public class EditActivity extends AppCompatActivity {

    private EditText editName, editDesc, editQty;
    private Button editBtn;
    private InventoryHelper inventoryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        inventoryHelper = new InventoryHelper(this);

        init();
        Inventory inventory = getIntent().getParcelableExtra("Inventory");
//        Log.d("Text Name", inventory.getName());
        editName.setText(inventory.getName());
        editDesc.setText(inventory.getDescription());
        editQty.setText(inventory.getQty().toString());

        editBtn.setOnClickListener(view -> {
            String edit_name = editName.getText().toString();
            String edit_desc = editDesc.getText().toString();
            String edit_qty = editQty.getText().toString();

            if(edit_name.isEmpty() && edit_desc.isEmpty() && edit_qty.isEmpty()){
                editName.setError("This field can not be empty");
                editDesc.setError("This field can not be empty");
                editQty.setError("This field can not be empty");
            }else if(edit_desc.isEmpty() && edit_qty.isEmpty()){
                editDesc.setError("This field can not be empty");
                editQty.setError("This field can not be empty");
            }else if(edit_name.isEmpty() && edit_desc.isEmpty()){
                editName.setError("This field can not be empty");
                editDesc.setError("This field can not be empty");
            }else if(edit_name.isEmpty() && edit_qty.isEmpty()){
                editName.setError("This field can not be empty");
                editQty.setError("This field can not be empty");
            }else{
                if(edit_name.isEmpty()){
                    editName.setError("This field can not be empty");
                }else if(edit_desc.isEmpty()){
                    editDesc.setError("This field can not be empty");
                }else if (edit_qty.isEmpty()){
                    editQty.setError("This field can not be empty");
                }else{
                    Integer qty = Integer.parseInt(edit_qty);
                    inventoryHelper.updateInventory(inventory.getId(), edit_name, edit_desc, qty);

                    Toast.makeText(EditActivity.this, "Success edit item", Toast.LENGTH_LONG).show();
                    Intent mainActivity = new Intent(EditActivity.this, MainActivity.class);
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
            Intent mainAct = new Intent(EditActivity.this, MainActivity.class);
            mainAct.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainAct);
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        editName = findViewById(R.id.edit_item_et_name);
        editDesc = findViewById(R.id.edit_item_et_desc);
        editQty = findViewById(R.id.edit_item_et_qty);
        editBtn = findViewById(R.id.edit_item_btn);
    }
}