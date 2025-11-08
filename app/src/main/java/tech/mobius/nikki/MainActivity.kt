package tech.mobius.nikki

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MaterialTheme {
                var hasPermission by remember { 
                    mutableStateOf(Settings.canDrawOverlays(this)) 
                }
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("N.I.K.K.I. Активация", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    if (!hasPermission) {
                        Button(onClick = {
                            startActivity(
                                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:$packageName"))
                            )
                            hasPermission = Settings.canDrawOverlays(this@MainActivity)
                        }) {
                            Text("Дать разрешение")
                        }
                    } else {
                        Button(onClick = {
                            startService(Intent(this@MainActivity, NikkiService::class.java))
                            finish()
                        }) {
                            Text("Запустить Никки")
                        }
                    }
                }
            }
        }
    }
}
