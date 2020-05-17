// package com.jiadao;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import com.jiadao.model.AjaxResult;
// import com.jiadao.module.process.service.ProcessService;
// import com.jiadao.service.DB;

// import org.activiti.engine.history.HistoricTaskInstance;
// import org.activiti.engine.task.Comment;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.nutz.dao.entity.Record;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import lombok.extern.slf4j.Slf4j;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = {WebApplication.class})// 指定启动类
// @Slf4j
// public class ActivitiTest {


//     @Autowired
//     DB db;

//     @Autowired
//     ProcessService processService;

//     String tableName = "t_leave";

//     String processKey = "leave"; // 这个是在流程设计时定义的流程id

//     String zhangsan = "张三"; //1001
//     int zhangsan_id = 1001; //1001
//     String lisi = "李四";  //1002
//     int lisi_id = 1002;

//     int leaveId = 0;
//     @Test
//     public void testStartProcess(){
//         //1、模拟提交申请
//         Map map = new HashMap();
//         map.put("user_id",zhangsan_id);//当前提交人id，map中必须包含
//         map.put("process_title","张三请假");
//         map.put("name","张三");
//         map.put("day","3");
//         map.put("reason","家里有事");
//         //String leave = "{'name':'zhangsan','day':'3','reason':'家里有事'}";
//         leaveId  = db.save(tableName,map);
//         log.info("leaveId====="+leaveId);
//         String processInstanceId =  processService.startProcess(processKey, ""+leaveId, zhangsan,map);
//         log.info("processInstanceId====="+processInstanceId);
//         db.updateSingleColumnByKey(tableName, "process_id", processInstanceId, "id", ""+leaveId);
//         Map<String, Object> taskVariables = new HashMap<String, Object>();
//         //提交表单之后，自动完成第一个节点
//         processService.completeTask(processInstanceId,zhangsan_id+"",zhangsan, "0","提交申请",taskVariables);
//         //启动流程后，流程会根据流程定义自动转到下一个节点的assign
//     }

//     @Test
//     public void testTaskList(){
//         //查看待办任务
        
//         AjaxResult result = processService.queryTaskList(processKey, "lisi", 1, 10);
//         List<Record> taskList = (List<Record>)result.get("data");
//         for(Record task:taskList){
//             //instance_id  task_name,data_id,process_name
//             System.out.println("task id :"+task.getString("task_id"));
//             System.out.println("task instance_id :"+task.getString("instance_id"));
//             //当前任务名称
//             System.out.println("task task_name :"+task.getString("task_name"));
//             System.out.println("task process_name :"+task.getString("process_name"));
//             System.out.println("task leave id :"+task.getString("data_id"));
//         }
//     }

//     @Test
//     public void testReComplete(){
//         //测试完成任务
//         Map<String, Object> taskVariables = new HashMap<String, Object>();
        
//         //重新提交
//         processService.completeTask("60001", ""+zhangsan_id,zhangsan,"2","重新提交", taskVariables);
//     }

//     @Test
//     public void testComplete(){
//         //测试完成任务
//         Map<String, Object> taskVariables = new HashMap<String, Object>();
        
//         //同意
//         //processService.completeTask("60001", ""+lisi_id,lisi,"1","请准时返回", taskVariables);
//         //驳回
//         processService.completeTask("60001", ""+lisi_id,lisi,"-1","请重新提交", taskVariables);
//     }


//     @Test
//     public void testGetComment(){
//         List<Comment> list = processService.getCommentList("60001");
//         for(Comment com:list){
//             System.out.println("ID:"+com.getId());
//             System.out.println("Message:"+com.getFullMessage());
//             System.out.println("TaskId:"+com.getTaskId());
//             System.out.println("Time:"+com.getTime());
//             System.out.println("ProcessInstanceId:"+com.getProcessInstanceId());
//             System.out.println("UserId:"+com.getUserId());
//         }
//     }


//     // @Test
//     // public void testHistory(){
//     //     List<HistoricTaskInstance> historyList = processService.queryDoneTasks("60001");
//     //     for(HistoricTaskInstance hti:historyList){
//     //         System.out.println("任务ID:"+hti.getId());
//     //         System.out.println("流程实例ID:"+hti.getProcessInstanceId());
//     //         System.out.println("任务名称："+hti.getName());
//     //         System.out.println("办理人："+hti.getAssignee());
//     //         System.out.println("开始时间："+hti.getStartTime());
//     //         System.out.println("结束时间："+hti.getEndTime());
//     //         System.out.println("=================================");
//     //     }
//     // }
// }
