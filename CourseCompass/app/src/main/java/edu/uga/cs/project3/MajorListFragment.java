package edu.uga.cs.project3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MajorListFragment extends Fragment {

    private OnItemSelectedListener callback;

    public interface OnItemSelectedListener {
        void onItemSelected(String majorName); // Pass the actual major name, not position
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            callback = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_major_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.major_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load items from strings.xml
        String[] majorArray = getResources().getStringArray(R.array.major_titles);
        List<String> majorList = Arrays.asList(majorArray);

        // Set up adapter
        MajorListAdapter adapter = new MajorListAdapter(majorList, majorName -> {
            if (callback != null) {
                callback.onItemSelected(majorName); // Pass the actual major name
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}