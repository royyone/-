##### MySQL启动与停止

```sql
net start mysql80 / net stop mysql80 //win+R cmd 管理员
```

##### 数据类型

```sql
#数值类型(signed有符号,unsigned无符号) int unsigned
--tinyint 1byte (-128,127) (0,255)
--smallint 2byte (-32768,32767) (0,65535)
--mediumint 3byte (-8388608,8388607) (0,16777215)
--int或 integer 4byte (-2147483648,2147483647) (0,4294967295)
--bigint 8byte (-2^63,2^63-1) (0,2^64-1)
--float 4byte 
--double 8byte                      double(5,2)
--decimal 依赖与M(精度)和D(标度)的值//123.45 M为5,D为2

#字符串类型
--char 0-255bytes 定长字符串
--varchar 0-65535 变长字符串
--二进制,文本 多用文件容器

#日期类型 大小
--date 3 1000-01-01 至 9999-12-31 日期值
--time 3 -838:59:59 至 -838:59:59 时间值或持续时间
--year 1 1901 至 2155 年份值
--datetime 8 1000-01-01 00:00:00 至 9999-12-31 23:59:59 混合日期和时间值
--timestamp 4 1970-01-01 00:00:01 至 2038-01-19 03:14:07 混合日期和时间值，时间戳
```



## DDL（去掉[ ]）

##### 数据库操作



```sql
--查询
	#查询所有数据库  show databases;
	#查询当前数据库  select database();
--创建
	create database 数据库名;
	create database [if not exists] 数据库名 [default charset 字符集] [collate 排序规则];
--删除
	drop database 数据库名;
	drop database [if exists] 数据库名;
--使用
	use 数据库名
```

##### 表操作

```sql
#查询
--查询当前数据库所有表
	show tables;
--查询表结构
	desc 表名;--show columns from 表名;--describe 表名;
--查询指定表的建表语句
	show create table 表名;
--显示广泛的数据库状态信息
	show status;
--分别用来显示创建特定数据库或表的MySQL语句
	show create database; 和 show create table;
--用于显示授予用户的安全权限
	show grants;
--用于显示服务区错误或警告消息
	show errors 和 show warnings
-----------------------------------
#创建(最后一个字段后面没有逗号)
create table 表名(
    字段1 字段1类型[comment 字段1注释],
    字段2 字段2类型[comment 字段2注释],
    ...
    字段n 字段n类型[comment 字段n注释]
)[comment 表注释];
-----------------------------------
#添加字段
alter table 表名 add 字段名 类型(长度) [comment 注释] [约束];
#修改数据类型
alter table 表名 modify 字段名 新数据类型(长度);
#修改字段名和字段类型
alter table 表名 change 旧字段名 新字段名 类型(长度) [comment 注释] [约束]
#修改表名
alter table 表名 rename to 新表名;
#删除字段
alter table 表名 drop 字段名;
#删除表
drop table [if exists] 表名;
#删除指定表，并重新创建该表
truncate table 表名;
```





## DML       

​	 数据的增删改

#####  添加 insert

```sql
#给指定字段添加数据
 insert into 表名 (字段名1,字段2,...) values (值1,值2,...);
#给全部字段添加数据
 insert into 表名 values (值1,值2,...);
#批量添加数据
 insert into 表名 (字段名1,字段2,...) values (值1,值2,...),(值1,值2,...);
 insert into 表名 values (值1,值2,...),(值1,值2,...);
```

##### 修改 updata

```sql
#修改数据
 update 表名 set 字段1=值1, 字段名2=值2,...[where 条件];
```

##### 删除delete

```sql
#删除数据
 delete from 表名 [where 条件];
```





## DQL

数据查询语言(select)

##### 基本查询

```sql
#查询多个字段
 select 字段1,字段2,字段3... from 表名;
 select * from 表名;   (所有字段,项目里不建议)
#设置别名
 select 字段1 [as 别名1], 字段2 [as 别名2]... from 表名;  (as可省略)
#去除重复记录
 select distinct 字段列表 from 表名;
```

##### 条件查询( where )

```sql
select 字段列表 from 表名 where 条件列表;
<> , !=  都为不等于; between 5 and 10   [5,10] 介于5~10之间;   字段 is null;
select 字段列表 from 表名 where 条件 or 条件;
select 字段列表 from 表名 where 条件 and 条件;
or 和 and 可以一起出现, and > or , 可用( ) 来改变顺序
select 字段列表 from 表名 where age in (19,20);in与 or作用一样,但 in更直观清除,执行速度快,更动态建立 where
select 字段列表 from 表名 where age not in (19,20);not用来否定后跟条件,mysql支持对 in,between,exists使用
```

