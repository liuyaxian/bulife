
## 安装
- 1、在liunx 环境安装 google 浏览器
```shell
$ wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
$ sudo apt install ./google-chrome-stable_current_amd64.deb
```
- 2、安装Chrome也会将资源库添加到包管理器中
```shell
$ sudo apt install google-chrome-stable

```
## 删除
```shell
$ sudo apt purge google-chrome-stable
```

### 将使用git和makepkg命令来帮助安装Chrome
```shell
$ git clone https://aur.archlinux.org/google-chrome.git
$ cd google-chrome/
$ makepkg -s
$ sudo pacman -U --noconfirm google-chrome-*.xz
```
### 删除
```shell
$ sudo pacman -R google-chrome
```

## Headless Chromium
```shell
chrome --headless --remote-debugging-port=9222 https://chromium.org
```