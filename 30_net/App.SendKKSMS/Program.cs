using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using XMJL.HR.Business;
using XMJL.SMSDATA.Business;

namespace App.SendKKSMS
{
    class Program
    {
        static void Main(string[] args)
        {
            //List<HRInfo> ls = GetHRInfo();
            ////List<HRInfo> ls = new List<HRInfo>();
            ////ls.Add(new HRInfo() { UserCode = "6924", UserName = "陈庆林", PhoneNumber = "18650108307" });

            //ls.ForEach(f =>
            //{
            //    string content = "工号：{0}，姓名：{1}。根据公司规定，后续将每月对车间员工进行考核，需要班长代评。现已统一更改所有车间班长EKP账户密码】。您的EKP账户为：a{0}，密码为：xmjlA{0}。如有疑问，请及时联系企信部—陈庆林(a6924，电话5608933)";

            //    //KKHelper.SendKK("a"+f.UserCode, String.Format(content, f.UserCode, f.UserName),"admin");
            //    //SMSDATADapHelper.C_MessageActDap.Send(String.Format(content, f.UserCode, f.UserName), f.PhoneNumber, "amdin", "EKP");
            //});
        }

        public static List<HRInfo> GetHRInfo()
        {
            #region sql
            string sql = @"select A0190 as UserCode,A0101 as UserName,CellPhone as PhoneNumber from jlekp_A01
where A0190 in(
'7237',
'7166',
'7162',
'7190',
'7160',
'7159',
'7242',
'5832',
'7241',
'7084',
'7239',
'7253',
'7635',
'7629',
'7622',
'7618',
'7396',
'7435',
'7402',
'7398',
'7401',
'1145',
'7514',
'7560',
'7772',
'7545',
'7504',
'3540',
'2065',
'0863',
'0702',
'0721',
'1226',
'4871',
'3019',
'1700',
'2056',
'2076',
'0675',
'0339',
'0412',
'1383',
'1296',
'1372',
'1197',
'1507',
'0502',
'0722',
'4473',
'1170',
'0777',
'0983',
'1258',
'0346',
'0338',
'0335',
'0362',
'0365',
'1124',
'3924',
'1687',
'0955',
'3305',
'3817',
'1329',
'0392',
'0514',
'4145',
'0442',
'0455',
'0794',
'0646',
'0421',
'2846',
'0487',
'1476',
'0267',
'1120',
'3438',
'2187',
'0274',
'2713',
'4449',
'0316'
)";
            #endregion

            return HRDapHelper.C_V_JLEKP_AO1_DEPTGWDap.Query<HRInfo>(sql).ToList();
        }
    }

    public class HRInfo
    {
        public string UserCode { get; set; }
        public string UserName { get; set; }
        public string PhoneNumber { get; set; }
    }
}
