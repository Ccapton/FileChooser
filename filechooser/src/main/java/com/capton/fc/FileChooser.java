package com.capton.fc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by capton on 2018/1/5.
 */

public class FileChooser {

     private Context mContext;
     private int themeColorRes = R.color.themeColor;
     private FileChoosenListener fileChoosenListener;
     private String mChoosenFilePath = "";
     private String title = "选择目标";
     private String doneText = "完成";
     private int backIconRes = R.drawable.back_white;
     private boolean showFile = true;

     public boolean isFileShow() {
          return showFile;
     }

     public FileChooser showFile(boolean showFile) {
          this.showFile = showFile;
          return this;
     }

     public FileChooser setCurrentPath(String currentPath){
          this.mChoosenFilePath = currentPath;
          return this;
     }
     public FileChooser setTitle(String title){
          this.title = title;
          return this;
     }

     public FileChooser setDoneText(String doneText) {
          this.doneText = doneText;
          return this;
     }

     public FileChooser setBackIconRes(int backIconRes) {
          this.backIconRes = backIconRes;
          return this;
     }

     public FileChooser(Fragment fragment , FileChoosenListener fileChoosenListener) {
          this.mContext = fragment.getContext();
          this.fileChoosenListener = fileChoosenListener;
          this.themeColorRes = R.color.themeColor;
     }
     public FileChooser(Activity activity ,FileChoosenListener fileChoosenListener) {
          this.mContext = activity;
          this.fileChoosenListener = fileChoosenListener;
          this.themeColorRes = R.color.themeColor;
     }
     public void open(){
          FileChooserActivity.mFileChooser = this;
          Intent intent = new Intent(mContext,FileChooserActivity.class);
          intent.putExtra("themeColorRes",this.themeColorRes);
          intent.putExtra("currentPath",this.mChoosenFilePath);
          intent.putExtra("title",this.title);
          intent.putExtra("doneText",this.doneText);
          intent.putExtra("backIconRes",this.backIconRes);
          intent.putExtra("showFile",this.showFile);
          this.mContext.startActivity(intent);
     }

     protected void finish(String filePath){
           if(fileChoosenListener != null)
                fileChoosenListener.onFileChoosen(filePath);
     }
     public FileChooser setThemeColor(int themeColorRes){
          this.themeColorRes = themeColorRes;
          return this;
     }

     public FileChooser setFileChoosenListener(FileChoosenListener fileChoosenListener) {
          this.fileChoosenListener = fileChoosenListener;
          return this;
     }

     public interface FileChoosenListener{
          void onFileChoosen(String filePath);
     }
}
