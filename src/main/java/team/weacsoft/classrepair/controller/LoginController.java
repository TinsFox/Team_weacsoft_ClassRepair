package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.entity.wx.Code2SessionBody;
import team.weacsoft.classrepair.repository.UserInfoRepository;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.util.WxUtils;

import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="/api/v1")
public class LoginController {

    private JSONObject jsonObject = null;
    private UserInfo userInfo = null;

    private UserInfoRepository userInfoRepository;
    private OperationLogService operationLogService;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setOperationLogService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> payload) {
        jsonObject = JSONUtil.parseObj(payload);
        userInfo = new UserInfo();

        JSONObject code2sessionResp = WxUtils.wxAuth(new Code2SessionBody(jsonObject.getStr("code")));
        userInfo.setOpenid(code2sessionResp.getInt("openid"));
        userInfo.setSessionKey(code2sessionResp.getStr("session_key"));

        setProperty("name");
        setProperty("phone");
        setProperty("avatar");
        setProperty("birth");
        setProperty("nickname");
        setProperty("password");
        setProperty("phonetype");
        setProperty("schoolid");
        setProperty("signature");
        setProperty("wechat");
        setProperty("token");
        if (jsonObject.containsKey("role")) {
            int role = jsonObject.getInt("role");
            if (!(role >= 1 && role <= 4)) {
                return ResultFactory.builePropertyErroresult("role字段错误，role范围为1到4");
            }
            userInfo.setRole(role);
        }
        //todo 改名login
        operationLogService.addLog(jsonObject.getInt("openid"), "Login", EventEnum.Login.event);
        try{
            userInfoRepository.save(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("保存失败，数据库已有该openid或name");
        }
  //todo addlog
        return ResultFactory.buildSuccessResult("成功");
    }

    private void setProperty(String key){
        if(jsonObject != null && userInfo != null){
            if(jsonObject.containsKey(key)){
                userInfo.setOpenid(jsonObject.getInt(key));
            }
        }
    }


}
