using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using XMJL.MES.Business;
using XMJL.MES.Data;
using XMJL.ClassLibrary;
using System.Net.Mail;

namespace MES.ERP.Sync
{
    class Program
    {
        public static string CONFIG_PATH = AppDomain.CurrentDomain.BaseDirectory + "/Config";
        public static string SETTING_PATH = AppDomain.CurrentDomain.BaseDirectory + "/Config/Settings.json";
        public static string LOG_PATH = AppDomain.CurrentDomain.BaseDirectory + "/Config/Log";

        static void Main(string[] args)
        {
            MyInitialize();
            //Console.WriteLine("========开始同步========");
            Do();
            //Console.WriteLine("========同步完成========");
            //Console.WriteLine("========按任意键退出========");
            //Console.ReadKey();
        }

        /// <summary>
        /// 执行同步
        /// </summary>
        private static void Do()
        {
            //获取MES部门信息
            string strDeptInfo = File.ReadAllText(SETTING_PATH, Encoding.UTF8);
            List<MES_ProdDeptInfo> lsDeptInfo = JsonConvert.DeserializeObject<List<MES_ProdDeptInfo>>(strDeptInfo);
            WriteLog("开始同步");

            //获取需要同步的数据
            List<MES_DTO_Sync4ERP> lsData = MESDapHelper.C_MES_T_CAR_WIPDap.GetSyncData(lsDeptInfo, "20180627".ToDecimal());
            //lsData = lsData.Where(w => w.Car_No == "单车号测试").ToList();
            WriteLog("准备同步的数据个数：" + lsData.Count);
            
            //同步数据
            string result = MESDapHelper.C_MES_T_CAR_WIPDap.SyncData(lsData);
            WriteLog("同步结果：" + result + "\r\n");

            if (!result.Contains("数据同步完成"))
            {
                string subject = " MES2ERP_制程信息同步失败 ";
                string body = "异常信息：" + result + "，请到 \\172.16.7.28\\E:\\AutoRun\\MES.ERP.Sync\\【定时任务计划】查看详情";

                //List<MailAddress> lsAddress = new List<MailAddress>
                //{
                //    new MailAddress("chenqinglin@xmjl.com","icql",Encoding.UTF8)
                //    ,new MailAddress("liweiming@xmjl.com","liweiming",Encoding.UTF8)
                //};
                //MailHelper.SendMail(subject,body,lsAddress);//发送不成功

                List<string> lsUserCode = new List<string> { "a6924", "a3750" };
                KKHelper.SendKK(lsUserCode, body, subject);
            }
        }

        /// <summary>
        /// 初始化配置文件
        /// </summary>
        private static void MyInitialize()
        {
            if (!Directory.Exists(CONFIG_PATH)) { Directory.CreateDirectory(CONFIG_PATH); }
            if (!Directory.Exists(LOG_PATH)) { Directory.CreateDirectory(LOG_PATH); }
            if (!File.Exists(SETTING_PATH))
            {
                #region 创建配置文件
                List<MES_ProdDeptInfo> ls = new List<MES_ProdDeptInfo>();
                MES_ProdDeptInfo settings = new MES_ProdDeptInfo();
                settings.ProdDeptName = "示例：中客七部";
                settings.ProdDeptID = "6f02b3d242984c0b84180eecee292d3f";
                settings.Workshops = new Dictionary<string, string>();
                settings.Workshops.Add("396145b8f3c34bc1a7ddd80a6ef7988c", "焊装上线");
                settings.Workshops.Add("0120cb5f71aa4e629587ee65b9b7784e", "焊装下线");
                settings.Workshops.Add("607b30b878104f99b2f744ed42501ee2", "电泳上线");
                settings.Workshops.Add("5079901d52fa4cdf8cda62c041dae430", "电泳下线");
                settings.Workshops.Add("7e99fcfe178a4bf0934b19ecbb4d65e4", "涂装上线");
                settings.Workshops.Add("ef38d8c6814b414a8a55705fd5732e58", "涂装下线");
                settings.Workshops.Add("8c8cb065339046c1a611153a15c93835", "底盘上线");
                settings.Workshops.Add("31966165118a433a949e465db649d0ef", "底盘下线");
                settings.Workshops.Add("075c4647393f4df5b1ba2a567cb4f267", "总装上线");
                settings.Workshops.Add("3c0d4f7a8fe643269c2ee33ec9640ed0", "总装下线");
                settings.Workshops.Add("4cc9e58b8a6d4aa9a37365c2ceeafb2d", "调试上线");
                settings.Workshops.Add("f98f008ef49940dca52ce88565792638", "调试下线");
                ls.Add(settings);
                string jsonData = JsonConvert.SerializeObject(ls);
                File.WriteAllText(SETTING_PATH, jsonData, Encoding.UTF8);
                #endregion
            }
        }

        /// <summary>
        /// 日志文件
        /// </summary>
        /// <param name="msg"></param>
        private static void WriteLog(string msg)
        {
            File.AppendAllText(LOG_PATH + "/" + DateTime.Now.ToString("yyyyMMdd") + ".log", DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "---" + msg + "\r\n", Encoding.UTF8);
        }
    }
}
