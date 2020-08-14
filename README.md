<h1>前端项目结构说明</h1>

<h2>目录结构</h2>

```javascript
src
│  App.vue					// 根组件
│  main.js					// 程序入口文件
│  README.md				// 结构说明
│  
├─assets
│  │  logo.png				// vue的logo
│  │  
│  ├─css 					//可能用到的css
│  │      swiper.min.css
│  │      visual.css
│  │      
│  ├─fonts					//导入的字体
│  │      DIGITALDREAMFAT.ttf
│  │      DIGITALDREAMFAT.woff
│  │      DIGITALDREAMFAT.woff2
│  │      yjsz.TTF
│  │      
│  ├─img
│  │  │  
│  │  ├─chart1				//可能用到的图片
│  │  │      
│  │  ├─chart2				//可能用到的图片
│  │  │      
│  │  ├─dynamic				//可能用到的图片
│  │  │      
│  │  └─screen_img			//Screen页面用到的图片
│  │          1.jpg			//背景图
│  │          head_bg.png	//页面头图
│  │          head_bg2.png	//页面头图
│  │          jt.png	 	//地球的边缘高亮图
│  │          lbx.png		//地球的边缘网结构图
│  │          lei.png		//雷天气图标
│  │          line.png		//组件边框的角
│  │          map.png		//地球图
│  │          qing.png		//晴天气图标
│  │          shachen.png	//沙尘天气图标
│  │          wu.png		//雾天气图标
│  │          xue.png		//雪天气图标
│  │          yin.png		//阴天气图标
│  │          yu.png		//雨天气图标
│  │          yun.png		//多云天气图标
│  │          
│  ├─js
│  │      request.js		//封装的请求类
│  │      utils.js			//封装的工具类
│  │      
│  └─styles
│          base.scss		//对浏览器的样式
│          common.scss		//公共样式
│          
├─components
│  │  index.js				//在此导入组件
│  │  
│  ├─colorfulRadar			//Home页面的雷达图组件
│  │      index.vue
│  │      
│  ├─dynamicLine			//Home页面的折线图组件
│  │      dynamicLine.vue
│  │      
│  ├─gauge					//Home页面的仪表盘组件
│  │      index.vue
│  │      
│  ├─HelloWorld				//根页面的初始vue项目界面
│  │      index.vue
│  │      
│  ├─leida					//Home页面的雷达图组件				
│  │      index.vue
│  │      
│  ├─ringPie				//Home页面的饼图组件
│  │      index.vue
│  │      
│  ├─Screen					//Screen页面的组件都在此
│  │      barChart.vue		//柱状图组件
│  │      dashboard.vue		//仪表盘组件
│  │      lineChart.vue		//折线图组件
│  │      list.vue			//列表组件
|  |      threeDashboards.vue //三雷达图组件
|  |      configChart.vue    //可配置组件
│  │      resume.vue			//报警部分组件
│  │      pieChart.vue		//饼图组件
|  |      pieChart1.vue     //饼图组件（纯前端）
│  │      radarChart.vue	//雷达图组件
│  │      waterSphereChart.vue	 //水球图组件
│  │      mapChart.vue     //地图组件
│  │      
│  └─seamless				//Home页面的动态信息组件
│          index.vue
│          
├─router					//路由，在此配置路径访问的页面
│      index.js
│      
├─store						//目前为空
│      index.js
│      
└─views						//两大页面
        Home.vue			//Home页面
        Screen.vue			//Screen页面
```

<h2>路径访问</h2>

访问Screen页面：

<http://localhost:8081/#/Screen>

<h2>界面展示</h2>

<img src="./README_Image/screen2.0.jpg"></img>

<h2>Screen页面html结构说明</h2>

<img src="./README_Image/1596495460545.png"></img>

<img src="./README_Image/1596495540204.png"></img>

<h2>风险指标</h2>

资本充足率=（核心资本+附属资本）/风险加权资产，不应低于8%

核心资本充足率=核心资本/风险加权资产，不应低于4%

核心一级资本充足率，不低于5%

资产利润率=税后净利润/平均资产总额*100%，不应低于0.6%

资本利润率=税后净利润/平均净资产*100%，不应低于11%

