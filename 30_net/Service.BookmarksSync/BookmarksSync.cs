using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Web.Script.Serialization;

namespace BookmarksSync
{
    partial class BookmarksSync : ServiceBase
    {
        public string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }
        public BookmarksSync()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            // TODO: 在此处添加代码以启动服务。
            WriteLog("服务开始");
            #region 定时服务
            Timer t = new Timer { Interval = 1000 * 60 * 10, AutoReset = true, Enabled = true };
            t.Elapsed += new System.Timers.ElapsedEventHandler(Do);
            #endregion
        }

        protected override void OnStop()
        {
            // TODO: 在此处添加代码以执行停止服务所需的关闭操作。
            WriteLog("服务停止");
        }

        public void Do(object sender, ElapsedEventArgs e)
        {
            JavaScriptSerializer js = new JavaScriptSerializer();
            if (!File.Exists(DllPath + "setting.json"))
            {
                return;
            }

            Setting setting = (Setting)js.Deserialize(File.ReadAllText(DllPath + "setting.json"), typeof(Setting));

            if (String.IsNullOrEmpty(setting.GitRepoPath) || String.IsNullOrEmpty(setting.Chrome.LocalPath) || String.IsNullOrEmpty(setting.Chrome.GitPath))
                return;

            //pull
            GitCommand(setting.GitRepoPath, "pull");

            bool chrome = ChromeSync(setting.Chrome.LocalPath + "\\Bookmarks", setting.Chrome.GitPath + "\\Bookmarks");
            bool IE = IESync(setting.IE.LocalPath, setting.Chrome.GitPath);
            if (chrome || IE)
                GitCommand(setting.GitRepoPath, "push");
        }

        #region 


        /// <summary>
        /// 执行pull或者push
        /// </summary>
        public void GitCommand(string GitRepoPath, string flag)
        {
            string batText = String.Empty;
            if (flag == "pull")
            {
                batText = @"cd /d " + GitRepoPath + @"
                                git pull";
            }
            else
            {
                batText = @"cd /d " + GitRepoPath + @"
                                          git add -A && git commit -m ""sync bookmarks"" && git push";
            }

            using (StreamWriter sw = new StreamWriter(DllPath + "temp.bat", false))
            {
                sw.Write(batText);
            }

            try
            {
                RunApp(DllPath + "temp.bat", ProcessWindowStyle.Hidden);
                WriteLog(flag);
            }
            catch (Exception error)
            {
                WriteLog(error.Message);
            }
            finally
            {
                File.Delete(DllPath + "temp.bat");
            }
        }

        /// <summary>
        /// Chrome书签同步：上传Bookmarks文件，写Chrome书签为html
        /// </summary>
        public bool ChromeSync(string localPath, string gitPath)
        {
            //chrome数据文件夹下没有Bookmarks
            if (!File.Exists(localPath))
                return false;

            //gitPath下没有Bookmarks
            if (!File.Exists(gitPath))
            {
                File.Copy(localPath, gitPath, true);
                WriteLog("复制文件：" + localPath + " 到 " + gitPath);
                WriteChromeHtml(gitPath);
                return true;
            }

            //git、local都存在，比较git local对象是否相同
            JavaScriptSerializer js = new JavaScriptSerializer();
            Boomarks localBookmarks = (Boomarks)js.Deserialize(File.ReadAllText(localPath), typeof(Boomarks));
            Boomarks gitBookmarks = (Boomarks)js.Deserialize(File.ReadAllText(gitPath), typeof(Boomarks));
            if (localBookmarks.Equals(gitBookmarks))
                return false;

            DateTime local, git;
            local = File.GetLastWriteTime(localPath);
            git = File.GetLastWriteTime(gitPath);

            if (local == git) { return false; }
            if (local > git)
            {
                File.Copy(localPath, gitPath, true);
                WriteLog("复制文件：" + localPath + " 到 " + gitPath);
            }
            else
            {
                File.Copy(gitPath, localPath, true);
                WriteLog("复制文件：" + gitPath + " 到 " + localPath);
                return false;
            }
            
            WriteChromeHtml(gitPath);
            return true;
        }
        public void WriteChromeHtml(string gitPath)
        {
            JavaScriptSerializer js = new JavaScriptSerializer();
            Boomarks bookmarks = (Boomarks)js.Deserialize(File.ReadAllText(gitPath), typeof(Boomarks));

            StringBuilder content = new StringBuilder();
            content.Append(@"
            <!DOCTYPE html>
                <html lang='en'>
                <head>
                    <meta charset='UTF-8'>
                    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                    <meta http-equiv='X-UA-Compatible' content='ie=edge'>
                    <title>ChromeBookmarks</title>
                </head>
                <style>
                    table{
                        margin:0 auto;
                        text-align:left
                    }
                    h1{
                        text-align: center
                    }
                </style>
                <body>
                    <h1>Bookmarks</h1>
                    <table>");
            SetFolder(ref content, bookmarks.roots.bookmark_bar);

            content.Append(@"
                </ table >
            </ body >
            </ html > ");
            File.WriteAllText(gitPath.Replace("Bookmarks", "")+@"ChromeBookmarks.html", content.ToString());
            WriteLog("Chrome书签生成html");
        }
        public void SetFolder(ref StringBuilder content, Data bookmarks)
        {
            if (bookmarks.type == "folder")
            {
                content.AppendFormat(@"<tr><td><h3>{0}</h3></td></tr>", bookmarks.name);
                foreach (var item in bookmarks.children)
                {
                    SetFolder(ref content, item);
                }
            }
            else
            {
                content.AppendFormat(@"<tr><td><a href='{0}'  target='_blank'>{1}</a></td></tr>", bookmarks.url, bookmarks.name);
            }
        }


        /// <summary>
        /// IE书签同步：写IE书签为html
        /// </summary>
        public bool IESync(string ieLocalPath, string ieGitPath)
        {
            if (String.IsNullOrEmpty(ieLocalPath) || String.IsNullOrEmpty(ieGitPath))
                return false;
            StringBuilder content = new StringBuilder();
            content.Append(@"
            <!DOCTYPE html>
                <html lang='en'>
                <head>
                    <meta charset='UTF-8'>
                    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                    <meta http-equiv='X-UA-Compatible' content='ie=edge'>
                    <title>IEBookmarks</title>
                </head>
                <style>
                    table{
                        margin:0 auto;
                        text-align:left
                    }
                    h1{
                        text-align: center
                    }
                </style>
                <body>
                    <h1>Bookmarks</h1>
                    <table>");
            GetDirectory(ieLocalPath, ref content);
            content.Append(@"
                </ table >
            </ body >
            </ html > ");

            if (File.Exists(ieGitPath + "//IEBookmarks.html"))
            {
                if (content.ToString() == File.ReadAllText(ieGitPath + "//IEBookmarks.html"))
                    return false;
            }

            File.WriteAllText(ieGitPath + "//IEBookmarks.html", content.ToString());
            WriteLog("IE书签生成html");
            return true;
        }
        public void GetDirectory(string path, ref StringBuilder content)
        {
            GetFile(path, ref content);
            string[] dirArray = Directory.GetDirectories(path);
            if (dirArray.Length == 0)
                return;
            List<string> dirList = dirArray.ToList();
            dirList.Sort();
            foreach (var item in dirList)
            {
                content.AppendFormat(@"<tr><td><h3>{0}</h3></td></tr>", System.IO.Path.GetFileName(item));
                GetDirectory(item, ref content);
            }
        }
        public void GetFile(string path, ref StringBuilder content)
        {
            string[] fileArray = Directory.GetFiles(path);

            List<string> fileList = fileArray.ToList();
            fileList.Sort();
            fileList.Remove(@"C:\Users\a6924.GOLDDRAGON.COM\Favorites\Links\desktop.ini");
            foreach (var item in fileList)
            {
                string str = File.ReadAllText(item);
                try
                {
                    content.AppendFormat(@"<tr><td><a href='{0}'  target='_blank'>{1}</a></td></tr>", str.Substring(19, str.IndexOf("[{") - 21), System.IO.Path.GetFileName(item).Replace(".url", ""));
                }
                catch
                {
                    WriteLog("IEinChrome书签项写入失败：" + item);
                }

            }
        }
        #endregion

        #region 日志、运行APP
        //写入日志
        public void WriteLog(string strLog)
        {
            using (StreamWriter sw = new StreamWriter(DllPath + "BookmarksSync.log", true))
            {
                sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "   ---   " + strLog);
            }
        }

        //读取日志
        public string ReadLog()
        {
            string content = String.Empty;
            if (File.Exists(DllPath + "BookmarksSync.log"))
            {
                using (StreamReader sw = new StreamReader(DllPath + "BookmarksSync.log"))
                {
                    content = sw.ReadToEnd();
                }
            }

            return content;
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

    #region 服务配置json文件解析类
    class Setting
    {
        public string GitRepoPath { get; set; }
        public Path Chrome { get; set; }
        public Path IE { get; set; }
    }
    class Path
    {
        public string LocalPath { get; set; }
        public string GitPath { get; set; }
    }
    #endregion

    #region chrome书签json解析类
    class Boomarks
    {
        public override bool Equals(object o)
        {
            Boomarks b = (Boomarks)o;
            if (this.checksum == b.checksum && this.version == b.version)
            {
                if (this.roots.Equals(b.roots))
                    return true;
            }
            return false;
        }
        public string checksum { get; set; }
        public Roots roots { get; set; }
        public string version { get; set; }
    }

    class Roots
    {
        public override bool Equals(object o)
        {
            Roots r = (Roots)o;
            if (this.bookmark_bar.Equals(r.bookmark_bar) && this.other.Equals(r.other) && this.synced.Equals(r.synced))
                return true;
            return false;
        }
        public Data bookmark_bar { get; set; }
        public Data other { get; set; }
        public Data synced { get; set; }
    }

    class Data
    {
        public override bool Equals(object o)
        {
            Data d = (Data)o;
            if ((this.id == d.id) && (this.name == d.name) && (this.type == d.type) && (this.url == d.url))
            {
                if (this.children == null && d.children == null)
                    return true;
                if (this.children != null && d.children == null)
                    return false;
                if (this.children == null && d.children != null)
                    return false;

                if (this.children.Length != d.children.Length)
                    return false;

                for (int i = 0; i < this.children.Length; i++)
                {
                    if (!this.children[i].Equals(d.children[i]))
                        return false;
                }

                return true;
            }

            return false;
        }
        public Data[] children { get; set; }
        public string id { get; set; }
        public string name { get; set; }
        public string type { get; set; }
        public string url { get; set; }
    }
    #endregion
}
