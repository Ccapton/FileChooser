# FileChooser
Android文件(路径)选择器，超简单配置 :fire:
## 效果
![](https://raw.githubusercontent.com/Ccapton/FileChooser/master/filechooser2.png)
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
	        compile 'com.github.Ccapton:FileChooser:1.0.1'
   }
```
**重要配置**
如果你的项目没有配置databinding和vectorDrawables
请在下图的位置加上这两句代码
        **vectorDrawables.useSupportLibrary = true**
        **dataBinding {
                enabled true
            }
         **
![](https://raw.githubusercontent.com/Ccapton/FileChooser/master/gradle_setting.jpg)

## 使用方法
示例代码:
```
FileChooser fileChooser = new FileChooser(MainActivity.this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        ((TextView)findViewById(R.id.hello)).setText(filePath);
                    }
                });
		/*
		* 1.默认配置
		*/
		fileChooser.open();
		
		/*
		* 2.自定义配置
		*/
                 fileChooser.setThemeColor(R.color.colorPrimary)
                        .setTitle("选择目录")
                         .setCurrentPath("/storage/emulated/0/Android/data/com.tencent.mm/files")
                         .setBackIconRes(R.drawable.back_white)
                         .setDoneText("OK")
                         .showFile(false)
                         .open();
```
