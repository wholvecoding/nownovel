package com.wolvescoding.nownovel.manager.message;

import com.wolvescoding.nownovel.core.config.MailProperties;
import com.wolvescoding.nownovel.core.constant.MessageSenderTypeConsts;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Component(value = MessageSenderTypeConsts.REGISTER_MAIL_SENDER)
@EnableConfigurationProperties(MailProperties.class)
public class RegisterMailSender extends AbstractMailSender {
    public RegisterMailSender(MailProperties mailProperties, JavaMailSender mailSender) {
        super(mailProperties, mailSender);//调用父类的构造函数
    }
    @Override
    protected String getTitleTemplate() {
        return "欢迎注册nownovel";
    }



    @Override
    protected String getContentTemplate() {
        return """
                <div>
                    感谢你注册小说精品屋！你的账户现在处于活动状态。
                </div>
                <ul>
                    <li> 你的账户电子邮件：{}
                    <li> 你的账户用户名：{}
                </ul>
                <div style="padding: 10px 0 50px 0; text-align: center;">
                    <a style="background: #0274be; color: #fff; padding: 12px 30px; text-decoration: none; border-radius: 3px; letter-spacing: 0.3px;" href="{}" target="_blank" rel="noopener">
                        登录我们的网站
                    </a>
                </div>
                
                如果你有任何问题，请通过 {} 与我们联系。
            """;
    }

    @Override
    protected String resolveContent(String content, Object... args) {
        String websiteLink = "https://www.xxyopen.com";
        String websiteEmail = "xxyopen@foxmail.com";

        return super.resolveContent(content,
                Stream.of(args, new Object[]{websiteLink, websiteEmail}).flatMap(Arrays::stream).toArray());
    }

}
