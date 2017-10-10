package com.matio.controller;

import com.matio.constraints.DefaultValue;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.SessionMapper;
import com.matio.mapping.UserMapper;
import com.matio.mapping.User_authorityMapper;
import com.matio.mapping.User_levelMapper;
import com.matio.pojo.Session;
import com.matio.pojo.User;
import com.matio.pojo.User_authority;
import com.matio.pojo.User_level;
import com.matio.tools.Tools;
import com.matio.unit.JsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by matioyoshitoki on 2017/8/29.
 */

@RestController
public class AccountController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SessionMapper sessionMapper;
    @Autowired
    private User_levelMapper user_levelMapper;
    @Autowired
    private User_authorityMapper user_authorityMapper;

    @RequestMapping(value = "/reg", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String regist(
            @RequestParam(Keys.PHONENUMBER) String phone_num,
            @RequestParam(Keys.PASSWORD) String password,
            @RequestParam(Keys.VCODE) String vcode
    ) throws IOException {

        User user = userMapper.selectByUserPhoneNum(phone_num);
        JSONObject result ;
        SimpleDateFormat dead_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (user == null){
            User new_user = new User();

            new_user.setPhoneNumber(phone_num);
            new_user.setUserPwd(password);
            new_user.setUserName("匿名用户");

            new_user.setUserRegisterTime(new Date());
            new_user.setUserDetail("Say Nothing");
            new_user.setUserIcon("Default icon");
            userMapper.insert(new_user);



            Session session = new Session();
            try {
                Date dead_time = dead_format.parse(Tools.getSomeDayBefore(dead_format.format(new Date()),-7));
                session.setDeadTime(dead_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Integer user_id = userMapper.selectByUserPhoneNum(phone_num).getUserId();

            session.setUserId(user_id);
            String session_id = Tools.generateSessionId(15);
            while (sessionMapper.selectBySession(session_id) != null){
                session_id = Tools.generateSessionId(15);
            }
            session.setSessionId(session_id);
            sessionMapper.insert(session);

            User_level user_level = new User_level();
            user_level.setUserId(user_id);
            user_level.setUserLevel(DefaultValue.DEFAULT_LEVEL);
            user_levelMapper.insert(user_level);

            User_authority user_authority = new User_authority();
            user_authority.setUserId(user_id);
            user_authority.setUserAuthority(DefaultValue.DEFAULT_AUTHORITY);
            user_authorityMapper.insert(user_authority);

            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.SESSIONID,session_id);
        }else {
            result = JsonUtil.fromErrors(Errors.NAME_DUPLICATE);
        }

        return result.toString();
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String login(
            @RequestParam(Keys.PHONENUMBER) String phone_num,
            @RequestParam(Keys.PASSWORD) String password
    ) throws IOException {
        User user = userMapper.selectByUserPhoneNum(phone_num);
        JSONObject result ;

        System.out.println(phone_num+":"+password);

        SimpleDateFormat dead_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (user == null){

            result = JsonUtil.fromErrors(Errors.NO_SUCH_NAME);

        }else {
            System.out.println("user:"+user.getUserName());
            if (!user.getUserPwd().equals(password)){
                result = JsonUtil.fromErrors(Errors.WRONG_PASSWORD);
            }else {
                String session_id ;
                if (sessionMapper.selectByUserId(user.getUserId())==null){
                    Session session = new GetSession(phone_num, dead_format).invoke();
                    System.out.println("新session:"+session.getSessionId());
                    sessionMapper.insert(session);
                    session_id = session.getSessionId();
                }else {
                    Session session = new GetSession(phone_num, dead_format).invoke();
                    System.out.println("新session:"+session.getSessionId());
                    sessionMapper.updateSession(session);
                    session_id = session.getSessionId();
                }

                User_level user_level = user_levelMapper.selectByPrimaryKey(user.getUserId());
                User_authority user_authority = user_authorityMapper.selectByPrimaryKey(user.getUserId());

                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.USERICON,user.getUserIcon());
                result.put(Keys.USERAUTHORITY,user_authority.getUserAuthority());
                result.put(Keys.USERLEVEL,user_level.getUserLevel());
                result.put(Keys.SESSIONID, session_id);


            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/autoLog", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String auto_login(
            @RequestParam(Keys.SESSIONID) String session_id
    ) throws IOException {
        System.out.println(session_id);
        Session user_session = sessionMapper.selectBySession(session_id);
        JSONObject result ;
//        System.out.println(user_session.getUserId());

        if (user_session == null){
            System.out.println("session不存在");
            result = JsonUtil.fromErrors(Errors.SESSION_DEAD);

        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(user_session.getDeadTime())+":"+user_session.getDeadTime().compareTo(new Date()));
            if (user_session.getDeadTime().compareTo(new Date()) < 0){
                result = JsonUtil.fromErrors(Errors.SESSION_DEAD);
            }else {
                User user = userMapper.selectByPrimaryKey(user_session.getUserId());
                System.out.println(user.getUserName());

                User_level user_level = user_levelMapper.selectByPrimaryKey(user_session.getUserId());
                User_authority user_authority = user_authorityMapper.selectByPrimaryKey(user_session.getUserId());

                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.USERNAME, user.getUserName());
                result.put(Keys.USERICON, user.getUserIcon());
                result.put(Keys.USERAUTHORITY, user_authority.getUserAuthority());
                result.put(Keys.USERLEVEL, user_level.getUserLevel());
            }

        }
        System.out.println(result.toString());
        return result.toString();
    }


    @RequestMapping(value = "/getvcode", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getVcode(
            @RequestParam(Keys.PHONENUMBER) String phone_num
    ) throws IOException {

        return "";
    }

    private class GetSession {
        private String phone_num;
        private SimpleDateFormat dead_format;

        public GetSession(String phone_num, SimpleDateFormat dead_format) {
            this.phone_num = phone_num;
            this.dead_format = dead_format;
        }

        public Session invoke() {
            Session session = new Session();
            try {
                Date dead_time = dead_format.parse(Tools.getSomeDayBefore(dead_format.format(new Date()), -7));
                session.setDeadTime(dead_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            session.setUserId(userMapper.selectByUserPhoneNum(phone_num).getUserId());
            String session_id = Tools.generateSessionId(15);
            while (sessionMapper.selectBySession(session_id) != null) {
                session_id = Tools.generateSessionId(15);
            }
            session.setSessionId(session_id);
            return session;
        }
    }
}
