## 一、原生 JDBC

````java
import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //数据库连接地址
        String url = "jdbc:mysql://localhost:3306/hello";
        String user = "root";//用户名
        String password = "root";//密码
        // 1.注册数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取数据库连接对象Connection。
        Connection conn = DriverManager.getConnection(url, user, password);
        //3.创建Sql语句对象Statement，填写SQL语句
        PreparedStatement preparedStatement = conn.prepareStatement("select * from t_user where name=?;");
        //传入查询参数
        preparedStatement.setString(1,"刘备【1】");
        //4.执行SQL查询，返回结果集对象ResultSet
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        //5.循环解析结果集，获取查询用户list集合
        while (resultSet.next()){
            User u = User.builder().id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .address(resultSet.getString("address"))
                    .build();
            users.add(u);
        }
        //打印查询结果
        System.out.println(users);
        //6.关闭连接，释放资源
        resultSet.close(); //关闭结果集对象
        preparedStatement.close(); //关闭Sql语句对象
        conn.close();//关闭数据库连接对象
    }
}
````

​	JDBC中代码问题分析：

​		1、结果集解析复杂，列名硬编码，sql变化导致解析代码变化，系统不易维护。

​		2、Sql语句硬编码，难以维护。 数据库配置硬编码。

​		3、频繁连接、释放数据库资源，降低系统性能。

​		4、preparedStatement向占位符号传参数存在硬编码，不易维护。

## 二、为什么用 Mabatis

​	1、可以解决 JDBC 的上述问题

​	2、在大厂中，会使用 CICD 流水线，可以将 Mybatis XML 中的 sql 发送给 DBA，DBA 审核 sql 执行情况，如果不满足要求，打回重写，如果使用 ORM 框架，则很难达到这种效果。

​	3、原生 Mybatis 用法

````java
// 创建 SqlSessionFactoryBuilder
SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
// 创建 SqlSessionFactory
SqlSessionFactory sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
// 获取 SqlSession
SqlSession sqlSession = sqlSessionFactory.openSession();
// 获取代理类
UserMapper mapper = session.getMapper(UserMapper.class); 
// 调用对应方法
List<User> userList = mapper.findAll();
````

## 三、Mybatis 源码概述

### （一）类概述

​	**1、四大组件**

​		（1）SqlSession：顶层API接口，对外暴露接口对数据库进行增删改查操作。通过会话工厂对象创建默认实现类是DefaultSQLSession类 

​		（2）Executor：执行器，是MyBatis的核心，负责SQL动态语句的生成 和 查询缓存的维护

​		（3）StatementHandler：负责处理JDBC的Statement的交互

​				ParameterHandler：负责参数设置

​				ResultSetHandler：负责结果集映射

​		（4）TypeHandler：负责JdbcType到JavaType之间的数据转换

​	**2、三个封装类**

​		（1）Configuration：封装全局配置文件和映射文件中的信息

​		（2）BoundSql：封装解析之后的SQL语句和解析出来的参数信息

​		（3）MappedStatement：封装select|insert|update|delete等标签信息，一个标签对应一个MappedStatement对象，包含入参和出参类型信息

				- Executor通过MappedStatement在执行sql前，将输入java对象属性映射至SQL中，输入参数映射就是jdbc编程中对preparedStatement设置参数
				- Executor通过Mapped Statement在执行sql后，将输出结果映射至java对象属性中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程

​	**3、其他相关类**

​		（1）SqlSessionFactoryBuilder：构建SqlSessionFactory

​		（2）SqlSessionFactory接口：默认实现类是DefaultSQLSessionFactory类

​		（3）XMLConfigBuilder：专门用来解析全局配置文件的解析器

​		（4）XMLMapperBuilder：专门用来解析映射文件的解析器

​		（5）SqlSource接口：SqlSource用于描述SQL资源，与动态SQL、#()和$()表达式相关

​					ProviderSqlSource：用于描述通过@Select、@SelectProvider等注解配置的SQL资源信息。

​					DynamicSqlSource：用于描述Mapper XML文件中配置的SQL资源信息，这些SQL通常包含动态SQL配置或者${}参数占位符，需要在Mapper调用时才能确定具体的SQL语句。

