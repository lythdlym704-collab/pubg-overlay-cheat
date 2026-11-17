from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.label import Label
from kivy.uix.button import Button
from kivy.uix.switch import Switch
from kivy.core.window import Window

# ضبط لون خلفية النافذة ليصبح شفافاً أو داكناً بنمط الأوفرلاي
Window.clearcolor = (0.1, 0.1, 0.1, 0.9)

class PUBGOverlayApp(App):
    def build(self):
        # التخطيط الرئيسي للقائمة العائمة
        root = BoxLayout(orientation='vertical', padding=20, spacing=15)
        
        # عنوان القائمة
        title_label = Label(
            text='[b]PUBG OVERLAY MENU[/b]',
            markup=True,
            font_size='22sp',
            size_hint_y=None,
            height=50,
            color=(1, 0.2, 0.2, 1)
        )
        root.add_widget(title_label)
        
        # زر تفعيل الهدف (Aimbot)
        aimbot_layout = BoxLayout(orientation='horizontal', size_hint_y=None, height=50)
        aimbot_label = Label(text='Aimbot Enable', font_size='16sp', color=(1, 1, 1, 1))
        aimbot_switch = Switch(active=False)
        aimbot_layout.add_widget(aimbot_label)
        aimbot_layout.add_widget(aimbot_switch)
        root.add_widget(aimbot_layout)
        
        # زر تفعيل كشف أماكن اللاعبين (ESP)
        esp_layout = BoxLayout(orientation='horizontal', size_hint_y=None, height=50)
        esp_label = Label(text='ESP Players Box', font_size='16sp', color=(1, 1, 1, 1))
        esp_switch = Switch(active=False)
        esp_layout.add_widget(esp_label)
        esp_layout.add_widget(esp_switch)
        root.add_widget(esp_layout)
        
        # زر إغلاق أو تصغير القائمة
        close_btn = Button(
            text='Minimize / Hide Menu',
            size_hint_y=None,
            height=45,
            background_color=(0.8, 0.1, 0.1, 1)
        )
        close_btn.bind(on_press=self.hide_menu)
        root.add_widget(close_btn)
        
        return root

    def hide_menu(self, instance):
        # وظيفة تصغير أو إخفاء الواجهة مؤقتاً
        print("Menu minimized")

if __name__ == '__main__':
    PUBGOverlayApp().run()
