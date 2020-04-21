package tonhi.com.hocmenutimkiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import tonhi.com.hocmenutimkiem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapterTinhThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addControls();
    }

    private void addControls() {
        adapterTinhThanh=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1);
        adapterTinhThanh.addAll(getResources().getStringArray(R.array.arrTinhThanh));
        binding.lvTinhThanh.setAdapter(adapterTinhThanh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem mnuSearch=menu.findItem(R.id.mnuSearch);
        SearchView searchView= (SearchView) mnuSearch.getActionView();
        MenuItemCompat.setOnActionExpandListener(mnuSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this,"Đang expand",Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this,"Đang Collapse",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTinhThanh.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
