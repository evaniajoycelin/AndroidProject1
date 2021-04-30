package com.pertemuan6.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pertemuan6.model.Inventory;

import java.util.Vector;

public class InventoryHelper {
    private static final String TABLE_NAME = "inventory";

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public InventoryHelper(Context context){
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

    }

    public Vector<Inventory> getInventory(){
        Vector<Inventory> inventories = new Vector<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null,
        null, null, null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0 ){
            do{
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                Integer qty = cursor.getInt(cursor.getColumnIndex("qty"));

                inventories.add(new Inventory(id, name, description, qty));
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        return inventories;
    }
    public void generateInventory(){
        String [] nameList = {"Mascara", "Foundation", "Concealer", "Powder", "Highlighter", "Eye Shadow"};
        String [] descList = {"Thick, black and long",
                                "Light, non-comedogenic, full-coverage",
                                "Perfect for under eye, easy to blend",
                                "Fresh, Glowing, Oil resistant",
                                "Hydrating, illuminates skin, glowing and radiant",
                                "Soft and Smooth, Colorful"};
        Integer [] qtyList = {10, 10, 10, 10, 10, 10};

        for(int i = 0 ; i<6; i++){
            Integer id = i;
            String name = nameList[i];
            String description = descList[i];
            Integer qty = qtyList[i];

            insertInventory(new Inventory(id, name, description, qty));
        }

    }
    public void insertInventory(Inventory inventory){

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", inventory.getName());
        contentValues.put("description", inventory.getDescription());
        contentValues.put("qty", inventory.getQty());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void updateInventory(Integer id, String name, String description, Integer qty){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("qty", qty);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "id=?", new String[]{id.toString()});

    }

    public void deleteInventory(Integer id){
        sqLiteDatabase.delete(TABLE_NAME, "id="+id, null);
    }

    public void deleteAllInventory(){
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }


}
