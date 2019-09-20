操作说明：
0、在jar包中解压找到 work/icql/account/App.properties 和 work/icql/account/script/run.bat，复制到jar同路径下
1、jar包 和 App.properties 文件名均不可修改
2、App.properties文件用来设置数据：
                                jdbcUrl=ekp数据库url
                                dbUser=ekp数据库用户名
                                dbPassword=ekp数据库用户密码
                                addUserCodes=需要新增的工号，多个工号用,分割
                                opUserCode=ekp登录账号
                                opPassword=ekp登录密码
3、双击run.bat开始执行，同目录下会生成当前时间戳文件名的log日志
4、设置内容有：新增用户，修改用户的邮件名，邮件地址，密码（xmjlA+工号）

开发说明：
0、使用maven-assembly-plugin插件将代码打包成jar（包含文档）
1、需要修改 account-1.0.0.jar/META-INF/MANIFEST.MF 文件最后一行为 Main-Class: work.icql.account.App
2、可以直接在pom文件中修改
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>${maven-assembly-plugin.version}</version>
    <configuration>
        <appendAssemblyId>false</appendAssemblyId>
        <classifier>xmjltool-account-1.0.0</classifier>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <archive>
            <manifest>
                <mainClass>work.icql.account.App</mainClass>
            </manifest>
        </archive>
    </configuration>
    <executions>
        <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
                <goal>assembly</goal>
            </goals>
        </execution>
    </executions>
</plugin>
3、运行请看操作说明