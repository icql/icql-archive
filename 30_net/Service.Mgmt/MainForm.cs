using System;
using System.Collections;
using System.Configuration.Install;
using System.ServiceProcess;
using System.Windows.Forms;

namespace ServicesMgmt
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
            this.textBox4ServiceName.Text = GetCurrentUserName();
        }
        public string ServiceName { get; set; }
        public string ServiceFilePath { get; set; }

        private void btnInstall_Click(object sender, EventArgs e)
        {
            SetNameAndPath();
            if (this.IsServiceExisted(ServiceName)) this.UninstallService(ServiceName);
            this.InstallService(ServiceFilePath);
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            SetNameAndPath();
            if (this.IsServiceExisted(ServiceName)) this.ServiceStart(ServiceName);
        }

        private void btnStop_Click(object sender, EventArgs e)
        {
            SetNameAndPath();
            if (this.IsServiceExisted(ServiceName)) this.ServiceStop(ServiceName);
        }

        private void btnUninstall_Click(object sender, EventArgs e)
        {
            SetNameAndPath();
            if (this.IsServiceExisted(ServiceName))
            {
                this.ServiceStop(ServiceName);
                this.UninstallService(ServiceFilePath);
            }
        }

        private void SetNameAndPath()
        {
            if (!String.IsNullOrEmpty(textBox4ServiceDllPath.Text.Trim()) && !String.IsNullOrEmpty(textBoxServiceName.Text))
            {
                ServiceName = textBoxServiceName.Text;
                ServiceFilePath = textBox4ServiceDllPath.Text.Trim();
            }
        }

        private void textBox4ServiceDllPath_MouseClick(object sender, MouseEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();//新建打开文件对话框
            ofd.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.Personal);//设置初始文件目录
            //ofd.Filter = "文本文件(*.txt)|*.txt|所有文件(*.*)|*.*";//设置打开文件类型
            if (ofd.ShowDialog(this) == DialogResult.OK)
            {
                textBox4ServiceDllPath.Text = ofd.FileName;//FileName就是要打开的文件路径
                //下边可以添加用户代码
            }
        }

        #region 服务帮助类

        //判断服务是否存在
        private bool IsServiceExisted(string ServiceName)
        {
            ServiceController[] services = ServiceController.GetServices();
            foreach (ServiceController sc in services)
            {
                if (sc.ServiceName.ToLower() == ServiceName.ToLower())
                {
                    return true;
                }
            }
            return false;
        }

        //安装服务
        private void InstallService(string ServiceFilePath)
        {
            using (AssemblyInstaller installer = new AssemblyInstaller())
            {
                installer.UseNewContext = true;
                installer.Path = ServiceFilePath;
                IDictionary savedState = new Hashtable();
                installer.Install(savedState);
                installer.Commit(savedState);
            }
        }

        //卸载服务
        private void UninstallService(string ServiceFilePath)
        {
            using (AssemblyInstaller installer = new AssemblyInstaller())
            {
                installer.UseNewContext = true;
                installer.Path = ServiceFilePath;
                installer.Uninstall(null);
            }
        }
        //启动服务
        private void ServiceStart(string ServiceName)
        {
            using (ServiceController control = new ServiceController(ServiceName))
            {
                if (control.Status == ServiceControllerStatus.Stopped)
                {
                    control.Start();
                }
            }
        }

        //停止服务
        private void ServiceStop(string ServiceName)
        {
            using (ServiceController control = new ServiceController(ServiceName))
            {
                if (control.Status == ServiceControllerStatus.Running)
                {
                    control.Stop();
                }
            }
        }
        #endregion

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

    }
}
