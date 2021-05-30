#!/bin/bash
# 参数说明：
# $0 -- 当前脚本的文件名;
# $n -- 传递给脚本或函数的参数。n 是一个数字，表示第几个参数。例如，第一个参数是$1，第二个参数是$2
# $# -- 传递给脚本或函数的参数个数。
# $* -- 传递给脚本或函数的所有参数。
# $@ -- 传递给脚本或函数的所有参数。被双引号(" ")包含时，与 $* 稍有不同
# $? -- 上个命令的退出状态，或函数的返回值。
# $$ -- 当前Shell进程ID。对于 Shell 脚本，就是这些脚本所在的进程ID。
#
# -f 存在且是普通文件; -d 存在且是目录; -s 存在且字节数大于0; -r 存在且可读; -w 存在且可写; -x 存在且可执行
# -z表示后面的值是否为空，为空则返回true，否则返回false。
# -n表示判断后面的值是否为空，不为空则返回true，为空则返回false。
#
#更新版本号
echo "更新版本号"
versionNum=$1
if [[ $# -eq 0 ]]; then
	echo "版本号为空"
 	exit 1
fi
echo "要更新的版本号为：$versionNum"
cd ..
echo "先通过mvn命令修改版本号"
mvn versions:set -DnewVersion=$versionNum
mvn -N versions:update-child-modules
mvn versions:commit
echo "命令修改版本号【结束】"