​					RawSqlSource：用于描述Mapper XML文件中配置的SQL资源信息，与DynamicSqlSource不同的是，这些SQL语句在解析XML配置的时候就能确定，即不包含动态SQL相关配置。

​					StaticSqlSource：用于描述ProviderSqlSource、DynamicSqlSource及RawSqlSource解析后得到的静态SQL资源。	

​		（6）SqlNode 接口

​				IfSqlNode：用于描述动态SQL中标签

​				ChooseSqlNode：用于描述动态SQL配置中的标签

​				ForEachSqlNode：用于描述动态SQL配置中的标签

​				SetSqlNode：用于描述动态SQL配置中的标签

​				WhereSqlNode：用于描述动态SQL中的标签

​				TrimSqlNode：用于描述动态SQL中的标签

​				VarDeclSqlNode：用于描述动态SQL中的标签

​				StaticTextSqlNode：用于描述动态SQL中的静态文本内容，不含占位符

​				TextSqlNode：该类与StaticTextSqlNode类不同的是，SQL包含${}占位符时，用TextSqlNode描述。

​				MixedSqlNode：用于描述一组SqlNode对象，通常一个Mapper配置是由多个SqlNode对象组成的，这些SqlNode对象通过MixedSqlNode进行关联，组成一个完整的动态SQL配置

### （二）代码执行流程

**1、创建 SqlSessionFactoryBuilder**

````java
// 创建 SqlSessionFactoryBuilder
SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
// 创建 SqlSessionFactory
SqlSessionFactory sqlSessionFactory = sessionFactoryBuilder.build(inputStream);
````

​		（1）使用 SqlSessionFactoryBuilder 读取读取全局配置文件，生成全局配置文件 Configuration

​				使用 XMLConfigBuilder 解析全局的配置信息，例如数据库驱动、数据库地址、数据库用户名密码等

​				使用 XMLMapperBuilder 解析每一个 Mapper 的 XML 文件，解析成为一个个 MappedStatement

​			因此 Configuration 中有主配置文件，还有所有的 MappedStatement

​		（2）解析 Mapper 的 XML  生成 MappedStatement

​				使用 namespace + method 生成 Key

​				然后解析 sql 标签，将其解析成一个个 SqlSource，SqlSource 有 DynamicSqlSource、RawSqlSource、StaticSqlSource，其中直接可以确定的 sql 内容解析为 RawSqlSource，对于有动态 sql 标签的，解析为 DynamicSqlSource，DynamicSqlSource 中包含的是一个个的 SqlNode，每一种动态 sql 标签都会封装成为一个个的 SqlNode。

​				最后将所有的 SqlSource 封装成 BoundSql，放入 MappedStatement

**2、获取代理类**

````java
// 获取 SqlSession
SqlSession sqlSession = sqlSessionFactory.openSession();
// 获取代理类
UserMapper mapper = session.getMapper(UserMapper.class); 
````

​	使用 JDK 动态代理生成对应 Mapper 的代理类

**3、调用方法执行**

````java
// 调用对应方法
List<User> userList = mapper.findAll();
````

​		（1）调用代理类的对应方法

​		（2）根据 代理类的 package + method 组成 key，然后从全局配置文件 Configuration 中根据 key 获取到对应的 MappedStatement，然后根据参数组装sql

​		（3）组装 sql 流程

​				根据输入参数，将动态 sql （SqlNode）的内容确定，将确定的 sql 和 RawSqlSource 组装成静态 sql：StaticSqlSource

​		（4）调用 Executor 执行

​				调用 ParameterHandler 设置参数，设置参数时调用 TypeHandler 将 JavaType 转换为 JdbcType

​				使用 jdbc 执行 sql，执行完 sql 获取 Result

​				调用 ResultSetHandler 做结果集映射，映射时调用 TypeHandler 将 JdbcType 转换为 JavaType

## 四、Mybatis 源码深入的几个点

### （一）延迟加载

​		Mybatis 仅支持 association 关联对象和 collection 关联集合对象的延迟加载。其中，association 指的就是一对一，collection 指的就是一对多查询。

​		在 Mybatis 配置文件中，可以配置 `<setting name="lazyLoadingEnabled" value="true" />` 来启用延迟加载的功能。默认情况下，延迟加载的功能是关闭的。

