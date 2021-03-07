package com.byd.dockerplugindemo.elevator.dump;

import java.util.concurrent.BlockingQueue;

public class ReqQueue {
    private BlockingQueue<Request> queue;

    public BlockingQueue<Request> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Request> queue) {
        this.queue = queue;
    }
}
