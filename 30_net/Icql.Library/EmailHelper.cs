using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

namespace Icql.Library
{
    public class EmailHelper
    {
        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="subject">邮件主题</param>
        /// <param name="body">邮件正文</param>
        /// <param name="fromUsername">发件人名</param>
        /// <param name="fromHost">发送人邮件服务器地址：pop3.hnu.edu.cn,smtp.hnu.edu.cn</param>
        /// <param name="fromMail">发件人邮箱地址</param>
        /// <param name="fromMailPassword">发件人邮箱密码</param>
        /// <param name="toMail">收件人邮箱地址</param>
        /// <param name="toMailCC">抄送收件人邮箱地址集合</param>
        /// <returns></returns>
        public bool SendMailUse(string subject, string body, string fromUsername,string fromHost, string fromMail, string fromMailPassword, string toMail,List<string> toMailCC = null)
        {
            LogHelper lh = new LogHelper();

            SmtpClient client = new SmtpClient();
            client.DeliveryMethod = SmtpDeliveryMethod.Network;//指定电子邮件发送方式    
            client.Host = fromHost;//发送端邮件服务器地址
            client.UseDefaultCredentials = true;
            client.Credentials = new System.Net.NetworkCredential(fromMail, fromMailPassword);//用户名、密码

            MailMessage msg = new MailMessage();
            msg.From = new MailAddress(fromMail, fromUsername);//发件人名
            msg.To.Add(toMail);
            if (toMailCC != null && toMailCC.Count > 0)
            {
                toMailCC.ForEach(x => msg.CC.Add(x));
            }

            msg.Subject = subject;//邮件标题   
            msg.Body = body;//邮件内容   
            msg.BodyEncoding = Encoding.UTF8;//邮件内容编码   
            msg.IsBodyHtml = true;//是否是HTML邮件   
            msg.Priority = MailPriority.High;//邮件优先级   

            try
            {
                client.Send(msg);
            }
            catch (SmtpException ex)
            {
                return false;
            }

            return true;
        }
    }
}
