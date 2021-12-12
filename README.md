> # Android补充复习课
>
> - 可以提前做一个意见收集表  把前面不太懂的内容 统一一下
> - **针对**前面不太懂的内容 做一些知识的**重复和梳理**
> - **约束布局的讲解**   **略提适配有关**
> - 再次强调 **命名规范**
> - **sp数据库的使用**
> - **权限的申请**
> - 拍照和相册的调用
> - **用基础网络请求  稍微封装**  做一个网络请求的**模拟开发流程**  发源代码



> 安卓复习课 
>
> 复习前面所学重点知识 
>
> 多媒体技术(拍照，播放音频，播放视频等) 动态权限申请 实战：综合接口和网络请求，模拟开发流程写一个界面展示





# RedRock Class 8

本次为移动开发第8次课程，本次课程主要以复习为主，新的内容并不多。



## ~~P~~review

Review和Preview有个P的区别。





## 命名规范

规范的存在是为了让代码看起来更通俗易懂，也就是常常说的代码的可读性。你的代码遵循一套规范自己看起来别人看起来都很轻松。

就拿简单的例子来说

这是两个变量如果他们在你的项目里面表达的意义都是相同的，你觉得哪个更好？

```java
boolean isLogin = false;
boolean a = false;
```

显然是isLogin，从这个名称我能知道它是一个boolean，它的含义是你是否登陆。



#### 包名

**小写命名，不能有下划线，单词与单词之间无需间隔。**

比如下列都是不合理的

- main feature
- mainFeature
- main_feature
- MainFeature
- ...

正确的应该是mainfeature

子所以这样命名是因为不合理的包名称可能会引发as或者idea的报错。

所以不习惯的也忍忍。



列举几个在Android 项目中会用到的常见包名

- ui 

  > 所有的界面都存储在这个包里面，内部可包含activity，fragment

- activity

  > 包内装activity

- fragment

  > 包内装fragment

- service

  > 装service或者一些公共的服务

- view/component

  > 自定义view

- utils

  > 工具类

- bean

  > 数据bean类

- model

  > 数据的获取来源

- adapter

  > 适配器目录



#### 类命名

```java
public class UserAction{

}

public class BugProvider{
    
}

public class BugSolver{
    
}


```





#### 方法命名



```java
public void getList()

public int getId()

public booolean ensureActive()
```





#### 变量命名



```java
ClickListener clickListener;
MyAdapter rvAdapter;
List<User> userList;
```





#### 常量命名

```java
public static final int USER_ID = 1;

public static final String DEFAULT_USER_NAME = "ROOT
```

所有字符都大写，用_做分隔

具体可见群文件 阿里巴巴Java开发手册P4

## Contraintlayout

Constraintlayout好用啊，这我熟啊。

但是其实Constraintlayout并不像它表面上那么简单。

我们使用它比较方便，但这不意味着它比较简单。翻开Design页面查看All Attributes就可知道它有多复杂。



#### 基础使用

当我直接从Design页面中拖拽出某个View的时候它其实时会报错的

![image-20211205151922752](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205151922752.png)

> This view is not constrained. It only has designtime positions, so it will jump to (0,0) at runtime unless you add the constraints
>

当前View**不受约束**，它只有一个design time的位置，**如果你不加约束他在运行时候会跑到0,0点处。**

不受约束这一点暗示我们约束布局的View是需要受到约束的。约束布局是通过约束子View来确定页面的布局的。

通常情况下至少需要两个约束，水平方向上一个，竖直方向上一个。



##### 添加单个约束

我们可以通过拖动design的小原点来添加约束

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205152510903.png" alt="image-20211205152510903" style="zoom:50%;" />

这样我们加了一个竖直方向的约束

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205152604844.png" alt="image-20211205152604844" style="zoom:50%;" />

可以看见它xml中的代码发生了变化

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205152713139.png" alt="image-20211205152713139" style="zoom: 67%;" />

```xml
app:layout_constraintTop_toTopOf="parent"
```

什么意思我们添加了一条约束这个约束是view的顶部和parent的顶部对齐。

相似的还有不少

```xml
app:layout_constraintTop_toTopOf=""
app:layout_constraintTop_toBottomOf=""

app:layout_constraintBottom_toBottomOf=""
app:layout_constraintBottom_toTopOf=""

app:layout_constraintEnd_toEndOf=""
app:layout_constraintEnd_toStartOf=""

app:layout_constraintStart_toStartOf=""
app:layout_constraintStart_toEndOf=""

app:layout_constraintLeft_toRightOf=""
app:layout_constraintLeft_toLeftOf=""

app:layout_constraintRight_toLeftOf=""
app:layout_constraintRight_toRightOf=""
```



这里我没有传入参数。

layout_constraintXXX_toXXXOf的参数可以是parrent和一个view的id。

**如果是parrent就是和父布局做约束。**



**如果是父布局中的某个view就是和对应的view做约束。**

比如我可以和上面的以上面的textview做约束

![image-20211205155140643](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205155140643.png)









