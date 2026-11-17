[app]

title = PUBG Overlay Cheat
package.name = pubgoverlay
package.domain = org.cheat.pubg

source.dir = .
source.include_exts = py,png,jpg,kv,atlas
source.exclude_exts = spec
source.exclude_dirs = bin, venv, .git, .github

version = 1.0
requirements = python3,kivy
orientation = portrait

android.permissions = INTERNET,SYSTEM_ALERT_WINDOW
android.api = 33
android.minapi = 21
android.sdk = 33
android.ndk = 25b
android.archs = arm64-v8a

[buildozer]
log_level = 2
warn_root = 1
