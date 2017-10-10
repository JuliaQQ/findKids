package com.matio.unit;


import com.matio.constraints.Errors;
import org.json.JSONObject;

public class JsonUtil {

	/**
	 * @note 把错误码转换成json对象
	 * @author sxy
	 * @param Errors.
	 *            *
	 * @return JSONObject
	 * @date 2016年4月28日
	 */
	public static JSONObject fromErrors(String err) {

		JSONObject json = new JSONObject();

		if (Errors.SUCCESS.equals(err)) {
			json.put("status", "0");
		} else {
			json.put("status", "1");
			json.put("info", err);
		}

		return json;
	}

	/**
	 * @note 把错误码转换成json字符串
	 * @author sxy
	 * @param Errors.
	 *            *
	 * @return String
	 * @date 2016年4月28日
	 */
	public static String fromErrorsToString(String err) {
		return fromErrors(err).toString();
	}

}
