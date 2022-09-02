package pers.lxl.mylearnproject.programbase.security;


import com.alibaba.fastjson.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AESUtil
 * @author: 〆、dyh
 * @since: 2022/3/3 10:38
 */
public class AESUtil {

}
