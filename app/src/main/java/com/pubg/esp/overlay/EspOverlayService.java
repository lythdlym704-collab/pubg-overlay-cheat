package com.pubg.esp.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EspOverlayService extends Service {
    private WindowManager windowManager;
    private View floatingIcon;
    private View menuView;
    private WindowManager.LayoutParams iconParams, menuParams;
    private boolean isMenuOpen = false;
    private boolean isEspActive = false;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        floatingIcon = new Button(this);
        ((Button) floatingIcon).setText("PUBG");
        floatingIcon.setBackgroundColor(0x99FF0000);
        ((Button) floatingIcon).setTextColor(0xFFFFFFFF);

        iconParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        iconParams.gravity = Gravity.TOP | Gravity.START;
        iconParams.x = 100;
        iconParams.y = 100;

        windowManager.addView(floatingIcon, iconParams);

        floatingIcon.setOnTouchListener(new View.OnTouchListener() {
            private int initialX, initialY;
            private float initialTouchX, initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = iconParams.x;
                        initialY = iconParams.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getRawX() - initialTouchX) < 10 && Math.abs(event.getRawY() - initialTouchY) < 10) {
                            toggleMenu();
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        iconParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                        iconParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(floatingIcon, iconParams);
                        return true;
                }
                return false;
            }
        });
    }

    private void toggleMenu() {
        if (!isMenuOpen) {
            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setOrientation(LinearLayout.VERTICAL);
            menuLayout.setBackgroundColor(0xCC000000);
            menuLayout.setPadding(20, 20, 20, 20);

            Button btnEspToggle = new Button(this);
            btnEspToggle.setText("تفعيل كشف الأماكن (ESP: OFF)");
            btnEspToggle.setOnClickListener(v -> {
                isEspActive = !isEspActive;
                if (isEspActive) {
                    btnEspToggle.setText("إغلاق كشف الأماكن (ESP: ON)");
                    Toast.makeText(this, "تم تفعيل كشف الأماكن عبر Shizuku", Toast.LENGTH_SHORT).show();
                } else {
                    btnEspToggle.setText("تفعيل كشف الأماكن (ESP: OFF)");
                    Toast.makeText(this, "تم إغلاق كشف الأماكن", Toast.LENGTH_SHORT).show();
                }
            });
            menuLayout.addView(btnEspToggle);

            Button btnClose = new Button(this);
            btnClose.setText("إخفاء القائمة");
            btnClose.setOnClickListener(v -> removeMenu());
            menuLayout.addView(btnClose);

            menuParams = new WindowManager.LayoutParams(
                    400,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
            menuParams.gravity = Gravity.CENTER;

            menuView = menuLayout;
            windowManager.addView(menuView, menuParams);
            isMenuOpen = true;
        } else {
            removeMenu();
        }
    }

    private void removeMenu() {
        if (isMenuOpen && menuView != null) {
            windowManager.removeView(menuView);
            isMenuOpen = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingIcon != null) windowManager.removeView(floatingIcon);
        removeMenu();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

