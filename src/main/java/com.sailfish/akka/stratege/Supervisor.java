package com.sailfish.akka.stratege;

import java.util.concurrent.TimeUnit;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * 监督者
 * @author sailfish
 * @create 2017-05-25-上午7:40
 */
public class Supervisor extends UntypedAbstractActor{

    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.SECONDS),
            new Function<Throwable, SupervisorStrategy.Directive>() {
                @Override
                public SupervisorStrategy.Directive apply(Throwable t) throws Exception {
                    if (t instanceof ArithmeticException) {
                        System.out.println("ArithmeticException, just resume");
                        return SupervisorStrategy.resume();
                    } else if (t instanceof NullPointerException) {

                        System.out.println("NullPointerException, restart");
                        return SupervisorStrategy.restart();
                    } else if (t instanceof IllegalArgumentException) {

                        return SupervisorStrategy.stop();
                    } else {
                        return SupervisorStrategy.escalate();
                    }
                }
            });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Props) {
            getContext().actorOf((Props) o, "restartActor");

        } else {
            unhandled(o);
        }
    }
}
