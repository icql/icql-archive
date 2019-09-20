using System;
using System.Collections.Generic;
using System.Configuration;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace App.Sync_icql.work
{
    class Program
    {
        public static string DllPath
        {
            get
            {
                return AppDomain.CurrentDomain.SetupInformation.ApplicationBase;
            }
        }

        static void Main(string[] args)
        {
            try
            {
                string sourceDir = ConfigurationManager.AppSettings["sourceDir"];
                string destDir = ConfigurationManager.AppSettings["destDir"];
                string gitRepoPath = ConfigurationManager.AppSettings["gitRepoPath"];
                if (Directory.Exists(destDir))
                {
                    DelectDir(destDir);
                    Directory.Delete(destDir);
                }
                Directory.Move(sourceDir, destDir);
                GitCommand(gitRepoPath, "push");
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Console.ReadKey();
            }
        }


        public static void GitCommand(string GitRepoPath, string flag)
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
                                          git add -A && git commit -m ""sync icql.work"" && git push";
            }

            using (StreamWriter sw = new StreamWriter(DllPath + "temp.bat", false))
            {
                sw.Write(batText);
            }

            RunApp(DllPath + "temp.bat", ProcessWindowStyle.Hidden);

            File.Delete(DllPath + "temp.bat");
        }


        public static void RunApp(string appPath, ProcessWindowStyle style)
        {
            using (Process process = new Process { StartInfo = new ProcessStartInfo { FileName = appPath, WindowStyle = style } })
            {
                process.Start();
                process.WaitForExit();
            }
        }

        public static void DelectDir(string srcPath)
        {
            try
            {
                DirectoryInfo dir = new DirectoryInfo(srcPath);
                FileSystemInfo[] fileinfo = dir.GetFileSystemInfos();  //返回目录中所有文件和子目录
                foreach (FileSystemInfo i in fileinfo)
                {
                    if (i is DirectoryInfo)            //判断是否文件夹
                    {
                        DirectoryInfo subdir = new DirectoryInfo(i.FullName);
                        subdir.Delete(true);          //删除子目录和文件
                    }
                    else
                    {
                        File.Delete(i.FullName);      //删除指定文件
                    }
                }
            }
            catch (Exception e)
            {
                throw;
            }
        }
    }
}
