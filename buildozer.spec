[app]
title = PUBG Overlay Cheat
package.name = pubgoverlay
package.domain = org.cheat.pubg

source.dir = .
source.include_exts = py,png,jpg,kv
source.exclude_dirs = bin, venv, .git, .github

version = 1.0
requirements = python3==3.11.5,hostpython3==3.11.5,kivy==2.3.0

orientation = all
fullscreen = 0

android.permissions = INTERNET,SYSTEM_ALERT_WINDOW
android.api = 33
android.minapi = 21
android.ndk = 25b
android.ndk_api = 21
android.archs = arm64-v8a
android.accept_sdk_license = True

[buildozer]
log_level = 1
warn_root = 1
