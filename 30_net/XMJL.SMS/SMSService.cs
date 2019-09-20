using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.ServiceProcess;
using System.Text;
using System.Timers;

namespace XMJL.SMS
{
    partial class SMSService : ServiceBase
    {
        public string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }
        public string AppPath { get; set; }
        public SMSService()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            try
            {
                AppPath = File.ReadAllText(DllPath + "setting.conf").Trim();
                if (String.IsNullOrEmpty(AppPath) || !AppPath.Contains(".exe")) { throw new Exception(); }
            }
            catch
            {
                Log("XMJLSMS服务启动失败，" + DllPath + " 路径下必须存在setting.conf配置文件，且setting.conf文件内容仅允许写入 [exe文件完整路径]");
                throw new Exception();
            }

            RunApp(AppPath);
            System.Threading.Thread.Sleep(20 * 1000);
            KillApp(AppPath);
            Log("XMJLSMS服务初始化");

            #region 定时服务
            Timer t = new Timer { Interval = 1000 * 60, AutoReset = true, Enabled = true };
            t.Elapsed += new System.Timers.ElapsedEventHandler(Do);
            #endregion
        }

        protected override void OnStop()
        {
            KillApp(AppPath);
            Log("XMJLSMS服务停止");
        }


        #region 方法
        public void Do(object sender, System.Timers.ElapsedEventArgs e)
        {
            if (!IsRun(AppPath))
            {
                RunApp(AppPath);
                Log("XMJLSMS服务启动");
            }
        }

        private bool IsRun(string appPath)
        {
            string appName = Path.GetFileNameWithoutExtension(appPath);
            Process[] processes = Process.GetProcessesByName(appName);
            if (processes.Length > 0)
            {
                return true;
            }
            return false;
        }
        private void RunApp(string appPath)
        {
            using (Process process = new Process { StartInfo = new ProcessStartInfo { FileName = appPath, WindowStyle = ProcessWindowStyle.Hidden, Arguments = "starttype=service" } })
            {
                process.Start();
            }
        }

        private void KillApp(string appPath)
        {
            string appName = Path.GetFileNameWithoutExtension(appPath);
            Process[] processes = Process.GetProcessesByName(appName);
            foreach (var item in processes)
            {
                if (!item.CloseMainWindow())
                {
                    item.Kill();
                }
            }
        }

        private void Log(string content)
        {
            string dirPath = DllPath + "log/" + DateTime.Now.Year + "/" + (DateTime.Now.Month) + "/";
            if (!Directory.Exists(dirPath))
            {
                Directory.CreateDirectory(dirPath);
            }
            using (StreamWriter sw = new StreamWriter(dirPath + (DateTime.Now.Day) + ".log", true))
            {
                sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   " + content);
            }
        }
        #endregion
    }
}
