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

public class PostsGVAdapter extends ArrayAdapter<PostClass> {
    public PostsGVAdapter(@NonNull Context context, ArrayList<PostClass> postModelArrayList) {
        super(context, 0, postModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_view, parent, false);
        }
        PostClass postModel = getItem(position);
        TextView postTV = listitemView.findViewById(R.id.idTVpost);
        postTV.setText(postModel.getName());
        ImageView postIV = listitemView.findViewById(R.id.idIVpost);
        Picasso.get().load(postModel.getImage()).into(postIV);


        return listitemView;
    }


}
