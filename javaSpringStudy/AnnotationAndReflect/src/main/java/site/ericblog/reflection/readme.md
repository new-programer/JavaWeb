##反射
###Java反射机制概述
####静态 VS 动态语言
- 动态语言

1.是一类在程序运行的时候可以改变自身结构的语言，例如新的函数、对象、甚至代码可以被引进，
已有的函数可以被删除或是其他结果上的变化。通俗来讲，在运行时代码可以根据某些条件改变自身结构。
2.常见的动态语言有：Object-C, C#, JavaScript, PHP, Python

- 静态语言

1.与动态语言相应， 运行时结构不可改变的语言就是静态语言。如Java, C, C++.
2.Java虽不是动态语言，但Java可称之为 “准动态语言” 。即Java有一定的动态性，我们可以利用反射机制
获得类似动态语言的特性。Java的动态性让编程的时候更加灵活。

###反射的优点和缺点
####优点
可以实现动态创建对象和编译，体现出很大的灵活性
####缺点
对性能有影响，使用反射基本上是一种解释操作，可以告知JVM希望做什么，并且它满足我们的要求。
这类操作总是慢于直接执行相同的操作。能不用反射构建对象（优先考虑new），就尽量别用反射（特别情况除外），
因为其性能差了好几十倍。

###Class类
Object 类中定义了 public final Class getClass()方法，该方法将被所有子类继承。
同时该方法的返回值的类型是一个Class类，此类事Java反射的源头，实际上所谓反射从程序的运行结果来看也好理解，
可以通过对象反射求出类的名称。

- Class 本身也是一个类，
- Class 对象只能由系统建立对象
- 一个加载的类关注 JVM 中的一个class 实例
- 一个Class对象对应的是一个加载到JVM中的一个.class文件
- 每个类的实例都会记得自己由哪一个Class实例所生成。
- 通过Class可以完整地得到一个类中的所有被加载的结构
- Class类是反射（Reflection）的根源，针对任何你想动态加载，运行的类，**必须先获得相应的Class对象**
#### Class类的常用方法
static ClassforName(String name)

#### 获取Class类的常用方法
- 已知具体的类，如User，可通过class属性获取，此方法最安全可靠，且程序的性能高:

    `
    Class clazz = User.class;
    `
    
- 已知某个类的实例，调用该实例的 getClass()方法获取Class对象

    `Class clazz = user. getClass();
    `
    
- 已知类的全类名，且该类在类的路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
  
    ` Class clazz = Class.forName("site.ericblog.reflection.User");
    `
    
#### 哪些类型可以有Class对象？
- class：外部类， 成员（成员内部类，静态内部类），局部内部类， 匿名内部类。
- interface：接口。
- []: 数组
- enum ： 枚举
- annotation：注解@interface
- primitive type ： 基本数据类型
- void

####Java内存分析
- 堆
1.存放new的对象和数组
2.可以被所有的线程共享，不会存放别的对象引用
- 栈
1.存放基本的变量类型（包含这个基本类型的具体数值）
2.引用对象的变量（会存放这个引用在堆里的具体地址）
- 方法区
1.可以被所有的线程共享
2.包含了所有的class和static变量

####类的加载过程（了解）
当程序主动使用某个类时，如果该类还未被加载到内存中，则系统会通过如下三个步骤来对该类进行实例化。

类的加载（Load） ---> 类的链接（Link）---> 类的初始化（Initialize）
其中：
- 类的加载（Load）：此过程由类加载器完成，将类的class文件字节码内容加载到内存中，并将那些静态数据
转换为方法区的运行时数据结构，然后生成一个代表这个类的java.lang.Class对象。故Class对象**只能获取不能自行创建**

- 类的链接：将类的二进制数据合并到JRE中，亦即将Java类的二进制代码合并到JVM的运行状态之中的过程。
   - 验证：确保加载的类信息符合JVM规范，没有安全方面的问题。
   - 准备：正式为类变量（static）**分配内存**并设置类变量默认初始值的阶段，这些内存都将在 **方法区** 中进行分配。
   - 解析：虚拟机 **常量池内** 的 **符号引用（常量名）替换为直接引用（地址）** 的过程。
- 类的初始化：JVM负责对类进行初始化。

   - 执行类构造器 <clinit>() 方法的过程。类构造器 <clinit>() 方法是由编译器自动收集类中所有
   类变量的赋值动作和静态代码块中的语句合并产生的，编译器收集的顺序是由语句在源文件中出现的顺序决定的，
   静态语句块中只能访问到定义在静态语句块之前的变量，定义在它后面的变量可以赋值但不能访问。
   （类构造器是构造类信息的，不是构造类对象的构造器）
   - 当初始化一个类的时候，如果发现其父亲还没有进行初始化，则需要 **先触发其父类的初始化**。
   - 虚拟机会保证一个类的 <clinit>() 方法在多线程环境中被正确加锁和同步
   
####什么情况下会进行类初始化？

- 类的主动引用（一定会发生类的初始化）
    - 当虚拟机启动，先初始化main方法所在的类
    - new一个类的对象
    - 调用类的静态成员（出final常量）和静态方法
    - 使用 `java.lang.reflect` 包的方法对类进行反射调用
    - 当初始化一个类，如果其父类没有被初始化，则会初始化它的父类
    
- 类的被动调用 （不会发生类的初始化）
    - 当访问一个静态域时，只有**真正**声明这个域的类才会被初始化。如：当通过子类引用父类的静态变量，
    **不会导致子类初始化**。
    - 通过数组定义类引用，不会触发此类的初始化
    - **引用常量**不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中了）
    
####类加载器的作用
- 类加载的作用：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，
然后在堆中生成一个代表这个类的java.lang.Class对象，**作为方法区中类数据的访问入口**。
- 类缓存：标准的JavaSE类加载器可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持
加载 **（缓存）** 一段时间。不过JVM垃圾回收机制可以回收这些Class对象。
- 类加载器的作用是把类（class）加载到内存。JVM规范定义了如下类型的类的加载器。
    - 引导类加载器（Bootstrap Classloader）：C++编写，是JVM自带的类加载器，**负责Java平台核心库（rt.jar）**，
    用来装载核心类库，也称根加载器。无法 *直接* 获取该加载器
    - 扩展类加载器（Extension Classloader）：负责jre/lib/ext目录下的的jar包或-D java.ext.dirs指定目录下的jar包装入工作库
    - 系统类加载器（System Classloader）：负责java -classpath或-D java.class.path 所指的目录下的类与jar包装入工作，
    是最常用的加载器。有的地方也指 Application Classloader
    - 自定义加载器：待完善
- 双亲委派机制：多重检测保证安全性，自己定义的类若和JDK内置的类一样，会忽略自定义的类，而且调用JDK内置的类

###什么是ORM?
####了解什么是ORM?
- Object relationship Mapping --> 对象关系映射
- 类和表结构对应
- 属性和字段对应
- 对象和记录对应
####要求：利用注解和反射完成类和表结构的映射关系（Reflection12.java）
    