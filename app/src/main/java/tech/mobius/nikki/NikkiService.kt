package tech.mobius.nikki

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.ImageView
import com.github.recruit_lifestyle.floatingview.FloatingViewManager

class NikkiService : Service() {
    
    private lateinit var manager: FloatingViewManager
    
    override fun onCreate() {
        super.onCreate()
        
        manager = FloatingViewManager(this, object : FloatingViewManager.FloatingViewListener {
            override fun onFinishFloatingView() {
                stopSelf()
            }
        })
        
        val icon = ImageView(this).apply {
            setImageResource(android.R.drawable.ic_dialog_info)
            layoutParams = android.view.ViewGroup.LayoutParams(128, 128)
        }
        
        manager.addViewToWindow(icon, FloatingViewManager.Options())
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onDestroy() {
        manager.removeAllViewToWindow()
        super.onDestroy()
    }
}
