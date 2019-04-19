package com.example.dip.lsl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;


import com.bumptech.glide.Glide;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<upload> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        upload uploadCurrent = mUploads.get(position);

        holder.description.setText(uploadCurrent.getKey());
        Glide.with(mContext).load(uploadCurrent.getUrl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView description;
        public ImageView iv;

        public ImageViewHolder(View itemView) {

            super(itemView);
            description = itemView.findViewById(R.id.des);
            iv = itemView.findViewById(R.id.iv);

        }


        @Override
        public void onClick(View v) {
            //  if (mListener != null) {
            int position = getAdapterPosition();
            //  if (position != RecyclerView.NO_POSITION) {
            mListener.onItemClick(position);
            //}
            // }
        }
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}