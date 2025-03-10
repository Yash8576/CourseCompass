package edu.uga.cs.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MajorListAdapter extends RecyclerView.Adapter<MajorListAdapter.ViewHolder> {

    private final List<String> majorList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String majorName); // Pass the actual major name
    }

    public MajorListAdapter(List<String> majorList, OnItemClickListener listener) {
        this.majorList = majorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.major_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String majorName = majorList.get(position);
        holder.majorName.setText(majorName);

        // Remove spaces when passing the name
        String formattedMajorName = majorName.replace(" ", "").toLowerCase();

        holder.itemView.setOnClickListener(v -> listener.onItemClick(formattedMajorName)); // Pass formatted name
    }

    @Override
    public int getItemCount() {
        return majorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView majorName;

        public ViewHolder(View view) {
            super(view);
            majorName = view.findViewById(R.id.major_name);
        }
    }
}