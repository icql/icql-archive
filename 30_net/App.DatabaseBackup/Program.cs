using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace App.DatabaseBackup
{
    class Program
    {
        private static readonly string ConStr = ConfigurationManager.ConnectionStrings["ConnectionString"].ConnectionString;
        private static readonly string BackupPath = ConfigurationManager.AppSettings.Get("BackupPath");

        static void Main(string[] args)
        {
            string sql = String.Format("BACKUP DATABASE ContractReview TO DISK = N'{0}' ", BackupPath + DateTime.Now.ToString("yyyyMMddHHmmss"));
            try
            {
                ExecuteNonQuery(String.Format(sql, DateTime.Now.ToString("yyyyMMdd")));
            }
            catch (Exception ex)
            {
                SendKK("a6924", "ContractReview数据库备份失败，请查看", "ContractReview数据库备份");
            }

            List<string> lstFilepath = Directory.GetFiles(BackupPath).ToList();
            if (lstFilepath.Count > 7)
            {
                lstFilepath.ForEach(f =>
                {
                    string filename = f.Replace(BackupPath, "").Substring(0, 8);

                    List<string> lsDate = new List<string>();
                    for (int i = -7; i <= 0; i++)
                    {
                        lsDate.Add(DateTime.Now.AddMonths(i).ToString("yyyyMMdd"));
                    }

                    if (!lsDate.Contains(filename))
                    {
                        File.Delete(f);
                    }
                });
            }
        }

        public static int ExecuteNonQuery(string sql, params SqlParameter[] pms)
        {
            using (SqlConnection con = new SqlConnection(ConStr))
            {
                using (SqlCommand cmd = new SqlCommand(sql, con))
                {
                    if (pms != null)
                    {
                        cmd.Parameters.AddRange(pms);
                    }
                    con.Open();
                    return cmd.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        /// 发送KK消息
        /// </summary>
        /// <param name="sendee">接收人</param>
        /// <param name="body">KK内容</param>
        /// <param name="fromName">发起人</param>
        public static void SendKK(string sendee, string body, string fromName)
        {
            try
            {
                string KKMsgServer = "http://soa.xmjl.com:8280/services/KKMsgService/sendMsg?user={0}&msg={1}&fromname={2}";
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create(string.Format(KKMsgServer, sendee, body, fromName));
                request.GetResponse();
            }
            catch (SystemException se)
            {
            }
        }
    }
}
