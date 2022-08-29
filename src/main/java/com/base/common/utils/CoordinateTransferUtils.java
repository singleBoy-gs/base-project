package com.base.common.utils;

import com.base.common.model.vo.GetLocationByAddressVO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
* @author Single Minded
* @create 2022-08-25 17:40:12
* @Description 坐标系转换工具类
* @since 1.0
*/
public class CoordinateTransferUtils {

   /**
	* 国内坐标边界
	*/
   private static final double MIN_LON = 72.004D;
   private static final double MAX_LON = 137.8347D;
   private static final double MIN_LAT = 0.8293D;
   private static final double MAX_LAT = 55.8271D;

   /**
	* PI 圆周率
	*/
   private static final double PI = 3.14159265358979324D;

   /**
	* A WGS 长轴半径
	*/
   private static final double A = 6378245.0D;

   /**
	* EE WGS 偏心率的平方
	*/
   private static final double EE = 0.00669342162296594323D;

   /**
	* WGS84转换GCJ02核心方法
	*
	* @param fromLon 转换前的经度
	* @param fromLat 转换前的纬度
	* @return 转换后的经纬度map对象
	*/
   public static GetLocationByAddressVO wgs84ToGcj02(double fromLon, double fromLat) {
	   GetLocationByAddressVO getLocationByAddressVO = new GetLocationByAddressVO();
	   // 国外坐标不用进行加密
	   if (outOfChina(fromLon, fromLat)) {
		   getLocationByAddressVO.setLon(fromLon);
		   getLocationByAddressVO.setLat(fromLat);
		   return getLocationByAddressVO;
	   }
	   // 计算转换后的经纬度坐标
	   double dLat = transformLat(fromLon - 105.0, fromLat - 35.0);
	   double dLon = transformLon(fromLon - 105.0, fromLat - 35.0);
	   double radLat = fromLat / 180.0 * PI;
	   double magic = Math.sin(radLat);
	   magic = 1 - EE * magic * magic;
	   double sqrtMagic = Math.sqrt(magic);
	   dLat = (dLat * 180.0) / ((A * (1 - EE)) / (magic * sqrtMagic) * PI);
	   dLon = (dLon * 180.0) / (A / sqrtMagic * Math.cos(radLat) * PI);
	   double mgLat = fromLat + dLat;
	   double mgLon = fromLon + dLon;
	   getLocationByAddressVO.setLon(new BigDecimal(mgLon + "", new MathContext(9, RoundingMode.HALF_UP)).doubleValue());
	   getLocationByAddressVO.setLat(new BigDecimal(mgLat + "", new MathContext(9, RoundingMode.HALF_UP)).doubleValue());

	   return getLocationByAddressVO;
   }

   /**
	* GCJ02转换WGS84
	*
	* @param lon 转换前的经度
	* @param lat 转换后的纬度
	* @return 转换后的经纬度map对象
	*/
   public static GetLocationByAddressVO gcj02ToWgs84(double lon, double lat) {
	   GetLocationByAddressVO getLocationByAddressVO = new GetLocationByAddressVO();
	   double longitude = lon * 2 - wgs84ToGcj02(lon, lat).getLon();
	   double latitude = lat * 2 - wgs84ToGcj02(lon, lat).getLat();
	   getLocationByAddressVO.setLon(longitude);
	   getLocationByAddressVO.setLat(latitude);
	   return getLocationByAddressVO;
   }

   /**
	* GCJ02转换BD09
	*
	* @param gcjLat GCL纬度坐标
	* @param gcjLng GCL经度坐标
	*/
   public static GetLocationByAddressVO Gcj02ToBd09(double gcjLat, double gcjLng) {
	   GetLocationByAddressVO getLocationByAddressVO = new GetLocationByAddressVO();
	   double z = Math.sqrt(gcjLng * gcjLng + gcjLat * gcjLat) + 0.00002 * Math.sin(gcjLat * PI);
	   double theta = Math.atan2(gcjLat, gcjLng) + 0.000003 * Math.cos(gcjLng * PI);
	   getLocationByAddressVO.setLon(z * Math.cos(theta) + 0.0065);
	   getLocationByAddressVO.setLat(z * Math.sin(theta) + 0.006);
	   return getLocationByAddressVO;
   }

   /**
	* BD09转换GCJ02
	*
	* @param bdLat 百度纬度坐标
	* @param bdLng 百度经度坐标
	*/
   public static GetLocationByAddressVO bd09ToGcj02(double bdLat, double bdLng) {
	   GetLocationByAddressVO getLocationByAddressVO = new GetLocationByAddressVO();
	   double x = bdLng - 0.0065, y = bdLat - 0.006;
	   double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
	   double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);

