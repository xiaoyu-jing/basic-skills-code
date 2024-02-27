###项目解决问题
打包容易打错
效率不够高
占用人力资源

###设计功能目标
1. 版本控制

2. 自动化发布
3. 能够和edas环境对接
4. 能够和云环境对接
5. 选择待发布信息
   1. 版本 名字 分支
6. 选择发布的环境
7. 能够保存发布履历
8. 能够保存发布产物

### 开发环境搭建

#### 环境依赖

mysql数据库（可以docker实现）
edas控制台

节点配置中心（运营平台子系统）

#### 步骤

1. 安装docker(可选)
   1. 下载地址 Windows https://download.docker.com/win/stable/InstallDocker.msi
   2. 下载地址 mac https://download.docker.com/mac/stable/Docker.dmg
   3. 安装完成docker之后，在项目跟目录下执行docker-compose up ，即可启动项目程序程序，(可选)

2. 创建数据库
   1. 主要数据库脚本如下

      1. CREATE DATABASE IF NOT EXISTS xservices_deploy DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

   2. 配置数据库链接

      1. 多个数据库链接通过maven 的profile 切换。

         1. 默认启用的配置项是 dev-localhost

            1. 默认已经配置了docker化数据库的帐号密码

         2. 或者根据自己需要配置

            1. ```
               <spring.datasource.driver-class-name>com.mysql.jdbc.Driver</spring.datasource.driver-class-name>
               <spring.datasource.url>jdbc:mysql://localhost:3306/xservices_deploy</spring.datasource.url>
               <spring.datasource.username>root</spring.datasource.username>
               <spring.datasource.password>q1w2e3r4</spring.datasource.password>
               <spring.datasource.validation-query>SELECT 1</spring.datasource.validation-query>
               ```

3. (首次启动)执行 mvn flyway:baseline

4. 执行mvn install

5. 执行mvn spring-boot:run 

6. 待日志执行完毕，访问docker环境的8080端口，就可以看到项目运行界面

