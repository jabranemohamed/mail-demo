package org.mhj.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jabrane mohamed
 */
@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;



    @RequestMapping(method = RequestMethod.POST,path = "/register")
    @ResponseBody
    public String register(@RequestBody UserInfo userInfo) throws Exception {
        emailService.sendEmail(userInfo);
        return "Email Sent..!";
    }
}
