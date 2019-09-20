using Microsoft.Win32;
using System;
using System.Diagnostics;
using System.Text;

namespace IEFixTool
{
    class Program
    {
        static void Main(string[] args)
        {
            WinSysHelper sys = new WinSysHelper();
            sys.RunApp("RunDll32.exe", "InetCpl.cpl,ClearMyTracksByProcess 255");//清除IE缓存、cookie以及历史记录
            sys.AddTrustedSites("*.xmjl.com");//添加可信站点
            sys.CloseRegionalSecurityLevel();//设置区域安全级别为最低
            sys.AddCompatibilitySite(new string[] { "xmjl.com", "172.16.10.114" });//添加兼容视图
        }
    }

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
