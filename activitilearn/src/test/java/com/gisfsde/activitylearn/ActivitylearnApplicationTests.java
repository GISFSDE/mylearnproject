package com.gisfsde.activitilearn;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cmd.CompleteTaskCmd;
import org.activiti.engine.impl.cmd.DeleteTaskCmd;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.history.HistoryManager;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManager;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * https://www.jianshu.com/p/c4976da56ba7
 * https://www.activiti.org/userguide/#_getting_started
 * https://blog.csdn.net/abu935009066/article/details/109964445
 * 流程：张三给老师老李请假
 **/
@SpringBootTest
class ActivitylearnApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(ActivitylearnApplicationTests.class);
    @Resource
    private ProcessEngine processEngine;
    /**
     * 数据存储服务
     * 管理和控制发布包和流程定义
     * 查询引擎中的发布包和流程定义。
     * 暂停或激活发布包，对应全部和特定流程定义。 暂停意味着它们不能再执行任何操作了，激活是对应的反向操作。
     * 获得多种资源，像是包含在发布包里的文件， 或引擎自动生成的流程图。
     * 获得流程定义的pojo版本， 可以用来通过java解析流程，而不必通过xml。
     */
    @Resource
    private RepositoryService repositoryService;
    /**
     * 运行服务
     * RuntimeService正好是完全相反的。它负责启动一个流程定义的新实例。
     * 如上所述，流程定义定义了流程各个节点的结构和行为。 流程实例就是这样一个流程定义的实例。
     * 对每个流程定义来说，同一时间会有很多实例在执行。 RuntimeService也可以用来获取和保存流程变量。
     * 这些数据是特定于某个流程实例的，并会被很多流程中的节点使用
     * （比如，一个排他网关常常使用流程变量来决定选择哪条路径继续流程）。
     * Runtimeservice也能查询流程实例和执行。 执行对应BPMN 2.0中的’token’。
     * 基本上执行指向流程实例当前在哪里。 最后，RuntimeService可以在流程实例等待外部触发时使用，这时可以用来继续流程实例。
     * 流程实例可以有很多暂停状态，而服务提供了多种方法来’触发’实例， 接受外部触发后，流程实例就会继续向下执行。
     */
    @Resource
    private RuntimeService runtimeService;
    /**
     * 任务服务
     * 任务是由系统中真实人员执行的，它是Activiti这类BPMN引擎的核心功能之一。 所有与任务有关的功能都包含在TaskService中：
     * 查询分配给用户或组的任务
     * 创建独立运行任务。这些任务与流程实例无关。
     * 手工设置任务的执行者，或者这些用户通过何种方式与任务关联。
     * 认领并完成一个任务。认领意味着一个人期望成为任务的执行者， 即这个用户会完成这个任务。完成意味着“做这个任务要求的事情”。
     * 通常来说会有很多种处理形式。
     */
    @Resource
    private TaskService taskService;
    /**
     * 历史服务
     * HistoryService提供了Activiti的所有历史数据。
     * 在执行流程时，引擎会保存很多数据（根据配置），比如流程实例启动时间，
     * 任务的参与者， 完成任务的时间，每个流程实例的执行路径，等等。
     * 这个服务主要通过查询功能来获得这些数据。
     */
    @Resource
    private HistoryService historyService;

    /**
     * ManagementService在使用Activiti的定制环境中基本上不会用到。
     * 它可以查询数据库的表和表的元数据。另外，它提供了查询和管理异步操作的功能。
     * Activiti的异步操作用途很多，比如定时器，异步操作， 延迟暂停、激活，等等。
     **/
    @Resource
    private ManagementService managementService;
    /**
     * FormService是一个可选服务。即使不使用它，Activiti也可以完美运行， 不会损失任何功能。
     * 这个服务提供了启动表单和任务表单两个概念。 启动表单会在流程实例启动之前展示给用户，
     * 任务表单会在用户完成任务时展示。Activiti支持在BPMN 2.0流程定义中设置这些表单。
     * 这个服务以一种简单的方式将数据暴露出来。再次重申，它时可选的， 表单也不一定要嵌入到流程定义中。
     **/
    @Resource
    private FormService formService;
    /**
     * 管理群组用户，无检查是否存在
     * IdentityService非常简单。它可以管理（创建，更新，删除，查询…）群组和用户。
     * 请注意， Activiti执行时并没有对用户进行检查。 例如，任务可以分配给任何人，但是引擎不会校验系统中是否存在这个用户。
     * 这是Activiti引擎也可以使用外部服务，比如ldap，活动目录，等等。
     **/
    @Resource
    private IdentityService identityService;

    /**
     * 1. 部署流程 repositoryService
     * 部署之后就可以在act_re_procdef表中看到对相应的流程信息
     */
    @Test
    public void deployProcess() {
        DeploymentBuilder builder = repositoryService.createDeployment();
        // bpmn文件的名称
        builder.addClasspathResource("processes/test.bpmn");
        // 设置key
        builder.key("myProcess_3");
        // 设定名称，也可以在图中定义
        builder.name("请假流程");
        // 进行布署
        Deployment deployment = builder.deploy();
        // 获取deployment的ID
        String deploymentId = deployment.getId();
        // 根据deploymentId来获取流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
        log.info("流程定义文件 [{}] , 流程ID [{}]", processDefinition.getName(), processDefinition.getId());
    }

    /**
     * 2. 启动流程 runtimeService 学生请假
     * 启动流程之后就会有相应的任务产生，存在act_ru_task表中，可以查看任务节点
     */
    @Test
    public void startProcess() {
        // act_ru_variable 表中存储运行时变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("submitter", "zs");//请假人张三
        variables.put("leader", "ll");//老师老李
        try {
            // 引擎自动操作如下：
            //act_ru_execution 表 START_USER_ID字段 插入用户id
            //引擎会记录启动人信息，在ACT_HI_PROINST表的START_USER_ID字段，记录用户id “zs”.
            identityService.setAuthenticatedUserId("zs");
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        // 流程的名称，也可以使用ByID来启动流程
        // key为流程图的ID,创建新的流程实例在 act_ru_execution 中
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_3", variables);
//        act_hi_actinst 增加历史动作
//        act_hi_varinst 增加历史变量
        log.info("流程启动成功，act_ru_execution中流程实例id:{}，act_re_procdef中流程定义id:{}"
                , processInstance.getId(), processInstance.getProcessDefinitionId());
    }

    /**
     * 3. 查询代理人待办任务，查看任务节点 taskService,查询可通过createNativeTaskQuery（） + sql进行更加复杂的查询
     */
    @Test
    public void queryTask() {
        //  根据assignee(代理人)查询代理人待办的任务
        String assignee = "admin";
        // 老李待办
        List<Task> tasks = taskService.createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .orderByTaskCreateTime().asc()//使用创建时间的升序排列
                .list();

        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
        }
        // 首次运行的时候这个没有输出，因为第一次运行的时候扫描act_ru_task的表里面是空的，
        // 但第一次运行完成之后里面会添加一条记录，之后每次运行里面都会添加一条记录
        for (Task task : tasks) {
            log.info("未办——-taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }

        // 老李已办
        List<HistoricTaskInstance> completed = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .finished()// 已完成
                .list();
        for (HistoricTaskInstance task : completed) {
            log.info("已办——-taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }


    /**
     * 4. 完成流程 taskService，admin通过审核，老师同意
     */
    @Test
    public void completeTasks() {
        try {
            //添加批注
            Authentication.setAuthenticatedUserId("zs");//批注人的名称  一定要写，不然查看的时候不知道人物信息
            String taskId = "80007"; // 任务id；
            Task taskOne = taskService.createTaskQuery().taskId(taskId).singleResult();
            String processInstanceId = taskOne.getProcessInstanceId(); // 实例id
            String type = "comment"; // 批注类型,这个参数如果不写默认就是"comment"，用于扩展 用的。
            String message = "同意请假"; // 批注内容
            // 给当前任务添加批注信息
            taskService.addComment(taskId, processInstanceId, type, message);

            //查看批注
            //根据实例id获取批注
            List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);
            //根据任务id获取批注
            List<Comment> taskComments = taskService.getTaskComments(taskId);

        } finally {
            Authentication.setAuthenticatedUserId(null);
        }

        //创建附件
        try {
            Authentication.setAuthenticatedUserId("zs");//批注人的名称  一定要写，不然查看的时候不知道人物信息
            String attachmentType = "";
            String taskId = "80007"; // 任务id
            String processInstanceId = "80001"; // 任务实例id
            String attachmentName = "test.png"; // 附件名称
            String attachmentDescription = "描述描述"; // 附件描述
            String url = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
            taskService.createAttachment(attachmentType, taskId, processInstanceId, attachmentName, attachmentDescription, url);
        } finally {
            Authentication.setAuthenticatedUserId(null);
        }
        //获取附件
        // 根据流程实例ID查询附件
        List<Attachment> processInstanceAttachments = taskService.getProcessInstanceAttachments("80001");
        // 根据任务ID查询附件
        List<Attachment> attachments = taskService.getTaskAttachments("80007");

        // 审批后，任务列表数据减少
        Map<String, Object> vars = new HashMap<>();
        //  按配置的任务id填写，_5是老师审批id
        vars.put("_5", "true");
        taskService.complete("80007", vars);
        //审批不通过，结束流程
        //    runtimeService.deleteProcessInstance(vacationAudit.getProcessInstanceId(), auditId);

    }

    /**
     * 获取流程定义图
     **/
    @Test
    public void getFlowPic() throws IOException {
//        默认生成流图
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//                .processDefinitionKey("myProcess_2")
//                .singleResult();
//        String diagramResourceName = processDefinition.getDiagramResourceName();
//        InputStream imageStream = repositoryService.getResourceAsStream(
//                processDefinition.getDeploymentId(), diagramResourceName);
//
//        getinputPic(imageStream);


//        自定义图片
//        repositoryService.createDeployment()
//                .key("myProcess_3")
//                .name("请假流程")
//                .addClasspathResource("processes/test.bpmn")
//                .addClasspathResource("processes/test.png")
//                .deploy();
//        接下来，可以通过API来获取流程定义图片资源：
        ProcessDefinition processDefinitionCustom = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_3")
                .singleResult();


        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionCustom.getId());// 模型
        List<String> highLightedActivities= new ArrayList<>();
//       highLightedActivities  = runtimeService.getActiveActivityIds("80001");// 高亮指定节点
        List<String> highLightedFlows = new ArrayList<>(); // 需要高亮的连接线

//        高亮处理过的连接线
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("80001")
                .orderByHistoricActivityInstanceId().asc().list();
        highLightedFlows = getHighLightedFlows(bpmnModel, historicActivityInstances);// 获取处理过的连接线

        // 高亮正在进行中的节点+处理过的连接线+处理过的节点
        // 高亮已经执行流程节点ID集合
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            highLightedActivities.add(historicActivityInstance.getActivityId());
        }




//        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
//        修改源码修改流程图颜色
        ProcessDiagramGenerator processDiagramGenerator = new LakerProcessDiagramGenerator();
        InputStream png = processDiagramGenerator.generateDiagram
                (bpmnModel, "png",highLightedActivities,
                        highLightedFlows, "宋体", "微软雅黑", "黑体", null, 2.0);




        getinputPic(png);

    }
        /**
         * 获取已经流转的线
         *
         * @param bpmnModel
         * @param historicActivityInstances
         * @return
         */
    private static List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlowIds = new ArrayList<>();
        // 全部活动节点
        List<FlowNode> historicActivityNodes = new ArrayList<>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstances = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true);
            historicActivityNodes.add(flowNode);
            if (historicActivityInstance.getEndTime() != null) {
                finishedActivityInstances.add(historicActivityInstance);
            }
        }

        FlowNode currentFlowNode = null;
        FlowNode targetFlowNode = null;
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstances) {
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
            List<SequenceFlow> sequenceFlows = currentFlowNode.getOutgoingFlows();

            /**
             * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转： 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             */
            if ("parallelGateway".equals(currentActivityInstance.getActivityType()) || "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
                // 遍历历史活动节点，找到匹配流程目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicActivityNodes.contains(targetFlowNode)) {
                        highLightedFlowIds.add(targetFlowNode.getId());
                    }
                }
            } else {
                List<Map<String, Object>> tempMapList = new ArrayList<>();
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
                        if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("highLightedFlowId", sequenceFlow.getId());
                            map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
                            tempMapList.add(map);
                        }
                    }
                }

                if (!CollectionUtils.isEmpty(tempMapList)) {
                    // 遍历匹配的集合，取得开始时间最早的一个
                    long earliestStamp = 0L;
                    String highLightedFlowId = null;
                    for (Map<String, Object> map : tempMapList) {
                        long highLightedFlowStartTime = Long.valueOf(map.get("highLightedFlowStartTime").toString());
                        if (earliestStamp == 0 || earliestStamp >= highLightedFlowStartTime) {
                            highLightedFlowId = map.get("highLightedFlowId").toString();
                            earliestStamp = highLightedFlowStartTime;
                        }
                    }

                    highLightedFlowIds.add(highLightedFlowId);
                }

            }

        }
        return highLightedFlowIds;
    }

   public void getinputPic(InputStream imageStream) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Dcjczx\\Desktop\\b.png");
        byte[] b = new byte[1024];
        while ((imageStream.read(b)) != -1) {
            fos.write(b);// 写入数据
        }
        imageStream.close();
        fos.close();// 保存数据
    }

    /**
     * 历史任务查询
     *
     * @throws ParseException
     */
    @Test
    public void findHistoricTasks() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskAssignee("admin") // 指定办理人
