package br.com.appdog.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.StatsSnapshot;

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


        viewHolder.imageView.buildDrawingCache();


        Picasso
                .get()
                .load(mListDog.get(position))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(viewHolder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        image[0] = viewHolder.imageView.getDrawable();
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso
                                .get()
                                .load(mListDog.get(position))
                                .into(viewHolder.imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        image[0] = viewHolder.imageView.getDrawable();
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                    }


                });

        viewHolder.imageView.setOnClickListener(view -> {
            Bitmap bitmap = ((BitmapDrawable) image[0]).getBitmap();
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                    bitmap, 300, 300, false);
            Bundle bundle = new Bundle();
            // bundle.pu
            bundle.putParcelable("image", resizedBitmap);
            OpenScreenUtil.openScreen(mContext, IntentActions.DOG_ACTIVITY.getAction(), bundle, false);
        });
    }


    public void setList(final List<String> list/*String [] list*/) {

        this.mListDog.clear();
        this.mListDog.addAll(/*Arrays.asList(list)*/list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mListDog == null) {
            return 0;
        }
        return mListDog.size();
    }


    private void loadImage(final String url, final ImageView image) {
        //print picasso snap stats
        StatsSnapshot ss = Picasso.get().getSnapshot();
        Log.d("download image stats", "" + ss.cacheHits);
        Log.d("download image stats", "" + ss.cacheMisses);
        Log.d("download image stats", "" + ss.downloadCount);
        Log.d("download image stats", "" + ss.totalDownloadSize);

        //clear cache and cancel pending requests
        Picasso.get().invalidate(url);
        Picasso.get().cancelRequest(image);

        //set image rotation and placeholder image
        RequestCreator rc = Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE);
        rc.fetch();
        rc = rc.rotate(20).placeholder(R.mipmap.ic_launcher);

        //set error image, memory policy
        rc = rc.error(R.mipmap.ic_launcher);
        rc.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);

        //resize and crop
        rc = rc.resize(400, 400).centerCrop();
        //load image to imageview
        rc.into(image);
    }

}
