package com.luoyang.txplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val txVodPlayer by lazy { TXVodPlayer(this) }

    companion object {
        fun goIn(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("url", "http://video.v2gogo.com/upload/fromWeb/video/2019/11/22/896656708BC67C4AF48D213FBC8777DB.mp4")
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_txcloud_video_view.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION)
        txVodPlayer.setPlayerView(main_txcloud_video_view)

        val url = intent.getStringExtra("url")
                ?: "http://video.v2gogo.com/upload/fromWeb/video/2019/11/18/6D245C2A1A96C561735CEC4F2036F3EC.mp4"
        txVodPlayer.startPlay(url)
        floatingActionButton.setOnLongClickListener { v ->
            SupperPlayerActivity.goIn(this)
            return@setOnLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()
        txVodPlayer.resume()
    }

    override fun onPause() {
        super.onPause()
        txVodPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        txVodPlayer.stopPlay(false)
        main_txcloud_video_view.onDestroy()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.floatingActionButton -> {
                goIn(this)
            }
        }
    }
}