//                .finished() // 查询已经完成的任务
                .list();
        for (HistoricTaskInstance hti : list) {
            log.info("任务ID:" + hti.getId());
            log.info("流程实例ID:" + hti.getProcessInstanceId());
            log.info("班里人：" + hti.getAssignee());
            log.info("创建时间：" + sdf.format(hti.getCreateTime()));
            log.info("结束时间：" + sdf.format(hti.getEndTime()));
            log.info("===========================");
        }
    }

    /**
     * 历史活动查询
     * 指定流程实例id,启动流程时，获取的实例ID
     */
    @Test
    public void historyActInstanceList() {
        List<HistoricActivityInstance> list = historyService // 历史任务Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .processInstanceId("32501") // 指定流程实例id
//                .finished() // 查询已经完成的任务
                .list();
        for (HistoricActivityInstance hai : list) {
            log.info("任务ID:" + hai.getId());
            log.info("流程实例ID:" + hai.getProcessInstanceId());
            log.info("活动名称：" + hai.getActivityName());
            log.info("办理人：" + hai.getAssignee());
            log.info("开始时间：" + hai.getStartTime());
            log.info("结束时间：" + hai.getEndTime());
            log.info("===========================");
        }
    }

    /**
     * 历史流程实例查询
     * 指定流程实例id,启动流程时，获取的实例ID
     */
    @Test
    public void historyProcessInstanceList() {
        List<HistoricProcessInstance> list = historyService // 历史任务Service
                .createHistoricProcessInstanceQuery()// 创建历史流程实例查询
//                .finished()
//                .involvedUser("zs")  //与zs相关的
                .startedBy("zs").list(); //由张三创建的
        for (HistoricProcessInstance hai : list) {
            log.info("任务ID:" + hai.getId());
            log.info("流程实例ID:" + hai.getProcessDefinitionId());
            log.info("活动名称：" + hai.getProcessDefinitionName());
            log.info("开始时间：" + hai.getStartTime());
            log.info("结束时间：" + hai.getEndTime());
            log.info("===========================");
        }
    }

    /**
     * 任务节点操作
     **/
    @Test
    public void tastOperat() {
        //可以在任意地方使用，service，自定义事件，自定义命令中等等
        //import org.activiti.engine.impl.context.Context;
        CommandContext commandContext = Context.getCommandContext();
        HistoryManager historyManager = commandContext.getHistoryManager();


//        获取任务实例管理类
        TaskEntityManager taskEntityManager = commandContext.getTaskEntityManager();
//        获取当前任务实例
        String taskId = "65007"; // 任务id；
        TaskEntity currentTask = taskEntityManager.findById(taskId);
        ExecutionEntity execution = currentTask.getExecution();
        String executionId = execution.getId();
        log.info("当期任务实例ID:{}", executionId);
//        获取流程定义
//        Process process = ProcessDefinitionUtil.getProcess(processDefinitionId);
//        根据节点id获取FlowElement节点对象
//        FlowElement flowElement = process.getFlowElement(targetNodeId);
//        设置执行实例的当前活动节点为目标节点
//        execution.setCurrentFlowElement(flowElement);
//        向operations中压入继续流程的操作类
//        commandContext.getAgenda().planContinueProcessOperation(execution);


//      刪除任务
//      执行方式1
//        new DeleteTaskCmd(delegateTask.getId(), null).execute(Context.getCommandContext());// 执行完成命令
//      执行方式2
//        processEngine.getManagementService().executeCommand(cmd);


//        完成任务
//        执行方式1
//        new CompleteTaskCmd(delegateTask.getId(), null).execute(Context.getCommandContext());// 执行完成命令
//        执行方式2
//        processEngine.getManagementService().executeCommand(cmd)
//        获取流程定义的start节点
//        Process process = ProcessDefinitionUtil.getProcess(processDefinitionId);
//        FlowElement flowElement = process.getInitialFlowElement();
//        FlowNode startActivity = (FlowNode) flowElement;


//        流程任意节点跳转、驳回、退回、拒绝等
        // 删除当前运行任务,自带的删除命令即可
//        managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
//        // 流程执行到指定节点
//        FlowNode targetNode = (FlowNode) process.getFlowElement(taskDefKey);"lakertask"
//        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));


    }

    //根据提供节点和执行对象id，进行跳转命令
    public class SetFLowNodeAndGoCmd implements Command<Void> {
        private FlowNode flowElement;
        private String executionId;

        public SetFLowNodeAndGoCmd(FlowNode flowElement, String executionId) {
            this.flowElement = flowElement;
            this.executionId = executionId;
        }

        public Void execute(CommandContext commandContext) {
            // 获取目标节点的来源连线
            List<SequenceFlow> flows = flowElement.getIncomingFlows();
            if (flows == null || flows.size() < 1) {
                throw new ActivitiException("回退错误，目标节点没有来源连线");
            }
            // 随便选一条连线来执行，当前执行计划为，从连线流转到目标节点，实现跳转
            ExecutionEntity executionEntity = commandContext
                    .getExecutionEntityManager().findById(executionId);
            executionEntity.setCurrentFlowElement(flows.get(0));
            commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(
                    executionEntity, true);
            return null;
        }
    }

    /**
     * 驳回到起始节点
     **/
    public static class RollbackFirstTaskCmd extends NeedsActiveTaskCmd<Void> {
        private static Logger logger = LoggerFactory.getLogger(RollbackFirstTaskCmd.class);

        public RollbackFirstTaskCmd(String taskId) {
            super(taskId);
        }

        public String deletereason;

        public Void execute(CommandContext commandContext, TaskEntity currentTask) {
            String processDefinitionId = currentTask.getProcessDefinitionId();
            String executionId = currentTask.getExecutionId();

            TaskEntityManager taskEntityManager = commandContext.getTaskEntityManager();
            taskEntityManager.deleteTask(currentTask, deletereason, false, false);

            FlowNode firstUserTask = this.findFirstActivity(processDefinitionId);
            ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findById(executionId);

            // 获取目标节点的来源连线
            List<SequenceFlow> flows = firstUserTask.getIncomingFlows();
            if (flows == null || flows.isEmpty()) {
                throw new ActivitiException("回退错误，目标节点没有来源连线");
            }
            // 随便选一条连线来执行，时当前执行计划为，从连线流转到目标节点，实现跳转
            executionEntity.setCurrentFlowElement(flows.get(0));
            commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);

            // executionEntity.setCurrentFlowElement(flowElement);
            // commandContext.getAgenda().planContinueProcessOperation(executionEntity);

            return null;
        }

        public String getSuspendedTaskException() {
            return "挂起的任务不能跳转";
        }

        /**
         * 获得第一个节点.
         */
        public FlowNode findFirstActivity(String processDefinitionId) {
            Process process = ProcessDefinitionUtil.getProcess(processDefinitionId);
            FlowElement flowElement = process.getInitialFlowElement();
            FlowNode startActivity = (FlowNode) flowElement;

            if (startActivity.getOutgoingFlows().size() != 1) {
                throw new IllegalStateException(
                        "start activity outgoing transitions cannot more than 1, now is : "
                                + startActivity.getOutgoingFlows().size());
            }

            SequenceFlow sequenceFlow = startActivity.getOutgoingFlows()
                    .get(0);
            FlowNode targetActivity = (FlowNode) sequenceFlow.getTargetFlowElement();

            if (!(targetActivity instanceof UserTask)) {
                logger.info("first activity is not userTask, just skip");

                return null;
            }

            return targetActivity;
        }
    }

    /**
     * 查询流程状态（正在执行 or 已经执行结束）
     */
    public void processState() {
        ProcessInstance pi = processEngine.getRuntimeService() // 获取运行时Service
                .createProcessInstanceQuery() // 创建流程实例查询
                .processInstanceId("2501") // 用流程实例id查询
                .singleResult();
        if (pi != null) {
            System.out.println("流程正在执行！");
        } else {
            System.out.println("流程已经执行结束！");
        }
    }

    /**
     * 获得第一个节点.
     */
    public FlowNode findFirstActivity(String processDefinitionId) {
        Process process = ProcessDefinitionUtil.getProcess(processDefinitionId);
        FlowElement flowElement = process.getInitialFlowElement();
        FlowNode startActivity = (FlowNode) flowElement;

        if (startActivity.getOutgoingFlows().size() != 1) {
            throw new IllegalStateException(
                    "start activity outgoing transitions cannot more than 1, now is : "
                            + startActivity.getOutgoingFlows().size());
        }

        SequenceFlow sequenceFlow = startActivity.getOutgoingFlows()
                .get(0);
        FlowNode targetActivity = (FlowNode) sequenceFlow.getTargetFlowElement();

        if (!(targetActivity instanceof UserTask)) {
            log.info("first activity is not userTask, just skip");

            return null;
        }

        return targetActivity;
    }

//    public void DeleteTaskCmd(String taskId, String deleteReason, boolean cascade) {
//        this.taskId = taskId;
//        this.cascade = cascade;
//        this.deleteReason = deleteReason;
//    }
//
//    public void DeleteTaskCmd(Collection<String> taskIds, String deleteReason, boolean cascade) {
//        this.taskIds = taskIds;
//        this.cascade = cascade;
//        this.deleteReason = deleteReason;
//    }
}