​		它的原理是，使用 CGLIB 或 Javassist( 默认 ) 创建目标对象的代理对象。当调用代理对象的延迟加载属性的 getting 方法时，进入拦截器方法。比如调用 `a.getB().getName()` 方法，进入拦截器的 `invoke(...)` 方法，发现 `a.getB()` 需要延迟加载时，那么就会单独发送事先保存好的查询关联 B 对象的 SQL ，把 B 查询上来，然后调用`a.setB(b)` 方法，于是 `a` 对象 `b` 属性就有值了，接着完成`a.getB().getName()` 方法的调用。这就是延迟加载的基本原理。

### （二）动态sql

​		动态 sql 的实现原理在上面已经分析，使用的是动态 SqlSource：DynamicSqlSource，以及对应的 SqlNode，当参数确定时，可以组装成为 StaticSqlSource

### （三）一二级缓存

**一级缓存：**

​		每个SqlSession中持有了Executor，每个Executor中有一个LocalCache。当用户发起查询时，MyBatis根据当前执行的语句生成`MappedStatement`，在Local Cache进行查询，如果缓存命中的话，直接返回结果给用户，如果缓存没有命中的话，查询数据库，结果写入`Local Cache`，最后返回结果给用户。

​		一级缓存。共有两个选项，`SESSION`或者`STATEMENT`，默认是`SESSION`级别，即在一个MyBatis会话中执行的所有语句，都会共享这一个缓存。一种是`STATEMENT`级别，可以理解为缓存只对当前执行的这一个`Statement`有效。

```xml
<setting name="localCacheScope" value="SESSION"/>
```

​		总结：			

​			MyBatis一级缓存的生命周期和SqlSession一致。

​			MyBatis一级缓存内部设计简单，只是一个没有容量限定的HashMap，在缓存的功能性上有所欠缺。

​			MyBatis的一级缓存最大范围是SqlSession内部，有多个SqlSession或者分布式的环境下，数据库写操作会引起脏数据，建议设定缓存级别为Statement。

**二级缓存：**

​		开启二级缓存后，会使用CachingExecutor装饰Executor，进入一级缓存的查询流程前，先在CachingExecutor进行二级缓存的查询	

​		二级缓存开启后，同一个namespace下的所有操作语句，都影响着同一个Cache，即二级缓存被多个SqlSession共享，是一个全局的变量。

​		当开启缓存后，数据的查询执行的流程就是 二级缓存 -> 一级缓存 -> 数据库。

​		二级缓存配置

​			要正确的使用二级缓存，需完成如下配置的。

​			（1）在MyBatis的配置文件中开启二级缓存。

```xml
<setting name="cacheEnabled" value="true"/>
```

​			（2）在MyBatis的映射XML中配置cache或者 cache-ref 。cache标签用于声明这个namespace使用二级缓存，并且可以自定义配置。

```xml
<cache/>   
```

​					`type`：cache使用的类型，默认是`PerpetualCache`，这在一级缓存中提到过。

​					`eviction`： 定义回收的策略，常见的有FIFO，LRU。

​					`flushInterval`： 配置一定时间自动刷新缓存，单位是毫秒。

​					`size`： 最多缓存对象的个数。

​					`readOnly`： 是否只读，若配置可读写，则需要对应的实体类能够序列化。

​					`blocking`： 若缓存中找不到对应的key，是否会一直blocking，直到有对应的数据进入缓存。

​			`cache-ref`代表引用别的命名空间的Cache配置，两个命名空间的操作使用的是同一个Cache。

```xml
<cache-ref namespace="mapper.StudentMapper"/>
```

​		总结：

​			MyBatis的二级缓存相对于一级缓存来说，实现了`SqlSession`之间缓存数据的共享，同时粒度更加的细，能够到`namespace`级别，通过Cache接口实现类不同的组合，对Cache的可控性也更强。

​			MyBatis在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻。

​			在分布式环境下，由于默认的MyBatis Cache实现都是基于本地的，分布式环境下必然会出现读取到脏数据，需要使用集中式缓存将MyBatis的Cache接口实现，有一定的开发成本，直接使用Redis、Memcached等分布式缓存可能成本更低，安全性也更高。

### （四）枚举映射

​	**1、内置枚举转换器**

