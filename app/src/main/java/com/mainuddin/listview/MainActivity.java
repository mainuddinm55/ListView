package com.mainuddin.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    private ActionMode mActionMode;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] countries = getResources().getStringArray(R.array.countyName);

        listView = findViewById(R.id.listViewId);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.listview_simple,R.id.textViewId,countries);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countries[position], Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnLongClickListener(this);
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        

    }
    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_menu,menu);
            mode.setTitle("Choose Your Option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.edit:
                    Toast.makeText(MainActivity.this, "Item Edited", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return  true;
                    default:
                        return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


    @Override
    public boolean onLongClick(View v) {
        if (mActionMode!=null){
            return false;
        }
        mActionMode = startSupportActionMode(mActionModeCallback);
        return true;
    }
}














