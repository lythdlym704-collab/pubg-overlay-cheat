package com.pubg.esp.overlay;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import rikka.shizuku.Shizuku;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(0xFF111111);

        android.widget.TextView title = new android.widget.TextView(this);
        title.setText("PUBG ESP (Shizuku Mode)");
        title.setTextColor(0xFF00FF00);
        title.setTextSize(20);
        layout.addView(title);

        Button btnOverlayPerm = new Button(this);
        btnOverlayPerm.setText("1. منح إذن الظهور فوق الشاشة");
        btnOverlayPerm.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            } else {
                Toast.makeText(this, "الإذن مفعّل مسبقاً", Toast.LENGTH_SHORT).show();
            }
        });
        layout.addView(btnOverlayPerm);

        Button btnShizuku = new Button(this);
        btnShizuku.setText("2. التحقق من صلاحيات شيزوكو");
        btnShizuku.setOnClickListener(v -> {
            if (Shizuku.pingBinder()) {
                if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Shizuku متصل ومهيأ بنجاح!", Toast.LENGTH_LONG).show();
                } else {
                    Shizuku.requestPermission(0);
                }
            } else {
                Toast.makeText(this, "يرجى تشغيل تطبيق Shizuku أولاً!", Toast.LENGTH_LONG).show();
            }
        });
        layout.addView(btnShizuku);

        Button btnLaunchPubg = new Button(this);
        btnLaunchPubg.setText("3. فتح لعبة ببجي وتفعيل الأيقونة");
        btnLaunchPubg.setBackgroundColor(0xFFD32F2F);
        btnLaunchPubg.setOnClickListener(v -> {
            startService(new Intent(this, EspOverlayService.class));
            
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.tencent.ig");
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(this, "لعبة PUBG غير مثبتة على الجهاز!", Toast.LENGTH_LONG).show();
            }
        });
        layout.addView(btnLaunchPubg);

        setContentView(layout);
    }
}

