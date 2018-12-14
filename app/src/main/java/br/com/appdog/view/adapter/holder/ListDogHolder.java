package br.com.appdog.view.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import br.com.appdog.R;


public class ListDogHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public ListDogHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_dog);
    }
}
