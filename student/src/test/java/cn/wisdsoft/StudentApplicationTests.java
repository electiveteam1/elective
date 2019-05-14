package cn.wisdsoft;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class StudentApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void test(){
//        Student student = new Student();
//        student.setStudentId("132465").setStudentPassword("12255").setStudentName("张三");
//        JSONObject jsonObject = new JSONObject();
//        String x = "[{\"week\":\"星期一\",\"times\":\"第一节课\",\"lou\":\"1号楼\",\"room\":\"101\"},{\"week\":\"星期一\",\"times\":\"第一节课\",\"lou\":\"1号楼\",\"room\":\"101\"},{\"week\":\"星期一\",\"times\":\"第一节课\",\"lou\":\"1号楼\",\"room\":\"101\"}]";

        Integer x = 1;
        Integer y = 2;
        try {
            if(x!=2){
                throw new Exception();
            }
            System.out.println(x.equals(y));

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    public static void test1() throws Exception {
        throw new Exception();
    }

}
