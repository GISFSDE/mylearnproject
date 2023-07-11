package com.jeethink.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeethink.common.utils.http.HttpUtils;
import com.jeethink.framework.config.JeeThinkConfig;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2023/02/03/16:03
 * @Description:
 */

public class GeoUtils {
    private static final Logger logger = LoggerFactory.getLogger(GeoUtils.class);

    /**
     * shp相关文件涉及的 后缀名
     */
    private static final String hasSuffix = "shp dbf shx prj";

    /**
     * 临时存放shp相关文件的根目录(Global为本地自定义的配置文件映射类)
     */
    private static final String tempShpDir = JeeThinkConfig.getProfile() + "/tempFile/";


    private static final Logger log = LoggerFactory.getLogger(GeoUtils.class);


    static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    static WKTReader reader = new WKTReader(geometryFactory);
    public static String shpPath;

    /**
     * geojson转换为shp文件
     *
     * @param geoJson
     * @param shpPath
     * @return
     */
    public static Map geojson2Shape(String geoJson, String shpPath) {
        Map map = new HashMap();
        GeometryJSON gjson = new GeometryJSON();
        try {

            JSONObject json = (JSONObject) JSONObject.parse(geoJson);

            JSONArray features = (JSONArray) json.get("features");
            JSONArray feature0 = (JSONArray) JSONObject.parse(features.get(0).toString());
            JSONObject feature00 = (JSONObject) JSONObject.parse(feature0.get(0).toString());
//            System.out.println(feature0.toString());
            String strType = ((JSONObject) feature00.get("geometry")).getString("type").toString();
//            JSONArray features = (JSONArray) json.get("features");
//            JSONObject feature0 = (JSONObject) JSONObject.parse(features.get(0).toString());
////            System.out.println(feature0.toString());
//            String strType = ((JSONObject) feature0.get("geometry")).getString("type").toString();

            Class<?> geoType = null;
            switch (strType) {
                case "Point":
                    geoType = Point.class;
                case "MultiPoint":
                    geoType = MultiPoint.class;
                case "LineString":
                    geoType = LineString.class;
                case "MultiLineString":
                    geoType = MultiLineString.class;
                case "Polygon":
                    geoType = Polygon.class;
                case "MultiPolygon":
                    geoType = MultiPolygon.class;
            }
            // 创建shape文件对象
            File file = new File(shpPath);
            Map<String, Serializable> params = new HashMap<String, Serializable>();
            params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
            // 定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");
            Map<String, Object> properties = ((JSONObject) feature00.get("properties"));
            properties.keySet().forEach((key) -> {
                tb.add(key, String.class);
            });
            tb.add("the_geom", geoType);
            tb.add("POIID", Long.class);
            ds.createSchema(tb.buildFeatureType());
            // 设置编码
            Charset charset = StandardCharsets.UTF_8;
            ds.setCharset(charset);
            // 设置Writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);

            for (int i = 0, len = features.size(); i < len; i++) {
                String strFeature = features.get(i).toString();
                Reader reader = new StringReader(strFeature);
                SimpleFeature feature = writer.next();


                JSONArray oneFeature = (JSONArray) JSONArray.parse(strFeature);
                JSONObject oneFeatureO = (JSONObject) JSONObject.parse(oneFeature.get(0).toString());

                JSONObject oneFeatureJSONObject = (JSONObject) JSONObject.parse(String.valueOf(oneFeatureO.get("properties")));
                Map<String, Object> oneFeatureJSONObject1 = oneFeatureJSONObject;
                oneFeatureJSONObject1.forEach((key, value) -> {
                    if (value == null) {
                        value = "";
                    }
                    feature.setAttribute(key, value.toString());
                });

                feature.setAttribute("the_geom", gjson.readMultiPolygon(reader));
                feature.setAttribute("POIID", i);
                writer.write();
            }
            writer.close();
            ds.dispose();
            map.put("status", "success");
            map.put("message", shpPath);
        } catch (Exception e) {
            map.put("status", "failure");
            map.put("message", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * wkt字符串转Geometry
     *
     * @param wkt 字符串
     * @return Geometry
     */
    public static Geometry wktStrToGeometry(String wkt) {
        try {
            return reader.read(wkt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * wkt转GeoJson包括属性值（feature）
     *
     * @param wkt wkt
     * @param map 属性值
     * @return GeoJson
     */
    public static HashMap<Object, Object> wktToJson(String wkt, Map<String, Object> map) {
        String json = null;
        // geoJson
        HashMap<Object, Object> feature = new HashMap<>();
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry, writer);
            JSONObject jsonObject = JSONObject.parseObject(writer.toString());
            feature.put("type", "Feature");
            feature.put("geometry", jsonObject);
            feature.put("properties", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feature;
    }

    /**
     * wkt转GeoJson（geometry）  不包括属性
     *
     * @param wkt wkt
     * @return geoJson
     */
    public static JSONObject wktToJson(String wkt) {
        JSONObject jsonObject = null;
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry, writer);
            jsonObject = JSONObject.parseObject(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static String geometry2Wkt(Geometry geometry) throws ParseException {
        WKTWriter writer = new WKTWriter();
        return writer.write(geometry);
    }

    /**
     * geoJson转Wkt（没有属性）
     *
     * @param geoJson geoJson
     * @return wkt
     */
    public static String geoJsonToWkt(String geoJson) {
        String wkt = null;
        GeometryJSON gJson = new GeometryJSON();
        Reader reader = new StringReader(geoJson);
        try {
            Geometry geometry = gJson.read(reader);

            wkt = geometry.toText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wkt;
    }


    /**
     * Geometry转GeoJson（无属性）
     *
     * @param geometry Geometry
     * @return JSONObject
     */
    public static JSONObject geometryToGeoJson(Geometry geometry) {
        String ret = null;
        try {
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry, writer);
            ret = writer.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return JSONObject.parseObject(ret);
    }

    /**
     * GeoJson转Geometry
     *
     * @param geoJson 字符串类型GeoJson
     * @return Geometry
     */
    public static Geometry geoJsonToGeometry(String geoJson) {
        GeometryJSON json = new GeometryJSON();
        Reader reader = new StringReader(geoJson);
        try {
            return json.read(reader);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 生成shape文件
     *
     * @param shpPath 生成shape文件路径（包含文件名称）
     * @param encode  编码
     * @param geoType 图幅类型，Point和Rolygon
     * @param geoms   图幅集合
     */
    public static void write2Shape(String shpPath, String encode, String geoType, List<Geometry> geoms) {
        try {
            // 创建shape文件对象
            File file = new File(shpPath);
            Map<String, Serializable> params = new HashMap<>();
            params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
            // 定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");

            if ("Polygon".equals(geoType)) {
                tb.add("the_geom", Polygon.class);
            } else if ("MultiPolygon".equals(geoType)) {
                tb.add("the_geom", MultiPolygon.class);
            } else if ("Point".equals(geoType)) {
                tb.add("the_geom", Point.class);
            } else if ("MultiPoint".equals(geoType)) {
                tb.add("the_geom", MultiPoint.class);
            } else if ("LineString".equals(geoType)) {
                tb.add("the_geom", LineString.class);
            } else if ("MultiLineString".equals(geoType)) {
                tb.add("the_geom", MultiLineString.class);
            } else {
                throw new Exception("Geometry中没有该类型：" + geoType);
            }

            ds.createSchema(tb.buildFeatureType());
            // 设置编码
            Charset charset = Charset.forName(encode);
            ds.setCharset(charset);
            // 设置Writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
            for (Geometry geom : geoms) {
                // String type = geom.getGeometryType();

                // 写下一条
                SimpleFeature feature = writer.next();

                feature.setAttribute("the_geom", geom);
            }
            writer.write();
            writer.close();
            ds.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成shape文件
     *
     * @param shpPath  生成shape文件路径（包含文件名称）
     * @param encode   编码
     * @param geoType  图幅类型，Point和Rolygon
     * @param shpKey   data中图幅的key
     * @param attrKeys 属性key集合
     * @param data     图幅和属性集合
     */
    public static void write2Shape(String shpPath, String encode, String geoType, String shpKey, List<String> attrKeys, List<Map<String, Object>> data) {
        try {
            if (data == null || data.size() == 0) {
                return;
            }
            // 创建shape文件对象
            File file = new File(shpPath);
            Map<String, Serializable> params = new HashMap<>();
            params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);

            // 定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");

            if ("Polygon".equals(geoType)) {
                tb.add("the_geom", Polygon.class);
            } else if ("MultiPolygon".equals(geoType)) {
                tb.add("the_geom", MultiPolygon.class);
            } else if ("Point".equals(geoType)) {
                tb.add("the_geom", Point.class);
            } else if ("MultiPoint".equals(geoType)) {
                tb.add("the_geom", MultiPoint.class);
            } else if ("LineString".equals(geoType)) {
                tb.add("the_geom", LineString.class);
            } else if ("MultiLineString".equals(geoType)) {
                tb.add("the_geom", MultiLineString.class);
            } else {
                throw new Exception("Geometry中没有该类型：" + geoType);
            }

            for (String field : attrKeys) {
                tb.add(field.toUpperCase(), String.class);
            }

            ds.createSchema(tb.buildFeatureType());
            // 设置编码
            Charset charset = Charset.forName(encode);
            ds.setCharset(charset);
            // 设置Writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
            // 写入文件信息
            for (int i = 0; i < data.size(); i++) {
                SimpleFeature feature = writer.next();
                Map<String, Object> row = data.get(i);
                Geometry geom = (Geometry) row.get(shpKey);
                feature.setAttribute("the_geom", geom);
                for (String key : row.keySet()) {
                    if (!key.equals(shpKey)) {
                        if (row.get(key) != null) {
                            feature.setAttribute(key.toUpperCase(), row.get(key).toString());
                        } else {
                            feature.setAttribute(key.toUpperCase(), "");
                        }
                    }
                }
            }
            writer.write();
            writer.close();
            ds.dispose();

            // 添加到压缩文件
            // zipShapeFile(shpPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 压缩shape文件
     *
     * @param shpPath shape文件路径（包含shape文件名称）
     */
    public static void zipShapeFile(String shpPath) {
        try {
            File shpFile = new File(shpPath);
            String shpRoot = shpFile.getParentFile().getPath();
            String shpName = shpFile.getName().substring(0, shpFile.getName().lastIndexOf("."));

            String zipPath = shpRoot + File.separator + shpName + ".zip";
            File zipFile = new File(zipPath);
            InputStream input = null;
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            // zip的名称为
            zipOut.setComment(shpName);
            String[] shpFiles = new String[]{
                    shpRoot + File.separator + shpName + ".dbf",
                    shpRoot + File.separator + shpName + ".prj",
                    shpRoot + File.separator + shpName + ".shp",
                    shpRoot + File.separator + shpName + ".shx",
                    shpRoot + File.separator + shpName + ".fix"
            };

            for (int i = 0; i < shpFiles.length; i++) {
                File file = new File(shpFiles[i]);
                input = new FileInputStream(file);
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(tempShpDir);
        return factory.createMultipartConfig();
    }

    /*解析结果转sql*/
    public static List<Map<String, Object>> jsonToSql(List<Map<String, Object>> list, String type) throws IOException {
        Map<String, Integer> nullMap = new HashMap<String, Integer>();
        List<Map<String, Object>> allTypeList = new ArrayList<>();


        // 数据截取
        int dataListSize = list.size();

        final int step = 200;
        int i = dataListSize / step + 1;
        for (int j = 0; j < i; j++) {
            Map<String, Object> jsonMap = new HashMap<>();
            List<Map<String, Object>> temList = new ArrayList<>();
            if (j == (i - 1)) {
                temList = list.subList(j * step, dataListSize);
            } else {
                temList = list.subList(j * step, (j + 1) * step);
            }
            temList.forEach((item) -> {
                        if (item.get("ydlx") != null && item.get("ydxz") != null && item.get("the_geom") != null && item.get("dkbh") != null) {
                            String oneKey = item.get("ydlx").toString() + "::" + item.get("ydxz");
                            String oneTypePolygon = "";
                            assemble(item, oneTypePolygon, jsonMap, oneKey, jsonMap.get(oneKey) != null);
                        } else {
                            logger.info("移除了已解析字段不全数据，编号：{}，类型：{}，性质：{}", item.get("dkbh"), item.get("ydlx"), item.get("ydxz"));
                        }
                    }
            );


            Iterator<Map.Entry<String, Object>> entries = jsonMap.entrySet().iterator();
            long startTime = System.currentTimeMillis();

            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();

                String mapKey = entry.getKey();
                String mapValue = (String) entry.getValue();

                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("funName", type);
                paramsMap.put("table", "");
                paramsMap.put("parameters", mapValue);

                JSONArray analysisResult = getAnalysisResult(JSONObject.toJSONString(paramsMap), false);
                if (analysisResult != null) {
                    analysisResult.forEach((item) -> {
                        Map<String, Object> temItem = (Map<String, Object>) item;
                        temItem.remove("geom");
                        allTypeList.add(temItem);
                    });
                } else {
                    if (nullMap.get(mapKey) == null) {
                        nullMap.put(mapKey, 1);
                    } else {
                        nullMap.put(mapKey, nullMap.get(mapKey) + 1);
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            logger.info("此次接口请求时间时间：{}s", (endTime - startTime) / 1000);

        }
        allTypeList.forEach((item) -> {
            String key = item.get("ydlx").toString() + "::" + item.get("ydxz");
            if (nullMap.get(key) != null) {
                nullMap.remove(key);
            }
        });

        logger.info("类型：{},接口请求为空统计:{}", type, nullMap);
        return allTypeList;
    }

    /*解析结果转sql包含日志版*/
    public static List<Map<String, Object>> jsonToSql(List<Map<String, Object>> list, String type, String lineChinese, Map<String, Object> scheduleMap) throws IOException {
        Map<String, Integer> nullMap = new HashMap<String, Integer>();
        List<Map<String, Object>> allTypeList = new ArrayList<>();

        // 数据截取
        int dataListSize = list.size();
        scheduleMap.put("log", "开始解析控制线：" + lineChinese + "，解析数据量：" + dataListSize + "条");

        int step = 200;
        int i = dataListSize / step + 1;
        for (int j = 0; j < i; j++) {
            Map<String, Object> jsonMap = new HashMap<>();
            List<Map<String, Object>> temList = new ArrayList<>();
            if (j == (i - 1)) {
                temList = list.subList(j * step, dataListSize);
            } else {
                temList = list.subList(j * step, (j + 1) * step);
            }
            temList.forEach((item) -> {
                        if (item.get("ydlx") != null && item.get("ydxz") != null && item.get("the_geom") != null && item.get("dkbh") != null) {
                            String oneKey = item.get("ydlx").toString() + "::" + item.get("ydxz");
                            String oneTypePolygon = "";
                            assemble(item, oneTypePolygon, jsonMap, oneKey, jsonMap.get(oneKey) != null);
                        } else {
                            logger.info("移除了已解析字段不全数据，编号：{}，类型：{}，性质：{}", item.get("dkbh"), item.get("ydlx"), item.get("ydxz"));
                        }
                    }
            );


            Iterator<Map.Entry<String, Object>> entries = jsonMap.entrySet().iterator();
            long startTime = System.currentTimeMillis();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();

                String mapKey = entry.getKey();
                String mapValue = (String) entry.getValue();

                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("funName", type);
                paramsMap.put("table", "");
                paramsMap.put("parameters", mapValue);

                JSONArray analysisResult = getAnalysisResult(JSONObject.toJSONString(paramsMap), true);
                if (analysisResult != null) {
                    analysisResult.forEach((item) -> {
                        Map<String, Object> temItem = (Map<String, Object>) item;
                        temItem.remove("geom");
                        allTypeList.add(temItem);
                    });
                } else {
                    nullMap.merge(mapKey, 1, Integer::sum);
                }

            }
            long endTime = System.currentTimeMillis();
            logger.info("此次接口请求时间时间：{}s", (endTime - startTime) / 1000);
            BigDecimal per = BigDecimal.valueOf(j).divide(BigDecimal.valueOf(i), 0, RoundingMode.HALF_DOWN);
            scheduleMap.put("log", "完成解析控制线：" + lineChinese + "，的进度：" + per.multiply(new BigDecimal(100)) + "%");
//            scheduleMap.put("log", "完成解析控制线：" + type + "，的进度：" + per.multiply(new BigDecimal(100))+"%");
            scheduleMap.put(type, per);
            if (type.contains("CSDL") || type.contains("SZ")) {
                BigDecimal ST_3X_HX_CSDL = getBigDecimal(scheduleMap.get("ST_3X_HX_CSDLYDXZ") == null ? 0 : scheduleMap.get("ST_3X_HX_CSDLYDXZ")).add(getBigDecimal(scheduleMap.get("ST_3X_HX_CSDL30") == null ? 0 : scheduleMap.get("ST_3X_HX_CSDL30")));
                scheduleMap.put("ST_3X_HX_CSDL", ST_3X_HX_CSDL);
                BigDecimal ST_3X_HX_SZ = getBigDecimal(scheduleMap.get("ST_3X_HX_XZSZ") == null ? 0 : scheduleMap.get("ST_3X_HX_XZSZ")).add(getBigDecimal(scheduleMap.get("ST_3X_HX_GHSZ") == null ? 0 : scheduleMap.get("ST_3X_HX_GHSZ")));
                scheduleMap.put("ST_3X_HX_SZ", ST_3X_HX_SZ);
            }
        }

        allTypeList.forEach((item) -> {
            String key = item.get("ydlx").toString() + "::" + item.get("ydxz");
            if (nullMap.get(key) != null) {
                nullMap.remove(key);
            }
        });

//        scheduleMap.put("log", "完成解析控制线：" + type + ",接口请求为空统计:" + nullMap);
//        updateScheduleMap(scheduleMap);
        logger.info("类型：{},接口请求为空统计:{}", type, nullMap);
        return allTypeList;
    }

    public static JSONArray getAnalysisResult(String Params, Boolean isNeedLog) throws IOException {
        String url = "http://172.18.2.90:3080/api/Analysis/customfunPost";
//        预防干预sql拼凑
        Params = Params.replaceAll(";", "-");
        JSONArray result = new JSONArray();
        try {
//            logger.info("接口参数{}..." ,Params);

            JSONObject jsonObject = HttpUtils.sendPostRowRequest(Params, url, null);
            result = (JSONArray) jsonObject.get("result");
        } catch (Exception e) {
            if (isNeedLog) {
                Map<String, Object> scheduleMap = new HashMap<>();
                scheduleMap.put("log", "解析接口请求错误,参数：" + Params + ",返回错误结果:" + e.getStackTrace());

            }
        }
        return result;
    }

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = BigDecimal.valueOf(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

    /*sql组装*/
    public static String assemble(Map<String, Object> item, String oneTypePolygon, Map<String, Object> jsonMap, String oneKey, Boolean isAdd) {
        StringBuffer sb = new StringBuffer();

        if (isAdd) {
            sb.append(jsonMap.get(oneKey)).append(" union all ").append("select ").append("'").append(item.get("dkbh")).append("'").append(" as dkbh,").append("'").append(item.get("ydxz")).append("'").append(" as ydxz,")
                    .append("'").append(item.get("ydlx")).append("'").append(" as ydlx,ST_GeomFromText(").append("'").append(item.get("the_geom")).append("'").append(",4490) as geom");
            oneTypePolygon = sb.toString();
        } else {
            sb.append("select ").append("'").append(item.get("dkbh")).append("'").append(" as dkbh,").append("'").append(item.get("ydxz")).append("'").append(" as ydxz,").append("'").append(item.get("ydlx")).append("'")
                    .append(" as ydlx,ST_GeomFromText(").append("'").append(item.get("the_geom")).append("'").append(",4490) as geom");
            oneTypePolygon = sb.toString();
        }
        item.remove("ydlx");
        item.remove("ydxz");
        item.remove("the_geom");
        item.remove("dkbh");
        oneTypePolygon = oneTypePolygon + "," + "'" + JSONObject.toJSONString(item) + "'" + " as property";
        jsonMap.put(oneKey, oneTypePolygon);
        return oneTypePolygon;
    }

    /**
     * 解压zip文件，获取shp相关文件，并解析.shp .prj文件，获取数据返回给前端
     *
     * @param file 要保存到本地的文件
     * @return 解析完的数据
     */
    public static List<Map<String, Object>> saveMultipartFile(MultipartFile file) {

        // 创建根目录
        String savePath = tempShpDir + UUID.randomUUID();
        try {
            // 先将zip文件存放到本地，解析完后删除
            File localFile = new File(savePath
            );
            localFile.mkdirs();
            File file1 = new File(localFile + File.separator + getMultipartFileName(file.getOriginalFilename()
            ));
            file.transferTo(file1);
            // 解析文件
            List<Map<String, Object>> list = resolveZip(file1, savePath);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 保存完相关文件后，删除压缩包和相关文件
            File parentDir = new File(savePath);
            File[] files = parentDir.listFiles();
            for (File f : files) {
                if (f.delete()) {
                    // 文件一直被占用无法删除，直接调用gc()回收垃圾，简单粗暴
                    System.gc();
                    f.delete();
                }
            }
            if (files.length > 0) {
                for (File f : files) {
                    if (f.delete()) {
                        f.delete();
                    }
                }
            }
            parentDir.delete();
        }
    }

    //    public static List<Map<String, Object>> saveMultipartFiles(String name,String originalName,MultipartFile file) throws IOException {
////        MultipartFile file = new MockMultipartFile(name,originalName, MediaType.MULTIPART_FORM_DATA_VALUE, inputStreamFile);
//        //创建根目录
//        String savePath = tempShpDir + UUID.randomUUID();
//        try {
//            //先将zip文件存放到本地，解析完后删除
//            File localFile = new File(savePath
//            );
//            localFile.mkdirs();
//            File file1 = new File(localFile + File.separator + getMultipartFileName(file.getOriginalFilename()
//            ));
//            file.transferTo(file1);
//            //解析文件
//            List<Map<String, Object>> list = resolveZip(file1, savePath);
//            return list;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            //保存完相关文件后，删除压缩包和相关文件
//            File parentDir = new File(savePath);
//            File[] files = parentDir.listFiles();
//            for (File f : files) {
//                if (f.delete()) {
//                    //文件一直被占用无法删除，直接调用gc()回收垃圾，简单粗暴
//                    System.gc();
//                    f.delete();
//                }
//            }
//            if (files.length > 0) {
//                for (File f : files) {
//                    if (f.delete()) {
//                        f.delete();
//                    }
//                }
//            }
//            parentDir.delete();
//        }
//    }
    public static byte[] wktToWkb(String wktString) throws ParseException {
        // String wktPoint = "POINT(101.23489 26.322)";
        // String wktLine = "LINESTRING(103.4210 26.30667,98.7509 27.72421)";
        // String wktPolygon = "POLYGON((100.032411 31.31231,102.76873121104 37.194305614622,107.0334056301 34.909658604412,105.96723702534 30.949603786713,100.032411 31.31231))";
        // String wktPolygonHole = "POLYGON((96.219409781775 32.777321394882,96.219409781775 40.240501628236,104.82491352023001 40.240501628236,104.82491352023001 32.777321394882,96.219409781775 32.777321394882))";

        WKTReader wktReader = new WKTReader();
        // Point point = (Point) wktReader.read(wktString);
        // LineString line = (LineString) wktReader.read(wktString);
        // Polygon polygon = (Polygon) wktReader.read(wktString);
        MultiPolygon multiPolygon = (MultiPolygon) wktReader.read(wktString);

        WKBWriter writer = new WKBWriter();
        WKBReader reader = new WKBReader();
        System.out.println(Arrays.toString(writer.write(multiPolygon)));
        System.out.println(reader.read(writer.write(multiPolygon)));

        return writer.write(multiPolygon);

        // String s = "MULTIPOLYGON (((78266.31549072266 75894.43670654297, 78488.65588378906 75995.52227783203, 78533.029296875 75877.5892944336, 78288.38348388672 75848.87707519531, 78266.31549072266 75894.43670654297)))";
        // WKBReader wkbReader = new WKBReader();
        //     WKTReader wktReader = new WKTReader();
        // Geometry readWkb = wkbReader.read(s);
        // Geometry readWkt = wktReader.read(s);
        // return readWkt;
    }

    public static void main(String[] args) throws ParseException {
        wktToWkb("MULTIPOLYGON (((78266.31549072266 75894.43670654297, 78488.65588378906 75995.52227783203, 78533.029296875 75877.5892944336, 78288.38348388672 75848.87707519531, 78266.31549072266 75894.43670654297)))");
    }

    /**
     * 从zip文件中获取 .shp .dbf .shx .prj 四个文件,并预存到本地
     *
     * @param srcFile  zip源文件
     * @param savePath 临时文件保存路径
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static List<Map<String, Object>> resolveZip(File srcFile, String savePath) {
        // 解压文件
        ZipFile zipFile = null;
        //.shp文件的存放路径
        String shpFilePath = "";
        //.prj文件的存放路径
        String prjFilePath = "";
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
//            zipFile = new ZipFile(srcFile, StandardCharsets.UTF_8);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
// 特别注意，entries包含了zip文件里的所有文件，包括子文件夹里的文件，而不仅仅只是压缩包里的第一层文件
            List<Map<String, Object>> list = new ArrayList<>();
            List<String> shpNameList = new ArrayList<>();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                // 判断是文件夹 或者 文件
                if (zipEntry.isDirectory()) {
                    continue;
                } else {
                    // 判断是否为我们需要的文件
                    if (hasSuffix.indexOf(zipEntry.getName().split("\\.")[1]) != -1) {

                        // 先临时保存到本地
                        String fileName = zipEntry.getName();
                        if (fileName.lastIndexOf("/") != -1) {
                            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                        }
                        File shpFile = new File(savePath + File.separator + fileName);
                        shpFile.createNewFile();
                        copyFile(zipFile.getInputStream(zipEntry), new FileOutputStream(shpFile));
                        // 获取.shp文件的保存路径
//                        if (
//                                shpFilePath.indexOf("shp") == -1
////                                        shpFile.getName().indexOf("shp") != -1
//                        ) {
                        shpFilePath = shpFile.getName().indexOf("shp") != -1 ? shpFile.getAbsolutePath() : "";
                        if (!"".equals(shpFilePath) && shpFile.getName().indexOf(".shp.") == -1) {
                            shpNameList.add(shpFilePath);
                            shpPath = shpFilePath;
                        }
                        //                        }
                        // 获取.prj文件的保存路径
                        if (prjFilePath.indexOf("prj") == -1) {
                            prjFilePath = shpFile.getName().indexOf("prj") != -1 ? shpFile.getAbsolutePath() : "";
                        }

                    }
                }
            }
            shpNameList.forEach((item) -> {
                list.addAll(readShapeFile(item, 4490));
            });

            // 解析prj文件,获取坐标系
//            Integer sRid = getSrid(prjFilePath);
            // 解析shp文件，wkt数据
//            List<Map<String, Object>> list = readShapeFile(shpFilePath, sRid);

            return list;
        } catch (IOException e) {
            throw new RuntimeException("上传shp文件错误:" + e.getMessage());
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 解析shp文件
     *
     * @param path 文件所在路径
     * @param sRid 坐标系
     */
    public static List<Map<String, Object>> readShapeFile(String path, Integer sRid) {
        File file = new File(path);
        if (file == null) {
            return null;
        }
        // map记录shapefile key-value数据
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // 通过store获取featurecollection
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            SimpleFeatureCollection features = featureSource.getFeatures();
            SimpleFeatureIterator iterator = features.features();
            // 遍历featurecollection
            while (iterator.hasNext()) {
                Map<String, Object> data = new HashMap<>();
                SimpleFeature feature = iterator.next();
                Collection<Property> properties = feature.getProperties();
                Iterator<Property> it = properties.iterator();
                // 遍历feature的properties
                while (it.hasNext()) {
                    Property pro = it.next();
                    String field = pro.getName().toString().toLowerCase(Locale.ROOT);

                    if (pro.getValue() != null) {

                        String value = pro.getValue().toString();
                        // 转码 防止中文乱码

                        byte[] bytes = value.getBytes(StandardCharsets.ISO_8859_1);
                        value = new String(bytes, StandardCharsets.UTF_8);
//                        boolean isGBK = Charset.forName("GBK").newEncoder().canEncode(value);
//                        if (!isGBK) {
                        if (isMessyCode(value)) {
                            value = new String(bytes, "GBK");
                        }

                        data.put(field, value);
//                        if(field.equals("ydlx")&&value==null){
//                            System.out.println();
//                        }
                    }
                }
                // 放入坐标系
                data.put("srid", sRid);
                list.add(data);
            }
            // 解析完毕后关闭store，否则文件无法删除
            store.dispose();
            return list;
        } catch (IOException e) {
            throw new RuntimeException("解析shp文件失败:" + e.getMessage());
        }
    }


    /**
     * 解析prj文件，获取坐标系
     *
     * @param shpFilePath prj文件路径
     * @return 坐标系的code ，例如3857,4326等等
     */
    public static Integer getSrid(String shpFilePath) {

        ShapefileDataStore dataStore = buildDataStore(shpFilePath);
        Integer code = null;
        try {
            String wkt = dataStore.getSchema().getCoordinateReferenceSystem().toWKT();
            CoordinateReferenceSystem crsTarget = CRS.parseWKT(wkt);
            code = CRS.lookupEpsgCode(crsTarget, true);

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FactoryException e) {
            e.printStackTrace();
        } finally {
            dataStore.dispose();
        }
        return code;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 构建ShapeDataStore对象。
     *
     * @param shpFilePath shape文件路径。
     * @return
     */
    public static ShapefileDataStore buildDataStore(String shpFilePath) {
        ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore) factory
                    .createDataStore(new File(shpFilePath).toURI().toURL());
            if (dataStore != null) {
                dataStore.setCharset(Charset.forName("UTF-8"));
            }
            return dataStore;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 复制文件
     *
     * @param in  原文件流输入流
     * @param out 目标文件输出流
     */
    public static void copyFile(InputStream in, OutputStream out) {
        int len;
        byte[] b = new byte[1024];
        try {
            while ((len = in.read(b, 0, 1024)) != -1) {
                out.write(b, 0, len);
                out.flush();
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * MultipartFile获取文件名
     */
    public static String getMultipartFileName(String fileName) {
        // 判断浏览器，ie浏览器会获取文件的路径，我们不需要路径
        // Linux
        int unixSep = fileName.lastIndexOf('/');
        // Windows
        int winSep = fileName.lastIndexOf('\\');
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1) {
            fileName = fileName.substring(pos + 1);
        }
        return fileName;

    }
}
