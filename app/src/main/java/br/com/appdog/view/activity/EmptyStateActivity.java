package br.com.appdog.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.appdog.R;

/**
 * class to display an empty state image to indicate that something went wrong.
 */
public class EmptyStateActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_state_activity);
        configureToolbar();
    }

    /**
     * configure screen toolbar.
     */

    private void configureToolbar() {
        mToolbar =  findViewById(R.id.toolbar_ficha);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back));
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
