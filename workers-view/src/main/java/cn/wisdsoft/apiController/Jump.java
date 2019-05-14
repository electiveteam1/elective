package cn.wisdsoft.apiController;
import cn.wisdsoft.feginService.administratorService.FeignDdms;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import cn.wisdsoft.pojo.ddmsPojo.Teacher;
import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.pojo.vo.TermVo;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/2 15:59
 * @Description:
 */
@Controller
public class Jump {

    @Autowired
    private FeignDdms feignDdms;
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "administrator/login";
    }
    @RequestMapping("/index")
    public String toIndex() {
        return "administrator/index";
    }
    @RequestMapping("/Welcome")
    public String Welcome() {
        return "administrator/welcome";
    }
    @RequestMapping("/termManager")
    public String termManager() {
        return "administrator/term_list";
    }

    /**
     * 添加学期规则
     * @param termId
     * @param model
     * @return
     */
    @RequestMapping("/termRuleAdd")
    public String termRuleAdd(String termId,Model model) {
        model.addAttribute("termId",termId);
        return "administrator/termRule-add";
    }
    @RequestMapping("/courseLibrary_list")
    public String course_list() {
        return "administrator/courseLibrary-list";
    }
    @RequestMapping("/courseLibrary")
    public String courseLibrary(){
        return "administrator/courseLibrary_add";
    }
    @RequestMapping("/allocationTeacher")
    public String allocationTeacher(){
        return "administrator/allocationTeacher";
    }
    @RequestMapping("/powerList")
    public String powerList(){
        return "administrator/power_list";
    }

    /**
     * 修改学期规则
     * @return
     */
    @RequestMapping("/termRule_edit")
    public String termRule_edit(String data,Model model){
        TermVo termVo = JSONObject.parseObject(data, TermVo.class);
        model.addAttribute("termVo",termVo);
        return "administrator/termRule_edit";
    }
    /**
     * 进入编辑权限页面
     * @param data
     * @param model
     * @return
     */
    @RequestMapping("/powerEdit")
    public String roleAdd(String data,Model model){
        Teacher teacher = JSONObject.parseObject(data, Teacher.class);
        List<CollegeEntity> collegeEntities = feignDdms.selectAllCollege();
        model.addAttribute("college",collegeEntities);
        model.addAttribute("teacher",teacher);
        return "administrator/powerEdit";
    }
    /**
     * 配置课程
     * @return
     */
    @GetMapping("/selectTeacherName")
    public String selectTeacherName(Model model,String data){
        CourseLibraryVo courseLibraryVo = JSONObject.parseObject(data, CourseLibraryVo.class);
//        String serialize = JSONSerializer.serialize(electiveResult.getData());
        System.out.println(courseLibraryVo);
        System.out.println(data);
        model.addAttribute("data",data);
        return "administrator/allocationTeacher";
    }

    /**
     * 查询教室信息
     */
    @RequestMapping("/selectAllRoom")
    @ResponseBody
    public ElectiveResult selectAllRoom(){
        //查询教室信息
        List<ClazzRoomEntity> clazzRoomEntities = feignDdms.selectAllClazzRoom();
        return ElectiveResult.ok(clazzRoomEntities);
    }

    /**
     * 根据楼号查询教室
     * @param crLocation
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLou")
    @ResponseBody
    public  ElectiveResult selectAllClazzRoomByLou(String crLocation){
        List<ClazzRoomEntity> clazzRoomEntities = feignDdms.selectAllClazzRoomByLou(crLocation);
        return ElectiveResult.ok(clazzRoomEntities);
    }

    /**
     * 根据楼号和教室，查最大容纳量
     * @param crLocation
     * @param crRoomNum
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLouAndRoom")
    @ResponseBody
    public ElectiveResult selectAllClazzRoomByLouAndRoom(@RequestParam("crLocation") String crLocation,
                                                          @RequestParam("crRoomNum") String crRoomNum){
        ClazzRoomEntity clazzRoomEntity = feignDdms.selectAllClazzRoomByLouAndRoom(crLocation, crRoomNum);
        return ElectiveResult.ok(clazzRoomEntity);
    }

    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping("/selectTeacherInfo")
    @ResponseBody
    public PageResult selectTeacherInfo(@RequestParam(value = "page",defaultValue = "1") int page,
                                        @RequestParam(value = "limit",defaultValue = "10") int limit,
                                        @RequestParam(value = "teaName",required = false) String teaName){
        return feignDdms.selectTeacherInfo(page,limit,teaName);
    }

    /**
     * 修改教师权限
     * @param workNumber
     * @param teaPower
     * @return
     */
    @PostMapping("/updateTeacherPower")
    @ResponseBody
    public ElectiveResult updateTeacherPower(@RequestParam("workNumber") String workNumber,
                                             @RequestParam("teaPower")String teaPower){
        ElectiveResult electiveResult = feignDdms.updateTeacherPower(workNumber, teaPower);
        return electiveResult;
    }

    /**
     * 选课列表
     * @return
     */
    @RequestMapping("/electiveCourseList")
    public String electiveCourseList(){
        return "administrator/electiveCourse-list";
    }


    /**
     * 选课列表详情
     * @return
     */
    @RequestMapping("/electiveCourseInfo")
    public String electiveCourseInfo(String data,Model model){
        ElectiveCourseEntity electiveCourseEntity = JSONObject.parseObject(data, ElectiveCourseEntity.class);
        model.addAttribute("electiveCourseEntity",electiveCourseEntity);
        return "administrator/selectCourse_Info";
    }

    @RequestMapping("/achievement_list")
    public String achievementList(){
        return "administrator/achievement_list";
    }

    @RequestMapping("/performanceBlack_list")
    public String performanceBlack_list(){
        return "administrator/performanceBlack_list";
    }


    /**
     * 查询教师姓名
     * @return
     */
    @GetMapping("/selectAllTeacherName")
    @ResponseBody
    public ElectiveResult selectAllTeacherName(){
        //查询教师信息
        return feignDdms.selectTeacherName();
    }

    @GetMapping("/all_achievement_list")
    public String all_achievement_list(){
        return "administrator/all_achievement_list";
    }

    /**
     * 根据工号和密码查询教师
     * @param username 工号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/selectTeacher")
    @ResponseBody
    public ElectiveResult selectOne(@RequestParam("username") String username,@RequestParam("password") String password) {
        return feignDdms.selectOne(username, password);
    }
    @GetMapping("/manager_edit")
    public String manager_edit(){
        return "administrator/manager_edit";
    }

    /***
     * 更改密码
     * @param username 用户名
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     * @return
     */
    @PostMapping("/updateTeacherPassword")
    @ResponseBody
    public ElectiveResult updateTeacherPassword(@RequestParam("username") String username,
                                                @RequestParam("OldPassword") String OldPassword,@RequestParam("NewPassword") String NewPassword){
        return feignDdms.updateTeacherPassword(username,OldPassword,NewPassword);
    }

    /**
     * @Author 李泽宇
     * @Description 汇总查询所有学期某课程下的所有人数
     * @Date 2019/4/24 17:44
     * @Param
     * @return
     **/
    @RequestMapping("/selectNumberToCourseLibraryName")
    public String selectNumberToCourseLibraryName(){
        return "administrator/selectNumberToCourseLibraryName";
    }

    /**
     * @Author 李泽宇
     * @Description 汇总某学期下那些课程被多少人选了
     * @Date 2019/4/24 17:45
     * @Param
     * @return
     **/
    @RequestMapping("/selectNumberToTermId")
    public String selectNumberToTermId(){
        return "administrator/selectNumberToTermId";
    }

    /**
     * @Author 李泽宇
     * @Description 汇总某学期某课程下的这些老师被多少人选了
     * @Date 2019/4/24 17:45
     * @Param
     * @return
     **/
    @RequestMapping("/TeacherSdNumber")
    public String TeacherSdNumber(){
        return "administrator/TeacherSdNumber";
    }


    /**
     * @Author 李泽宇
     * @Description 某一学期下某个老师下的所有课程选课人数
     * @Date 2019/4/24 17:45
     * @Param
     * @return
     **/
    @RequestMapping("/TeacherAllCourseSdNumber")
    public String TeacherAllCourseSdNumber(){
        return "administrator/TeacherAllCourseSdNumber";
    }

    @RequestMapping("/echarts1")
    public String echarts1(){
        return "administrator/echarts1";
    }
    @RequestMapping("/echarts2")
    public String echarts2(){
        return "administrator/echarts2";
    }

    @GetMapping("/echartsTeaAllCourse")
    public String echartsTeaAllCourse(){
        return "administrator/echartsTeaAllCourse";
    }


    @GetMapping("/CoursePassRate")
    public String CoursePassRate(){
        return "administrator/CoursePassRate";
    }
}