​		内置枚举转换器有 EnumTypeHandler 和 EnumOrdinalTypeHandler，其中 EnumTypeHandler 是默认的枚举转换器，该转换器将枚举实例转换为实例名称的字符串，即将 SexEnum.MAN 转换 MAN，EnumOrdinalTypeHandler 将枚举实例的 ordinal 属性作为取值，即 SexEnum.MAN 转换为 0, SexEnum.WOMAN转换为 1

​		内置枚举转换器使用：mybatis-config.xml
````xml
<typeHandlers>
	<typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.answer.aal.entity.StatusEnum" />
	<typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.answer.aal.entity.SexEnum" />
</typeHandlers>
````

​	**2、自定义枚举转换器**

​		（1）BaseTypeHandler 抽象类

````java
public abstract class BaseTypeHandler<T> extends TypeReference<T> implements TypeHandler<T> {

// ...

  /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
     * */
  public abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) 
						throws SQLException;

  /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
  public abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

  /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
  public abstract T getNullableResult(ResultSet rs, int columnIndex) throws SQLException;

  /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     * */
  public abstract T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException;

}
````

​		（2）枚举接口

````java
public interface IEnum<E extends Enum<?>, T> {
    T getValue();
}
````

​		（3）枚举

````java
public enum SexEnum implements IEnum<EducationEnum, Integer> {

    /** 男 */
    MAN(0),

    /** 女 */
    WOMAN(1);

    private int value;

    SexEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
````

````java
public enum EducationEnum implements IEnum<EducationEnum, String> {

    /** 小学 */
    PRIMARY_SCHOOL("PRIMARY"),

    /**
     * 初中
     * */
    JUNIOR_SCHOOL("JUNIOR"),

    /**
     * 高中
     * */
    HIGH_SCHOOL("HIGH"),

    /**
     * 大学
     * */
    UNIVERSITY_SCHOOL("UNIVERSITY")
    ;

    private String value;

    EducationEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
````

​		（4）自定义枚举转换器

````java
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@MappedTypes(value = {SexEnum.class, EducationEnum.class})
public class BaseEnumTypeHandler<E extends Enum<E> & IEnum> extends BaseTypeHandler<E> {

    /**
     * 枚举的class
     */
    private Class<E> type;
    /**
     * 枚举的每个子类枚
     */
    private E[] enums;

    /**
     * 一定要有默认的构造函数, 不然抛出 not found method 异常
     */
    public BaseEnumTypeHandler() {
    }

