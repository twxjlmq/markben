#!/bin/bash
# 打包，所有模块打成jar包，并发布到maven仓库中

cd ..
echo "清空打包信息"
mvn clean

echo "开始打包..."
mvn -Dmaven.test.skip=true deploy
echo "整体打包结束"