##### 通配符过滤

```sql
#用通配符速度慢, 除非绝对有必要,否则不要放在搜索模式的开始处,搜索起来是最慢
like 操作符(谓词); % 通配符:在给定位置匹配0,1,多个字符; (下划线) _ 通配符: 匹配一个字符;
select name from cy where name like '%斌%';
select name from cy where name like '陈_';
```

##### 正则表达式 ( regexp )

```sql

```

##### 聚合函数

```sql
select 聚合函数 (字段列表) from 表名;
count 统计数量, max, min, avg 平均值, sum 求和
```

##### 分组查询

``` sql
select 字段列表 from 表名 [where 条件] group by 分组字段名 [having 分组后过滤条件];
// where 不能用聚合, having 可以用聚合函数
// 执行顺序 where > 聚合 > having
```

##### 排序查询

```sql
select 字段列表... from 表名 order by 字段1 排序方式1 , 字段2 排序方式2 [limit 1(最...)];
asc 升序(默认值)   ,   desc 降序
```

##### 分页查询

```sql
select 字段列表 from 表名 limit 起始索引, 查询记录数;
起始索引从0开始(起始索引可忽略),起始索引 = (查询页码 - 1) * 每页显示记录数
如果查询的是第一页数据,起始索引可以忽略,直接简写为 limit 10
```

```sql
from > where > group by > having > select > order by > limit
```



## DCL

管理数据库 用户 , 控制数据库的访问 权限

##### 管理用户

```sql
#查询用户
use mysql;
select * from user;
#创建用户
create user '用户名'@'主机名' identified by '密码';
#修改用户密码
alter user '用户名'@'主机名' identified with mysql_native_password by '新密码';
#删除用户  
drop user '用户名'@'主机名';
```

##### 权限控制

```sql
#查询权限
show grants for '用户名'@'主机名';
#授予权限
grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';
#撤销权限
revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';
---------------------------------------------------------
all,all privileges 所有权限
select             查询数据
insert             插入数据
update             修改数据
delete             删除数据
alter              修改表
drop               删除数据库/表/视图
create             创建数据库/表
```

## 函数

##### 字符串函数

```sql
select concat(S1,S2,...Sn)     #字符串拼接
lower(str)              #转为小写
upper(str)              #转为大写
lpad(str,n,pad)         #左填充,用字符串pad对str的右边进行填充,达到n个字符串长度(用于编号)
rpad(str,n,pad)         #右填充
trim(str)               #去掉字符串头尾的空格
substring(str,start,len)#返回从字符串str从start位置起的len个长度的字符串
```

##### 数值函数

```sql
ceil(x)       #向上取整
floor(x)      #向下取整
mod(x,y)      #返回x/y的模
rand()        #返回0~1内的随机数
round(x,y)    #求参数x的四舍五入的值，保留y位小数
```

##### 日期函数

```sql
curdate()    #返回当前日期
curtime()    #返回当前时间
now()        #返回当前日期和时间
year(date)   #获取指定date的年份
month(date)  #获取指定date的月份
day(date)    #获取指定date的日期
date_add(date,interval expr type)#返回一个日期/时间值加上一个时间间隔expr后的时间值(type->year,moth,day)
datediff(date1,date2)#返回起始时间date1和结束时间date2之间的天数
```

##### 流程函数

```sql
if(value,t,f) #如果value为true,则返回t,否则返回f
ifnull(value1,value2) #如果v1不为空(null),返回v1,否则返回v2
case when [val1] then [res1]...else [default] end   #如果v1为true,返回res1,...否则返回default默认值
case [expr] when [val1] then [res1]...else [default] end #如果expr的值等于v1,返回res1,...否则返回default默认值
```

## 约束

```sql
primary key      主键
auto_increment   自动增长
not null         不为空
unique           唯一
check            给定范围
default          默认值
```

##### 外键约束

```sql
#添加外键
create table 表名(
    字段名 数据类型,
    ...
    [constraint] [外键名称] foreign key (外键字段名) references 主表(主表列名)
);
alter table 表名 add constraint 外键名称 foreign key (外键字段名) references 主表 (主表列名);
#删除外键
alter table 表名 drop foreign key 外键名称;
#删除/更新行为
no action / restrict 一致:当在父表中删除/更新对应记录时,首先检查该记录是否有对应外键,如果有则不允许删除/更新
cascade 当在父表中删除/更新对应记录时,首先检查该记录是否有对应外键,如果有,则也删除/更新外键在子表中的记录
set null 当在父表中删除对应记录时,首先检查该记录是否有对应外键,如果有则设置子表中该外键值为null(这就要求该外键允许取null)
set default 父表有变更时,子表将外键列设置成一个默认的值(innodb不支持)

alter table 表名 add constraint 外键名称 foreign key (外键字段) references 主表名(主表字段名) on update 行为(如 cascade) on delete 行为(如 cascade)
```



