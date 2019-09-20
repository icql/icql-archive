using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Timers;

namespace UpdateSVN
{
    partial class UpdateSVN : ServiceBase
    {
        public string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }
        public UpdateSVN()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            // TODO: 在此处添加代码以启动服务。
            WriteLog("服务开始");
            #region 定时服务
            Timer t = new Timer { Interval = 1000 * 60 * 30, AutoReset = true, Enabled = true };
            t.Elapsed += new System.Timers.ElapsedEventHandler(Do);
            #endregion
        }

        protected override void OnStop()
        {
            // TODO: 在此处添加代码以执行停止服务所需的关闭操作。
            WriteLog("服务停止");
        }

        /// <summary>
        /// 执行
        /// </summary>
        private void Do(object source, ElapsedEventArgs e)
        {
            string batText = @"
@echo off
title Update Projects
echo -------------------------------------
echo 开始更新 %date% %time%
echo -------------------------------------

for /d %%i in (e:\projects\*) do (svn update ""%%i""
echo.)

echo -------------------------------------
echo 更新完毕 %date% %time%
echo -------------------------------------";

            using (StreamWriter sw = new StreamWriter(DllPath + "UpdateSVN.bat", false))
            {
                sw.Write(batText);
            }

            try
            {
                RunApp(DllPath + "UpdateSVN.bat", ProcessWindowStyle.Hidden);
                WriteLog("Update SVN Projects");
            }
            catch (Exception error)
            {
                WriteLog(error.Message);
            }
        }

        #region 日志、运行App
        public void WriteLog(string strLog)
        {
            using (StreamWriter sw = new StreamWriter(DllPath + "UpdateSVN.log", true))
            {
                sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   " + strLog);
            }
        }

        public void RunApp(string appPath, ProcessWindowStyle style)
        {
            using (Process process = new Process { StartInfo = new ProcessStartInfo { FileName = appPath, WindowStyle = style } })
            {
                process.Start();
                process.WaitForExit();
            }
        }
        #endregion

    }
}
