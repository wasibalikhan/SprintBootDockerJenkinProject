package com.wak;


import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private Environment environment;

    public static final String DISABLE_SMS_BASED_ON_VISA_TYPE = "DISABLE_SMS_BASED_ON_VISA_TYPE";

    private static List<String> disableSMSVisaTypeList = null;


    @RequestMapping(value="/v1/searchUser",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    Long getUserDetails(
            @RequestParam(value ="ppsId", required = false) long ppsId) throws Exception{

        String smsDisabledVisaTypes = environment.getProperty(DISABLE_SMS_BASED_ON_VISA_TYPE);
        System.out.println("Configured Viva Types : " + smsDisabledVisaTypes);
        disableSMSVisaTypeList = Lists.newArrayList(Splitter.on(",").split(smsDisabledVisaTypes));
        System.out.println(disableSMSVisaTypeList.toString());
        if (!disableSMSVisaTypeList.contains("3")) {
            System.out.println("success");
        }

         long id = ppsId;
         return id;


    }
}