这里有一个start和end可能会感觉有些陌生。

这主要是为了适配布局，因为有的地区的布局不是左到右的顺序，比如阿拉伯这些地方他们的习惯是右边到左边(想想他们读一段文字都是从右边读到左边)，所以我们如果使用left/right打出来的app到了他们那些地区布局很是魔性，不符合当地人们的习惯，所以有了start和end，start就是布局的起始，在大多数地区的left，随着地区的切换他会自动切换。end相似的就是右边。



##### 确定view的位置

start和父start对其

top和父top对其。

好现在我们加两个约束确定子view的位置

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    >

    <TextView
        android:id="@+id/textView3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>

```



除此之外我们还可以加margin(left，right，top，bottom,start，end)都行。

可以在design里面加

![image-20211205161556718](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205161556718.png)



也可以在xml中直接写

```xml
android:layout_marginTop="10dp"
android:layout_marginStart="10dp"
```



##### bias

我和左边对齐我还和右边对齐，所以我应该和哪边对齐？

答案是左右的中心处。

因为bias=0.5

当你同一个方向上有两个约束的时候你的位置还多了一个控制条件也就是bias

```xml
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
```

这样view的中心就会和中线对齐。(不写bias默认就是0.5)

如果我想view的中心在start到end的0.7处，可以这样设置

```
app:layout_constraintHorizontal_bias="0.7"
```

也就是说同一方向上两个constraint在默认情况下会让**view的中心**与**两个约束的中心**对齐。

可以通过设置

app:layout_constraintHorizontal_bias=""

来控制位置。

这样能理解创建一个Empty Activity的时候生成的Hello World为什么是居中的了吧

```xml
<TextView
    android:id="@+id/textView3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="TextView"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

因为有4个约束，水平两个，竖直两个，而且没有使用bias(没使用就是默认为0.5)



##### 小结

- 在一个方向上的单个约束就是对齐。
- 在一个方向上有两个约束，默认是和这两个约束的中线对齐，可以通过bias设置对齐的位置。

可看下面的几种约束想想他们添加约束后位置

![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/parent-constraint_2x.png)

![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/position-constraint_2x.png)

![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/alignment-constraint_2x.png)



![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/alignment-constraint-offset_2x.png)

除此之外对于类似TextView的字体还有一个base_line

这个base_line就是字体的基线。

想想小学的时候用的拼写本的一条条横线，其中下面的那条就是基线(这玩意据说只有英文单词才有，汉字好像是没有基线的，了解一下即可)。

![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/baseline-constraint_2x.png)







#### 中级使用



##### ratio

***假如***你有一张图片

<img src="C:\Users\Fool\AppData\Roaming\Typora\typora-user-images\image-20211205170848000.png" alt="image-20211205170848000" style="zoom:25%;" />

你要把他放在一个ImgaView中

然后很自然的，你在constraintLayout中拖了一个ImageView然后选择了这张图片。

悲剧出现了，图片太大了，很是头疼，你该怎么修改imageView的size？

自己根据宽高比算？

![image-20211205170938421](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205170938421.png)

不会的，constraintLayout支持设置宽高比

先top来个约束

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205171649347.png" alt="image-20211205171649347" style="zoom: 80%;" />

可以看到top已经加入了约束，我们的宽高是wrap。

点击Constraint Widget的箭头。

给点击一下左上角的三角形。





![image-20211205172139935](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205172139935.png)



弹出来一个输入框，让我们输入比例。注意这里的比例得分情况讨论

- 如果你match_constraint的是width就是高：宽
- 如果你match_constraint的是height就是宽：高



![image-20211205172334452](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205172334452.png)

这里宽高比就通过查看图片的分辨率就是了。右键属性，或者用as打开就可。

设置以后直接贴合在一起了，完美。

![image-20211205172615216](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205172615216.png)



这样我们改变width或者height(记住**别修改match_constraint的边**) 的时候imageView的大小就自动适应了

这里我设置width为100dp自动适配了

![image-20211205173827671](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205173827671.png)



##### chain

chain其实全名叫做constraint chain

chain是比较重要的，没了chain屏幕适配的难度会大上不少。

chain其实并不难

通过这张图可以了解什么是chain，可以发现他们的约束时这样的A，B是互相约束的，A约束B，B约束A，然后A，B分别还约束了左边和右边，这是两个View形成的chain



![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/constraint-chain_2x.png)

这是三个View形成的Chain。

会发现其实很类似，就是A，B，C相邻两个互相连接。

![img](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/constraint-chain-styles_2x.png)

我拿这张图绝对不止是要讲chain是什么。配合着顺便还想说说chain的种类也叫做chain style

按照方向分有两种

- vertical chain
- horizontal chain

按照功能划分有三种

- spread —— 1
- spread inside ——2
- packed——4

**但是为啥有4个序号呢hh**，序号3有些特殊(最后一个讲).

