using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using XMJL.ClassLibrary;
using XMJL.NewEnergy.Business;

namespace App.EKPWorkFlowData4FCExpense
{
    class Program
    {
        static void Main(string[] args)
        {
            #region sql
            string sql = @"
select 
--*
--distinct 节点ID,节点名
--distinct 申请人部门
--count(0) as ""总数""
--,sum( ""时间差(秒)"" ) as ""总时间"" 
round(avg( ""时间差(秒)"" )/60/60,4) as ""平均时间""


from

(select
m.doc_subject as ""标题""
,m.fd_id as ""流程ID""
,e.fd_name as ""申请人部门""
,decode(m.doc_status,'11','驳回','20','待审','30','结束','31','已反馈') as ""流程状态""
,to_char(m.doc_create_time,'yyyy-mm-dd hh24:mi:ss') as ""申请时间""
--,to_char(m.doc_publish_time,'yyyy-mm-dd hh24:mi:ss') as ""结束时间""
,n.fd_fact_node_id as ""节点ID""
,n.fd_fact_node_name as ""节点名""
,decode(i.fd_action_name,'%sysWfOperations.fdOperType.draft.submit%','',i.fd_action_name) as ""处理意见""
,(select o.fd_name from ekp.sys_org_element o where o.fd_id = i.fd_handler_id) as ""处理人""
,to_char(i.fd_start_date,'yyyy-mm-dd hh24:mi:ss') as ""到达时间""
,to_char(i.fd_finish_date,'yyyy-mm-dd hh24:mi:ss') as ""完成时间""
,round((to_date(to_char(i.fd_finish_date,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss') - 
to_date(to_char(i.fd_start_date,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')) * 24 * 60 * 60,2) as ""时间差(秒)""
from ekp.sys_wf_history_workitem i, ekp.sys_wf_history_node n, ekp.jl_fc_expense_main m,ekp.sys_org_element e
where m.fd_id = n.fd_process_id  
and i.fd_history_id = n.fd_id 
and m.doc_create_time >= to_date('2018/01/01','yyyy/mm/dd')
and m.doc_status in ('11','20','30','31')  --驳回,待审,结束,已反馈
and e.fd_id = m.FD_CREATOR_DEPT

and   e.fd_id in(



select fd_id from ekp.sys_org_element e where   
 e.fd_org_type = '2'  and e.fd_is_available !=0    
and substr(e.fd_hierarchy_id,35,32) != '13dc465c3066de58eecfcd143d3adf31'   --技术中心
and substr(e.fd_hierarchy_id,35,32) != '13dc465c5371768e9413bf3430db6074'  -- 售后
and substr(e.fd_hierarchy_id,35,32) != '13dc465c50921dfcda35dac4d6a89ba7'  --海外销售
and substr(e.fd_hierarchy_id,35,32) != '13dc465c299e28f97d80b7144c7bf491'   --国内销售

)

order by  m.doc_create_time, i.fd_order
)
where 
";
            List<string> lsNode = new List<string>
            {
                "节点ID = 'N2' and 节点名 = '起草节点'",
                "节点ID = 'N4' and 节点名 = '财务审核制单'",
                "节点ID = 'N4' and 节点名 = '上一级审核'",
                "节点ID = 'N4' and 节点名 = '事务部审核'",
                "节点ID = 'N5' and 节点名 = '上二级审核'",
                "节点ID = 'N5' and 节点名 = '事务部车队队长审核'",
                "节点ID = 'N6' and 节点名 = '上三级审核'",
                "节点ID = 'N7' and 节点名 = '部门经理审批'",
                "节点ID = 'N7' and 节点名 = '事务部经理'",
                "节点ID = 'N8' and 节点名 = '分管领导审批'",
                 "节点ID = 'N9' and 节点名 = '财务审核'",
                "节点ID = 'N10' and 节点名 = '财务经理'",
                "节点ID = 'N19' and 节点名 = '部门经理审批'",
                "节点ID = 'N20' and 节点名 = '财务副经理'",
                "节点ID = 'N22' and 节点名 = '财务副经理'",
                 "节点ID = 'N22' and 节点名 = '常务副总审批'",
                "节点ID = 'N22' and 节点名 = '总经理审批'",
                "节点ID = 'N23' and 节点名 = '常务副总审批'",
                "节点ID = 'N23' and 节点名 = '总经理审批'"
            };

            #endregion

            List<decimal?> re = new List<decimal?>();
            lsNode.ForEach(f =>
            {
                re.Add(NewEnergyDapHelper.C_NE_AuthorityDap.Query<decimal?>(sql + f).FirstOrDefault());
            });
            

            Console.WriteLine("执行完毕");
            Console.ReadKey();
        }
    }
    
}