	   getLocationByAddressVO.setLon(z * Math.cos(theta));
	   getLocationByAddressVO.setLat(z * Math.sin(theta));
	   return getLocationByAddressVO;
   }

   /**
	* WGS84转换GCJ02优化（处理转换前经纬度坐标为null问题）
	*
	* @param fromLon 转换前的经度
	* @param fromLat 转换前的纬度
	* @param toLon   转换后的经度
	* @param toLat   转换后的纬度
	*/
   private static void wgs84ToGcj02(Supplier<BigDecimal> fromLon, Supplier<BigDecimal> fromLat, Consumer<BigDecimal> toLon, Consumer<BigDecimal> toLat) {
	   wgs84ToGcj02(Optional.ofNullable(fromLon.get()).orElse(BigDecimal.ZERO), Optional.ofNullable(fromLat.get()).orElse(BigDecimal.ZERO), toLon, toLat);
   }

   /**
	* WGS84转换GCJ02优化（处理转换后经纬度坐标）
	*
	* @param fromLon 转换前的经度
	* @param fromLat 转换前的纬度
	* @param toLon   转换后的经度
	* @param toLat   转换后的纬度
	*/
   private static void wgs84ToGcj02(BigDecimal fromLon, BigDecimal fromLat, Consumer<BigDecimal> toLon, Consumer<BigDecimal> toLat) {
	   final GetLocationByAddressVO transformRes = wgs84ToGcj02(fromLon.doubleValue(), fromLat.doubleValue());
	   toLon.accept(new BigDecimal(transformRes.getLon() + ""));
	   toLat.accept(new BigDecimal(transformRes.getLat() + ""));
   }

   /**
	* 坐标是否在国外
	*
	* @param lon 经度
	* @param lat 纬度
	* @return true 国外坐标 false 国内坐标
	*/
   private static boolean outOfChina(double lon, double lat) {
	   if (lon < MIN_LON || lon > MAX_LON) {
		   return true;
	   }
	   return lat < MIN_LAT || lat > MAX_LAT;
   }

   /**
	* 转换经度坐标
	*
	* @param x 偏移后的经度
	* @param y 偏移后的纬度
	* @return double 转换经度坐标
	*/
   private static double transformLat(double x, double y) {
	   double transform = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
	   transform += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
	   transform += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
	   transform += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
	   return transform;
   }

   /**
	* 转换纬度坐标
	*
	* @param x 偏移后的经度
	* @param y 偏移后的纬度
	* @return double 转换纬度坐标
	*/
   private static double transformLon(double x, double y) {
	   double transform = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
	   transform += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
	   transform += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
	   transform += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
	   return transform;
   }

   /**
	* 坐标系转换工具类测试
	*
	* @param args 参数
	*/
   public static void main(String[] args) {

	   // WGS84ToGCJ02 116.404, 39.915 转换后 [ 116.41024449916938, 39.91640428150164 ]
	   /*double lon3 = 116.404D;
	   double lat3 = 39.915D;
	   GetLocationByAddressVO transform3Result = wgs84ToGcj02(lon3, lat3);
	   System.out.println("WGS84ToGCJ02 转换前经纬度(" + lon3 + "," + lat3 + ") 转换后经纬度(" + transform3Result.get("lon") + "," + transform3Result.get("lat") + ")");*/

	   // GCJ02ToWGS84 116.404, 39.915 转换后 [ 116.39775550083061, 39.91359571849836 ]
	   double lon4 = 118.155510D;
	   double lat4 = 39.669007D;
	   GetLocationByAddressVO getLocationByAddressVO = gcj02ToWgs84(lon4, lat4);
	   System.out.println("GCJ02ToWGS84 转换前经纬度(" + lon4 + "," + lat4 + ") 转换后经纬度(" + getLocationByAddressVO.getLon() + "," + getLocationByAddressVO.getLat() + ")");

	   // BD09ToGCJ02 116.404, 39.915 转换后 [ 116.39762729119315, 39.90865673957631 ]
	   /*double lon5 = 116.404D;
	   double lat5 = 39.915D;
	   Map<String, Double> transform5Result = bd09ToGcj02(lon5, lat5);
	   System.out.println("BD09ToGCJ02 转换前经纬度(" + lon5 + "," + lat5 + ") 转换后经纬度(" + transform5Result.get("lon") + "," + transform5Result.get("lat") + ")");*/
   }
}
