# ************************************************************
# Sequel Ace SQL dump
# 版本号： 20083
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# 主机: localhost (MySQL 8.3.0)
# 数据库: biji
# 生成时间: 2025-02-20 08:49:00 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# 转储表 t_note
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_note`;

CREATE TABLE `t_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `t_note` WRITE;
/*!40000 ALTER TABLE `t_note` DISABLE KEYS */;

INSERT INTO `t_note` (`id`, `user_id`, `title`, `content`, `created_at`, `updated_at`, `image`)
VALUES
	(2,4,'题目','内容','2024-11-01 19:25:39','2024-11-03 00:00:00','https://note-content.oss-cn-shenzhen.aliyuncs.com/72e7806c9be0c3defe8ca99d8091b10d73459549.jpg'),
	(3,4,'标题1','内容1','2024-11-02 18:00:18','2024-11-03 00:00:00','https://note-content.oss-cn-shenzhen.aliyuncs.com/mao1.jpg'),
	(5,4,'标题学习','内容学习','2024-11-04 15:27:39','2024-11-19 00:00:00','https://note-content.oss-cn-shenzhen.aliyuncs.com/72e7806c9be0c3defe8ca99d8091b10d73459549.jpg'),
	(6,4,'标题学习','内容学习','2024-11-04 15:31:51','2024-11-19 00:00:00','https://note-content.oss-cn-shenzhen.aliyuncs.com/72e7806c9be0c3defe8ca99d8091b10d73459549.jpg'),
	(8,4,'研究title','研究content','2024-11-04 16:04:49','2024-11-21 00:00:00','https://note-content.oss-cn-shenzhen.aliyuncs.com/72e7806c9be0c3defe8ca99d8091b10d73459549.jpg'),
	(11,4,'aaajfapfjaljfak','aghhj','2024-11-22 00:00:00','2024-11-22 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Fri Nov 22 19:33:54 CST 20246e1e200bd30945ae982a5ccb125681caimage_picker_7442B733-8949-4145-835E-FC825C033CD8-32152-000000C9CAF6D943.jpg'),
	(12,4,'pppppp','agahhsga','2024-11-22 00:00:00','2024-11-22 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Fri Nov 22 20:10:53 CST 20248e63552d2d454e4aaa49eb0b4db61c1fimage_picker_3B1FA03D-A561-43E0-B5F1-3F7528C7567C-49368-000000D638079F0F.jpg'),
	(17,4,'测试内容','测试标题','2024-11-23 00:00:00','2024-11-23 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Nov 23 21:14:00 CST 202449ad6e3179404c9d9a95c3e44a9e4a22image_picker_9C99B65A-140E-4421-BAF6-FA6FE25AAE9B-55069-000000D7BFD5DFEE.jpg'),
	(23,4,'kelly','contentll','2024-11-24 13:14:28','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 13:14:24 CST 2024b838a887a54d4957a36467f9828e2f39image_picker_236035E9-A779-47A9-9CA4-7F347CC524B3-38141-00000041AC7C0CD4.jpg'),
	(24,4,'写点什么','写内容','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 13:19:08 CST 2024d2e81fe54d5a40aa91417060115e2225image_picker_C5C56771-1F9B-4B30-A447-D9C72972A067-38141-00000044B156CCE1.jpg'),
	(25,4,'天天','天天开心','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 16:56:08 CST 2024a4f035161b304733beea1c7845cd7fb4image_picker_EF64518B-8363-4594-A4E1-57F829503D01-95702-0000008D7FEC1DF1.jpg'),
	(33,4,'在来一次','继续开发','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 20:27:54 CST 2024449df4eff6e34ecdb584855255d2c13fimage_picker_3D1BA835-93FB-45E0-A97E-43D56F56BB46-41794-000000D478BCCAD2.jpg'),
	(42,4,'单纯的','测试','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 21:07:30 CST 20248983d61ae6c04e08be0b3896a74cc49eimage_picker_3F8BA442-66AB-4566-A579-780AAFE4660A-51984-000000DE269E4533.jpg'),
	(44,4,'单纯的','测试','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 21:07:55 CST 2024383b1ba8b62f4969b21300fd9942bfe0image_picker_3F8BA442-66AB-4566-A579-780AAFE4660A-51984-000000DE269E4533.jpg'),
	(45,4,'单纯的','测试','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 21:08:35 CST 202449be1bf07d0c441d838331da4cac9434image_picker_3F8BA442-66AB-4566-A579-780AAFE4660A-51984-000000DE269E4533.jpg'),
	(46,4,'单纯的','测试','2024-11-24 00:00:00','2024-11-24 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sun Nov 24 21:16:22 CST 2024b7072bb30eb247ffbc7d0fd46d8e9a32image_picker_3F8BA442-66AB-4566-A579-780AAFE4660A-51984-000000DE269E4533.jpg'),
	(49,4,'Colors','import \'package:flutter/material.dart\';\nimport \'package:flutter_slidable/flutter_slidable.dart\';\nimport \'package:get/get.dart\';\nimport \'package:note/main.dart\';\nimport \'package:note/pages/home/home_controller.dart\';\n\nimport \'../AuthService/AuthService.dart\';\nimport \'../utils/calendarUtil/MSCustomDatePicker.dart\';\n// import \'../controllers/search.dart\';\n\nclass HomePage extends GetView<HomeController> {\n  const HomePage({super.key});\n\n  @override\n  Widget build(BuildContext context) {\n    return Scaffold(\n      appBar: AppBar(\n        title: Obx((){\n          return Text(controller.dateObs.toString());\n        }),\n        actions: [\n          IconButton(\n              icon: Icon(Icons.date_range),\n              padding: EdgeInsets.all(10.0), //\n              onPressed: () {\n                controller.selectDate(context);\n              }),\n        ],\n      ),\n      drawer: Drawer(\n        child: Column(\n          children: [\n            Obx((){\n              return Row(\n                children:  [\n                  Expanded(\n                      flex: 1,\n                      child: UserAccountsDrawerHeader(\n                        accountName:  Text(controller.userMap[\"username\"].toString()),\n                        accountEmail: Text(controller.userMap[\"phone\"].toString().isEmpty ? controller.userMap[\"phone\"].toString() :controller.userMap[\"email\"].toString()),\n\n                        otherAccountsPictures:[\n                          Image.network(\"https://www.itying.com/images/flutter/1.png\"),\n                          Image.network(\"https://www.itying.com/images/flutter/2.png\"),\n                          Image.network(\"https://www.itying.com/images/flutter/3.png\"),\n                        ],\n                        currentAccountPicture:const CircleAvatar(\n                            backgroundImage:NetworkImage(\"https://www.itying.com/images/flutter/3.png\")\n                        ),\n                        decoration: const BoxDecoration(\n                            image: DecorationImage(\n                                fit: BoxFit.cover,\n                                image: NetworkImage(\n                                    \"https://www.itying.com/images/flutter/2.png\"))),\n                      ))\n                ],\n              );\n            }),\n             ListTile(\n              leading: CircleAvatar(\n                child: Icon(Icons.people),\n              ),\n              title: Text(\"个人中心\"),\n              onTap:() {\n                Get.toNamed(\"/UserCenterPage\",arguments: {\n                  \"id\":\"\"\n                });\n              },\n            ),\n            const Divider(),\n            const ListTile(\n              leading: CircleAvatar(\n                child: Icon(Icons.settings),\n              ),\n              title: Text(\"系统设置\"),\n            ),\n            const Divider(),\n          ],\n        ),\n      ),\n      body: Obx((){\n        return RefreshIndicator(\n            child: Container(\n              color: Colors.grey[200], // 这里设置背景颜色为浅灰色\n              child: getListViewBuilder(),\n\n            ), onRefresh: () async {\n                final authService = AuthService();\n                String dateStore = await authService.getDate();\n                DateTime date = DateTime.parse(dateStore);\n                controller.hotList.clear();\n                controller.getHotList(date.toString());\n             },\n        );\n      }),\n    );\n  }\n\n\n  ListView getListViewBuilder(){\n    return ListView.builder(\n      itemCount: controller.hotList.length,\n      itemBuilder: (context, index) {\n        Map item = controller.hotList[index];\n\n        return Slidable(\n          key: ValueKey(\"$index\"),\n          // startActionPane:  ActionPane(\n          //   // A motion is a widget used to control how the pane animates.\n          //   motion: const ScrollMotion(),\n          //   // A pane can dismiss the Slidable.\n          //   dismissible: DismissiblePane(onDismissed: () {}),\n          //   // All actions are defined in the children parameter.\n          //   children: const [\n          //     // A SlidableAction can have an icon and/or a label.\n          //     SlidableAction(\n          //       onPressed: null,\n          //       backgroundColor: Color(0xFFFE4A49),\n          //       foregroundColor: Colors.white,\n          //       icon: Icons.delete,\n          //       label: \'Delete\',\n          //     ),\n          //     SlidableAction(\n          //       onPressed: null,\n          //       backgroundColor: Color(0xFF21B7CA),\n          //       foregroundColor: Colors.white,\n          //       icon: Icons.share,\n          //       label: \'Share\',\n          //     ),\n          //   ],\n          // ),\n          //左滑删除\n          //dismissal: buildSlidableDismissal(),\n          //滑动方向\n          direction: Axis.horizontal,\n          // The end action pane is the one at the right or the bottom side.\n          endActionPane: ActionPane(\n            motion: const ScrollMotion(),\n            children: [\n              SlidableAction(\n                onPressed: (BuildContext context) {\n                  var fileUrls = item[\"image\"].toString();\n                  var imageArr = fileUrls.split(\",\");\n                  controller.deleteImages(imageArr);\n                  controller.deleteItem(index);\n\n                },\n                backgroundColor: Colors.red,\n                foregroundColor: Colors.white,\n                icon: Icons.delete,\n                label: \'Save\',\n              ),\n            ],\n          ),\n          //列表显示的子Item\n          child: getListBody(item),\n        );\n      },\n    );\n  }\n\n  ListBody getListBody(Map item){\n    return ListBody(\n      children: [\n        InkWell(\n          onTap: () {\n            Get.toNamed(\"/HomedetailPage\",arguments: {\n              \"id\":item[\"id\"]\n            });\n          },\n          child: Container(\n              height: 150,\n              padding: EdgeInsets.all(10),\n              margin: EdgeInsetsDirectional.only(top:10.0,start: 15.0,end: 15.0,bottom: 0.0),\n              decoration: BoxDecoration(\n                color: Colors.white,\n                borderRadius: BorderRadius.all(Radius.circular(15)),\n              ),\n              child: Container(\n                margin: EdgeInsetsDirectional.only(top:0.0,start: 10.0,end: 0.0,bottom: 0.0),\n                child: Column(\n                  crossAxisAlignment: CrossAxisAlignment.start,\n                  children: [\n                    Text(item[\"title\"],\n                      overflow: TextOverflow.ellipsis, //长度溢出后显示省略号\n                      style: TextStyle(fontSize: 15,),\n                    ),\n                    Text(item[\"content\"],\n                      overflow: TextOverflow.ellipsis,//长度溢出后显示省略号\n                      style: TextStyle(fontSize: 15,),\n                    ),\n                  ],\n                ),\n              )\n          ),\n        ),\n      ],\n    );\n  }\n\n\n\n  //------------------------------------------------------------------------------------------------\n  List<Widget> getList(){\n    return controller.hotList.map((value){\n      var updateAt = value[\"updateAt\"];\n      var createAt = value[\"createAt\"];\n      print(\"updateAt====\"+updateAt.toString());\n      print(\"createAt====\"+createAt.toString());\n      print(\"dateObs====\"+controller.dateObs.value);\n\n      return getItem(value);\n    }).toList();\n  }\n  /// 获取子项目\n  Widget getItem(Map item) {\n    var imageStr = item[\"image\"];\n    print(\"map==\"+item.toString());\n    List imageStrList =imageStr.split(\',\');\n    var imageUrl0 = imageStrList[0].toString();\n    // return getListTitle(imageUrl0, item);\n    return getListBody(item);\n  }\n\n  ListView getListView(){\n    return ListView(\n      children: getList(),\n    );\n  }\n\n\n  List<Widget> getNullListView(){\n    List<Widget> list = [];\n    print(\"dateObs====\"+controller.dateObs.value);\n    list.add(Text(\"data\"));\n    return list;\n  }\n\n  ListTile getListTitle(String imageUrl0,Map item){\n    return ListTile(\n      // 前部图片\n      leading: Image.network(\n        imageUrl0,\n        width: 80,\n        height: 60,\n        fit: BoxFit.cover,\n      ),\n      // 标题\n      title: Text(item[\"title\"]),\n      // 副标题\n      subtitle: Text(item[\"content\"]),\n      // 后部箭头\n      trailing: Icon(Icons.keyboard_arrow_right_outlined),\n      onTap: () {\n        print(\'${item[\"id\"]}\');\n        Get.toNamed(\"/HomedetailPage\",arguments: {\n          \"id\":item[\"id\"]\n        });\n\n      },\n      onLongPress: () {\n        print(\'${item[\"userId\"]}\');\n      },\n    );\n  }\n\n}','2024-11-25 00:00:00','2024-11-25 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Mon Nov 25 14:27:20 CST 20244fd01f21e357463c882ac0f74558e2ebimage_picker_07B90E91-7BC9-4BFA-A656-39CFACC19659-60418-00000057F1DE3DD2.jpg'),
	(50,4,'paaa','piu','2024-11-25 00:00:00','2024-11-25 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Mon Nov 25 14:30:31 CST 2024bfaea011bc1c48d0afa62e089acb7186image_picker_1B709705-5BAC-4D9C-B161-AB5595862F5A-60418-00000058F6919132.jpg'),
	(51,4,'多图片2','多图片1','2024-11-25 00:00:00','2024-11-25 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Mon Nov 25 15:02:30 CST 202483179d2215e949cda854bd444e044f6aimage_picker_45595F19-3857-47A8-8762-3E3708182475-64277-00000063AA8550F3.jpg,http://note-content.oss-cn-shenzhen.aliyuncs.com/Mon Nov 25 15:02:40 CST 20245a72b87f3f904317bb81315a16b9fe2bimage_picker_E92BDE2C-0C7E-4569-9338-FCACFA055289-64277-00000063BA1B6216.jpg,http://note-content.oss-cn-shenzhen.aliyuncs.com/Mon Nov 25 15:02:52 CST 2024ad76cdbe031942ee8499482c74c5f2beimage_picker_D5B1F011-0C9B-47A4-9A8E-74F27AD40DA4-64277-00000063BEE4EE47.jpg'),
	(59,4,'age1','age2','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 11:17:55 CST 20248e3844c142fc4133be0e1b73947ac659image_picker_31BD80D6-1A36-4D2D-9195-B4199400BE15-6056-0000002181088854.jpg'),
	(60,4,'age3','age4','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 12:17:52 CST 2024c7cda5f2f9f8496eabb1dfb98a51cfaeimage_picker_3346BB91-3A3A-448B-A684-8F2F8BDF081F-6056-0000003595760ADD.jpg'),
	(67,6,'大学a','专业a','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 12:49:16 CST 2024c30e72cae020496ca65a2ec9706d6cd9image_picker_80333440-2749-4115-BB18-4FED51136412-48726-0000004019F6C945.jpg'),
	(68,6,'好好学习1','天天向上1','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 12:50:01 CST 2024e1a18c208aab4923aa5a2a5e588de749image_picker_DA8EC5D7-62C5-4478-AE25-359BE7AF704A-48726-0000004064813489.jpg'),
	(69,6,'ios','android','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 18:15:17 CST 2024669bdf5d2e0641fb9f0a12ae089380a3image_picker_6FF3725C-8859-41CE-A1CB-9C77261F69AF-19148-000000AD67F09015.jpg'),
	(70,6,'kit','hello','2024-11-26 00:00:00','2024-11-26 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Tue Nov 26 22:14:26 CST 2024f686728acf174e3299a9176ca9462553image_picker_D296A26B-39D4-4634-81A3-FADA7CCE290C-78742-000000FD8DE4B528.jpg'),
	(71,6,'gh','ff','2024-11-27 00:00:00','2024-11-27 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Wed Nov 27 11:12:45 CST 2024e856a66816e54deaa9eccd2127d1fa37image_picker_11737AB6-CED2-4AEE-A63D-E965BCE1542A-27005-00000022CF6DF3C3.jpg'),
	(72,6,'fzm','cl','2024-11-27 00:00:00','2024-11-27 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Wed Nov 27 11:33:15 CST 2024106a63e8adfb43bb91542a7b6e3cf71aimage_picker_2C4AA129-954C-4365-A658-A6D6C310DB6E-30512-00000029C6348348.jpg'),
	(73,6,'pic','pic','2024-11-27 00:00:00','2024-11-27 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Wed Nov 27 19:40:39 CST 20246b99eccd643a432daa5babd693de3e2bimage_picker_D73B1C0E-57A5-439A-B345-D649BB30DA95-23866-000000CD16E42001.jpg,http://note-content.oss-cn-shenzhen.aliyuncs.com/Wed Nov 27 19:40:39 CST 20244c93e9a93f784f288bbd98fc4f053b47image_picker_9D3CA06B-4C92-4575-A51D-D4695DD3C958-23866-000000CD1A592499.jpg,http://note-content.oss-cn-shenzhen.aliyuncs.com/Wed Nov 27 19:40:40 CST 2024724dee378b2944b1ac8a5c7349a707d6image_picker_5EAD405C-5C28-4907-A51B-35B466620F33-23866-000000CD1E4FF5B4.jpg'),
	(74,6,'今晚打老虎','打老虎\n\n\n','2024-11-28 00:00:00','2024-11-28 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Thu Nov 28 22:47:35 CST 20246bf3c34470ec4e52b390af4e4da0308dimage_picker_E2B11711-9CB9-4830-ACB4-C4C86357BADE-34164-000020E98940611B.jpg'),
	(75,4,'滚滚滚出vv','常出现','2024-11-29 00:00:00','2024-11-29 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Fri Nov 29 14:28:47 CST 2024c1186478d5804fedb8fa8d95111b0505image_picker_3DC064F8-063F-4096-9A67-5FCD1A4AF6C7-37971-00002160D3318BEF.jpg'),
	(76,4,'反反复复','高耗能','2024-11-29 00:00:00','2024-11-29 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Fri Nov 29 14:30:31 CST 2024467e129f78b642a495193faddd71c25cimage_picker_CEE92A2D-4E3F-4A8A-AE28-5718202D4012-37971-0000216160CA1B4E.jpg'),
	(78,8,'口','看书','2024-11-30 00:00:00','2024-11-30 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Nov 30 15:49:29 CST 2024019faee68be74dbbb5c170eac5e3ac6eimage_picker_BE9EC2DA-1E7D-41EE-9B7F-198B35AB446D-46176-00002287107F3927.png'),
	(79,8,'用','看电影','2024-11-30 00:00:00','2024-11-30 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Nov 30 15:50:42 CST 20243998f0bbccc74b5ea615e4a8d428bc26image_picker_6AF35C7A-0623-4F09-A843-FDB93DE48B55-46176-000022876B728DF0.jpg'),
	(80,4,'test','test2','2024-11-30 00:00:00','2024-11-30 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Nov 30 16:17:12 CST 20242ffee234b2dc47628e2d277722870d0aimage_picker_33B5FD66-421C-4566-8C1F-43B7B4D70A68-95411-0000008540B0ED6B.jpg'),
	(81,4,'hhjkk','ttttt','2024-11-30 00:00:00','2024-11-30 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Nov 30 16:20:35 CST 20244b920088b2684a159cd648ed2458180fimage_picker_6D7720F7-9FA0-489E-A6D8-990E2AFD0D3B-95411-000000866BECE97F.jpg'),
	(82,4,'aaa','ggg','2024-12-14 00:00:00','2024-12-14 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Dec 14 13:31:14 CST 20240ede733b86f14a809f0a14e9672e9e64image_picker_3334F37A-8F91-4AA0-B5B1-784B19086E2A-49049-0000003C607FB44F.jpg'),
	(83,4,'12000','3234242','2024-12-14 00:00:00','2024-12-14 00:00:00','http://note-content.oss-cn-shenzhen.aliyuncs.com/Sat Dec 14 13:37:22 CST 2024867eed46df53421599bdeeb5cee0fb2dimage_picker_85B781E2-952B-4396-8FF9-722BFB00DE0F-53469-0000003E6F90BD95.jpg');

