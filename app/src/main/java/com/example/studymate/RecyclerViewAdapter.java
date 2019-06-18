package com.example.studymate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList mImageNames = new ArrayList<>();
    private ArrayList mImages;
    //    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private Main as=new Main();

    public RecyclerViewAdapter(Context context, ArrayList imageNames,ArrayList image) {
        mImageNames = imageNames;
        mImages = image;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        String message=mImageNames.get(position).toString();
        String upperString = message.substring(0,1).toUpperCase() + message.substring(1);

        holder.imageName.setText((upperString));


        byte[] bitmap= (byte[]) mImages.get(position);
        if(bitmap!=null){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bitmap);
            Bitmap bp = BitmapFactory.decodeStream(inputStream);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.cardView.setVisibility(View.VISIBLE);
            holder.imageView.setImageBitmap(bp);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
//                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
                String question= String.valueOf(mImageNames.get(position));
                byte[] bitmap= (byte[]) mImages.get(position);

                Bundle basket=new Bundle();
                basket.putString("question",question);
                if (bitmap!=null) {
                    basket.putByteArray("image", bitmap);
                }
                Intent intent=new Intent("ke.co.nanotechsoftwares.muemasn.tabbedclasswork.ANSWERS");
                intent.putExtras(basket);
                mContext.startActivity(intent);
                //Log.d(TAG,"onAphonse"+image.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        //        CircleImageView image;
        TextView imageName;
        ImageView imageView;
        CardView cardView;
        RelativeLayout relativeLayout;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            cardView=itemView.findViewById(R.id.cardViewAnswe);
            imageView=itemView.findViewById(R.id.imageView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
