package br.com.appdog.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import br.com.appdog.R;
import br.com.appdog.util.IntentActions;
import br.com.appdog.util.OpenScreenUtil;
import br.com.appdog.view.adapter.holder.ListDogHolder;



public class ListDogAdapter extends RecyclerView.Adapter<ListDogHolder> {

    private Context mContext;
    private List<String> mListDog = new ArrayList<>();

    public ListDogAdapter(@NonNull Context context) {
        this.mContext = context;

    }

    @Override
    public ListDogHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list, viewGroup, false);
        return new ListDogHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListDogHolder viewHolder, int position) {

        final Drawable[] image = new Drawable[1];
        viewHolder.imageView.buildDrawingCache ();

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                viewHolder.imageView.setImageBitmap(bitmap);
                image[0] = viewHolder.imageView.getDrawable();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }



            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        };

        Picasso.get().load(mListDog.get(position)).into(target);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)image[0]).getBitmap();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                        bitmap, 300, 300, false);
                Bundle bundle = new Bundle();
               // bundle.pu
                bundle.putParcelable ("image", resizedBitmap);
                OpenScreenUtil.openScreen(mContext, IntentActions.DOG_ACTIVITY.getAction(), bundle, false);
            }
        });
    }

    public void setList(final List<String> list) {
        this.mListDog.clear();
        this.mListDog.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mListDog == null) {
            return 0;
        }
        return mListDog.size();
    }
}
