# FileChooser
Android文件(路径)选择器，超简单配置 :fire:
## 效果
![](https://raw.githubusercontent.com/Ccapton/FileChooser/master/filechooser.png) 
![](https://raw.githubusercontent.com/Ccapton/FileChooser/master/filechooser.gif) 

## gradle引入
build.gradle(Project)
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
build.gradle(Module:app)
```
dependencies {
	        compile 'com.github.Ccapton:FileChooser:1.0.0'
   }
```
## 使用方法
示例代码:
```
FileChooser fileChooser = new FileChooser(MainActivity.this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        ((TextView)findViewById(R.id.hello)).setText(filePath);
                    }
                });
                 fileChooser.setThemeColor(R.color.colorPrimary)
                        .setTitle("选择目录")
                         .setCurrentPath("/storage/emulated/0/Android/data/com.tencent.mm/files")
                         .setBackIconRes(R.drawable.back_white)
                         .setDoneText("OK")
                         .showFile(false)
                         .open();
```
