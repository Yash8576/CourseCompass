package edu.uga.cs.project3;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MajorListFragment.OnItemSelectedListener {

    private boolean isLandscape;
    private String selectedMajorName = null; // Store selected major

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct layout (Android auto-selects portrait or landscape XML)
        setContentView(R.layout.activity_main);

        // Check if the device is in landscape mode
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            // Restore previously selected major
            selectedMajorName = savedInstanceState.getString("selected_major");
        }

        if (savedInstanceState == null) {
            // Always ensure the list fragment is present in BOTH portrait & landscape
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container, new MajorListFragment())
                    .commit();
        }

        if (isLandscape) {
            // 💡 Ensure the LIST fragment is visible in landscape mode
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container, new MajorListFragment())
                    .commit();

            // Restore the correct details fragment
            if (selectedMajorName != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_container, DetailFragment.newInstance(selectedMajorName))
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_container, DetailFragment.newInstance(null))
                        .commit();
            }
        }
    }

    @Override
    public void onItemSelected(String majorName) {
        selectedMajorName = majorName; // Store the selected major

        DetailFragment detailFragment = DetailFragment.newInstance(majorName);

        if (isLandscape) {
            // Always ensure the list remains in landscape mode and update only the detail fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container, new MajorListFragment()) // Ensure list stays visible
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, detailFragment)
                    .commit();
        } else {
            // In portrait, replace the list fragment with details
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment_container, detailFragment)
                    .addToBackStack(null) // Allow back navigation
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("selected_major", selectedMajorName); // Save the selected major
    }
}