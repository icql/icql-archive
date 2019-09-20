namespace MES_ERP
{
    partial class Mainfrm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Mainfrm));
            this.dpSyncDate = new System.Windows.Forms.DateTimePicker();
            this.lbSyncDate = new System.Windows.Forms.Label();
            this.cbProdDep = new System.Windows.Forms.ComboBox();
            this.lbProdDep = new System.Windows.Forms.Label();
            this.lbLogText = new System.Windows.Forms.Label();
            this.btnSync = new System.Windows.Forms.Button();
            this.lbSettingsData = new System.Windows.Forms.Label();
            this.txtLog = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.txtCarno = new System.Windows.Forms.TextBox();
            this.btnSetting = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // dpSyncDate
            // 
            this.dpSyncDate.Location = new System.Drawing.Point(362, 24);
            this.dpSyncDate.Name = "dpSyncDate";
            this.dpSyncDate.Size = new System.Drawing.Size(105, 21);
            this.dpSyncDate.TabIndex = 0;
            this.dpSyncDate.Value = new System.DateTime(2018, 6, 1, 0, 0, 0, 0);
            // 
            // lbSyncDate
            // 
            this.lbSyncDate.AutoSize = true;
            this.lbSyncDate.Location = new System.Drawing.Point(277, 29);
            this.lbSyncDate.Name = "lbSyncDate";
            this.lbSyncDate.Size = new System.Drawing.Size(83, 12);
            this.lbSyncDate.TabIndex = 1;
            this.lbSyncDate.Text = "同步开始日期:";
            // 
            // cbProdDep
            // 
            this.cbProdDep.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbProdDep.FormattingEnabled = true;
            this.cbProdDep.Location = new System.Drawing.Point(121, 25);
            this.cbProdDep.Name = "cbProdDep";
            this.cbProdDep.Size = new System.Drawing.Size(101, 20);
            this.cbProdDep.TabIndex = 2;
            // 
            // lbProdDep
            // 
            this.lbProdDep.AutoSize = true;
            this.lbProdDep.Location = new System.Drawing.Point(56, 29);
            this.lbProdDep.Name = "lbProdDep";
            this.lbProdDep.Size = new System.Drawing.Size(59, 12);
            this.lbProdDep.TabIndex = 3;
            this.lbProdDep.Text = "生产部门:";
            // 
            // lbLogText
            // 
            this.lbLogText.AutoSize = true;
            this.lbLogText.Location = new System.Drawing.Point(56, 107);
            this.lbLogText.Name = "lbLogText";
            this.lbLogText.Size = new System.Drawing.Size(59, 12);
            this.lbLogText.TabIndex = 4;
            this.lbLogText.Text = "同步日志:";
            // 
            // btnSync
            // 
            this.btnSync.Location = new System.Drawing.Point(237, 273);
            this.btnSync.Name = "btnSync";
            this.btnSync.Size = new System.Drawing.Size(104, 23);
            this.btnSync.TabIndex = 6;
            this.btnSync.Text = "开始同步";
            this.btnSync.UseVisualStyleBackColor = true;
            this.btnSync.Click += new System.EventHandler(this.btnSync_Click);
            // 
            // lbSettingsData
            // 
            this.lbSettingsData.AutoSize = true;
            this.lbSettingsData.Location = new System.Drawing.Point(377, 71);
            this.lbSettingsData.Name = "lbSettingsData";
            this.lbSettingsData.Size = new System.Drawing.Size(77, 12);
            this.lbSettingsData.TabIndex = 7;
            this.lbSettingsData.Text = "Settings数据";
            this.lbSettingsData.Visible = false;
            // 
            // txtLog
            // 
            this.txtLog.Location = new System.Drawing.Point(121, 107);
            this.txtLog.Multiline = true;
            this.txtLog.Name = "txtLog";
            this.txtLog.ReadOnly = true;
            this.txtLog.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtLog.Size = new System.Drawing.Size(346, 156);
            this.txtLog.TabIndex = 8;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(56, 70);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(59, 12);
            this.label1.TabIndex = 9;
            this.label1.Text = "单台车号:";
            // 
            // txtCarno
            // 
            this.txtCarno.Location = new System.Drawing.Point(121, 66);
            this.txtCarno.Name = "txtCarno";
            this.txtCarno.Size = new System.Drawing.Size(100, 21);
            this.txtCarno.TabIndex = 10;
            // 
            // btnSetting
            // 
            this.btnSetting.Location = new System.Drawing.Point(279, 66);
            this.btnSetting.Name = "btnSetting";
            this.btnSetting.Size = new System.Drawing.Size(92, 23);
            this.btnSetting.TabIndex = 11;
            this.btnSetting.Text = "编辑配置文件";
            this.btnSetting.UseVisualStyleBackColor = true;
            this.btnSetting.Click += new System.EventHandler(this.btnSetting_Click);
            // 
            // Mainfrm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(538, 317);
            this.Controls.Add(this.btnSetting);
            this.Controls.Add(this.txtCarno);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtLog);
            this.Controls.Add(this.lbSettingsData);
            this.Controls.Add(this.btnSync);
            this.Controls.Add(this.lbLogText);
            this.Controls.Add(this.lbProdDep);
            this.Controls.Add(this.cbProdDep);
            this.Controls.Add(this.lbSyncDate);
            this.Controls.Add(this.dpSyncDate);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Mainfrm";
            this.Text = "MES&ERP 制程数据同步";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DateTimePicker dpSyncDate;
        private System.Windows.Forms.Label lbSyncDate;
        private System.Windows.Forms.ComboBox cbProdDep;
        private System.Windows.Forms.Label lbProdDep;
        private System.Windows.Forms.Label lbLogText;
        private System.Windows.Forms.Button btnSync;
        private System.Windows.Forms.Label lbSettingsData;
        private System.Windows.Forms.TextBox txtLog;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtCarno;
        private System.Windows.Forms.Button btnSetting;
    }
}