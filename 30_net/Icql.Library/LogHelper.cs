using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Icql.Library
{
    public class LogHelper
    {
        public string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }

        /// <summary>
        /// 写入日志
        /// </summary>
        public void WriteLog(string strLog)
        {
            if (!Directory.Exists(DllPath + @"Files"))
            {
                Directory.CreateDirectory(DllPath + @"Files");
            }

            try
            {
                using (StreamWriter sw = new StreamWriter(DllPath + @"Files\temp", true))
                {
                    sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   " + strLog);
                }
            }
            catch (Exception e)
            {
                //SendMailUse();
            }
        }

        /// <summary>
        /// 读取日志
        /// </summary>
        public string ReadLog()
        {
            string content = String.Empty;

            if (!Directory.Exists(DllPath + @"Files"))
            {
                Directory.CreateDirectory(DllPath + @"Files");
            }

            if (!File.Exists(DllPath + @"Files\temp"))
            {
                using (StreamWriter sw = new StreamWriter(DllPath + @"Files\temp", true))
                {
                    sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   创建log文件");
                }
            }

            try
            {
                using (StreamReader sw = new StreamReader(DllPath + @"Files\temp"))
                {
                    content = sw.ReadToEnd();
                }
            }
            catch (Exception e)
            {
                //SendMailUse();
                content = "false";
            }

            return content;
        }
    }
}