/*!40000 ALTER TABLE `t_note` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 t_note_tags
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_note_tags`;

CREATE TABLE `t_note_tags` (
  `note_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`note_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `t_note_tags` WRITE;
/*!40000 ALTER TABLE `t_note_tags` DISABLE KEYS */;

INSERT INTO `t_note_tags` (`note_id`, `tag_id`)
VALUES
	(2,2),
	(23,7),
	(24,8),
	(25,9),
	(26,10),
	(27,0),
	(31,0),
	(32,0),
	(33,0),
	(34,2),
	(36,10),
	(41,17),
	(42,41),
	(43,41),
	(44,41),
	(45,21),
	(46,45),
	(47,23),
	(48,2),
	(49,25),
	(50,26),
	(51,27),
	(52,28),
	(53,52),
	(54,52),
	(55,23),
	(56,45),
	(57,27),
	(58,7),
	(59,28),
	(60,28),
	(61,32),
	(62,33),
	(63,34),
	(64,35),
	(65,36),
	(66,36),
	(67,37),
	(68,37),
	(69,38),
	(70,39),
	(71,40),
	(72,41),
	(73,42),
	(74,21),
	(75,43),
	(77,44),
	(80,45);

/*!40000 ALTER TABLE `t_note_tags` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 t_tags
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_tags`;

CREATE TABLE `t_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `t_tags` WRITE;
/*!40000 ALTER TABLE `t_tags` DISABLE KEYS */;

