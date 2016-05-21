package com.gurmandeep.canvasjspaint;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PaintPadView hbView;

    private AlertDialog dialog;
    private View dialogView;
    private TextView shouWidth;
    private SeekBar widthSb;
    private int paintWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        dialogView = getLayoutInflater().inflate(R.layout.activity_dialog__width_set, null);
        shouWidth = (TextView) dialogView.findViewById(R.id.textView1);
        widthSb = (SeekBar) dialogView.findViewById(R.id.seekBar1);
        widthSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                shouWidth.setText("Current Width："+(progress+1));
                paintWidth = progress+1;
            }
        });
        hbView = (PaintPadView)findViewById(R.id.drawPadView1);
        dialog = new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info).setTitle("Set the size of your Pen").
                setView(dialogView).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                hbView.setPaintWidth(paintWidth);
            }
        }).setNegativeButton("Cancel", null).create();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu colorSm = menu.addSubMenu(1, 1, 1, "Select Pen color");
        colorSm.add(2, 200, 200, "red");
        colorSm.add(2, 210, 210, "green");
        colorSm.add(2, 220, 220, "blue");
        colorSm.add(2, 230, 230, "purple");
        colorSm.add(2, 240, 240, "yellow");
        colorSm.add(2, 250, 250, "black");
        menu.add(1, 2, 2, "Set pen size");
        SubMenu widthSm = menu.addSubMenu(1, 3, 3, "Set Pen style");
        widthSm.add(3, 300, 300, "Stoke");
        widthSm.add(3, 301, 301, "Fill ");
        menu.add(1, 4, 4, "Clear Drawing");
        menu.add(1, 5, 5, "Save drawing");
        menu.add(1, 6, 6, "Exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int index = item.getItemId();
        switch(index){
            case 200:
                hbView.setColor(Color.RED);
                break;
            case 210:
                hbView.setColor(Color.GREEN);
                break;
            case 220:
                hbView.setColor(Color.BLUE);
                break;
            case 230:
                hbView.setColor(Color.MAGENTA);
                break;
            case 240:
                hbView.setColor(Color.YELLOW);
                break;
            case 250:
                hbView.setColor(Color.BLACK);
                break;
            case 2:
                dialog.show();
                break;
            case 300:
                hbView.setStyle(PaintPadView.PEN);
                break;
            case 301:
                hbView.setStyle(PaintPadView.PAIL);
                break;
            case 4:
                hbView.clearScreen();
                break;
            case 5:
                if(SaveViewUtil.saveScreen(hbView)){
                    Toast.makeText(this, "Drawing Saved Successfully!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Save drawing fail. Please check your SD card", Toast.LENGTH_LONG).show();
                }
                break;
            case 6:
                finish();
                break;
        }
        return true;
    }
}
