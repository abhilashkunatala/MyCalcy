 package com.example.mycalcy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fathzer.soft.javaluator.DoubleEvaluator;

 public class MainActivity extends AppCompatActivity {

    EditText e1;
         EditText e2;
    int count= 0;
    String expression = "null";
    String text="";
    Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1= (EditText)findViewById(R.id.editText1);
        e2= (EditText)findViewById(R.id.editText2);
        e1.setText("1");
        e2.setText("0");
        //initial display

    }


    public  void  onClick(View v){

        switch (v.getId()){

            case R.id.num0:
                e2.setText(e2.getText()+"0");
                break;

            case R.id.num1:
                e2.setText(e2.getText()+"1");
                break;

            case R.id.num2:
                e2.setText(e2.getText()+"2");
                break;

            case R.id.num3:
                e2.setText(e2.getText()+"3");
                break;

            case R.id.num4:
                e2.setText(e2.getText()+"4");
                break;



            case R.id.num5:
                e2.setText(e2.getText()+"5");
                break;

            case R.id.num6:
                e2.setText(e2.getText()+"6");
                break;

            case R.id.num7:
                e2.setText(e2.getText()+"7");
                break;

            case R.id.num8:
                e2.setText(e2.getText()+"8");
                break;

            case R.id.num9:
                e2.setText(e2.getText()+"9");
                break;


            case R.id.dot:// for dot functionality
                if(count==0 && e2.length()!=0) {
                    e2.setText(e2.getText() + ".");
                    count++;
                }
                break;

            case R.id.clear:
                e2.setText(e2.getText()+"");
                e2.setText(e1.getText()+"");
                count=0;
                expression="";// in starting we defined the expression as null
                break;

            case R.id.backSpace:

                text = e2.getText().toString();
                if(text.length()>0){
                    if(text.endsWith("0")){
                        count = 0;
                    }
                    String newText = text.substring(0, text.length() - 1);
                    if(text.endsWith(")")){
                        char []a = text.toCharArray();
                        int pos = a.length-2;
                        int counter = 1;
                        for(int i =a.length-1;i>=0;i--){
                            if(a[i]==')')
                                counter++;

                            else
                                if (a[i]== '('){
                                    counter--;
                                }

                                else
                                    if(a[i]=='.'){
                                        count=0;
                                    }

                                    else if(counter==0){
                                        pos=i;
                                        break;
                                    }
                        }
                        newText.substring(0,pos);

                    }

                    if(newText.equals("-")|| newText.endsWith("srt")) {
                        newText = " ";
                    } else if (newText.endsWith("^")){
                        newText= newText.substring(0,newText.length()-1);
                    }
                    e2.setText(newText);
                }


                break;


            case R.id.plus:
               OperationClicked("+");
                break;
            case R.id.minus:
                OperationClicked("-");
                break;
            case R.id.divide:
                OperationClicked("/");
                break;
            case R.id.multiply:
                OperationClicked("*");
                break;

            case R.id.sqrt:
                if(e2.length()!=0)
                {
                    text= e2.getText().toString();

                    e2.setText("sqrt("+text+")");
                }

                break;

            case R.id.square:

                if(e2.length()!=0)
                {
                    text= e2.getText().toString();

                    e2.setText("("+text+")^2");
                }


                break;


            case R.id.posneg://postive negative
                if(e2.length()!=0){
                    String s = e2.getText().toString();
                    char arr[]= s.toCharArray();
                    if (arr[0] == '-') {

                        e2.setText(s.substring(1,s.length()));
                    }
                    else
                    {
                        e2.setText("-"+s);
                    }
                }
            case R.id.equal:// this method also include when we wnter the 2 nd letter..the first letter and operator
                //goes to above edit text
                if(e2.length()!=0){
                    text = e2.getText().toString();
                    expression = e1.getText().toString()+text;
                }
              //  e1.setText("");
                if(expression.length()==0){
                    expression="0.0";
                }

                DoubleEvaluator evaluator = new DoubleEvaluator();
                try{
                    result = new ExtendedDoubleEvaluator().evaluate(expression);

                    if(!evaluator.equals("0.0")) {
                        e2.setText(result +"");
                    }
                }

                catch(Exception e)
            {
                e2.setText("Invalid expresson ");
                e1.setText("");
                expression="";
                e.printStackTrace();

            }
                break;
            case R.id.openBracket:
                e2.setText(e2.getText()+"(");
                break;
            case R.id.closeBracket:
                e2.setText(e2.getText()+")");
                break;






        }

    }


     private void OperationClicked(String s) {


        if(e2.length()!=0){
            String text = e2.getText().toString();
            e2.setText(e1.getText()+text+s);// s  is the operaion +*-*
            e1.setText("");
        count=0;
        }
        else {
                String text  = e1.getText().toString();

                if(text.length()>0){
                    String newText = text.substring(0,text.length()-1)+s;
                    e1.setText(newText);
                }

        }
     }
 }
