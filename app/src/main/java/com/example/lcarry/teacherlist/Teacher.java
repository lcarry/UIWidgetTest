package com.example.lcarry.teacherlist;

import com.example.lcarry.teacherlist.TeacherBean;
import com.example.lcarry.uiwidgettest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lcarry on 2017/3/31.
 */

public class Teacher {
    //构造


    public Teacher(TeacherBean teacherBean) {
        this.name = teacherBean.getName();
        this.imgId = Integer.valueOf(teacherBean.getImgId());
        this.desc = teacherBean.getDesc();
        this.status = teacherBean.getStatus();
        this.time = teacherBean.getTime();
    }

    private String name;
    private int imgId;
    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String status;
    private String time;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


//    public static List<String> getAllTeachers(){
//        List<String> teachers = new ArrayList<String>();
//        teachers.add("test1");
//        teachers.add("test2");
//        teachers.add("test3");
//        teachers.add("test4");
//            return teachers;
//}

    public static List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<Teacher>();
        for (int i = 0; i < 100; i++) {
            TeacherBean teacherBean = new TeacherBean();
            teacherBean.setName(getRandomLengthName("张海霞"));
            teacherBean.setImgId(String.valueOf(R.drawable.zhx));
            teacherBean.setDesc("张海霞，北京大学教授国际大学生iCAN创新创业大赛发起人、主席北京大学信息科学技术学院教授全球华人微纳米分子系统学会秘书长IEEE NTC 北京分会主席。");
            teacherBean.setStatus("已转司局办理");
            teacherBean.setTime("2017-04-01");
            teachers.add(new Teacher(teacherBean));

            TeacherBean teacherBean2 = new TeacherBean();
            teacherBean2.setName(getRandomLengthName("陈江"));
            teacherBean2.setImgId(String.valueOf(R.drawable.cj));
            teacherBean2.setDesc("陈江，北京大学信息科学技术学院副教授，高等学校电路和信号系统教学与教材研究会常务理事，中国通信理论与信号处理委员会委员。");
            teacherBean2.setStatus("已转司局办理");
            teacherBean2.setTime("2017-04-01");
            teachers.add(new Teacher(teacherBean2));

            TeacherBean teacherBean3 = new TeacherBean();
            teacherBean3.setName(getRandomLengthName("叶蔚"));
            teacherBean3.setImgId(String.valueOf(R.drawable.yw));
            teacherBean3.setDesc("叶蔚，北京大学软件工程国家工程研究中心副研究员，创办了技术学习服务平台天码营(http://tianmaying.com)与软件开发协同工具Onboard(http://onboard.cn)。");
            teacherBean3.setStatus("已转司局办理");
            teacherBean3.setTime("2017-04-01");
            teachers.add(new Teacher(teacherBean3));
        }


//        teachers.add(new Teacher("张海霞", R.drawable.zhx, "张海霞，北京大学教授国际大学生iCAN创新创业大赛发起人、主席北京大学信息科学技术学院教授全球华人微纳米分子系统学会秘书长IEEE NTC 北京分会主席。",));
//        teachers.add(new Teacher("陈江", R.drawable.cj, "陈江，北京大学信息科学技术学院副教授，高等学校电路和信号系统教学与教材研究会常务理事，中国通信理论与信号处理委员会委员。"));
//        teachers.add(new Teacher("叶蔚", R.drawable.yw, "叶蔚，北京大学软件工程国家工程研究中心副研究员，创办了技术学习服务平台天码营(http://tianmaying.com)与软件开发协同工具Onboard(http://onboard.cn)。"));
//

        return teachers;
    }
    private static String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