不良贷款率=不良贷款/贷款总额，不应高于5%

不良资产率=不良信用风险资产/信用风险资产总额 ，不应高于4%

流动性比例=流动性资产余额/流动性负债余额，不应低于25%

<span style="color:red">核心负债依存度=核心负债/总负债，不应低于60%（新增）</span>

<h2>衡量银行的指标</h2>

风险加权资产收益率RORWA=归母净利润 / 期末风险加权资产 

<h2>数据图表定义说明</h2>

**三仪表盘**

表名：工行风险指标

值：

资本充足率、核心资本充足率、核心一级资本充足率

资产利润率、资本利润率、不良贷款率

流动性比例、不良资产率、核心负债依存度

<span style="color:red">风险提示：已经按各个指标的阈值，设置了风险提示<br>即：当低于阈值时指针和百分比变为红色</span>

轮播：

<img src="./README_Image/info0.JPG"></img>

<img src="./README_Image/info1.JPG"></img>

<img src="./README_Image/info2.jpg"></img>

**柱状图**

表名：工行重要风险指标

X轴：比率（默认2020年平均值）

Y轴：各个风险指标

资产利润率、资本利润率、不良贷款率、不良资产率、流动性比例

<img src="./README_Image/barline.jpg"></img>

**饼图**

表名：工行营业收入构成

分类：利息、投资收益、公允价值变动损益、汇总收益、手续费及佣金收入、其他业务收入

<img src="./README_Image/pie.jpg"></img>

**报警模块**

水球图：省分行风险比率<span style="color:red">（已与下面两个参数绑定）</span>

​		=存在风险的省分行数/工商银行省分行总数

参数：工商银行省分行总数、存在风险的省分行数

<img src="./README_Image/resume.jpg"></img>

**地图**

数据：全国省分行所在省份名、以及对应的4个指标

<span style="color:red">在组件data域的data为每个省份定义了value，表明当前省分行有多少个指标有风险（共展示的4个），如风险报警不使用，可无视</span>

<img src="./README_Image/mapdata.jpg"></img>

<img src="./README_Image/map.jpg"></img>

**折线图**

表名：2020年工行核心指标变化

X轴：今年的月份

Y轴：百分比

两条线：资本充足率与核心资本充足率

<img src="./README_Image/line.jpg"></img>

**雷达图**

表名：四大行对比

五个指标：资本充足率、核心一级资本充足率、资产利润率、不良贷款率、净息差

银行：工行、中行、建行、农行

<img src="./README_Image/radar.jpg"></img>

**可配置 折线图**

 选项：工行、中行、建行、农行

 x轴：资本充足率、核心资本充足率、资产利润率

y轴：百分比

<img src="./README_Image/config_line.jpg"></img>

**可配置 雷达图**

 选项：工行、中行、建行、农行

 值：RORWA

<img src="./README_Image/config_dashboard.jpg"></img>

**列表**

表名：17家全国性银行总资产规模

表头：公司、2019年资产值（万亿）、2020年资产值（万亿）增幅、类型

<img src="./README_Image/info.jpg"></img>


<h1>后端项目提供的API</h1>

http://localhost:8088/GetData/{type}
示例：http://localhost:8088/GetData/Plane

<h4>其中{type}可选的值有：</h4>
> **Plane**仪表盘
> 返回JSON数据示例：

> [{"name":"资产利润率","value":2},
>
> {"name":"资本利润率","value":13},
>
> {"name":"不良贷款率","value":1},
>
> {"name":"流动性比例","value":35},
>
> {"name":"不良资产率","value":2},
>
> {"name":"核心负债依存度","value":69},
>
> {"name":"核心资本充足率","value":14},
>
> {"name":"资本充足率","value":17},
>
> {"name":"核心一级资本充足率","value":13}]

```
可视化数据：
+--------------------+-------+
| NAME               | VALUE |
+--------------------+-------+
| 资产利润率         | 2     |
| 资本利润率         | 13    |
| 不良贷款率         | 1     |
| 流动性比例         | 35    |
| 不良资产率         | 2     |
| 核心负债依存度     | 69    |
| 核心资本充足率     | 14    |
| 资本充足率         | 17    |
| 核心一级资本充足率 | 13    |
+--------------------+-------+
```

