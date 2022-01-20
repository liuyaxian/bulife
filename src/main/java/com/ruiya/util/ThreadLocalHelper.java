package com.ruiya.util;

import com.ruiya.bean.CustAction;
import org.apache.log4j.Logger;

import java.util.LinkedList;

/**
 * @desc:　 使用ThreadLocal监听对应线程每个动作
 * @author: admin
 * @since: 2022/1/20 10:16
 * @history:
 */
public class ThreadLocalHelper {
    /**
     * 日志器
     */
    private static final Logger logger = Logger.getLogger(ThreadLocalHelper.class);

    private static ThreadLocalHelper instance = new ThreadLocalHelper();

    private ThreadLocal<LinkedList<TimeMeter>> threadLocalTimer = new ThreadLocal<LinkedList<TimeMeter>>();

    private ThreadLocal<CustAction> threadLocalCustAction = new ThreadLocal<CustAction>();

    private ThreadLocalHelper() {

    }

    public static ThreadLocalHelper getInstance() {
        return instance;
    }

    /**
     * <p>Title: addAction</p>
     * <p>Description: </p>
     */
    public void addAction(String action) {
        LinkedList<TimeMeter> list = threadLocalTimer.get();
        if (list == null || list.size() == 0) {
            list = new LinkedList<ThreadLocalHelper.TimeMeter>();
            list.add(new TimeMeter(action, 1));
            threadLocalTimer.set(list);
        } else {
            TimeMeter lastTM = list.getLast();
            lastTM.setEndTime(System.currentTimeMillis());
            list.add(new TimeMeter(action, lastTM.getPos() + 1));
            // 为了防止内存泄露，删除第一条记录。 限制每个监控不超过200个动作。
            if (list.size() > 200) {
                TimeMeter tm = list.removeFirst();
                logger.warn("currentThread action list is too long:" + list.size() + " auto remove the first action "
                        + tm);
            }
        }
        logger.debug("currentThread action " + list.getLast());
    }

    /**
     * <p>Title: getExpiredTime</p>
     * <p>Description: 获取监控动作的过期时间</p>
     */
    public long getExpiredTime() {
        LinkedList<TimeMeter> list = threadLocalTimer.get();
        if (list == null || list.size() == 0) {
            return 0;
        } else {
            TimeMeter firstTM = list.getFirst();
            TimeMeter lastTM = list.getLast();
            return lastTM.getEndTime() - firstTM.getStartTime();
        }
    }

    /**
     * 获取custAction
     * @return
     * @history:
     */
    public CustAction getCustAction() {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        return custAction;
    }
    /**
     * 增加custaction的action属性
     * @param action
     * @history:
     */
    public void setCustActionAction(String action) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setAction(action);
        threadLocalCustAction.set(custAction);
    }

    /**
     * 增加custaction的callFrom属性
     * @param callFrom
     * @history:
     */
    public void setCustActionCallFrom(String callFrom) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setCallFrom(callFrom);
        threadLocalCustAction.set(custAction);
    }

    /**
     * 增加custaction的CustIP属性
     * @param custIP
     * @history:
     */
    public void setCustActionCustIP(String custIP) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setCustIP(custIP);
        threadLocalCustAction.set(custAction);
    }

    /**
     * 增加custaction的remark属性
     * @param remark
     * @history:
     */
    public void setCustActionRemark(String remark) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setRemark(remark);
        threadLocalCustAction.set(custAction);
    }

    /**
     * 增加custaction的serialno属性
     * @param serialno
     * @history:
     */
    public void setCustActionSerialno(String serialno) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setSerialno(serialno);
        threadLocalCustAction.set(custAction);
    }

    /**
     * @param custno
     */
    public void setCustActionCustno(String custno) {
        CustAction custAction = threadLocalCustAction.get();
        if (null == custAction) {
            custAction = new CustAction();
        }
        custAction.setCustno(custno);
        threadLocalCustAction.set(custAction);
    }

    /**
     * <p>Title: init</p>
     * <p>Description: 线程监测时先初始化，确保业务记录从头开始</p>
     */
    public void init() {
        threadLocalTimer.remove();
        threadLocalCustAction.remove();
    }

    /**
     * <p>Title: clear</p>
     * <p>Description: 线程动作监听结束后，释放资源</p>
     */
    public void clear() {
        threadLocalTimer.remove();
        threadLocalCustAction.remove();
    }

    @Override
    public String toString() {
        LinkedList<TimeMeter> list = threadLocalTimer.get();
        if (list != null) {
            return list.toString();
        } else {
            return "No Action: threadLocal.get() is null";
        }
    }

    class TimeMeter {
        private String action;

        private long startTime;

        private long endTime;

        private long pos = 1;

        public TimeMeter(String action, long pos) {
            this.action = action;
            this.startTime = System.currentTimeMillis();
            // 通过后续的更新，刷新结束时间
            this.endTime = System.currentTimeMillis();
            this.pos = pos;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getExpiredTime() {
            return endTime - startTime;
        }

        public long getPos() {
            return pos;
        }

        public void setPos(long pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "TimeMeter [action=" + action + ", expiredTime=" + getExpiredTime() + ", startTime=" + startTime
                    + ", endTime=" + endTime + ", pos=" + pos + "]";
        }

    }

    public static void main(String[] args) {
        ThreadLocalHelper.getInstance().init();
        ThreadLocalHelper.getInstance().addAction("test1");
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        ThreadLocalHelper.getInstance().addAction("test2");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        ThreadLocalHelper.getInstance().addAction("test3");
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        ThreadLocalHelper.getInstance().addAction("test4");

        System.out.println("expired time :" + ThreadLocalHelper.getInstance().getExpiredTime());
        System.out.println(ThreadLocalHelper.getInstance().toString());

        for (int i = 0; i < 220; i++) {
            ThreadLocalHelper.getInstance().addAction("new" + i);
            try {
                Thread.sleep(i);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ThreadLocalHelper.getInstance().toString());

        ThreadLocalHelper.getInstance().clear();
        System.out.println(ThreadLocalHelper.getInstance().toString());
    }
}

