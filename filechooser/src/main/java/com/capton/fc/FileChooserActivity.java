package com.capton.fc;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;

import com.blankj.utilcode.util.Utils;
import com.capton.fc.databinding.ActivityFileChooserBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileChooserActivity extends BaseActivity<ActivityFileChooserBinding> {

    private int themeColorRes;
    private int backIconRes;
    private boolean showFile = true;
    public static FileChooser mFileChooser;
    private String mChoosenFilePath;

    private FileTourController tourController;
    private FileAdapter adapter;
    private CurrentFileAdapter currentFileAdapter;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.init(getApplication());

        resetThemeColor();
        resetBackIconRes();
        setMiddleTitle();
        setDoneText();
        setShowRightText(true);

        baseBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooserActivity.this.finish();
            }
        });

     }

     private void setMiddleTitle(){
         String middleTitle = getIntent().getStringExtra("title");
         setTitle(middleTitle);
     }
    private void setDoneText(){
        String doneText = getIntent().getStringExtra("doneText");
        setRightText(doneText);
    }
    private void resetThemeColor() {
        this.themeColorRes = getIntent().getIntExtra("themeColorRes",R.color.themeColor);
        setThemeColor(themeColorRes);
     }
     private void resetBackIconRes(){
        this.backIconRes = getIntent().getIntExtra("backIconRes",R.drawable.back_white);
         setBackIcon(backIconRes);
     }

    @Override
    public String[] getPermissions() {
        return requestPermissions;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_file_chooser;
    }

    @Override
    public void setClickListener() {
        this.showFile = getIntent().getBooleanExtra("showFile",true);
        this.mChoosenFilePath =getIntent().getStringExtra("currentPath");

        tourController = new FileTourController(this,mChoosenFilePath);
        tourController.setShowFile(this.showFile);

        adapter = new FileAdapter(this, (ArrayList<FileInfo>) tourController.getCurrenFileInfoList(),R.layout.item_file);
        binding.fileRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.fileRv.setAdapter(adapter);

        currentFileAdapter = new CurrentFileAdapter(this, (ArrayList<File>) tourController.getCurrentFolderList(),R.layout.item_current_file);
        binding.currentPath.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.currentPath.setAdapter(currentFileAdapter);
        binding.currentPath.scrollToPosition(tourController.getCurrentFolderList().size()-1);

        adapter.setItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                File selectFile =new File(tourController.getCurrenFileInfoList().get(position).getFilePath());
                ArrayList<FileInfo> childFileInfoList = (ArrayList<FileInfo>) tourController.addCurrentFile(selectFile);
                adapter.setData(childFileInfoList);
                adapter.notifyDataSetChanged();

                currentFileAdapter.setData(tourController.getCurrentFolderList());
                currentFileAdapter.notifyDataSetChanged();

                binding.currentPath.scrollToPosition(tourController.getCurrentFolderList().size()-1);
             }
        });

        currentFileAdapter.setItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<FileInfo> fileInfoList = tourController.resetCurrentFile(position);
                adapter.setData(fileInfoList);
                adapter.notifyDataSetChanged();

                currentFileAdapter.setData(tourController.getCurrentFolderList());
                currentFileAdapter.notifyDataSetChanged();

                binding.currentPath.scrollToPosition(tourController.getCurrentFolderList().size()-1);
            }
        });

        binding.switchSdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListPopupWindow listPopupWindow= new ListPopupWindow(FileChooserActivity.this);
                listPopupWindow.setAnchorView(v);

                ArrayList<String> sdcardList = new ArrayList<>();
                   sdcardList.add("手机存储");
                if(FileTourController.getStoragePath(FileChooserActivity.this,true) != null)
                    sdcardList.add("SD卡");
                SdCardAdapter sdCardAdapter = new SdCardAdapter(FileChooserActivity.this,sdcardList);
                listPopupWindow.setAdapter(sdCardAdapter);
                listPopupWindow.setWidth(sdCardAdapter.getItemViewWidth());
                //listPopupWindow.setAdapter(new ArrayAdapter<String>(FileChooserActivity.this,android.R.layout.simple_list_item_1,sdcards));
                listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(tourController!=null)
                            tourController.switchSdCard(position);
                        if(adapter!=null) {
                            adapter.setData(tourController.getCurrenFileInfoList());
                            adapter.notifyDataSetChanged();
                        }
                        if(currentFileAdapter!=null) {
                            currentFileAdapter.setData(tourController.getCurrentFolderList());
                            currentFileAdapter.notifyDataSetChanged();
                        }
                        listPopupWindow.dismiss();
                    }
                });
                listPopupWindow.show();

            }
        });
     }

    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {
         if(tourController != null)
        mChoosenFilePath = tourController.getCurrentFile().getAbsolutePath();
         if(this.mFileChooser != null)
             mFileChooser.finish(mChoosenFilePath);
         finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFileChooser = null;
        System.out.println("FileChooserActivity.onDestroy");
     }

    @Override
    public void onBackPressed() {
        if(!tourController.isRootFile()) {

            List<FileInfo> currentList = tourController.backToParent();
            adapter.setData(currentList);
            adapter.notifyDataSetChanged();

            currentFileAdapter.setData(tourController.getCurrentFolderList());
            currentFileAdapter.notifyDataSetChanged();

        } else {
            super.onBackPressed();
        }
    }
}
