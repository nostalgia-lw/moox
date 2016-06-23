package com.moox.system.sendMsg;

import com.moox.system.util.CommonUtils;
import com.moox.system.util.SendSmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
@Controller
@RequestMapping("/sendMsg")
public class SendMsgController {
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendMsg(String mobile,String msg){
        SendSmsUtil s=new SendSmsUtil();
        try{
            String body = s.sendSms(mobile, msg);
            if(body=="0"){
                return CommonUtils.msgCallback(true, "发送成功", null, null);
            }else{
                return CommonUtils.msgCallback(false, "发送失败", null, null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonUtils.msgCallback(false, "发送失败", null, null);
    }
}
