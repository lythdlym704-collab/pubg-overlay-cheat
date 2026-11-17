[app]

# (str) Title of your application
title = PUBG Overlay Cheat

# (str) Package name
package.name = pubgoverlay

# (str) Package domain (needed for android packaging)
package.domain = org.cheat.pubg

# (list) Source files to include (let it empty to include all files)
source.include_exts = py,png,jpg,kv,atlas

# (list) Source files to exclude (let it empty to exclude none)
source.exclude_exts = spec

# (list) List of directory to exclude (let it empty to exclude none)
source.exclude_dirs = bin, venv, .git, .github

# (list) List of inclusions
source.include_dir = .

# (str) Application versioning
version = 1.0

# (list) Application requirements
# comma separated e.g. requirements = sqlite3,kivy
requirements = python3,kivy

# (str) Supported orientations (portrait or landscape)
orientation = portrait

# (list) Permissions
android.permissions = INTERNET,SYSTEM_ALERT_WINDOW

# (int) Target Android API, should be as high as possible.
android.api = 33

# (int) Minimum API your APK will support.
android.minapi = 21

# (int) Android SDK version to use
android.sdk = 33

# (str) Android NDK version to use
android.ndk = 25b

# (list) Area of intent filters
android.archs = arm64-v8a

[buildozer]

# (int) Log level (0 = error only, 1 = info, 2 = debug (with command output))
log_level = 2

# (int) Display warning if buildozer is run as root (0 = False, 1 = True)
warn_root = 1

