package com.pgb.spider.wechat.eumeration;

public class Constant {
    public static final String SET_USERINFO = "您说得我不是太懂！如果您是想设置个人信息，请按照如下格式设置：\n" +
                    "【职位名称】+【薪资要求】+【期望公司】";
    public static final String WELCOME_INFO = "欢迎订阅！此公众号为湖北经济学院毕业设计——大数据采集系统的职位查询公众号。\n" +
            "\n" +
            "如果您是首次订阅我的公众号，请点击个人中心设置您对为职位的要求，即可使用此公众号的功能。也可以编辑发送【职位名称】+【薪资要求】+【期望公司】来设置您的个人信息。设置好个人信息后，我们定期为您推送符合您要求的工作信息。\n" +
            "\n" +
            "下面是对此公众号功能的一些介绍。\n" +
            "【找工作】\n" +
            "    在您设置了个人信息后，点击找工作，即可获取满足您条件的一批工作。\n" +
            "【搜索】\n" +
            "    在这里您可以根据个人需要查找您想要的职位。\n" +
            "【个人中心】\n" +
            "    用于设置您的个人信息。";

    public static final String WARNING_SET_USERINFO = "你还没有设置个人信息， 请点击个人中心设置您对为职位的要求，即可使用此公众号的功能。也可以编辑发送【职位名称】+【薪资要求】+【期望公司】来设置您的个人信息。设置好个人信息后，我们定期为您推送符合您要求的工作信息。";

    public static final String appid = "wx2aab304f3597214c";

    public static final String appsecret = "b5573b8c3563af835da6f2d784c3ce50";

    public static final String redirect_uri = "http%3a%2f%2fpenggb.top%2fwechat%2fgetOpenid";
}
