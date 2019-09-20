using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RenameFileWithDate
{
    class Program
    {
        static void Main(string[] args)
        {
            string path = Directory.GetCurrentDirectory().ToString();
            string[] strFiles = Directory.GetFiles(path);
            int num = 0;
            string message = String.Empty;
            foreach (var file in strFiles)
            {
                string fileName = Path.GetFileName(file);
                if (fileName.Length >= 8)
                {
                    if (Regex.IsMatch(fileName.Substring(0, 8), @"\d{8}"))
                        continue;
                }
                try
                {
                    File.Move(file, path + "/" + DateTime.Now.ToString("yyyyMMdd") + "-" + fileName);
                    num++;
                }
                catch
                {
                    message += fileName + "\n";
                }
            }
            if (String.IsNullOrEmpty(message))
            {
                MessageBox.Show(String.Format("操作成功，重命名了{0}个文件！", num));
                return;
            }
            MessageBox.Show(String.Format("重命名了{0}个文件！\n\n文件：\n{1}\n重命名后会和已有文件重复！\n\n请检查！", num, message));
        }
    }
}
