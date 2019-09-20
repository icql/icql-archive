package work.icql.account.pojo;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/15 9:54
 * @Title EkpConst
 * @Description EkpConst
 */
public enum EkpConst {
    SSOURL("http://sso.xmjl.com/sso/login"),
    SSOLOGINURL("http://sso.xmjl.com/sso/login"),
    ADDUSERURL("http://ekp.xmjl.com/jl/hr/jl_hr_temp_sys_person/jlHrTempSysPerson.do?method=adda&fdNos="),
    OSSINURL("http://ekp.xmjl.com/sys/quartz/sys_quartz_job/sysQuartzJob.do?method=run&fdId=13daad49ae123e86549a4424fc4a4baf&s_css=default"),
    OSSOUTURL("http://ekp.xmjl.com/sys/quartz/sys_quartz_job/sysQuartzJob.do?method=run&fdId=13daad49ae1a9a1cf5940d0409e89d88&s_css=default");

    private String value;

    EkpConst(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
