package org.cx.thread.java7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskDemo extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 2; //阈值
    private int start;
    private int end;

    public RecursiveTaskDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean cancompute = (end - start) <= THRESHOLD;

        if (cancompute) {
            for (int i = start; i <= end; i++) {
                sum +=i;
            }
        } else {
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            RecursiveTaskDemo leftTask = new RecursiveTaskDemo(start, middle);
            RecursiveTaskDemo rightTask = new RecursiveTaskDemo(middle+1, end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等子任务执行完，并得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //合并子任务
            sum = leftResult + rightResult;

        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务，计算1+2+3+4
        RecursiveTaskDemo task = new RecursiveTaskDemo(1, 4);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
