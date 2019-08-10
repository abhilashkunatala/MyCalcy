package com.example.mycalcy;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

import java.lang.reflect.Parameter;
import java.util.Iterator;

// first add the .jar in gradle (module app)

public class ExtendedDoubleEvaluator extends DoubleEvaluator {

    private static final Function SQRT = new Function("sqrt",1);


    private static  final Parameters PARMS;
    static
    {
        PARMS = DoubleEvaluator.getDefaultParameters();
        PARMS.add(SQRT);
    }

    public ExtendedDoubleEvaluator(){
        super();

    }

    @Override
    protected Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext) {

        if(function==SQRT){
            return Math.sqrt(arguments.next());
        }
        else
        {
            return super.evaluate(function, arguments, evaluationContext);

        }




    }



}
