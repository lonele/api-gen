工作流开发指南
一、设计表单
1、表单必须包含instance_id字段，用户存储流程实例id
2、
二、设计添加，查询，更新instance_id接口
form表单提交时，1、先保存业务数据,返回业务id，2、再启动流程，返回instance_id，3、启动成功后，更新业务表的instance_id
三、添加模型，设计流程，注意：
1、流程设计器中的id即是流程标示key，启动流程，查询该类流程的待办和已办任务时使用
2、流程设计器中，第一个节点通常是用户提交申请，在启动时会默认跳过，
预定义三种受理人assignee: initator，manager，boss
initator一般为当前用户id ，默认1001
manager用户所属部门经理 ，默认1002
boss为老板，默认1003
一般的流程：
员工提交 （assignee=initator）-> 经理审批（assignee=manager） -> 老板审批（assignee=boss）
具体项目中需要自行实现AssigneeService类中的三个抽象方法，预定义角色可以自己扩展，增加相应的抽象方法，并修改getNextAssigneeId方法
