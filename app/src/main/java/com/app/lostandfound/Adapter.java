package com.app.lostandfound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<PostModel> {
    public Adapter(@NonNull Context context, ArrayList<PostModel> postModelArrayList) {
        super(context, 0, postModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.post_design, parent, false);
        }
        PostModel postModel = getItem(position);
        TextView title = listitemView.findViewById(R.id.titlePost);
        title.setText(postModel.getTitle());
        TextView category = listitemView.findViewById(R.id.categoryPost);
        category.setText(postModel.getCategory());
        TextView desc = listitemView.findViewById(R.id.descriptionPost);
        desc.setText(postModel.getDescription());
        ImageView image = listitemView.findViewById(R.id.imageView1);
        Picasso.get().load(postModel.getImage()).into(image);


        return listitemView;
    }
}
