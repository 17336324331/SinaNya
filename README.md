---
description: 基于酷Q的，使用Java语言开发的，面向COC/DND跑团的骰子机器人组件
---

# SinaNya

## 前言

大家好，我是开发者斯塔尼亚

对这个工程感兴趣，或者搭建遇到问题的朋友可以加群162279609咨询

也许来到这个页面的很多人都是COC/DND跑团的玩家，因此也自然知道，目前已经有溯洄大佬开发的骰子插件被广泛使用了。

然而，溯洄大佬开发的语言是C++，而我个人更擅长Java，这导致我在修改溯洄大佬代码尝试添加自定义功能时遇到一系列的困难。

最终，我选择了基于法欧特桑大佬开发的JavaApi构建新的骰子机器人程序。

## 基于Java程序的优点与缺点

### 缺点

我们先来谈谈坏处吧，目前有几个明显的问题存在

#### 效率问题

很显然，Java在运行效率上完全无法与C++比较，即使使用了多线程后，回复速度仍然比C++版本普遍慢0.1到0.5秒。 好在，0.1~0.5秒仍在大家的可接受范围内

#### api缺失

有些酷Q的API无法使用，好在缺失的API并不是很致命

* 可以获取群号，但无法获得群名
* 无法拦截消息阻止优先级低的其它应用处理

#### 部署略微繁琐

因为本插件需要使用佐天泪子亲卫队大佬开发的Lemoc作为中间件，因此需要先在酷Q的app目录下放置lemoc.cpk并开启服务，然后再启动本服务才算做开启

好在，放置Lemoc.cpk并开启服务是一次性工作，之后更新等操作只需要重启本服务即可。

#### 无法获取到异常关闭

有些朋友可能安装了识别酷Q是否崩溃的组件，但由于本服务是独立的Java进程，所以即使本服务崩溃，酷Q仍然会正常运行。

好在本服务崩溃可能性较低，无需担心

### 优点

现在可以来谈谈优点了

#### 本机debug

标准的酷Q开发流程需要将服务打包为.cpk文件后放给酷Q然后执行，期间发生的报错和运行中流转情况是无法知晓的

而本服务由于只是对接Lemoc端口，因此您甚至可以在本机的IDE中开启服务后，连接到远程服务器上的酷Q-lemoc端口进行debug调试，极大地提升了解决问题的效率和准确性

#### 配置文件

由于本服务无需打包为cpk，因此可以调用指定目录下的配置文件。对于很多不会进行打包，完全不懂开发的人来说，只需要修改文本文件即可完成一大部分的自定义回复语修改

#### 日志打印

由于是Java，可以打印更为详细的log4j日志到指定目录下，可以直观的看到那个类的哪一行中，哪个变量发生了什么错误，前面是如何一步步调用过来的。

#### 跨平台性

众所周知，Java在跨平台性方面非常优秀。因此这个程序可以直接在Linux服务器运行，而我们同时知道，Linux服务器在同性能下的性能和稳定性比windows服务器要好很多。当然，Linux可能需要你有一定的电脑基础和钻研能力，并在第一次布置时耗费你的一定时间。

**但无论如何，我希望你读完所有教程后再购买服务器**

综合而言就是你可以买一台更便宜的Linux服务器做骰子了

## 准备开始

在开始之前，为了方便帮助大家，我需要先向大家了解一下需求:

1. [您是一个新手，不太想自己写任何代码，只是单纯的想做一个自己的骰子机器人。当然，如果能自定义一些个性回复词那就更好了](https://sitcnya.gitbook.io/sinanya/da-jian-shuo-ming/wo-shi-yi-ming-xin-shou)
2. [您是一位开发者，除了自定义回复词之外您还想要增加自己独有的功能。](https://sitcnya.gitbook.io/sinanya/da-jian-shuo-ming/wo-shi-yi-wei-kai-fa-zhe)

