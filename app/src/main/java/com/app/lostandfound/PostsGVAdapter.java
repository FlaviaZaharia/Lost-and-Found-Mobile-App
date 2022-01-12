package com.app.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    private static final String TAG ="" ;

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
        if(!postModel.getImage().equals(""))
        Picasso.get().load(postModel.getImage()).into(postIV);
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),IndividualFound.class);
                i.putExtra("category",postModel.getCategory());
                i.putExtra("name",postModel.getName());
                i.putExtra("url",postModel.getImage());
                i.putExtra("status",postModel.getStatus());
                i.putExtra("description",postModel.getDescription());
                i.putExtra("location",postModel.getLocation());
                i.putExtra("d",postModel.getDescriptions());
                i.putExtra("uid",postModel.getUid());
               getContext().startActivity(i);

            }
        });
        return listitemView;
    }


}
