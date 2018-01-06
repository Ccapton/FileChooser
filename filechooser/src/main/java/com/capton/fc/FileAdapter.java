package com.capton.fc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by capton on 2018/1/6.
 */

public class FileAdapter extends CommonAdapter <FileInfo> {
    public FileAdapter(Context context, ArrayList<FileInfo> dataList, int resId) {
        super(context, dataList, resId);
      }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, FileInfo data, int position) {
        TextView textView = holder.itemView.findViewById(R.id.fileName);
        textView.setText(data.getFileName());
        ImageView imageView = holder.itemView.findViewById(R.id.fileIcon);
        View divider = holder.itemView.findViewById(R.id.divider);
        if(FileInfo.FILE_TYPE_VIDEO.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_slow_motion_video_grey);
        }else if(FileInfo.FILE_TYPE_AUDIO.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_music_video_grey);
        }else if(FileInfo.FILE_TYPE_APK.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_insert_drive_file_grey);
        }else if(FileInfo.FILE_TYPE_ZIP.equals(data.getFileType())||FileInfo.FILE_TYPE_RAR.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_compress_file);
        }else if(FileInfo.FILE_TYPE_JPEG.equals(data.getFileType())
                ||FileInfo.FILE_TYPE_JPG.equals(data.getFileType())
                ||FileInfo.FILE_TYPE_PNG.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_image_grey);
        }else if(FileInfo.FILE_TYPE_FOLDER.equals(data.getFileType())){
            imageView.setImageResource(R.drawable.ic_folder_open_grey);
        }else{
            imageView.setImageResource(R.drawable.ic_insert_drive_file_grey);
        }
        if(position != dataList.size()-1)
            divider.setVisibility(View.VISIBLE);
         else
            divider.setVisibility(View.GONE);
    }
}
