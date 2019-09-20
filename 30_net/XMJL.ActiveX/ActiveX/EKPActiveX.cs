using System;
using System.Collections.Generic;
using System.Net.NetworkInformation;
using System.Runtime.InteropServices;
using System.Text;

namespace XMJL.ActiveX
{
    [Guid("5D0498A8-F6A2-4ABA-AC00-17536AC0DCD1")]
    public class EKPActiveX : ActiveX
    {
        //js使用示例：alert(new ActiveXObject("XMJL.ActiveX.EKPActiveX").GetMac())
        public string GetMac()
        {
            List<string> macs = new List<string>();
            NetworkInterface[] interfaces = NetworkInterface.GetAllNetworkInterfaces();
            foreach (NetworkInterface ni in interfaces)
            {
                string temp = ni.GetPhysicalAddress().ToString();
                if (String.IsNullOrEmpty(temp.Trim())) { continue; }
                if (temp.Length >= 11)
                {
                    temp = temp.Insert(2, ":");
                    temp = temp.Insert(5, ":");
                    temp = temp.Insert(8, ":");
                    temp = temp.Insert(11, ":");
                    temp = temp.Insert(14, ":");
                }
                if (!macs.Contains(temp))
                {
                    macs.Add(temp);
                }
            }
            string strMacs = String.Empty;
            foreach (var item in macs)
            {
                strMacs += item + ",";
            }
            strMacs = strMacs.Substring(0, strMacs.Length - 2);
            return strMacs;
        }
    }
}
