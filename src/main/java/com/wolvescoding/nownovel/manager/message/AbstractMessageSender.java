package com.wolvescoding.nownovel.manager.message;


public abstract class AbstractMessageSender implements MessageSender {
    private static final String PLACEHOLDER = "{}";
    @Override
    public final void sendMessage(Long toUserId, Object... args) {
        //1. 获取标题模板
        String titleTemplate = getTitleTemplate();
        //2. 获取内容
        String contentTemplate = getContentTemplate();
        //3. 解析标题
        String title = resolveTitle(titleTemplate, args);
        //4. 解析内容
        String content = resolveContent(contentTemplate, args);
        //5. 发送消息,由不同子类型的消息发送器（邮件发送器、系统通知发送器等）去实现该步骤
        sendMessage(toUserId, title, content);
    }

    protected  String resolveTitle(String titleTemplate, Object[] args){
        return titleTemplate;
    }

    protected abstract String getContentTemplate();

    protected String resolveContent(String contentTemplate, Object[] args){
        if (args.length > 0) {
            StringBuilder formattedContent = new StringBuilder(contentTemplate);
            for (Object arg : args) {
                int start = formattedContent.indexOf(PLACEHOLDER);
                formattedContent.replace(start, start + PLACEHOLDER.length(),
                        String.valueOf(arg));
            }
            return formattedContent.toString();
        }
        return contentTemplate;
    }

    protected abstract void sendMessage(Long toUserId, String messageTitle, String messageContent);

 

   

    protected abstract String getTitleTemplate();
    

}
