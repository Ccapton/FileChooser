package com.capton.fc;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by capton on 2018/1/4.
 */

public class BottomDialog {

    private View contentView;
    private Dialog dialog;

    private BottomDialog() {
    }

    public View getContentView() {
        return contentView;
    }

    public void setCancelable(boolean cancelable){
        dialog.setCancelable(cancelable);
    }
    public void setCanceledOnTouchOutside(boolean cancel){
        dialog.setCanceledOnTouchOutside(cancel);
    }

    public static class Builder{
        private LinearLayout bottomLayout;
        private View contentView;
        private Dialog dialog;
        private boolean hasAnimation = true;
        private Context context;
        private int layoutId;

        public Builder(Context context) {
             this.context = context;
             bottomLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_bottomdialog,null);
        }
        public Builder setContentView(int layoutId){
            this.layoutId = layoutId;
            this.contentView = LayoutInflater.from(context).inflate(this.layoutId,bottomLayout);
            return Builder.this;
        }
        public Builder setContentView(View contentView){
            this.contentView = contentView;
            this.bottomLayout.addView(contentView);
            return Builder.this;
         }
        public Builder setHasAnimation(boolean hasAnimation){
            this.hasAnimation  = hasAnimation;
            return Builder.this;
        }

        public BottomDialog create(){

            BottomDialog bottomDialog = new BottomDialog();

            dialog = new Dialog(context,R.style.BottomDialog);
            contentView.measure(0, 0);
            bottomLayout.measure(0,0);
            dialog.setContentView(bottomLayout);

            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);

            if(hasAnimation)
            dialogWindow.setWindowAnimations(R.style.DialogAnimation);

            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = (int) context.getResources().getDisplayMetrics().widthPixels;
            lp.height = bottomLayout.getMeasuredHeight();
            Log.i("BottomDialog","width = "+lp.width);
            Log.i("BottomDialog","height = "+lp.height);
            lp.alpha = 9f; // 透明度
            dialogWindow.setAttributes(lp);

            dialog.show();

            bottomDialog.dialog = this.dialog;
            bottomDialog.contentView = this.contentView;

            return bottomDialog;
        }
    }

    public void show(){
         dialog.show();
    }

    public void dismiss(){
         dialog.dismiss();
    }
}
