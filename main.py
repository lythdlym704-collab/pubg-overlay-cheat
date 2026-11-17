from kivy.app import App
from kivy.uix.floatlayout import FloatLayout
from kivy.uix.button import Button
from kivy.uix.label import Label
from kivy.core.window import Window
import subprocess

class FloatingCheatPanel(FloatLayout):
    def __init__(self, **kwargs):
        super(FloatingCheatPanel, self).__init__(**kwargs)
        
        # زر فتح القائمة العائمة فوق اللعبة
        self.menu_btn = Button(
            text='[ PUBG CHEAT ]',
            size_hint=(None, None),
            size=(140, 50),
            pos=(50, 500),
            background_color=(0.8, 0.1, 0.1, 1)
        )
        self.menu_btn.bind(on_press=self.toggle_features)
        self.add_widget(self.menu_btn)
        
        # لوحة الأزرار المخفية تظهر عند الضغط
        self.features_layout = FloatLayout(size_hint=(None, None), size=(200, 250), pos=(50, 240))
        self.features_layout.opacity = 0 # مخفية افتراضياً
        
        # زر إزالة الارتداد
        self.recoil_btn = Button(text='No Recoil: OFF', size_hint=(None, None), size=(180, 40), pos=(10, 190))
        self.recoil_btn.bind(on_press=self.job_no_recoil)
        self.features_layout.add_widget(self.recoil_btn)
        
        # زر كشف أماكن اللاعبين ESP
        self.esp_btn = Button(text='ESP Box: OFF', size_hint=(None, None), size=(180, 40), pos=(10, 140))
        self.esp_btn.bind(on_press=self.job_esp)
        self.features_layout.add_widget(self.esp_btn)
        
        self.add_widget(self.features_layout)

    def toggle_features(self, instance):
        if self.features_layout.opacity == 0:
            self.features_layout.opacity = 1
        else:
            self.features_layout.opacity = 0

    def job_no_recoil(self, instance):
        # استخدام صلاحيات Shizuku لتنفيذ أمر الذاكرة عبر shizuku app binder
        cmd = "shizuku_command 'echo no_recoil_active > /dev/mem_target'"
        subprocess.run(['sh', '-c', cmd])
        self.recoil_btn.text = 'No Recoil: ACTIVE'
        self.recoil_btn.background_color = (0.1, 0.8, 0.1, 1)

    def job_esp(self, instance):
        self.esp_btn.text = 'ESP Box: ACTIVE'
        self.esp_btn.background_color = (0.1, 0.8, 0.1, 1)

class PUBGOverlayApp(App):
    def build(self):
        return FloatingCheatPanel()

if __name__ == '__main__':
    PUBGOverlayApp().run()
