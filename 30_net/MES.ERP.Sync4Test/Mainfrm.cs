using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using XMJL.MES.Data;
using XMJL.MES.Business;
using System.Threading;

namespace MES_ERP
{
    public partial class Mainfrm : Form
    {
        public string CONFIG_PATH = Environment.CurrentDirectory.ToString() + "/Config";
        public string SETTING_PATH = Environment.CurrentDirectory.ToString() + "/Config/Settings.json";
        public string LOG_PATH = Environment.CurrentDirectory.ToString() + "/Config/Sync.log";
        public string CB_PRODDEPT_DEFAULT = "选择生产部门";

        public Mainfrm()
        {
            InitializeComponent();
            MyInitialize();
        }

        private void MyInitialize()
        {
            if (!Directory.Exists(CONFIG_PATH)) { Directory.CreateDirectory(CONFIG_PATH); }
            if (!File.Exists(LOG_PATH))
            {
                File.AppendAllText(LOG_PATH, DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "---" + "创建日志" + "\r\n\r\n", Encoding.UTF8);
            }
            if (!File.Exists(SETTING_PATH))
            {
                #region 创建配置文件
                List<MES_ProdDeptInfo> ls = new List<MES_ProdDeptInfo>();
                MES_ProdDeptInfo settings = new MES_ProdDeptInfo();
                settings.ProdDeptName = "示例：中客七部";
                settings.ProdDeptID = "6f02b3d242984c0b84180eecee292d3f";
                settings.Workshops = new Dictionary<string, string>();
                settings.Workshops.Add("396145b8f3c34bc1a7ddd80a6ef7988c", "焊装上线");
                settings.Workshops.Add("0120cb5f71aa4e629587ee65b9b7784e", "焊装下线");
                settings.Workshops.Add("607b30b878104f99b2f744ed42501ee2", "电泳上线");
                settings.Workshops.Add("5079901d52fa4cdf8cda62c041dae430", "电泳下线");
                settings.Workshops.Add("7e99fcfe178a4bf0934b19ecbb4d65e4", "涂装上线");
                settings.Workshops.Add("ef38d8c6814b414a8a55705fd5732e58", "涂装下线");
                settings.Workshops.Add("8c8cb065339046c1a611153a15c93835", "底盘上线");
                settings.Workshops.Add("31966165118a433a949e465db649d0ef", "底盘下线");
                settings.Workshops.Add("075c4647393f4df5b1ba2a567cb4f267", "总装上线");
                settings.Workshops.Add("3c0d4f7a8fe643269c2ee33ec9640ed0", "总装下线");
                settings.Workshops.Add("4cc9e58b8a6d4aa9a37365c2ceeafb2d", "调试上线");
                settings.Workshops.Add("f98f008ef49940dca52ce88565792638", "调试下线");
                ls.Add(settings);
                string jsonData = JsonConvert.SerializeObject(ls);
                File.WriteAllText(SETTING_PATH, jsonData, Encoding.UTF8);
                #endregion
            }

            #region 日志刷新 线程

            CheckForIllegalCrossThreadCalls = false;
            Thread thread = new Thread(new ThreadStart(LogRefresh));
            thread.IsBackground = true;
            thread.Start();
            #endregion

            #region 设置部门下拉列表
            cbProdDep.Items.Add(CB_PRODDEPT_DEFAULT);
            cbProdDep.SelectedItem = CB_PRODDEPT_DEFAULT;
            lbSettingsData.Text = File.ReadAllText(SETTING_PATH, Encoding.UTF8);
            List<MES_ProdDeptInfo> lsSettings = JsonConvert.DeserializeObject<List<MES_ProdDeptInfo>>(lbSettingsData.Text);
            lsSettings.ForEach(dept => cbProdDep.Items.Add(dept.ProdDeptName));
            #endregion
        }

        private void btnSync_Click(object sender, EventArgs e)
        {
            if (cbProdDep.SelectedItem.ToString() == CB_PRODDEPT_DEFAULT)
            {
                MessageBox.Show("请选择需要同步的生产部门！");
                return;
            }
            string carno = txtCarno.Text.Trim();
            if (String.IsNullOrEmpty(carno)) { MessageBox.Show("请输入需要同步的车号！"); return; }

            WriteLog("开始同步");
            //部门信息
            List<MES_ProdDeptInfo> lsDeptInfo = JsonConvert.DeserializeObject<List<MES_ProdDeptInfo>>(lbSettingsData.Text);
            MES_ProdDeptInfo dept = lsDeptInfo.Where(w => w.ProdDeptName == cbProdDep.SelectedItem.ToString()).FirstOrDefault();
            lsDeptInfo.Clear();
            lsDeptInfo.Add(dept);

            //获取需要同步的数据
            List<MES_DTO_Sync4ERP> lsData = MESDapHelper.C_MES_T_CAR_WIPDap.GetSyncData(lsDeptInfo, Convert.ToDecimal(dpSyncDate.Value.ToString("yyyyMMdd")));
            lsData = lsData.Where(w => w.Car_No == carno).ToList();
            WriteLog("准备同步的数据个数：" + lsData.Count);

            //同步数据
            string result = MESDapHelper.C_MES_T_CAR_WIPDap.SyncData(lsData);

            #region 写入日志
            WriteLog("同步结果：" + result + "\r\n");
            #endregion
        }

        private void btnSetting_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("notepad.exe", SETTING_PATH);
        }

        private void WriteLog(string msg)
        {
            File.AppendAllText(LOG_PATH, DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") + "---" + msg + "\r\n", Encoding.UTF8);
        }

        #region 定时服务
        private void LogRefresh()
        {
            System.Timers.Timer t = new System.Timers.Timer { Interval = 1000 * 1, AutoReset = true, Enabled = true };
            t.Elapsed += new System.Timers.ElapsedEventHandler(Do);
        }
        public void Do(object sender, System.Timers.ElapsedEventArgs e)
        {
            txtLog.Text = File.ReadAllText(LOG_PATH, Encoding.UTF8);
        }
        #endregion
    }
}
