package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;
    // 获取Template中的信息

    @Test
    public void testTextMail() {
        mailClient.sendMail("1494385066@qq.com","TEST","Welcome");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "sunday");


        String content = templateEngine.process("/mail/demo.html", context);
        System.out.println(content);

        mailClient.sendMail("1494385066@qq.com", "HTML", content);
    }
}