    /**
     * 设置配置文件设置的转换类以及枚举类内容, 供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        }
    }

    /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
     * */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
                                    JdbcType jdbcType) throws SQLException {
        /*
         * BaseTypeHandler已经帮我们做了parameter的null判断
         * 数据库存储的是枚举的值, 所以我们这里使用 value ,  如果需要存储 name, 可以自定义修改
         */
        if (jdbcType == null) {
            ps.setString(i, Objects.toString(parameter.getValue()));
        } else {
            ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String i = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String i = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     * */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String i = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换
     * 
     * @param value 数据库中存储的自定义value属性
     * @return value 对应的枚举类
     */
    private E locateEnumStatus(String value) {
        for (E e : enums) {
            if (Objects.toString(e.getValue()).equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对"
                + type.getSimpleName());
    }

}
````

​		注意： 如果使用了 `@MappedTypes` 注解， 需要在 `application.properties` 中添加如下配置

````properties
# 类型处理器类所在的包路径. eg: BaseEnumTypeHandler
mybatis.type-handlers-package=com.answer.aal.entity
````

​		否则， 直接在 mybatis 配置文件 `mybatis-config.xml` 添加如下配置

````xml
<typeHandlers>
	<typeHandler handler="com.answer.aal.entity.BaseEnumTypeHandler" javaType="com.answer.aal.entity.EducationEnum" />
	<typeHandler handler="com.answer.aal.entity.BaseEnumTypeHandler" javaType="com.answer.aal.entity.SexEnum" />
</typeHandlers>
````

​		**3、Mybatis-Plus 通用枚举**

​		（1）步骤1: 声明通用枚举属性

> 方式一： 使用 @EnumValue 注解枚举属性

```java
public enum GradeEnum {

    PRIMARY(1, "小学"),  SECONDORY(2, "中学"),  HIGH(3, "高中");

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;
    //。。。
}
```

> 方式二： 枚举属性，实现 IEnum 接口如下：

```java
public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    private int value;
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
```

> 实体属性使用枚举类型

```java
public class User {
    /**
     * 名字
     * 数据库字段: name varchar(20)
     */
    private String name;

    /**
     * 年龄，IEnum接口的枚举处理
     * 数据库字段：age INT(3)
     */
    private AgeEnum age;


    /**
     * 年级，原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库字段：grade INT(2)
     */
    private GradeEnum grade;
}
```

​		（2）步骤2-配置扫描通用枚举)步骤2: 配置扫描通用枚举（**从 3.5.2 开始无需配置**）

​			方式一：仅配置指定包内的枚举类使用 MybatisEnumTypeHandler

> 配置文件 resources/application.yml

```yml
mybatis-plus:
    # 支持统配符 * 或者 ; 分割
    typeEnumsPackage: com.baomidou.springboot.entity.enums
  ....
```

​				当添加这个配置后，mybatis-plus 提供的 `MybatisSqlSessionFactoryBean` 会自动扫描包内合法的枚举类（使用了 `@EnumValue` 注解，或者实现了 `IEnum` 接口），分别为这些类注册使用 `MybatisEnumTypeHandler`。

​				换句话说，只有指定包下的枚举类会使用新的 TypeHandler。其他包下，或者包内没有做相关改造的枚举类，仍然会使用 mybatis 的 DefaultEnumTypeHandler。

​		方式二：直接指定 DefaultEnumTypeHandler

​				此方式用来 `全局` 修改 mybatis 使用的 EnumTypeHandler。

> 配置文件 resources/application.yml

```yml
mybatis-plus:
    # 修改 mybatis 的 DefaultEnumTypeHandler
    configuration:
        default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
```

> 自定义配置类 MybatisPlusAutoConfiguration

```java
@Configuration
public class MybatisPlusAutoConfiguration {

    @Bean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return properties -> {
            GlobalConfig globalConfig = properties.getGlobalConfig();
            globalConfig.setBanner(false);
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setDefaultEnumTypeHandler(MybatisEnumTypeHandler.class);
            properties.setConfiguration(configuration);
        };
    }
}
```

### （五）分页插件实现

**插件原理：**

​	MyBatis 允许你在映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：

​		Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)

​		ParameterHandler (getParameterObject, setParameters)

​		ResultSetHandler (handleResultSets, handleOutputParameters)

​		StatementHandler (prepare, parameterize, batch, update, query)

​		这些类中方法的细节可以通过查看每个方法的签名来发现，或者直接查看 MyBatis 发行包中的源代码。 如果你想做的不仅仅是监控方法的调用，那么你最好相当了解要重写的方法的行为。 因为在试图修改或重写已有方法的行为时，很可能会破坏 MyBatis 的核心模块。 这些都是更底层的类和方法，所以使用插件的时候要特别当心。

​		通过 MyBatis 提供的强大机制，使用插件是非常简单的，只需实现 Interceptor 接口，并指定想要拦截的方法签名即可。

```java
// ExamplePlugin.java
@Intercepts({@Signature(type= Executor.class, method = "update", args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {
  private Properties properties = new Properties();

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    // implement pre processing if need
    Object returnObject = invocation.proceed();
    // implement post processing if need
    return returnObject;
  }

  @Override
  public void setProperties(Properties properties) {
    this.properties = properties;
  }
}
```

​		mybatis-config.xml

````xml
<plugins>
  <plugin interceptor="org.mybatis.example.ExamplePlugin">
    <property name="someProperty" value="100"/>
  </plugin>
</plugins>
````

​		上面的插件将会拦截在 Executor 实例中所有的 “update” 方法调用， 这里的 Executor 是负责执行底层映射语句的内部对象。

​		除了用插件来修改 MyBatis 核心行为以外，还可以通过完全覆盖配置类来达到目的。只需继承配置类后覆盖其中的某个方法，再把它传递到 SqlSessionFactoryBuilder.build(myConfig) 方法即可。再次重申，这可能会极大影响 MyBatis 的行为，务请慎之又慎。

​	MyBatis插件本质上其实是一个拦截器**InterceptorChain**，以执行一个查询操作为例，介绍一个MyBatis插件实现原理

​		SqlSession获取Executor实例的过程如下：

​			1、SqlSessionFactory中会调用Configuration类提供的newExecutor()工厂方法创建Executor对象

​			2、Configuration类中通过一个InterceptorChain对象维护了用户自定义的拦截器链。

​			3、newExecutor()工厂方法中调用InterceptorChain对象的pluginAll()方法

​			4、InterceptorChain对象的pluginAll()方法中会调用自定义拦截器的plugin()方法

​			5、自定义拦截器的plugin()方法是由我们来编写的，通常会调用Plugin类的wrap()静态方法创建一个代理对象

````java
DefaultSqlSessionFactory#openSessionFromDataSource()//开启会话 
		Configuration.newExecutor()//创建执行器 
				executor = new CachingExecutor(executor);////创建缓存类型的执行器，装饰器模式
				InterceptorChain.pluginAll();//为执行器设置拦截器链，当Executor执行的时候，就会触发拦截器，这其中包含我们编写的自定义拦截器
````

​		SqlSession获取到的Executor实例实际上是个动态代理对象。接下来，我们就以SqlSession执行查询操作为例，介绍自定义插件执行拦截逻辑的过程。

​			1、SqlSession操作数据库依赖于Executor，SqlSession会调用Configuration#newExecutor()方法获取Executor代理对象

​			2、SqlSession获取的是Executor组件的代理对象，执行查询操作时，会调用代理对象的query()方法

​			3、按照JDK动态代理机制，调用Executor代理对象的query()方法时，会调用Plugin类的invoke()方法

​			4、Plugin类的invoke()方法中会调用自定义拦截器对象的intercept()方法执行拦截逻辑

​			5、自定义拦截器对象的intercept()方法调用完毕后，调用目标Executor对象的query()方法

​			6、所有操作执行完毕后，会将查询结果返回给SqlSession对象

````
SqlSessionFactory#selectOne()//执行查询 
SqlSessionFactory#selectList()//执行查询 
		ExecutorProxy#query()//代理执行器执行查询 
				Plugin#invoke();//插件拦截 
						MyInterceptor#intercept()//调用自定义拦截器的拦截方法 
								Executor#query()//执行被代理的Executor的query方法
````



**自定义分页插件：**

​	1、Pageable 接口

````java
public interface Pageable<T> { 
  /** 总记录数 */ 
  int getTotalCount(); 
  /** 总页数 */ 
  int getTotalPage(); 
  /** 每页记录数 */ 
  int getPageSize(); 
  /** 当前页号 */ 
  int getPageNo(); 
  /** 是否第一页 */ 
  boolean isFirstPage(); 
  /** 是否最后一页 */ 
  boolean isLastPage(); 
  /** 返回下页的页号 */ 
  int getNextPage(); 
  /** 返回上页的页号 */ 
  int getPrePage(); 
  /** 取得当前页显示的项的起始序号 */ 
  int getBeginIndex(); 
  /** 取得当前页显示的末项序号 */ 
  int getEndIndex(); 
  /** 获取开始页*/ 
  int getBeginPage(); 
  /** 获取结束页*/ 
  int getEndPage(); 
}
````

​	2、Page 类

````java
public class Page<T> implements Pageable<T> { 
  public static final int DEFAULT_PAGE_SIZE = 10; // 默认每页记录数 
  public static final int PAGE_COUNT = 10; 
  private int pageNo = 1; // 页码 
  private int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数 
  private int totalCount = 0; // 总记录数 
  private int totalPage = 0; // 总页数 
  //... 
}
````

​	3、PageInterceptor拦截器

````java
// MyBatis用户自定义插件类都必须实现Interceptor接口，因此我们自定义的PageInterceptor 类也实现了该接口 
@Intercepts({ 
  // 这里指定对StatementHandler实例的prepare()方法进行拦截， 
  @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}) 
	})
