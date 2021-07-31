package com.ktao1.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.ktao1.youtubeplayer.databinding.ActivityStandaloneBinding;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityStandaloneBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStandaloneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnPlayVideo = binding.standaloneBtnPlayVideo;
        Button btnPlayList = binding.standaloneBtnPlayPlaylist;

        btnPlayVideo.setOnClickListener(this);
        btnPlayList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.standalone_btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID, 0 , true, false);
                break;
            case R.id.standalone_btnPlayPlaylist:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_PLAYLIST, 0, 0, true, true);
                break;
            default:
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
