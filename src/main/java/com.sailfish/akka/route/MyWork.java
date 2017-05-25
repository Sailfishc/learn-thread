package com.sailfish.akka.route;

import akka.actor.UntypedAbstractActor;

/**
 * @author sailfish
 * @create 2017-05-25-上午9:50
 */
public class MyWork extends UntypedAbstractActor {

    public enum Msg{
        WORKING, DONE, CLOSE;
    }
    @Override
    public void preStart() throws Exception {
        System.out.println("Myword is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("my work is stopping");
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Msg.WORKING) {

            System.out.println("i am working");
        } else if (o == Msg.DONE) {

            System.out.println("i am stoped");
        } else if (o == Msg.CLOSE) {

            System.out.println("i will shutdown");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