public class PageInterceptor implements Interceptor { 
  private String databaseType; 
  // 我们只需要在该方法中把执行的SQL语句替换成分页查询SQL即可 
  public Object intercept(Invocation invocation) throws Throwable { 
    // 获取拦截的目标对象 
    RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget(); 
    StatementHandler delegate = (StatementHandler) ReflectionUtils.getFieldValue(handler, "delegate"); 
    BoundSql boundSql = delegate.getBoundSql(); 
    // 获取参数对象，当参数对象为Page的子类时执行分页操作 
    Object parameterObject = boundSql.getParameterObject(); 
    if (parameterObject instanceof Page<?>) { 
      Page<?> page = (Page<?>) parameterObject;
      MappedStatement mappedStatement = 			   
        		(MappedStatement)ReflectionUtils.getFieldValue(delegate,"mappedStatement"); 
      Connection connection = (Connection) invocation.getArgs()[0]; 
      String sql = boundSql.getSql(); 
      if (page.isFull()) { 
        // 获取记录总数 
        this.setTotalCount(page, mappedStatement, connection); 
      }
      page.setTimestamp(System.currentTimeMillis()); 
      // 获取分页SQL 
      String pageSql = this.getPageSql(page, sql); 
      // 将原始SQL语句替换成分页语句 
      ReflectionUtils.setFieldValue(boundSql, "sql", pageSql); 
    }
    return invocation.proceed(); 
  }
  /**
  * 根据page对象获取对应的分页查询Sql语句， 
  * 这里只做了三种数据库类型，MySQL、Oracle 
  * 其它的数据库都没有进行分页 
  * @param page 分页对象 
  * @param sql 原始sql语句 
  */ 
  private String getPageSql(Page<?> page, String sql) { 
    StringBuffer sqlBuffer = new StringBuffer(sql); 
    if ("mysql".equalsIgnoreCase(databaseType)) { 
      return getMysqlPageSql(page, sqlBuffer); 
    } else if ("oracle".equalsIgnoreCase(databaseType)) { 
      return getOraclePageSql(page, sqlBuffer); }return sqlBuffer.toString(); 
  }
  /**
  * 获取Mysql数据库的分页查询语句 
  * @param page 分页对象 
  * @param sqlBuffer 包含原sql语句的StringBuffer对象 
  * @return Mysql数据库分页语句 
  */ 
  private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) { 
    int offset = (page.getPageNo() - 1) * page.getPageSize(); 
    sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize()); 
    return sqlBuffer.toString(); 
  }
  
  //...省略不重要代码 
}
````

