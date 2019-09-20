package work.icql.springboot.service;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/31 17:22
 * @Title MessageService
 * @Description MessageService
 */
public interface MessageService {
    void kk(String usercode, String subject, String content);

    void email(String email, String subject, String content);

    void sms(String mobile, String subject, String content);

    void all(String usercode, String mobile, String email, String subject, String content);
}
