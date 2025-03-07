# 基础镜像，使用 OpenJDK 17
FROM openjdk:17
# 设置工作目录
WORKDIR /app
# 将打包好的 JAR 文件复制到工作目录
COPY target/PJ1-0.0.1-SNAPSHOT.jar /app/app.jar
# 暴露应用端口
EXPOSE 8014
# 定义环境变量，用于配置数据库连接
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=db_pj_dining
ENV DB_USER=root
ENV DB_PASSWORD=123456
# 容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]