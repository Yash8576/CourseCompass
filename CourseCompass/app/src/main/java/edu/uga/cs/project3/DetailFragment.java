package edu.uga.cs.project3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DetailFragment extends Fragment {

    private static final String ARG_MAJOR_NAME = "major_name";

    public static DetailFragment newInstance(String majorName) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MAJOR_NAME, majorName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView textView = view.findViewById(R.id.detail_text);
        ImageView image1 = view.findViewById(R.id.img1);
        ImageView image2 = view.findViewById(R.id.img2);

        // ✅ Handle null value to prevent crashes
        String majorName = null;
        if (getArguments() != null) {
            majorName = getArguments().getString(ARG_MAJOR_NAME);
        }

        if (majorName == null) {
            // ✅ If no major is selected, show default text and hide images
            textView.setText("Select a major to see details");
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            return view;
        }

        // ✅ Convert to lowercase safely
        majorName = majorName.toLowerCase();

        // Get image prefix
        String imagePrefix = getImagePrefix(majorName);

        // Load images dynamically
        Context context = requireContext();
        int imageResId1 = context.getResources().getIdentifier(imagePrefix + "1", "drawable", context.getPackageName());
        int imageResId2 = context.getResources().getIdentifier(imagePrefix + "2", "drawable", context.getPackageName());

        if (imageResId1 != 0) {
            image1.setImageResource(imageResId1);
            image1.setVisibility(View.VISIBLE);
        } else {
            image1.setVisibility(View.GONE);
        }

        if (imageResId2 != 0) {
            image2.setImageResource(imageResId2);
            image2.setVisibility(View.VISIBLE);
        } else {
            image2.setVisibility(View.GONE);
        }

        // Load text from raw folder using the formatted name
        int textResId = context.getResources().getIdentifier(majorName, "raw", context.getPackageName());
        if (textResId != 0) {
            textView.setText(readTextFile(context, textResId));
        } else {
            textView.setText("Text content not available.");
        }

        return view;
    }

    // Helper method to read text file from raw folder
    private String readTextFile(Context context, int resId) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = context.getResources().openRawResource(resId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading text.";
        }
        return content.toString();
    }

    // Helper method to map major names to corresponding image prefixes
    private String getImagePrefix(String majorName) {
        // Map values exactly as per image names
        Map<String, String> imageMap = new HashMap<>();
        imageMap.put("computerscience", "cs");
        imageMap.put("biology", "bio");
        imageMap.put("economics", "eco");
        imageMap.put("chemistry", "chem");
        imageMap.put("mechanicalengineering", "mech");
        imageMap.put("psychology", "psych");

        return imageMap.getOrDefault(majorName, "default");
    }
}