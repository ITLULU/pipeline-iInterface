package com.hogwarts.common;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class CityWeather {
    private static String url = "";
    private static  Logger logger = Logger.getLogger(CityWeather.class);
    private static String propFileName = "src/main/resources/iInterface.properties";
    private  Properties prop = loadFromEnvProperties(propFileName);

    public String geturl() {
        return url;
    }

    public String getHttpRespone(String cityCode) throws IOException {
        String httpResults = "";
        String server_address = prop.getProperty("server_addr", "www.weather.com.cn");
        url = "http://" + server_address + "/data/cityinfo/" + cityCode + ".html";
        logger.debug("请求地址：" + url);
        httpResults = HttpClient.sendGet(url);
        return httpResults;
    }

    //加载配置文件
    private static Properties loadFromEnvProperties(String propFileName) {
        Properties prop = null;
        //读入envProperties属性文件
        try {
            prop = new Properties();
            InputStream in = new BufferedInputStream(
                    new FileInputStream(propFileName));
            prop.load(in);
            in.close();
        } catch (IOException ioex) {
            logger.error("配置文件加载失败，请检查 " + propFileName + "文件是否存在！");
        }

        return prop;
    }
  /*  public static void main(String[] args) {
    	 Properties prop = loadFromEnvProperties(propFileName);
    	 logger.info("server_addr:"+prop.getProperty("server_addr"));
	}*/
}
