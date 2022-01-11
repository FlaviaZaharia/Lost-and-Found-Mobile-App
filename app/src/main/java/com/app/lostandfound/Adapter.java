package com.app.lostandfound;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<PostModel> posts;
    public Adapter(List<PostModel> posts) {
        this.posts=posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_design,parent,false);
        return new ViewHolder(view); }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        int resource = posts.get(position).getImage();
        String title=posts.get(position).getTitle();
        String description=posts.get(position).getDescription();
        String category=posts.get(position).getCategory();

        holder.setData(resource,title,description,category);
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }

    //holder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView ttl;
        private TextView desc;
        private TextView categ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView1);
            ttl=itemView.findViewById(R.id.titlePost);
            desc=itemView.findViewById(R.id.description);
            categ=itemView.findViewById(R.id.category);
        }
        public void setData(int res,String title,String description,String category) {
            img.setImageResource(res);
            ttl.setText(title);
            desc.setText(description);
            categ.setText(category);
        }

    }
}