- spread style chain的特点是间距是相等的
- spread inside style chain的特点就是它两边是没有的，中间的间距是相等的。
- packed style chain的特点是它把中间的挤掉了，空白部分分布在两边。

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205191530298.png" alt="image-20211205191530298" style="zoom:80%;" />



通过align vertical center使得他们在水平方向上中心对齐。



![image-20211205191825603](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205191825603.png)



![image-20211205191948119](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205191948119.png)



第三个其实是百分比实现的一种模式它有些类似于LinearLayout的weight

将view的width设置为0dp (准确来说是match_constraint)然后他会吸收所有的空白。

（只有match_constraint的布局才能参与空白部分的吸收）吸收的多少和

```xml
app:layout_constraintHorizontal_weight=""
```

大小有关。这就是吸收空白的权重比。默认不设置默认相等。（就是说不设置整个属性，所有match_constraint的view共同**平等吸收**空白）



vertical chain和horizontal chain是一样的不做阐述。



coding....

practice......

how to create constraint chain.....



#### 高级使用



##### group

Group可以控制组内的引用的visibility和elevation

实现效果可见demo





##### Guideline



guideLine是简单看来就是没有size的组件，虽然没有size但是它是具有方位，也就是说可以通过在布局里面引入它来实现view的精确布局.

有的时候我们需要精确的确定我们View的位置，比如我现在有两个需求。

- 把ImageView放置在水平方向距离边界200dp，竖直距离边界200dp？（这个确实不难）
- 把ImageView的位置设置到水平方向70%的位置，竖直方向70%的位置怎么弄？(有点难是吧)

当然是可以使用Guideline来实现更加精确的布局。



引入两条guideLine

![image-20211206092935680](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211206092935680.png)

与guideLine做约束即可实现



![image-20211206093103347](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211206093103347.png)



同理第二个需求的实现。

略微不同的是guildeLine由dp转化为了百分比，这里稍作切换即可

![image-20211206093816519](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211206093816519.png)



coding

note：

看上去用处挺大的，但是最好是**合理**的去**使用**，所有的constraint helper要遵循**需要的时候才用，不需要就别用，不要刻意去使用，刻意的乱用来提升逼格容易造成这种或者那种奇怪玄幻的问题。**





##### barrier

barrier比较简单

barrier是一个帮助类。

他的主要用途就是语言切换或者其他引起界面View大小变动中的适配。

关说是不行的，得看实际场景。

如果我们用GuideLine写了一个登陆界面

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211212163939949.png" alt="image-20211212163939949" style="zoom: 80%;" />

界面元素显示正常。

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211212164252214.png" alt="image-20211212164252214" style="zoom:25%;" />



适配一下语言。

发现什么了，语言变化了，TextView的长度变了，TextView跑到EditText那里去了。这样不好

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211212164452083.png" alt="image-20211212164452083" style="zoom:25%;" />

这时候就该Barrier上场了。删掉GuideLine，加入一个Barrier，然后把两个TextView加入进去，然后设置Barrier的方向为right。

这样Barrier的位置就处于barrier内部的View的最右边。

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211212164740719.png" alt="image-20211212164740719" style="zoom: 80%;" />

切换语言前后不会出现覆盖的问题了。

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211212164900315.png" alt="image-20211212164900315" style="zoom: 25%;" />

Barrier的使用大致如此，只要遇上需要确定边界的都可以使用Barrier，不要认为只有语言切换才会使用到Barrier。



##### 拓展

有兴趣就去了解一下。

其他的Constraint Helper的用法

- ###### flow

- ###### layer

- ###### 自定义helper




#### 参考

