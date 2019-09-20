using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Security.Principal;
using System.Text;
using System.Threading.Tasks;

namespace Icql.Library
{
    public class WinSysHelper
    {

        #region 操作系统

        /// <summary>
        /// 运行APP
        /// </summary>
        /// <param name="fileName">包含路径的APP名称</param>
        /// <param name="args">运行参数</param>
        public void RunApp(string fileName, string args)
        {
            Process process = new Process();
            process.StartInfo.FileName = fileName;
            process.StartInfo.Arguments = args;
            process.StartInfo.UseShellExecute = false;
            process.StartInfo.RedirectStandardInput = true;
            process.StartInfo.RedirectStandardOutput = true;
            process.StartInfo.RedirectStandardError = true;
            process.StartInfo.CreateNoWindow = true;
            process.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;

            process.Start();
            process.WaitForExit();//等待执行完成后，继续执行主线程
        }

        /// <summary>
        /// 操作系统是否以管理员运行
        /// </summary>
        public bool IsAdministrator()
        {
            return new WindowsPrincipal(WindowsIdentity.GetCurrent()).IsInRole(WindowsBuiltInRole.Administrator);
        }

        /// <summary>
        ///  获取当前登录用户名
        /// </summary>
        public string GetCurrentUserName()
        {
            string username = String.Empty;
            if (Environment.UserDomainName.ToLower() == Environment.UserName)
            {
                username = @".\" + Environment.UserName;
            }
            else
            {
                username = Environment.UserDomainName + @"\" + Environment.UserName;
            }
            return username;
        }
        #endregion

        #region 文件操作
        /// <summary>
        /// 复制文件夹
        /// </summary>
        public bool CopyDirectory(string SourcePath, string DestinationPath, bool isOverwriteExisting)
        {
            bool ret = false;
            try
            {
                SourcePath = SourcePath.EndsWith(@"\") ? SourcePath : SourcePath + @"\";
                DestinationPath = DestinationPath.EndsWith(@"\") ? DestinationPath : DestinationPath + @"\";

                if (Directory.Exists(SourcePath))
                {
                    if (Directory.Exists(DestinationPath) == false)
                        Directory.CreateDirectory(DestinationPath);

                    foreach (string fls in Directory.GetFiles(SourcePath))
                    {
                        FileInfo flinfo = new FileInfo(fls);
                        flinfo.CopyTo(DestinationPath + flinfo.Name, isOverwriteExisting);
                    }
                    foreach (string drs in Directory.GetDirectories(SourcePath))
                    {
                        DirectoryInfo drinfo = new DirectoryInfo(drs);
                        if (CopyDirectory(drs, DestinationPath + drinfo.Name, isOverwriteExisting) == false)
                            ret = false;
                    }
                }
                ret = true;
            }
            catch (Exception ex)
            {
                ret = false;
            }
            return ret;
        }

        ///<summary>
        /// 清空文件夹内容
        /// </summary>
        public void EmptyFolder(string dir)
        {
            foreach (string d in Directory.GetFileSystemEntries(dir))
            {
                if (File.Exists(d))
                {
                    FileInfo fi = new FileInfo(d);
                    if (fi.Attributes.ToString().IndexOf("ReadOnly") != -1)
                        fi.Attributes = FileAttributes.Normal;
                    File.Delete(d);//直接删除其中的文件  
                }
                else
                {
                    DirectoryInfo dirInfo = new DirectoryInfo(d);
                    if (dirInfo.GetFiles().Length != 0)
                    {
                        EmptyFolder(dirInfo.FullName);////递归删除子文件夹
                    }
                    Directory.Delete(d);
                }
            }
        }
        #endregion

        #region IE浏览器

        /// <summary>
        /// 添加可信站点
        /// </summary>
        /// <param name="domain"></param>
        public void AddTrustedSites(string domain)
        {
            string[] array = domain.Replace("http://", "").Replace("https://", "").Split('.');
            if (array.Length < 3) { return; }

            RegistryKey hkCurrentUser = Registry.CurrentUser;
            string address = @"SOFTWARE\MICROSOFT\WINDOWS\CURRENTVERSION\INTERNET SETTINGS\ZONEMAP\Domains";
            RegistryKey key = hkCurrentUser.OpenSubKey(address, true);
            RegistryKey sonKey = key.CreateSubKey(array[1] + "." + array[2]);
            sonKey.SetValue(array[0], 0x2, RegistryValueKind.DWord);
        }

        /// <summary>
        /// 设置区域安全级别为最低
        /// </summary>
        public void CloseRegionalSecurityLevel()
        {
            string key = @"HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Internet Settings\Zones\2";
            Registry.SetValue(key, "1201", 0);
        }

        /// <summary>
        /// 添加兼容性视图
        /// </summary>
        /// <param name="domains"></param>
        public void AddCompatibilitySite(String[] domains)
        {
            byte[] header = new byte[] { 0x41, 0x1F, 0x00, 0x00, 0x53, 0x08, 0xAD, 0xBA };
            byte[] checksum = new byte[] { 0xFF, 0xFF, 0xFF, 0xFF };
            byte[] delim_a = new byte[] { 0x01, 0x00, 0x00, 0x00 };
            byte[] delim_b = new byte[] { 0x0C, 0x00, 0x00, 0x00 };
            byte[] filler = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };

            int count = domains.Length;
            byte[] entries = new byte[0];
            foreach (String d in domains)
            {
                entries = Combine(entries, GetDomainEntry(d));
            }

            byte[] regbinary = new byte[] { };
            regbinary = header;
            regbinary = Combine(regbinary, BitConverter.GetBytes(count));
            regbinary = Combine(regbinary, checksum);
            regbinary = Combine(regbinary, delim_a);
            regbinary = Combine(regbinary, BitConverter.GetBytes(count));
            regbinary = Combine(regbinary, entries);

            RegistryKey hkCurrentUser = Registry.CurrentUser;
            string address = @"Software\Microsoft\Internet Explorer\BrowserEmulation\ClearableListData";
            RegistryKey key = hkCurrentUser.OpenSubKey(address, true);
            key.SetValue("UserFilter", regbinary, RegistryValueKind.Binary);
        }

        //获取单个网站的二进制数据
        private byte[] GetDomainEntry(String domain)
        {
            byte[] delim_a = new byte[] { 0x01, 0x00, 0x00, 0x00 };
            byte[] delim_b = new byte[] { 0x0C, 0x00, 0x00, 0x00 };
            byte[] filler = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 };

            byte[] tmpbinary = new byte[0];
            byte[] length = BitConverter.GetBytes((UInt16)domain.Length);
            byte[] data = Encoding.Unicode.GetBytes(domain);
            tmpbinary = Combine(tmpbinary, delim_b);
            tmpbinary = Combine(tmpbinary, filler);
            tmpbinary = Combine(tmpbinary, delim_a);
            tmpbinary = Combine(tmpbinary, length);
            tmpbinary = Combine(tmpbinary, data);
            return tmpbinary;
        }

        #endregion

        #region 公共方法

        /// <summary>
        /// 合并两个byte[]数组
        /// </summary>
        /// <param name="a"></param>
        /// <param name="b"></param>
        /// <returns></returns>
        private byte[] Combine(byte[] a, byte[] b)
        {
            byte[] c = new byte[a.Length + b.Length];
            Buffer.BlockCopy(a, 0, c, 0, a.Length);
            Buffer.BlockCopy(b, 0, c, a.Length, b.Length);
            return c;
        }

        #endregion
    }
}