> **Bar**柱状图
> 返回JSON数据示例：
> [{"name":"资产利润率","value":2},
> {"name":"资本利润率","value":13},
> {"name":"不良贷款率","value":1},
> {"name":"不良资产率","value":2},
> {"name":"流动性比例","value":35}

```
可视化数据：
+------------+-------+
| NAME       | VALUE |
+------------+-------+
| 资产利润率 | 2     |
| 资本利润率 | 13    |
| 不良贷款率 | 1     |
| 不良资产率 | 2     |
| 流动性比例 | 35    |
+------------+-------+
```

> **Pie**饼图
> 返回JSON数据示例：

> [{"name":"利息净收入","value":590260},
> 
> {"name":"投资收益","value":37772},
> 
> {"name":"公允价值变动收益","value":24828},
> 
> {"name":"汇兑及汇率产品净收益","value":13096},
> 
> {"name":"手续费及佣金收入","value":190752},
> 
> {"name":"其他业务收入","value":20160}]

```
可视化数据：
+----------------------+--------+
| NAME                 | VALUE  |
+----------------------+--------+
| 利息净收入           | 590260 |
| 投资收益             |  37772 |
| 公允价值变动收益     |  24828 |
| 汇兑及汇率产品净收益 |  13096 |
| 手续费及佣金收入     | 190752 |
| 其他业务收入         |  20160 |
+----------------------+--------+
```

> **Line**折线图
> JSON数据：

> [{"name":"资本充足率","value":15,"valuedate":"2020-01-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-02-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-03-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-04-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-05-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-06-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-07-01"},
>
> {"name":"资本充足率","value":16,"valuedate":"2020-08-01"},
>
> {"name":"资本充足率","value":16,"valuedate":"2020-09-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-10-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-11-01"},
>
> {"name":"资本充足率","value":15,"valuedate":"2020-12-01"},
>
> {"name":"核心资本充足率","value":12,"valuedate":"2020-01-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-02-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-03-01"},
>
> {"name":"核心资本充足率","value":12,"valuedate":"2020-04-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-05-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-06-01"},
>
> {"name":"核心资本充足率","value":12,"valuedate":"2020-07-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-08-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-09-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-10-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-11-01"},
>
> {"name":"核心资本充足率","value":13,"valuedate":"2020-12-01"}]

```
可视化数据：
+----------------+-------+------------+
| NAME           | VALUE | valuedate  |
+----------------+-------+------------+
| 资本充足率     | 15    | 2020-01-01 |
| 资本充足率     | 15    | 2020-02-01 |
| 资本充足率     | 15    | 2020-03-01 |
| 资本充足率     | 15    | 2020-04-01 |
| 资本充足率     | 15    | 2020-05-01 |
| 资本充足率     | 15    | 2020-06-01 |
| 资本充足率     | 15    | 2020-07-01 |
| 资本充足率     | 16    | 2020-08-01 |
| 资本充足率     | 16    | 2020-09-01 |
| 资本充足率     | 15    | 2020-10-01 |
| 资本充足率     | 15    | 2020-11-01 |
| 资本充足率     | 15    | 2020-12-01 |
| 核心资本充足率 | 12    | 2020-01-01 |
| 核心资本充足率 | 13    | 2020-02-01 |
| 核心资本充足率 | 13    | 2020-03-01 |
| 核心资本充足率 | 12    | 2020-04-01 |
| 核心资本充足率 | 13    | 2020-05-01 |
| 核心资本充足率 | 13    | 2020-06-01 |
| 核心资本充足率 | 12    | 2020-07-01 |
| 核心资本充足率 | 13    | 2020-08-01 |
| 核心资本充足率 | 13    | 2020-09-01 |
| 核心资本充足率 | 13    | 2020-10-01 |
| 核心资本充足率 | 13    | 2020-11-01 |
| 核心资本充足率 | 13    | 2020-12-01 |
+----------------+-------+------------+
```

> **Rada**雷达图
> JSON数据：

> [{"bank":"工商银行","name":"资本充足率","value":17},
> {"bank":"农业银行","name":"资本充足率","value":16},
> {"bank":"建设银行","name":"资本充足率","value":18},
> {"bank":"中国银行","name":"资本充足率","value":16},
> {"bank":"工商银行","name":"核心一级资本充足率","value":13},
> {"bank":"农业银行","name":"核心一级资本充足率","value":11},
> {"bank":"建设银行","name":"核心一级资本充足率","value":14},
> {"bank":"中国银行","name":"核心一级资本充足率","value":11},
> {"bank":"工商银行","name":"不良贷款率","value":1},
> {"bank":"农业银行","name":"不良贷款率","value":1},
> {"bank":"建设银行","name":"不良贷款率","value":1},
> {"bank":"中国银行","name":"不良贷款率","value":1},
> {"bank":"工商银行","name":"净息差","value":2},
> {"bank":"农业银行","name":"净息差","value":2},
> {"bank":"建设银行","name":"净息差","value":2},
> {"bank":"中国银行","name":"净息差","value":2},
> {"bank":"工商银行","name":"资产利润率","value":2},
> {"bank":"农业银行","name":"资产利润率","value":2},
> {"bank":"建设银行","name":"资产利润率","value":2},
> {"bank":"中国银行","name":"资产利润率","value":2}]

```
可视化数据：
+--------------------+-------+----------+
| NAME               | VALUE | bank     |
+--------------------+-------+----------+
| 资本充足率         | 17    | 工商银行 |
| 资本充足率         | 16    | 农业银行 |
| 资本充足率         | 18    | 建设银行 |
| 资本充足率         | 16    | 中国银行 |
| 核心一级资本充足率 | 13    | 工商银行 |
| 核心一级资本充足率 | 11    | 农业银行 |
| 核心一级资本充足率 | 14    | 建设银行 |
| 核心一级资本充足率 | 11    | 中国银行 |
| 不良贷款率         | 1     | 工商银行 |
| 不良贷款率         | 1     | 农业银行 |
| 不良贷款率         | 1     | 建设银行 |
| 不良贷款率         | 1     | 中国银行 |
| 净息差             | 2     | 工商银行 |
| 净息差             | 2     | 农业银行 |
| 净息差             | 2     | 建设银行 |
| 净息差             | 2     | 中国银行 |
| 资产利润率         | 2     | 工商银行 |
| 资产利润率         | 2     | 农业银行 |
| 资产利润率         | 2     | 建设银行 |
| 资产利润率         | 2     | 中国银行 |
+--------------------+-------+----------+
```

> **CLine**可配置折线图
> JSON数据：

> [{"bank":"中国银行","name":"核心资本充足率","value":13},
> {"bank":"中国银行","name":"资产利润率","value":2},
> {"bank":"中国银行","name":"资本充足率","value":15},
> {"bank":"农业银行","name":"核心资本充足率","value":13},
> {"bank":"农业银行","name":"资产利润率","value":2},
> {"bank":"农业银行","name":"资本充足率","value":16},
> {"bank":"工商银行","name":"核心资本充足率","value":13},
> {"bank":"工商银行","name":"资产利润率","value":2},
> {"bank":"工商银行","name":"资本充足率","value":15},
> {"bank":"建设银行","name":"核心资本充足率","value":13},
> {"bank":"建设银行","name":"资产利润率","value":2},
> {"bank":"建设银行","name":"资本充足率","value":15}]

```
可视化数据：
+----------+----------------+-------+
| bank     | NAME           | value |
+----------+----------------+-------+
| 中国银行 | 核心资本充足率 | 13    |
| 中国银行 | 资产利润率     | 2     |
| 中国银行 | 资本充足率     | 15    |
| 农业银行 | 核心资本充足率 | 13    |
| 农业银行 | 资产利润率     | 2     |
| 农业银行 | 资本充足率     | 16    |
| 工商银行 | 核心资本充足率 | 13    |
| 工商银行 | 资产利润率     | 2     |
| 工商银行 | 资本充足率     | 15    |
| 建设银行 | 核心资本充足率 | 13    |
| 建设银行 | 资产利润率     | 2     |
| 建设银行 | 资本充足率     | 15    |
+----------+----------------+-------+
```

> **CRada**可配置雷达图
> 返回JSON数据示例：

> [{"name":"工商银行","value":2},
> {"name":"农业银行","value":1},
> {"name":"建设银行","value":2},
> {"name":"中国银行","value":1}]

```
可视化数据：
+----------+-------+
| NAME     | value |
+----------+-------+
| 工商银行 | 2     |
| 农业银行 | 1     |
| 建设银行 | 2     |
| 中国银行 | 1     |
+----------+-------+
```

> **MList**列表
> 返回JSON数据：

> [{"bank":"工商银行","growth_rate":9,"total_assets_2019":27699540,"total_assets_2020":30109436,"type":"国有行"},
> {"bank":"农业银行","growth_rate":10,"total_assets_2019":22609471,"total_assets_2020":24878288,"type":"国有行"},
> {"bank":"建设银行","growth_rate":10,"total_assets_2019":23222693,"total_assets_2020":25436261,"type":"国有行"},
> {"bank":"中国银行","growth_rate":7,"total_assets_2019":21267275,"total_assets_2020":22769744,"type":"国有行"},
> {"bank":"交通银行","growth_rate":4,"total_assets_2019":9531171,"total_assets_2020":9905600,"type":"国有行"},
> {"bank":"邮储银行","growth_rate":7,"total_assets_2019":9516211,"total_assets_2020":10216706,"type":"国有行"},
> {"bank":"招商银行","growth_rate":10,"total_assets_2019":6745729,"total_assets_2020":7417240,"type":"股份行"},
> {"bank":"兴业银行","growth_rate":6,"total_assets_2019":6711657,"total_assets_2020":7145681,"type":"股份行"},
> {"bank":"浦发银行","growth_rate":11,"total_assets_2019":6289606,"total_assets_2020":7005929,"type":"股份行"},
> {"bank":"中信银行","growth_rate":11,"total_assets_2019":6066714,"total_assets_2020":6750433,"type":"股份行"},
> {"bank":"民生银行","growth_rate":11,"total_assets_2019":5994822,"total_assets_2020":6681841,"type":"股份行"},
> {"bank":"光大银行","growth_rate":9,"total_assets_2019":4357332,"total_assets_2020":4733431,"type":"股份行"},
> {"bank":"平安银行","growth_rate":15,"total_assets_2019":3418592,"total_assets_2020":3939070,"type":"股份行"},
> {"bank":"华夏银行","growth_rate":13,"total_assets_2019":2680580,"total_assets_2020":3020789,"type":"股份行"},
> {"bank":"广发银行","growth_rate":12,"total_assets_2019":2360850,"total_assets_2020":2632797,"type":"股份行"},
> {"bank":"浙商银行","growth_rate":9,"total_assets_2019":1646694,"total_assets_2020":1800785,"type":"股份行"},
> {"bank":"渤海银行","growth_rate":9,"total_assets_2019":1034451,"total_assets_2020":1113116,"type":"股份行"}]

```
可视化数据：
+----------+-------------+-------------------+-------------------+--------+
| bank     | growth_rate | total_assets_2019 | total_assets_2020 | type   |
+----------+-------------+-------------------+-------------------+--------+
| 工商银行 | 9           |          27699540 |          30109436 | 国有行 |
| 农业银行 | 10          |          22609471 |          24878288 | 国有行 |
| 建设银行 | 10          |          23222693 |          25436261 | 国有行 |
| 中国银行 | 7           |          21267275 |          22769744 | 国有行 |
| 交通银行 | 4           |           9531171 |           9905600 | 国有行 |
| 邮储银行 | 7           |           9516211 |          10216706 | 国有行 |
| 招商银行 | 10          |           6745729 |           7417240 | 股份行 |
| 兴业银行 | 6           |           6711657 |           7145681 | 股份行 |
| 浦发银行 | 11          |           6289606 |           7005929 | 股份行 |
| 中信银行 | 11          |           6066714 |           6750433 | 股份行 |
| 民生银行 | 11          |           5994822 |           6681841 | 股份行 |
| 光大银行 | 9           |           4357332 |           4733431 | 股份行 |
| 平安银行 | 15          |           3418592 |           3939070 | 股份行 |
| 华夏银行 | 13          |           2680580 |           3020789 | 股份行 |
| 广发银行 | 12          |           2360850 |           2632797 | 股份行 |
| 浙商银行 | 9           |           1646694 |           1800785 | 股份行 |
| 渤海银行 | 9           |           1034451 |           1113116 | 股份行 |
+----------+-------------+-------------------+-------------------+--------+
```

> **MMap**地图
>
> 返回JSON数据(**部分数据名称是拼音缩写**)：

> [{"hxzbczl":11,"ldxbl":35,"province":"安徽分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":11,"ldxbl":34,"province":"北京分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":10,"ldxbl":34,"province":"重庆分行","zbczl":12,"zblrl":13},
>
> {"hxzbczl":10,"ldxbl":32,"province":"大连分行","zbczl":12,"zblrl":11},
>
> {"hxzbczl":9,"ldxbl":31,"province":"福建分行","zbczl":11,"zblrl":12},
>
> {"hxzbczl":11,"ldxbl":34,"province":"甘肃分行","zbczl":13,"zblrl":10},
>
> {"hxzbczl":10,"ldxbl":37,"province":"广东分行","zbczl":12,"zblrl":12},
>
> {"hxzbczl":11,"ldxbl":37,"province":"广西分行","zbczl":12,"zblrl":11},
>
> {"hxzbczl":11,"ldxbl":33,"province":"贵州分行","zbczl":13,"zblrl":10},
>
> {"hxzbczl":10,"ldxbl":31,"province":"海南分行","zbczl":11,"zblrl":13},
>
> {"hxzbczl":9,"ldxbl":32,"province":"河北分行","zbczl":11,"zblrl":12},
>
> {"hxzbczl":10,"ldxbl":31,"province":"河南分行","zbczl":11,"zblrl":10},
>
> {"hxzbczl":10,"ldxbl":35,"province":"黑龙江分行","zbczl":12,"zblrl":13},
>
> {"hxzbczl":12,"ldxbl":37,"province":"湖北分行","zbczl":14,"zblrl":11},
>
> {"hxzbczl":11,"ldxbl":31,"province":"湖南分行","zbczl":13,"zblrl":12},
>
> {"hxzbczl":11,"ldxbl":37,"province":"吉林分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":10,"ldxbl":32,"province":"江苏分行","zbczl":12,"zblrl":11},
>
> {"hxzbczl":13,"ldxbl":32,"province":"江西分行","zbczl":16,"zblrl":10},
>
> {"hxzbczl":13,"ldxbl":34,"province":"辽宁分行","zbczl":15,"zblrl":11},
>
> {"hxzbczl":13,"ldxbl":31,"province":"内蒙古分行","zbczl":15,"zblrl":12},
>
> {"hxzbczl":13,"ldxbl":32,"province":"宁波分行","zbczl":15,"zblrl":13},
>
> {"hxzbczl":12,"ldxbl":35,"province":"宁夏分行","zbczl":14,"zblrl":12},
>
> {"hxzbczl":12,"ldxbl":36,"province":"青岛分行","zbczl":14,"zblrl":12},
>
> {"hxzbczl":13,"ldxbl":36,"province":"青海分行","zbczl":15,"zblrl":12},
>
> {"hxzbczl":13,"ldxbl":36,"province":"山东分行","zbczl":16,"zblrl":11},
>
> {"hxzbczl":11,"ldxbl":30,"province":"山西分行","zbczl":13,"zblrl":12},
>
> {"hxzbczl":11,"ldxbl":35,"province":"陕西分行","zbczl":13,"zblrl":14},
>
> {"hxzbczl":9,"ldxbl":36,"province":"上海分行","zbczl":10,"zblrl":11},
>
> {"hxzbczl":11,"ldxbl":35,"province":"深圳分行","zbczl":13,"zblrl":12},
>
> {"hxzbczl":10,"ldxbl":36,"province":"四川分行","zbczl":12,"zblrl":11},
>
> {"hxzbczl":10,"ldxbl":34,"province":"苏州分行","zbczl":12,"zblrl":11},
>
> {"hxzbczl":11,"ldxbl":35,"province":"天津分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":11,"ldxbl":33,"province":"西藏分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":11,"ldxbl":38,"province":"厦门分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":12,"ldxbl":34,"province":"新疆分行","zbczl":14,"zblrl":10},
>
> {"hxzbczl":11,"ldxbl":33,"province":"云南分行","zbczl":13,"zblrl":13},
>
> {"hxzbczl":10,"ldxbl":33,"province":"浙江分行","zbczl":12,"zblrl":12}]

```
可视化数据：
+------------+-------+---------+-------+-------+
| province   | zbczl | hxzbczl | zblrl | ldxbl |
+------------+-------+---------+-------+-------+
| 安徽分行   | 13    | 11      | 13    | 35    |
| 北京分行   | 13    | 11      | 13    | 34    |
| 重庆分行   | 12    | 10      | 13    | 34    |
| 大连分行   | 12    | 10      | 11    | 32    |
| 福建分行   | 11    | 9       | 12    | 31    |
| 甘肃分行   | 13    | 11      | 10    | 34    |
| 广东分行   | 12    | 10      | 12    | 37    |
| 广西分行   | 12    | 11      | 11    | 37    |
| 贵州分行   | 13    | 11      | 10    | 33    |
| 海南分行   | 11    | 10      | 13    | 31    |
| 河北分行   | 11    | 9       | 12    | 32    |
| 河南分行   | 11    | 10      | 10    | 31    |
| 黑龙江分行 | 12    | 10      | 13    | 35    |
| 湖北分行   | 14    | 12      | 11    | 37    |
| 湖南分行   | 13    | 11      | 12    | 31    |
| 吉林分行   | 13    | 11      | 13    | 37    |
| 江苏分行   | 12    | 10      | 11    | 32    |
| 江西分行   | 16    | 13      | 10    | 32    |
| 辽宁分行   | 15    | 13      | 11    | 34    |
| 内蒙古分行 | 15    | 13      | 12    | 31    |
| 宁波分行   | 15    | 13      | 13    | 32    |
| 宁夏分行   | 14    | 12      | 12    | 35    |
| 青岛分行   | 14    | 12      | 12    | 36    |
| 青海分行   | 15    | 13      | 12    | 36    |
| 山东分行   | 16    | 13      | 11    | 36    |
| 山西分行   | 13    | 11      | 12    | 30    |
| 陕西分行   | 13    | 11      | 14    | 35    |
| 上海分行   | 10    | 9       | 11    | 36    |
| 深圳分行   | 13    | 11      | 12    | 35    |
| 四川分行   | 12    | 10      | 11    | 36    |
| 苏州分行   | 12    | 10      | 11    | 34    |
| 天津分行   | 13    | 11      | 13    | 35    |
| 西藏分行   | 13    | 11      | 13    | 33    |
| 厦门分行   | 13    | 11      | 13    | 38    |
| 新疆分行   | 14    | 12      | 10    | 34    |
| 云南分行   | 13    | 11      | 13    | 33    |
| 浙江分行   | 12    | 10      | 12    | 33    |
+------------+-------+---------+-------+-------+
```



<h4>sse监听器可选的名称</h4>

```vue
监听器的名称将决定监听器调用的回调函数接收的图表数据类型

例如：
在Screen.vue中：
created() {
            //在这里写sse数据接收监听器
            this.$sse.getSource().addEventListener('pievalues', this.pieListener)
			//其他sse数据监听器....
        },
mounted() {
            //天气
            this.getWeather();
            //每隔一天调用函数getWeather
            this.timer = setInterval(() => {
                this.getWeather();
            }, 1000 * 60 * 60)
            //时间日期
            this.getTimes();
        },
methods: {
            pieListener:function (e) {
                var data1=JSON.parse(e.data);
                console.log(data1)
                this.$store.commit('pie/setValues',data1)
            }
            
在created中创建名字为pievalues（饼图数据）的监听器，回调函数为pieListener（定义在methods中），当后端有饼图的数据推送到前端时会自动传到回调函数中的e中，于是就可以对该数据进行任意处理（一般是把数据传入自己的组件store中）
            
```

```
可选的监听器名称（不可以输入别的，否则会接收不到数据）
1、pievalues       饼图
2、barvalues       柱状图
3、panelvalues     仪表盘
4、entryvalues     动态列表
5、mapvalues       地图数据
6、clinevalues     可配置折线图
7、linevalues      折线图
8、cradavalues     可配置雷达图
9、radavalues      雷达图
```