Bilibili教程 [part1](https://www.bilibili.com/video/BV1w4411t7UQ?p=3) .[part2](https://www.bilibili.com/video/BV1w4411t7UQ?p=4)，[part3](https://www.bilibili.com/video/BV1w4411t7UQ?p=8)(比较通俗简单)

[Bilibili教程](https://www.bilibili.com/video/BV1eh411d7T9?t=17.3)（比较硬核）

[ConstraintLayout引导文档](https://developer.android.google.cn/training/constraint-layout)



## SharedPreferences

SharedPreferences又叫SP（为了方便，下文的SP均代指SharedPreferences），SP数据库是Android提供的一种键值对数据的存储方式。

### 什么是键值对与SP

键值对就是一个二元的组合，类似于这样key-value

"a"-1 ，,"1"->3,3->3这些都是键值对。

很奇怪？为什么要讲这个？因为我们的日常开发中可能会用到。

有的时候我们就是想存储一些变量

比如

- 用户自动登陆。

  > 用户第一次登陆输入账号密码,把账号和密码存储起来。然后下次需要用到的时候在读取。比如一个username为root 密码为123456的用户就可以转化为这两个键值对
  >
  > "userName":"root"
  >
  > "passWord":"123456"
  >
  > **（不是很推荐这样做，因为sp存入的数据是明文的，也就是说可以直接看，没有被加密，会存在账号的安全问题）**

- 配置信息

  > 市面上的绝大多数的app都有一个设置界面，你可以对app进行一些参数的配置，比如打开网易云
  >
  > <img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211202192434570.png" alt="image-20211202192434570" style="zoom:25%;" />
  >
  > 我们可以看到有一堆的配置参数，当我们的App关闭以后然后进入以后我们会发现，App被我门自定义了。
  >
  > 这可以通过将一些基础的配置参数进行保存，然后在打开的时候进行读取，这样就达到了类似的效果。



### SP的使用

通过上面的描述相信大家已经对于SP有了一些了解，下面就对于SP的使用进行一些阐述。

#### 获取SP

> SP既然是一个数据库那肯定具有一些增删改查的操作。所以我们如果要对数据库做操作得通过一个对象来。
>
> ```java
> Context context = getActivity();
> SharedPreferences sharedPref = context.getSharedPreferences( 
>         getString(R.string.preference_file_key), Context.MODE_PRIVATE);
> ```
>
> 也就是调用Activity的getSharedPreferences

#### 对SP进行操作

这里的操作分为几种，**增删改查**

- 插入一条数据

  > ```java
  > //获取SharedPreferences实例
  > SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
  > //获取Editor
  > SharedPreferences.Editor editor = sharedPref.edit();
  > //通过Editor对向sp数据库里面插入一条数据
  > editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
  > //别放了最后得提交这种操作
  > editor.apply();
  > ```
  >
  > 其中Editor可能比较特殊，他是用于对SP操作的一个对象。可以通过这个对象对SP数据库进行操作。
  >
  > 除了putInt以外还有
  >
  > - putString
  > - putBoolean
  > - putInt
  > - putFloat
  > - putLong
  > - putStringSet

- 删除一条数据

  > ```java
  > sharedPref.remove("你需要移除键值对的Key值");
  > editor.apply();
  > ```
  >
  > 这里remove传入了你需要移除的key值。
  >
  > 除此之外还可以移除所有的内容,这个操作得三思而后行
  >
  > ```java
  > sharedPref.clear();
  > editor.apply();
  > ```

- 读取一条数据

  > ```java
  > SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
  > int value = sharedPref.getInt("键值对的Key值", defaultValue);
  > ```
  >
  > 可以看到这里需要传入了两个值，一个是你需要获取的key值,除此之外还得传入一个defaultValue,当通过传入的key值，找不到匹配的键值对的时候就会使用defaultValue
  >
  > 除了getInt以外还有其他的方法，他们在用法上市是类似的就不做阐述了
  >
  > - getBoolean
  > - getInt
  > - getString
  > - getFloat
  > - getLong
  > - getStringSet
  > - getAll
  >
  > 

- 修改一条数据

  > ```java
  > editor.putInt(key,value);
  > ```
  >
  > **这个方法既可以是插入也可以是改。**
  >
  > -  如果之前的sp数据库里面没有对应的键值对那么就会进行插入操作。
  > -  如果有匹配的键值对就会对该键值对进行更新。（会用传入的值来更新匹配的字符串）。



#### SP的存储路径

前面说过SP数据库适用于Key-Value字符串的存储，这里的存储时持久化存储。既然时持久化存储那肯定有个存储路径，存储在哪呢？

/data/data/com.example.sp/shared_prefs/LOGIN_SP.xml

(这个时demo的sp数据库的存储路径)

/data/data/你的包名/shared_prefs/你的sp数据库名称.xml

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205113016662.png" alt="image-20211205113016662" style="zoom: 80%;" />

可以看到SP数据库的存储是用的xml文件存储

好了我们点开这个数据库文件看看里面是怎么装的

![image-20211205113919895](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211205113919895.png)

#### 注意事项

- 当我们要对sp数据库内容进行修改的时候，我们会先通过调用getSharedPreferences获取实例，然后调用SP的edit方法，获取Editor对象。然后调用putXXX，但是，别忘了要commit或者apply，否者put操作会被视为无效的。

- commit和apply的区别，commit是阻塞的调用，也就是说它会等待数据写入完成才返回。所以当数据比较多的时候，而且在UI线程调用的时候得注意，因为可能会造成UI线程的阻塞导致ANR。apply则是在其他线程中执行put操作。使用上推荐使用apply。

- **不要**使用SP数据库存储太多的信息，**不要！不要！！不要！！！**

  SP被设计出来的初衷就是以**键值对**的方式存储**配置信息**，配置信息是**少量**的，比如这个页面的配置，设置的一些配置选项，这些都是允许的。存储账号密码啥的虽然很离谱，但是也是勉强能接受的。但是如果将一个大型页面的所有数据都存储进入SP，那就显得怪诞了。



内容参考自[官方文档](https://developer.android.google.cn/training/data-storage/shared-preferences?hl=en)

## 权限申请

> Android引入权限主要是为了保护用户的隐私。
>
> 毕竟谁也不想兜里的手机在没有任何征兆的情况下播放XXX.mp3。
>
> 亦或者是在你不方便时候给你录制了一段《社死.mp4》
>
> 更多的情况就不再一一列举.....



#### Android权限的作用

- 限制访问数据(系统的状态参量，用户的个人信息)
- 限制行为(如连接配对设备，播放录制视频，音乐等......)



#### 权限分类

大致有三类权限

- 安装时权限
- 运行时权限
- 特殊权限

你可能会问我哪知道哪些是安装时权限，那些是运行时权限？可以在[文档](https://developer.android.google.cn/reference/android/Manifest.permission#ACCEPT_HANDOVER)里面查询

比如这里展示几个权限

![image-20211211131323243](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211211131323243.png)



![image-20211211131449108](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211211131449108.png)



![image-20211211131600720](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211211131600720.png)

上面展示了

网络权限，相机权限，存储权限的相关描述，他们分别对应安装时，运行时，特殊权限。

可以发现

Protection level 分为几个等级

- normal表示安装时权限。
- dangerous表示运行时权限
- appop表示特殊权限





##### 安装时权限

这类权限或许是比较通用,**我推测**可能没和用户隐私有过大的牵扯所以**只要声明在安装后系统会自动同意。**

比如下列请求

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/install-time.svg" alt="The left image shows a list of an app's install-time permissions. The     right image shows a pop-up dialog that contains 2 options: allow and deny." style="zoom: 25%;" />

对于涉及到这些权限的内容我们只需要声明,安装app后系统会自动给我们批准。

权限声明挺简单的

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211206223729069.png" alt="image-20211206223729069" style="zoom:67%;" />

> 小结：
>
> 对于安装时权限我们只需要在Manifest文件里申明即可。





##### 运行时权限

运行时权限就是**比较危险**的权限了，与用户隐私涉及比价深。在Android 6.0(API 23)以前是只需要申明权限而不用请求权限的，但是我们的手机的系统版本通常是大于6.0的，所以我们需要在运行app的时候申请权限。

比如我们要通过一个按钮点击给10086打电话。

在不考虑申请权限的基础上我们直接跑，发现

```java
final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
startActivity(intent);
```

> java.lang.SecurityException: Permission Denial:

运行时权限在Android 6.0(API 23)以上必须在运行的时候申请，光是在Manifest文件中申明系统是不会认账的。

所以得加上这段代码

```java
(v) -> {

    if (PackageManager.PERMISSION_GRANTED == ActivityCompat
            .checkSelfPermission(this, Manifest.permission.CALL_PHONE)) {
        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
        startActivity(intent);
    } else {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

}
```

checkSelfPermission是检查对应的权限是否被允许。

然后requestPermissions是动态申请权限

然后可以在activity覆写一个方法来检测请求之后之后的回调

```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    
}
```

<img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211207120230475.png" alt="image-20211207120230475" style="zoom:25%;" />

可以看到弹出了一个权限申请弹窗。

允许后再次点击就跳转到拨号界面。

但是有一个很奇怪的问题，就是如果我们禁止之后，以后再也不会弹窗了。

因为禁止以后下次通过requestPermissions的时候会自动拒绝。

所以这里给出一种权限被拒绝的解决方案。

先判断一下是否被拒绝如果被拒绝了会返回true。

如果被拒绝了就直接跳转到setting界面。

```java
if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
}
```

> 小结：
>
> 运行时权限需要
>
> - 在Manifest中申明
> - 在app跑起来的时候通过调用requestPermissions来动态地申请(申请成功一次以后可以一直使用)，如果被拒绝了就得引导用户去设置里面把权限打开(这对于不同的手机生产产商有区别)



##### 特殊权限

特殊权限比较少，但是每个权限的涉猎都比较广，可能会危害到用户的隐私。这里只讲一个特殊(其他的可能用不上。)

注意，注意，注意！！！特殊权限特殊的一点是，当你按照运行时权限申请的时候，先申明，然后动态去请求。你会发现：

- 没有弹窗提示了
- onRequestPermissionsResult直接返回false了

这就是说他不能发弹窗让用户选择是否接受了。因为系统默认直接给你返回false了。

对于特殊权限的**唯一**授权方式是引导用户到对应app的设置里面授予权限。



###### 存储权限

存储权限是比较常见的，也是比较常用的。

我们的软件很可能会写一些文件，读取一些文件，频繁和文件打交道，所以存储权限是比较常见的。

- 概述

  > 在讲Android存储权限之前，先聊聊Android的存储策略，Android的存储空间分为两个部分
  >
  > - 内部存储空间
  > - 外部存储空间

- 权限

  > 与存储相关的权限有3种
  >
  > - [READ_EXTERNAL_STORAGE](https://developer.android.google.cn/reference/android/Manifest.permission#READ_EXTERNAL_STORAGE)
  >
  > - [WRITE_EXTERNAL_STORAGE](https://developer.android.google.cn/reference/android/Manifest.permission#WRITE_EXTERNAL_STORAGE)
  > -  [MANAGE_EXTERNAL_STORAGE](https://developer.android.google.cn/reference/android/Manifest.permission#MANAGE_EXTERNAL_STORAGE)

- 权限演变

  

  > 最开始的时候只要是访问app路径外的外部存储空间都得表明READ_EXTERNAL_STORAGE，写入相应的就需要WRITE_EXTERNAL_STORAGE。
  >
  > 到了Android 10 （API 29）多了一个叫[Scoped Storage](https://developer.android.google.cn/about/versions/10/privacy/changes#scoped-storage)的限制，单申请读写权限时没有任何意义的。因为申请成功了也不能访问外部空间。
  >
  > 如果要在9.0的版本下访问外部的资源得在之前动态权限申请的基础上在manifest里面的application标签下加入
  >
  > ```xml
  > android:requestLegacyExternalStorage="true"
  > ```
  >
  > ***又***从Android 11（API 30）开始，**为了更好地保护用户的隐私**，原来的requestLegacyExternalStorage也被ban掉了。他被替换为了一个更加严格的权限，MANAGE_EXTERNAL_STORAGE。值得注意的是这个权限是个特殊权限。

- 外部存储权限的申请

  > - Android 10以前
  >
  >   简单的动态权限声明。
  >
  >   manifest文件中声明
  >
  >   ```xml
  >   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  >   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  >   ```
  >
  >   运行时申请
  >
  >   ```java
  >   requestPermissions();
  >   ```
  >
  > - Android 10
  >
  >   在原来的基础上在manifest文件中加入
  >
  >   android:requestLegacyExternalStorage="true"
  >
  > - Android 11
  >
  >   还记得前面那句话吗？
  >
  >   对于特殊权限的**唯一**授权方式是引导用户到对应app的设置里面授予权限。
  >
  >   所以我们申请权限得这样
  >
  >   ```java
  >   private void goSetting() {
  >       Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
  >       Uri uri = Uri.fromParts("package", getPackageName(), null);
  >       intent.setData(uri);
  >       startActivity(intent);
  >   }
  >   ```
  >
  >   跳转到应用详细界面，让用户自行赋予权限。
  >
  > 特殊权限大致就这样。



#### 参考

[官方文档 Permissions Overview](https://developer.android.google.cn/guide/topics/permissions/overview)

[官方文档 Data and file storage overview](https://developer.android.google.cn/training/data-storage)

[官方文档 Android 10 隐私变动](https://developer.android.google.cn/about/versions/10/privacy#top-privacy-changes)

[官方文档 Android 11 隐私变动](https://developer.android.google.cn/about/versions/11/privacy#top-privacy-changes)

[官方文档 Android Scoped Storage](https://developer.android.google.cn/about/versions/10/privacy/changes#scoped-storage)



## 多媒体

多媒体是手机上比较常见的一个内容。

在手机的使用过程中我们常常能使用到，比如音乐的播放，音效的播放，视频放映，拍照......等一系列都属于多媒体。



### MediaPlayer

MediaPlayer是Android给我们提供的一个播放器。封装性比较强，我们很难对此加入一些自定义的播放操作，但是对于一些简单的音乐播放，其实使用MediaPlayer已经足够了。



#### MediaPlayer的基础认知



##### 播放格式以及播放数据源

MediaPlayer**支持常见音频文件播放**(除此之外还支持**视频文件**播放)，在此不一一例举。如有兴趣可查看[MediaPlayer播放格式列表](https://developer.android.google.cn/guide/topics/media/media-formats)

除此之外，还可以**设置播放的数据源**

- res/raw下的音频视频文件
- 本地存储的音频/视频文件
- 网络音频/视频资源



##### 使用步骤

使用通常分为以下几步

- 实例化MediaPlayer对象
- 配置MediaPlayer的播放配置(播放的参数,播放的数据源)
- 准备播放(解析音频/视频编码)
- 播放视频/音频





##### 状态转移图表

如下是MediaPlayer的状态转移过程(贴图，选讲)

![MediaPlayer State diagram](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/mediaplayer_state_diagram.gif)

> 播放需要经过
>
> 闲置状态->初始化状态->准备状态->准备完成状态->播放状态

> 在进入播放状态的时候我们可以通过提过的pause何start进行播放和暂停状态的**双向切换**。
>
> 播放状态<->停止状态

> 在播放完成后会进入完成状态
>
> 播放状态->完成状态

> 如果我们需要切歌可以先调用方法使其进入进入暂停状态然后再通过准备方法，再进行播放。也就是
>
> 播放状态->停止状态->准备状态->准备完成->播放状态
>
> 或者是
>
> 完成状态->停止状态->准备状态->准备完成->播放状态



#### Code实操

前面说过MediaPlayer的使用分为4个步骤。我们尝试着逐一进行讲解

##### 实例化MediaPlayer对象

两种方法

最为朴实无华

```java
MediaPlayer mediaPlayer = new MediaPlayer();
```

简便方法

```java
MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.sun);
```

为什么说他比较简便呢？

因为后续的MediaPlayer的**播放配置和准备**步骤它已经自动帮你完成了。

但是不是很推荐这样用，这个主要是因为它的准备也就是解码过程是同步的，可能会阻塞UI线程。

而且这样配置的MediaPlayer只能播放raw下的或者本地音视频文件，网络上的无法解析。

故**推荐前者**





##### 配置MediaPlayer的播放配置(播放的参数,播放的数据源)

```java
//配置播放的数据源
try {
    mediaPlayer.setDataSource(this,Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sun));
} catch (IOException e) {
    e.printStackTrace();
}

//配置播放设备的配置
mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        //设置播放的类型
                        //音频or视频or......
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        );
```

- 播放类型的设置

> 常用的就两种，一种是音乐，一种是视频
>
> 音乐
>
> ```java
> mediaPlayer.setAudioAttributes(
>                 new AudioAttributes.Builder()
>                         //设置播放的类型
>                         //音频or视频or......
>                 .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
>                 .setUsage(AudioAttributes.USAGE_MEDIA)
>                 .build()
>         );
> ```
>
> 视频
>
> ```java
> mediaPlayer.setAudioAttributes(
>                 new AudioAttributes.Builder()
>                         //设置播放的类型
>                         //音频or视频or......
>                 .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
>                 .setUsage(AudioAttributes.USAGE_MEDIA)
>                 .build()
>         );
> ```
>
> 



- 播放的数据源

> apk路径下的，apk路径外的本地资源，网络资源。都可以的。
>
> 具体数据源的设置就是看
>
> ```java
> player.setDataSource（）
> ```
>
> 的几个重载方法，这里就不多讲了。







##### 准备播放(解析音频/视频编码)

**准备过程实则是对音视频文件进行解码。**

同步准备。

***不是很推荐使用这种方案。***

主要原因是解码的过程涉及到大量的IO操作。所以**存在阻塞UI**线程的可能。

```java
try {
    mediaPlayer.prepare();
} catch (IOException e) {
    e.printStackTrace();
}
```

异步准备，异步本质也就是讲准备过程放到一个新的线程中处理。

```java
mediaPlayer.prepareAsync();
```









##### 播放视频/音频

播放视频的代码很短，就一句hh。

```java
mediaPlayer.start();
```

关于音频的播放就到这里了。

下面就演示以下视频播放需要进行哪些配置。

视频的播放其实和音频是类似的。

也需要经过 实例化MediaPlayer->配置->准备->开始 这几个步骤

但是配置有些不太一样(毕竟播放的是视频，不是音频了)，除此之外还多了一个也就是视频的内容需要呈现在一个View上，这个也得做设置。

这里就直接贴一下代码

```java
//由于我是用的之前的播放音乐的那个mediaPlayer所以使用它进行视频播放的时候得重新设置。
            player.reset();
            //设置播放设置
            player.setAudioAttributes(
                    new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()

            );
            //设置数据源
            try {
                player.setDataSource(
                        this,Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.solitary_brave)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置准备完成的监听
            player.setOnPreparedListener(MediaPlayer::start);
            //设置MediaPlayer视频内容的载体
            player.setSurface(new Surface(textureView.getSurfaceTexture()));
            //异步准备(解码)
            player.prepareAsync();
```









##### MediaPlayer常用API总结

- mediaPlayer.start()
- mediaPlayer.pause()
- mediaPlayer.prepare()
- mediaPlayer.seekTo()
- mediaPlayer.prepareAsync()
- mediaPlayer.setOnPreparedListener()
- mediaPlayer.getDuration()
- mediaPlayer.isPlaying()
- mediaPlayer.setOnCompletionListener()
- mediaPlayer.setDataSource()
- mediaPlayer.setAudioAttributes()
- mediaPlayer.release()
- mediaPlayer.reset()

详细介绍可自行翻阅[官方文档](https://developer.android.google.cn/reference/android/media/MediaPlayer)。





##### 使用注意事项

- 一个MediaPlayer只能播放一首歌，也就是说多个实例可以播放多首，所以为了更符合观感尽量保持单例。
- MediaPlayer最好和Service一起使用，Service是一个不需要图形界面的Activity，而MediaPlayer所做的事情确实就是这样，不需要图形界面，只需要执行。
- 如果在Activity中使用MediaPlayer，考虑使用preparesync(),prepare()可能会阻塞UI线程，在Activity destroy的时候得释放资源也就是release()。













### SoundPool

SoundPool是用来播放音效的，所谓音效就是比较短的音频，比如"支付宝到账XXX元","拼多多现金已到账",QQ消息三全音提示......

嗯！SoudPool很好。

但是MediaPlayer可以播放音视频，为什么还要需要SoundPool呢？

主要的原因是MediaPlayer是重量级的，它需要消耗很多的资源，他在播放之前要进行音视频文件的解码这样极不方便也比较消耗性能，而且MediaPlayer的设定也是为了歌曲或者视频的播放，也就是说设定都不符合音效，所以处于对资源的分配有了SoundPool这个东西，**凡是存在，必有道理。**



##### SoundPool的使用

相比于MediaPlayer，SoundPool要简单不少。

SoundPool使用只需要三个过程

- 初始化SoundPool对象
- 加载音效资源
- 播放音效资源



##### 初始化SoundPool

```java
new SoundPool.Builder().
        setMaxStreams(Integer.MAX_VALUE)
        .build();
```

这里就是setMaxStreams这个的意思是支持多少的音效同时播放。（MediaPlayer一个实例只能播放一首歌）。

这里设置为maxValue，设置为多少其实都可以。



##### 加载音效资源

```java
int sid = soundPool.load(this, rawId, 1);
```

这里需要注意 load之后会生成一个id，播放需要使用这个id来播放对应的资源。





##### 播放音效

```java
soundPool.play(sid, 1f, 1f, 1, 0, 1f);
```

看起来有点复杂，play里面需要转入比较多的参数。

第一个load之后生成的id

第二个左声道的声音

第二个右声道的声音

第三个循环次数

第四个播放的速率



SoundPool的使用大致就是如此



如果想了解更多内容可以翻阅[官方文档](https://developer.android.google.cn/reference/kotlin/android/media/SoundPool?hl=en)



### Camera

> Camera是什么我就不多讲了，相机，没了，相机时用来干什么的？除了拍照，录像还能干啥嘛。



#### 拍照

试想这完成一个拍照软件，点击后跳转到相机，然后照片拍好以后在页面里显示刚才拍的照片。

- 直接返回

  拍照就是调用相机，调用相机其实是比较轻松的，startActivityForResult完事。

  ```java
  intent.putExtra(MediaStore.EXTRA_OUTPUT);
  startActivityForResult(intent, RESULT_CODE);
  ```

  然后再onActivityResult设置

  ```java
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (resultCode != RESULT_OK) return;
     	Bitmap bitmap = (Bitmap) data.getExtras().get("data");
      ivShow.setImageBitmap(bitmap);
  }
  ```

  好像核心代码并不难。

  

  ![image-20211211231800217](https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211211231800217.png)

  <img src="https://gitee.com/False_Mask/pics/raw/master/PicsAndGifs/image-20211211231656916.png" alt="image-20211211231656916" style="zoom:25%;" />

  

  如果这样的话可以看见图片是比较糊的。主要的原因是直接通过照相机放回的Bitmap是一个缩略图，所以比较糊。



- 本地存储后使用图片文件进行展示。

  只要在startActivity中传入一个键值对即可，photoURI为图片的存放Uri路径。

  ```java
  intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
  ```

  这里的代码有些长有些难以理解，大致说说，先通过getExternalFilesDir获取了一个本地存储路径。

  通过createTempFile创建一个唯一的临时文件，然后吧路径记录下来，然后把File对象返回，最后把File通过FileProvider转化为Uri传入到Intent里面。

  ```java
  File picFile = null;
              try {
                  picFile = createImageFile();
              } catch (IOException e) {
                  e.printStackTrace();
              }
  
  Uri photoURI = FileProvider.getUriForFile(this, getPackageName() + "." + "fileprovider", picFile);
  
  private File createImageFile() throws IOException {
          File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
          File image = File.createTempFile(
                  "pic",  /* prefix */
                  ".jpg",         /* suffix */
                  storageDir      /* directory */
          );
  
          // Save a file: path for use with ACTION_VIEW intents
          currentPhotoPath = image.getAbsolutePath();
          return image;
      }
  ```

  对了在使用FileProvider的时候得在manifest里面加入一个application标签下加入一个provider配置

  ```xml
  <provider
      android:name="androidx.core.content.FileProvider"
      android:authorities="com.example.multimedia.fileprovider"
      android:exported="false"
      android:grantUriPermissions="true">
      <meta-data
          android:name="android.support.FILE_PROVIDER_PATHS"
          android:resource="@xml/file_paths" />
  </provider>
  ```

  以及一个xml配置文件

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <paths xmlns:android="http://schemas.android.com/apk/res/android">
      <external-files-path
          name="my_images"
          path="Pictures"/>
  </paths>
  ```

  

#### 录像

类似于前面的功能，我们可以实现很轻松实现，跳转到相机，录一个视频，然后播放。(其实比拍照还简单)

```java
public static final int REQUEST_VIDEO_CAPTURE = 1;
Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);

......

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            vvShow.setVideoURI(videoUri);
            vvShow.start();
        }
    }
```

通过Intent跳转到录制界面，然后在ActivityResult之时获取intent里面uri信息，最后通过ViewView播放。(其实使用MediaPlayer播放视频也可以)。

 

### 参考

MediaPlayer

- [MediaPlayer overFlow](https://developer.android.google.cn/guide/topics/media/mediaplayer?hl=en)
- [MediaPlayer APIs](https://developer.android.google.cn/reference/android/media/MediaPlayer)

SoundPool

- [Sound Pool APIs](https://developer.android.google.cn/reference/kotlin/android/media/SoundPool?hl=en)

Camera

- [Take Photos](https://developer.android.google.cn/training/camera/photobasics)
- [Record videos](https://developer.android.google.cn/training/camera/videobasics)





## 网络请求





# 最后





