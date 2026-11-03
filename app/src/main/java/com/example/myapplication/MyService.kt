package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val intent=Intent(this, MyReceiver::class.java)
//        sendBroadcast(intent)
//        Toast.makeText(this,"JK",Toast.LENGTH_SHORT).show()
        if (mediaPlayer==null){
            mediaPlayer= MediaPlayer.create(this,R.raw.sample_1)
            mediaPlayer?.isLooping=true
            mediaPlayer?.start()
        }
        return START_STICKY
    }
    override fun onDestroy() {

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}