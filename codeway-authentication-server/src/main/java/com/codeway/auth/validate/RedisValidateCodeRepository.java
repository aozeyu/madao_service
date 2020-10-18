package com.codeway.auth.validate;

import com.codeway.auth.exception.ValidateCodeException;
import com.codeway.auth.validate.impl.ValidateCode;
import com.codeway.constant.CommonConst;
import com.codeway.db.redis.service.RedisService;
import com.codeway.enums.StatusEnum;
import com.codeway.enums.ValidateCodeType;
import com.codeway.exception.custom.CaptchaNotMatchException;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.LogBack;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * redis验证码
 * 将图片验证码或者短信验证码存在redis中
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisService redisService;


	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		String validateCode = JsonUtil.toJsonString(code);
		redisService.setKeyStr(buildKey(request, type), validateCode, CommonConst.TIME_OUT_FIVE_MINUTES.longValue());
		//redisTemplate.opsForValue().set(buildKey(request, type), s, 30, TimeUnit.MINUTES);
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		String keyStr = redisService.getKeyStr(buildKey(request, type))
				.orElseThrow(CaptchaNotMatchException::new)
				.toString();
		if (keyStr == null) {
			return null;
		}
		return JsonUtil.jsonToPojo(keyStr, ValidateCode.class);
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		//redisService.del(buildKey(request, type));
	}

	/**
	 * 获取请求头中DEVICE-ID的值，此值与客户端绑定，一端一码
	 *
	 * @param type 验证码类型：sms or captcha
	 */
	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId = request.getHeader("DEVICE-ID");
		if (StringUtils.isBlank(deviceId) && type.toString().equals("CAPTCHA")) {
			throw new ValidateCodeException("请在请求头中携带DEVICE-ID参数");
		}
		if (type.toString().equalsIgnoreCase("sms")) {
			String paramName = CommonConst.DEFAULT_PARAMETER_NAME_PHONE;
			String phone = null;
			try {
				phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
			} catch (ServletRequestBindingException e) {
				LogBack.error("buildKey失败：{}", StatusEnum.PARAM_MISSING.getMsg(), e);
				throw new RuntimeException(StatusEnum.PARAM_MISSING.getMsg());
			}
			return "code:" + type.toString().toLowerCase() + ":" + phone;
		}
		return "code:" + type.toString().toLowerCase() + ":" + deviceId;
	}

}
