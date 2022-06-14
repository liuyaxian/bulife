# Docker
> Docker学习
> 来源狂神： https://www.bilibili.com/video/BV1og4y1q7M4/?p=3&spm_id_from=pageDriver 
> Docker是基于Go语言开发的开源项目!

官网: https://www.docker.com/

文档地址: https://docs.docker.com/get-docker/

仓库地址: https://hub.docker.com/

## Docker概述
### Docker为什么会出现?
问题： 开发、运维环境不一致， 版本更新

环境配置麻烦 每个机器需要部署多个软件

部分软件不支持跨平台，

java （jar ）-》 打包项目带上环境（镜像）-》 docker 仓库-》下载所需镜像--》 直接部署

docker理念： 来源于集装箱， 打包装箱， 每个箱子隔离。安全。

### Docker的 历史
2010年在美国成立的dotcloud 容器化技术， docker 2013年开源，

vm 与 docker 

vm： linux centos 原生镜像隔离， 需要开启多个虚拟机， 耗时耗空间

docker （镜像）,隔离，小巧，运行镜像就可以了


### Docker 能干嘛
虚拟机技术缺点:

- 资源占用十分多 
- 冗余技术多
- 启动很慢

容器化技术
https://javamana.com/2021/07/20210708163859824Q.html

##Docker安装
### Docker的基本组成
#### 镜像(image):
docker镜像好比一个模板,可以通过这个模板来创建容器服务,
tomcat镜像===>run ==>tomcat01容器(提供服务器)

通过这个镜像可以创建多个容器(最终服务运行或者项目运行就是在容器中的)

#### 容器(container):
Docker利用容器技术,独立运行一个或者一个组应用,通过镜像来创建的启动,停止,删除,基本命令!

#### 仓库(repository):
仓库就是存放镜像的地方!仓库分为共有仓库和私有仓库
Docker Hub(默认是国外的)
国内有阿里云、华为云都有容器服务器

####  阿里云镜像加速
- 登录阿里云服务器，找到**容器镜像服务**
- 设置Registry登录密码
- 找到镜像加速器
- 配置使用
~~~shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
     # 自己的镜像地址
  "registry-mirrors": ["https://pi9dpp60.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
~~~
### 安装 docker
- 查看是否已经存在 docker    # Docker Desktop
> 环境查看
```shell
 # 查看系统内核
uname -r

3.10.0-1062.18.1.el7.x86_64
 # 系统版本
 cat /etc/os-release
 
    NAME="CentOS Linux"
    VERSION="7 (Core)"
    ID="centos"
    ID_LIKE="rhel fedora"
    VERSION_ID="7"
    PRETTY_NAME="CentOS Linux 7 (Core)"
    ANSI_COLOR="0;31"
    CPE_NAME="cpe:/o:centos:centos:7"
    HOME_URL="https://www.centos.org/"
    BUG_REPORT_URL="https://bugs.centos.org/"
    CENTOS_MANTISBT_PROJECT="CentOS-7"
    CENTOS_MANTISBT_PROJECT_VERSION="7"
    REDHAT_SUPPORT_PRODUCT="centos"
    REDHAT_SUPPORT_PRODUCT_VERSION="7"

```
- 安装
~~~shell
# 1、 卸载旧的版本
sudo yum remove docker \
docker-client \
docker-client-latest \
docker-common \
docker-latest \
docker-latest-logrotate \
docker-logrotate \
docker-engine

#2、 需要安装的包 安装所需的软件包。yum-utils 提供了 yum-config-manager ，
# 并且 device mapper 存储驱动程序需要 device-mapper-persistent-data 和 lvm2。
yum install -y yum-utils
# 多个安装
sudo yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
  
#3、 设置镜像仓库
# 默认是国外的
yum-config-manager \
--add-repo \
https://download.docker.com/linux/centos/docker-ce.repo 

# 推荐使用使用阿里云
yum-config-manager \
--add-repo \
https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 清华大学源
 sudo yum-config-manager \
    --add-repo \
    https://mirrors.tuna.tsinghua.edu.cn/docker-ce/linux/centos/docker-ce.rep

# 4、更新yum软件包索引
yum makecache fast

# 5、安装docker   社区版本的
sudo yum install docker-ce docker-ce-cli containerd.io

# 6、 启动docker
systemctl start docker

# 7、 查看docker 版本
docker version
~~~

- 卸载docker
```shell
# 1、卸载依赖
yum remove docker-ce docker-ce-cli containerd.io

# 2、 删除资源
rm -rf /var/lib/docker

/var/lib/docker  docker的默认工作路径

```

##Docker命令
https://docs.docker.com/reference/

- 基本命令
~~~shell
#版本信息
docker version       
#详细信息  包括镜像和容器数
docker info
#命令帮助
docker [command] --help

~~~

- 镜像相关
~~~shell
#搜索镜像
docker search xxxx
# 可选项,通过收藏来过滤
--filter=stars=3000 # 搜索出来的镜像收藏就是大于3000的
#拉取镜像 
docker pull mysql[:版本号]
docker pull mysql:5.7.1
#查看镜像:-a-->查看全部；-q-->只查看id
# docker pull mysql 等价于 dicker pull docker.io/library/mysql:latest

docker images [-a/-q]
#删除指定镜像
docker rmi -f 镜像id
#删除多个镜像
docker rmi -f 镜像id 镜像id
#删除全部镜像  查询并删除
docker rmi -f $(docker images -aq)
~~~

- docker run 的底层原理
  - docker 在本地机器中寻找该镜像
  - 如果存在， 以镜像为模板生产容器示例运行
  - 如果不存在， 从 docker hub 查找镜像
  - hub 找不到 返回错误信息，找不到镜像
  - hub 存在， 下载镜像到本地， 以镜像为模板生产容器示例运行
- docker 底层原理
  - Docker Engine是一个客户端-服务器应用程序，具有以下主要组件:
    - 一个服务器，它是一种长期运行的程序，称为守护进程(dockerd命令)
    - 一个REST API，它指定程序可以用来与守护进程对话并指示它做什么的接口。


###镜像命令     
###容器命令
~~~shell

[root@localhost ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED        SIZE
hello-world   latest    feb5d9fea6a5   8 months ago   13.3kB
centos        latest    5d0da3dc9764   8 months ago   231MB
[root@localhost ~]# docker run -it centos /bin/bash
# 查看内部的 
[root@d7400fd55837 /]# ls
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@d7400fd55837 /]# 
exit 退出



docker search nginx
docker pull nginx 
docker images  
防火墙 开启
 docker run -d --name nginxtest -p 3344:80 nginx
<title>Welcome to nginx!</title>


docker exec -it nginxtest /bin/bash

~~~
###操作命令

https://javamana.com/2021/07/20210708163859824Q.html
https://blog.csdn.net/qq_21197507/article/details/115071715
https://juejin.cn/post/6978879621768937486
##Docker镜像

##容器的数据卷

##DockerFIle

##Docker网络原理

##IDEA整合Docker

##Docker Compose

##Docker Swarm

## CI\CD jenkins