​	4、使用插件

​		（1）注册插件：在Mybatis主配置文件中注册插件

````xml
<plugins> 
  <plugin interceptor="com.hero.plugin.pager.PageInterceptor"> 
    <!-- 支持MySQL和Oracle --> 
    <property name="databaseType" value="mysql"/> 
  </plugin> 
</plugins>
````

​		（2）Mapper接口使用分页插件：定义Mapper接口，使用Page的子类作为参数，使用分页查询数据

````java
public interface UserMapper { 
  @Select("select * from t_user") 
  List<User> getUserPageable(HeroPager pager); 
  @Insert("insert into t_user (id,name,age,address) values (#{id},# {name},#{age},#{address});") 
  void save(User user); 
  @Delete("delete from t_user") 
  void deleteAll(); 
}
public class HeroPager extends Page<User> {}
````

​		（3）测试分页插件

````java
public class HeroPagerExample { 
  private UserMapper userMapper; 
  private SqlSession sqlSession; 
  
  @Before 
  public void init() throws IOException { 
    // 获取配置文件输入流 
    InputStream inputStream = Resources.getResourceAsStream("mybatis- config.xml"); 
    // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例 
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
    // 调用openSession()方法创建SqlSession实例 
    sqlSession = sqlSessionFactory.openSession(); 
    // 获取UserMapper代理对象 
    userMapper = sqlSession.getMapper(UserMapper.class); 
    //初始化一些数据 
    initUsers(userMapper); 
  }
 
  private void initUsers(UserMapper userMapper) { 
    userMapper.deleteAll(); 
    for (int i = 1; i < 20; i++) { 
      User user = User.builder().id(Long.valueOf(i)).name("刘备【" + i + "】").age(11).address("蜀国").build(); 
      userMapper.save(user); 
    }
    sqlSession.commit(); 
  }
  
  @Test 
  public void testPageInterceptor() { 
    HeroPager pager = new HeroPager(); 
    pager.setPageSize(5);
    pager.setFull(true); 
    List<User> users = userMapper.getUserPageable(pager); 
    System.out.println("总数据量：" + pager.getTotalCount() + ",总页数：" + pager.getTotalPage()+ "，当前查询数据：" + JSON.toJSONString(users)); 
    sqlSession.close(); 
  } 
}
````