## 多表查询

##### 连接查询

###### 内连接

```sql
#内连接查询的是两张表交集的部分
--隐式内连接
	select 字段列表 from 表1,表2 where 条件...;
--显示内连接
	select 字段列表 from 表1 [inner] join 表2 on 连接条件...;
```

###### 外连接

```sql
--左外连接:相当于查询表1(左表)的所有数据,包含表1和表2交集部分的数据
	select 字段列表 from 表1 left [outer] join 表2 on 条件...;
--右外连接:相当于查询表2(右表)的所有数据,包含表1和表2交集部分的数据
	select 字段列表 from 表1 right [outer] join 表2 on 条件...;
```

###### 自连接

```sql
select 字段列表 from 表A 别名A join 表A 别名 表B on 条件...;
自连接查询,可以是内连接查询,也可以是外连接查询
```

###### 联合查询

```sql
select 字段列表 from 表A...
union [all]
select 字段列表 from 表B...;
对于联合查询的多张表的列数必须保持一致,字段类型也需要保持一致
union all 会将全部的数据直接合并在一起,union 会对合并之后的数据去重
```



##### 子查询( 嵌套查询 )

```sql
select * from t1 where column1 = (select column1 from t2);
子查询外部的语句可以是 insert / update / delete / select 
#根据子查询结果不同
--标量子查询(子查询结果为单个值[数字、字符串、日期等])
--列子查询(子查询结果为一列)
	in 在指定的集合范围内,多选一
	not in 不在指定的集合范围之内
	any / some 子查询返回列表中,有任意一个满足即可
	all 子查询返回列表的所有值都必须满足
--行子查询(子查询结果为一行)   (name,age)=(...)
--表子查询(子查询结果为多行多列) (name,age) in (...)
#根据子查询位置,分为:where之后、from之后、select之后
```

## 事务 

一组操作的集合，不可分割，要么同时成功，要么同时失败(银行转账)

默认MySQL的事务是自动 ( 隐式 ) 提交的

##### 事务操作

```sql
#查看/设置事务提交方式
select @@autocommit;(查看为1,自动提交;  为0,手动提交)
set @@autocommit = 0; (设置为1 or 0)
#提交事务
commit; (为0的时候运行指令后需输入该指令来提交事务)
#回滚事务
rollback;
#开启事务
start transaction 或 begin; (为1的时候使用,提示要进行手动提交)
```

##### 事务四大特性 ( ACID )

```sql
原子性(Atomicity):事务是不可分割的最小操作单元,要么全部成功,要么全部失败
一致性(Consistency):事务完成时,必须使所有的数据都保持一致状态
隔离性(Isolation):数据库系统提供的隔离机制,保证事务在不受外部并发操作影响的独立环境下运行
持久性(Durability):事务一旦提交或回滚,它对数据库中的数据的改变使永久的
```

##### 并发事务问题

```sql
脏读:一个事务读到另一个事务还没提交的数据
不可重复读:一个事务先后读取同一条记录,但两次读取的数据不同
幻读:一个事务按照条件查询数据时,没有对应的数据行,但在插入数据时,又发现这行数据已经存在,好像出现了" 幻影 "
```

##### 事务隔离级别

```sql
--事务隔离级别越高,数据越安全,但是性能越低
read uncommitted 读未提交 #会出现脏读,不可重复读,幻读
read committed 读已提交 #解决脏读--会出现不可重复读,幻读
repeatable read(MySQL默认) 可重复读 #解决脏读,不可重复读--会出现幻读
serializable 可串行化(只有一个事务执行完之后才能执行另一个事务) #解决脏读,不可重复读,幻读
---------------------------------------------------
--查看事务隔离级别
select @@transaction_isolation;
--设置事务隔离级别
set [session|global] transaction isolation level {read uncommitted | read committed | repeatable read | serializable}
```



*

```sql
mysql> create table g
    -> (
    -> 　　id int primary key auto_increment,
    -> 　　name varchar(20),
    -> 　　num int
    -> );
 INSERT INTO g (name, num) VALUES ('123', 20), ('321', 30);//
```

