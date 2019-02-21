package com.example.wangyan.movejsonfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MainActivity extends AppCompatActivity {

    File sdCardDir = Environment.getExternalStorageDirectory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(android.view.View view){
        newDirectory(sdCardDir.toString(), "TestDir");//首先先建一个TestDir文件夹
        Date date = new Date();//这里目的是将当前时间转换为字符串，然后为后面建立文件夹
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(date);
        moveFileFolder(sdCardDir.toString()+"/ThinkTime", now);//把原先的文件夹里面的所有
        //文件移动到新的文件夹中去
    }

    public void newDirectory(String _path, String dirName){//新建一个文件夹的函数
        File file = new File(_path + "/" + dirName);
        try{
            if(!file.exists()){
                file.mkdir();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void moveFileFolder(String oldPath, String newPath){
        newDirectory(sdCardDir.toString() + "/" + "TestDir", newPath);//先新建一个文件夹，
        //待会把文件移动到这个新建的文件夹
        try{
            File baseFile = new File(oldPath);//这里可以填写文件路径，而不只是具体的某个文件
            File[] fileList = baseFile.listFiles();//把文件下的所有文件都放到list列表中去
            for (int i = 0; i < fileList.length; i++){
                //下面一条是移动函数代码
                if( fileList[i].renameTo(new File(sdCardDir.toString() + "/TestDir" + "/" + newPath + "/" + fileList[i].getName()))){
                    System.out.println("move successful.");
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
