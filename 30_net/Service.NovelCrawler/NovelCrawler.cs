using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.ServiceProcess;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Timers;

namespace NovelCrawler
{
    partial class NovelCrawler : ServiceBase
    {
        public string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }

        public NovelCrawler()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            // TODO:  在此处添加代码以启动服务。
            WriteLog("服务开始");
            #region 定时服务
            Timer t = new Timer { Interval = 1000 * 60 * 10, AutoReset = true, Enabled = true };
            t.Elapsed += new System.Timers.ElapsedEventHandler(Do);
            #endregion
        }

        protected override void OnStop()
        {
            // TODO:  在此处添加代码以执行停止服务所需的关闭操作。
            WriteLog("服务停止");
        }

        /// <summary>
        /// 固定时间内执行：22:00——00:00 期间每10分钟执行一次
        /// </summary>
        private void Do(object sender, ElapsedEventArgs e)
        {
            if (e.SignalTime.Hour < 22)
                return;

            List<string> urls = GetTheLastUrl(5);
            List<string> urlsForToday = new List<string>();
            string content = String.Empty;
            string log = ReadLog();//读取日志文件

            //获取日志文件的最新url，筛选出日志里没有记录的urls里的url，若筛选完成后urlsForToday的数量大于0，则代表有更新，发送邮件

            foreach (var item in urls)
            {
                if (!log.Contains(item))
                {
                    urlsForToday.Add(item);
                }
            }
            List<string> newUrls = new List<string>();
            urlsForToday.ForEach(
                x =>
                {
                    newUrls.Add(x);
                    newUrls.Add(x.Substring(0, x.Length - 5) + "_2.html");
                });

            newUrls.Sort();

            if (newUrls.Count > 0)
            {
                foreach (var item in newUrls)
                {
                    content += GetContentByUrl(item);
                }
                if (SendMailUse("icql—" + DateTime.Now.ToLongDateString(), content, "icql", "test4icql@qq.com", "haofomnbrukhfihg", "icql618@qq.com"))//发送邮件
                {
                    foreach (var item in newUrls)
                    {
                        WriteLog("发送成功：" + "icql—" + DateTime.Now.ToLongDateString() + " " + item);//将成功发送更新链接保存在日志中
                    }
                }
            }
        }

        #region 爬虫
        /// <summary>
        /// 获取最近num章的url
        /// </summary>
        public List<string> GetTheLastUrl(int num)
        {
            string basicUrl = "http://wap.xxbiquge.com/0_780/";//主页网址
            HtmlDocument doc = GetHtmlDocByUrlStr(basicUrl);
            HtmlNodeCollection hrefList = doc.DocumentNode.SelectNodes("//a[@href]");
            List<string> urlList = new List<string>();

            for (int i = 0; i < hrefList.Count; i++)
            {
                HtmlAttribute att = hrefList[i].Attributes["href"];
                Regex reg = new Regex(@"\d{7}\s*");
                if (reg.IsMatch(att.Value))
                    urlList.Add(basicUrl + att.Value);
            }

            urlList = urlList.Distinct().ToList();//去重
            urlList.Sort();//排序
            urlList.RemoveRange(0, urlList.Count - num);//取前num章

            return urlList;
        }

        /// <summary>
        /// 根据url获取单章内容
        /// </summary>
        public string GetContentByUrl(string url)
        {
            HtmlDocument doc = GetHtmlDocByUrlStr(url);
            HtmlNodeCollection collect = doc.DocumentNode.SelectNodes("//div[@id='chaptercontent']");
            HtmlNodeCollection titleCollect = doc.DocumentNode.SelectNodes("//div[@id='chaptercontent']");
            string title = doc.DocumentNode.SelectNodes("//span[@class='title']")[0].InnerText.Replace("&nbsp;&nbsp;校花的贴身高手", "");
            string content = doc.DocumentNode.SelectNodes("//div[@id='chaptercontent']")[0].InnerText.Replace("\n", "").Replace("style_top()&nbsp;&nbsp;&nbsp;&nbsp;『章节错误,点此举报』&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", "").Replace("style_top()", "").Replace("『章节错误,点此举报』", "").Replace("『加入书签，方便阅读』", "").Replace("&nbsp;&nbsp;&nbsp;&nbsp;", "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

            return "<b style='color:red'>" + title + "</b><br/><b>" + content + "</b><br/>";
        }

        /// <summary>
        /// 根据url获取HtmlDocument
        /// </summary>
        public HtmlDocument GetHtmlDocByUrlStr(string url)
        {
            var client = new WebClient();
            HtmlDocument doc = new HtmlDocument();
            client.Encoding = Encoding.GetEncoding("UTF-8");
            doc.LoadHtml(client.DownloadString(url));
            return doc;
        }

        #endregion

        #region 日志、邮件

        //写入日志
        public void WriteLog(string strLog)
        {
            using (StreamWriter sw = new StreamWriter(DllPath + "NovelCrawler.log", true))
            {
                sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   " + strLog);
            }
        }

        //读取日志
        public string ReadLog()
        {
            string content = String.Empty;
            if (File.Exists(DllPath + "NovelCrawler.log"))
            {
                using (StreamReader sw = new StreamReader(DllPath + "NovelCrawler.log"))
                {
                    content = sw.ReadToEnd();
                }
            }

            return content;
        }

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="subject">邮件主题</param>
        /// <param name="body">邮件正文</param>
        /// <param name="fromUsername">发件人名</param>
        /// <param name="fromMail">发件人邮箱地址</param>
        /// <param name="fromMailPassword">发件人邮箱密码</param>
        /// <param name="toMail">收件人邮箱地址</param>
        /// <param name="toMailCC">抄送收件人邮箱地址集合</param>
        /// <returns></returns>
        public bool SendMailUse(string subject, string body, string fromUsername, string fromMail, string fromMailPassword, string toMail, List<string> toMailCC = null)
        {
            SmtpClient client = new SmtpClient();
            client.DeliveryMethod = SmtpDeliveryMethod.Network;//指定电子邮件发送方式    
            client.EnableSsl = true;
            client.Host = "smtp.qq.com";//邮件服务器
            client.UseDefaultCredentials = false;
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
                WriteLog("发送失败：" + ex.Message + DateTime.Now.ToLongDateString());
                return false;
            }

            return true;
        }
        #endregion
    }
}
