package com.amol.bharatagri.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amol.bharatagri.data.VideoModel;

import java.util.LinkedList;

import com.amol.bharatagri.R;

public class CustomVideoAdapter extends RecyclerView.Adapter<CustomVideoAdapter.VideoViewHolder> {
    private final Context context;
    private LinkedList<VideoModel> videos;

    public CustomVideoAdapter(Context context, LinkedList<VideoModel> videos) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_video_item, viewGroup, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder videoViewHolder, int i) {

        final VideoModel video = videos.get(i);

        videoViewHolder.tvName.setText(video.getName());

        videoViewHolder.btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String url = "http://www.youtube.com/watch?v=" + video.getKey();

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void setVideos(LinkedList<VideoModel> videos) {
        this.videos = videos;
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final Button btnPlay;

        VideoViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tv_name);
            btnPlay = view.findViewById(R.id.btn_play);

        }
    }

}
