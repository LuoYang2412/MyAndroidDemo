package com.luoyang.txplayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tencent.liteav.demo.play.SuperPlayerModel
import kotlinx.android.synthetic.main.activity_supper_player.*

class SupperPlayerActivity : AppCompatActivity() {
    companion object {
        fun goIn(context: Context) {
            context.startActivity(Intent(context, SupperPlayerActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supper_player)
        val superPlayerModel = SuperPlayerModel()
        superPlayerModel.url = "http://video.v2gogo.com/upload/fromWeb/video/2019/11/18/6D245C2A1A96C561735CEC4F2036F3EC.mp4"
        superPlayerModel.title = "普通播放"
        superPlayerView.playWithModel(superPlayerModel)

        val superPlayerModel1 = SuperPlayerModel()
        superPlayerModel.title = "多清晰度"
        superPlayerModel.multiURLs.add(SuperPlayerModel.SuperPlayerURL("http://video.v2gogo.com/upload/fromWeb/video/2019/11/18/6D245C2A1A96C561735CEC4F2036F3EC.mp4", "高清"))
        superPlayerModel.multiURLs.add(SuperPlayerModel.SuperPlayerURL("http://video.v2gogo.com/upload/fromWeb/video/2019/11/22/896656708BC67C4AF48D213FBC8777DB.mp4", "超清"))
        superPlayerModel1.playDefaultIndex = 0;
        superPlayerView2.playWithModel(superPlayerModel1)
    }
}
