package com.byd.dockerplugindemo.elevator.dump;

/**
 * 怎么把电梯放在独立的线程中去执行？
 * 由谁去放？Manager还是电梯自己？
 * 直接使用线程池实现生产消费模式
 *
 * 为什么使用生产者消费者模式，而不是为什么使用消息队列。
 * 分解
 * - 功能上分解（解耦）->分阶段->相互协作
 * - 性能上分解（慢的/快的）
 *   - 批量执行（生产速度小于消费速度）。如入库。
 *   - 延迟执行（生产速度大于消费速度）。处理不过来，慢慢消费
 * 分解是普遍的，所以生产消费模式广泛运用
 *
 * 抽象1
 * 生产者和消费者运行在各自的线程里，因此他们都是Runnable。
 * 一个Runnable是一个Task，执行完了就没有了，所以需要死循环 + 退出条件。
 * 每一个Runnable都需要一个线程池执行。
 * run()方法里面实现的是生产策略和消费策略。
 *
 * 抽象2
 * Queue里面装的是个Runnable。生产和消费者都是线程。
 *
 * 抽象3
 * 在使用线程池的场景下，只需要生产者不断提交任务即可。不需要显式创建队列。
 * 但是线程池中的线程每次只能从任务队列中消费一个任务来执行，
 * 对于大部分并发场景这种策略都没有问题。
 * 但是有些场景还是需要自己来实现，例如需要批量执行以及分阶段提交的场景。
 * https://segmentfault.com/a/1190000019769530
 *
 * 生产者和消费者都要持有线程安全的公共队列？
 * 不一定。一方持有也可以，另一方通过API进行访问。
 */
public class Elevator {
    private ReqQueue reqQueue;

    public Elevator(ReqQueue reqQueue) {
        this.reqQueue = reqQueue;
    }

}