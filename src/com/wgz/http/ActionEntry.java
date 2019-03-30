package com.wgz.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wgz.frame.utils.JsonUtil;
import com.wgz.xml.XmlUtil;



public class ActionEntry extends HttpServlet {
	private static final long serialVersionUID = 987565432145253L;
	private static final Logger log = LoggerFactory.getLogger(ActionEntry.class);
	
	@Override
	public void init(){
		System.out.println("Action初始化");
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("xml请求");
        try {
            // 读取请求报文
            String content = getRequestParams(request);
            BaseModel model = XmlUtil.toBean(BaseModel.class,content);
            log.info(model.toString());
            String jsonStr = JsonUtil.objectToJson(model);
            log.info(jsonStr);
            sendResponseResult(response,jsonStr);
		} catch (Exception e) { 
			throw new RuntimeException("执行失败");
	    }
            
	}
	
    private String getRequestParams(HttpServletRequest request) throws Exception {
        try (InputStream is = request.getInputStream(); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            request.setCharacterEncoding("UTF-8");

            IOUtils.copy(is, os);

            String json = os.toString("UTF-8");
            log.info("请求报文: {}", json);

            return json;
        } catch (Exception e) {
			throw new RuntimeException("读取请求报文失败");
        }
    }

    private void sendResponseResult(HttpServletResponse response, String json) {
        byte[] bytes = null;
        try {
            bytes = json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
        }

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(bytes.length);

        try (OutputStream os = response.getOutputStream()) {
            log.info("应答结果: {}", json);
            os.write(bytes);
        } catch (Exception e) {
            log.error("发应答送报文错误", e);
        }
    }

    private String badMessage(String code,String message) {
        BaseModel model = new BaseModel();
        model.setRetCode(code);
        model.setRetMsg(message);
        return JsonUtil.objectToJson(model);
    }
    
    public static void main(String[] args){
    	String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+
			 "<model>"+
			 "<version>1.0</version>"+
  			 "<retCode>0000</retCode>"+
  			 "<retMsg>成功</retMsg>"+
  			 "</model>";
	
       BaseModel model = null;
		try {
			model = XmlUtil.toBean(BaseModel.class,content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       System.out.println(model.toString());
       String jsonStr = JsonUtil.objectToJson(model);
       System.out.println(jsonStr);
    }
	
}
