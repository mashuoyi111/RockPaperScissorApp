package com.example.myapp1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */


    //0 means have not pick, 1 means rock, 2 means paper, 3 means scissor
    Integer selection=0;
    Integer bet=0;
    Integer currentMoney=100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void pickRock(View view){
        TextView t=(TextView) findViewById(R.id.textView2);
        String s="You Choose Rock!";
        t.setText(s);
        selection=1;
    }

    public void pickPaper(View view){
        TextView t=(TextView) findViewById(R.id.textView2);
        String s="You Choose Paper!";
        t.setText(s);
        selection=2;
    }

    public void pickScissor(View view){
        TextView t=(TextView) findViewById(R.id.textView2);
        String s="You Choose Scissor!";
        t.setText(s);
        selection=3;
    }


    public void helpPop(View view){
        new AlertDialog.Builder(this).setPositiveButton("got it",null).setTitle("Game created by Max Ma, UBC").setMessage("Enter your bet, press ''set bet'' and then choose rock, paper or scissor. Then press ''Let's go'' to play!").show();
    }

    public void setBet(View view){
        EditText e=(EditText) findViewById(R.id.editText);
        TextView t=(TextView) findViewById(R.id.textView2);
        String s="";
        if(e.getText().length()==0){
            s="Please make a bet!";
            t.setText(s);
            return;
        }
        Integer bet=Integer.parseInt(e.getText().toString());

        if(bet<=0){
            s="You cannot set 0 bet";
            t.setText(s);
            return;
        }
        if(bet>currentMoney){
            s="You don't have that much money";
            t.setText(s);
            return;
        }
        s="You set a "+e.getText().toString()+"$ bet!";
        this.bet=bet;
        t.setText(s);
        e.setText("");
    }

    public void playGame(View view){
        TextView t=(TextView) findViewById(R.id.textView2);
        TextView m=(TextView) findViewById(R.id.textViewMoney);
        String s="";
        if(selection==0){
            s="You Must choose one to play!";
            t.setText(s);
            return;
        }
        if(bet<=0||bet>currentMoney){
            s="You Must set your bet correctly!";
            t.setText(s);
            return;
        }
        Integer rand=1+ (int)(Math.random()*3);
        System.out.println(rand);
        switch (rand){
            case 1: switch (selection){
                case 1:s="Opponent choose Rock, Draw!";break;
                case 2:s="Opponent choose Rock, You WIN!";currentMoney+=bet;break;
                case 3:s="Opponent choose Rock, You LOSE!";currentMoney-=bet;break;
            }break;
            case 2:switch (selection){
                case 1:s="Opponent choose Paper, You LOSE!";currentMoney-=bet;break;
                case 2:s="Opponent choose Paper, Draw!";break;
                case 3:s="Opponent choose Paper, You WIN!";currentMoney+=bet;break;
            }break;
            case 3:switch (selection){
                case 1:s="Opponent choose Scissor, You WIN!";currentMoney+=bet;break;
                case 2:s="Opponent choose Scissor, You LOSE!";currentMoney-=bet;break;
                case 3:s="Opponent choose Scissor, Draw!";break;
            }break;
        }
        t.setText(s);
        selection=0;
        bet=0;
        m.setText(currentMoney.toString());
        if(currentMoney==0){
            currentMoney=100;
            m.setText(currentMoney.toString());
            new AlertDialog.Builder(this).setMessage("GAME OVER! here is another 100$ for you").show();
        }
    }
}
