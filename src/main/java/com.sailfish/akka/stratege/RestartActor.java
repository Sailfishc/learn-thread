package com.sailfish.akka.stratege;

import java.util.Optional;

import akka.actor.UntypedAbstractActor;

/**
 * @author sailfish
 * @create 2017-05-25-上午8:03
 */
public class RestartActor extends UntypedAbstractActor {

    public enum Msg{
        DONE, RESTART;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("prestart hashcode" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("poststop hashcode" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        System.out.println("prerestart hashcode" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postrestart hashcode" + this.hashCode());
    }

    @Override
    public void onReceive(Object msg) throws Throwable {

        if (msg == Msg.DONE) {
            getContext().stop(getSelf());
        } else if (msg == Msg.RESTART) {
            //  nullPointException
            System.out.println(((Object) null).toString());

            //  抛出异常，默认会被restart，这里会被resume
            double a = 0 / 0;

        }
        unhandled(msg);
    }
}