INSERT INTO `t_tags` (`id`, `name`)
VALUES
	(35,'a'),
	(36,'aga'),
	(28,'age'),
	(39,'book'),
	(25,'code'),
	(32,'da'),
	(41,'fbb'),
	(10,'flutter'),
	(5,'gaa'),
	(3,'ghhh'),
	(45,'h'),
	(21,'ko'),
	(34,'lm'),
	(44,'lop'),
	(17,'ok'),
	(27,'picture'),
	(40,'pk'),
	(23,'po'),
	(33,'pop'),
	(7,'tag'),
	(6,'tags'),
	(26,'test'),
	(42,'topic'),
	(37,'学习'),
	(4,'学校'),
	(9,'心情'),
	(2,'旅行'),
	(8,'标签'),
	(43,'看'),
	(38,'移动端'),
	(1,'计划');

/*!40000 ALTER TABLE `t_tags` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `is_delete` int DEFAULT '0',
  `created_user` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(255) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `t_user_chk_1` CHECK ((`gender` in (0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `phone`, `email`, `gender`, `avatar`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`)
VALUES
	(4,'loooo','5FC7AD2C344FBEC3D4CBC31CDAC053E3','C5E09F90-0D78-4DFB-A9AC-CE27A805191F',NULL,'1051136697@qq.com',NULL,NULL,0,'loooo','2024-11-18 16:51:45','loooo','2024-11-18 16:51:45'),
	(6,'lppp','2E2A2FBFAC641DB2989FCC15C398FA17','3F943137-C683-4B1E-B856-3F6471A61310',NULL,'JunChinaPKU1990@pku.org.cn',NULL,NULL,0,'lppp','2024-11-26 12:25:13','lppp','2024-11-26 12:25:13'),
	(8,'lmmm','82554013F6703C4F33A9F4071530D0E9','708F286C-E441-4496-9FAF-50935C5432DA','18589237952',NULL,NULL,NULL,0,'lmmm','2024-11-30 13:25:03','lmmm','2024-11-30 13:25:03');

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
