package work.pangbai.r2toolm.tool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import work.pangbai.r2toolm.r2.R2;

public class ThreadUtil {


    // 创建一个新的线程并启动它

    public static void thread(Runnable task) {

        Thread thread = new Thread(task);

        thread.start();

    }


    // 线程睡眠

    public static void sleep(long millis) {

        try {

            Thread.sleep(millis);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt(); // 重新设置中断状态


            throw new RuntimeException(e);

        }

    }


    // 线程中断

    public static void interrupt(Thread thread) {

        if (thread != null) {

            thread.interrupt();

        }

    }


    // 检查线程是否被中断

    public static boolean isInterrupted(Thread thread) {

        return thread != null && thread.isInterrupted();

    }


    public static ThreadPoolExecutor executor(Runnable runnable){
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,1, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        executor.execute(runnable);
        return executor;
    }


    // 等待线程结束


}