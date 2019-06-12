spring-cloud-gateway
====

**2019.5.29** ~~修订信息~~
* 网关简单token拦截
* 网关跨域配置
* 令牌通限流

~~使用方法~~
* token拦截
    * 当前配置的是有无token验证，请求格式：http://localhost:8000/client/hello?token=1111111
* 网关跨域配置
    * 当前配置的是放开所有跨域
* 令牌通限流
    * 集成redis，详细看yml配置
        * 测试：将yml里的令牌桶数量和每秒处理数量都置为1，多次请求同一接口即可看到效果

~~可能出现问题~~
* 不加token路由会报错，暂未处理异常返回
* 得下载redis安装并启动即可
* 若出现找不到类请先检查jar是否全部下载完毕，unkown则表示jar无效，可在idea右边maven的Dependencies中查看，若出现unkown请先删除本地jar再更新pom，若尝试多次下载不下来请至maven公共库搜索对应jar并选择最多使用的版本替换pom里的相关Jar即可

**2019.6.12** ~~修订信息~~
* token无效返回处理
* 优化部分配置