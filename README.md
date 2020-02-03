# Meow Bottom Navigation For Java Projects without androidx  
A simple & curved & material bottom navigation for Android written in kotlin.  
  
![](https://github.com/shetmobile/MeowBottomNavigation/raw/master/resources/Preview.gif)  
  
> CAUTION : if you are **Kotlin** developer, so check this link: https://github.com/shetmobile/MeowBottomNavigation
  
## Download  
build.gradle (project path)  
```groovy  
buildscript {  
 repositories { jcenter() }}  
```  
build.gradle (module path)  
```groovy  
dependencies {  
 implementation 'com.etebarian:meow-bottom-navigation-java:1.2.0'}  
```  
  
## Usage  
Add Meow Bottom Navigation in xml  
```xml  
<com.etebarian.meowbottomnavigation.MeowBottomNavigation  
	 android:layout_width="match_parent"
	 android:layout_height="wrap_content" />
 ``` 
  
Add menu items in code.  
```java
bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));  
bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_explore));  
bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_message));
```
Remember that icons must be vector drawable.   
Add vectorDrawables.useSupportLibrary = true to your build.gradle inside defaultConfig{ ... }  
  
## Customization  
```xml  
<com.etebarian.meowbottomnavigation.MeowBottomNavigation  
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	app:mbn_circleColor="#ffffff"
	app:mbn_backgroundBottomColor="#ffffff"
	app:mbn_countBackgroundColor="#ff6f00"
	app:mbn_countTextColor="#ffffff"
	app:mbn_countTypeface="fonts/SourceSansPro-Regular.ttf"
	app:mbn_defaultIconColor="#90a4ae"
	app:mbn_rippleColor="#2f424242"
	app:mbn_selectedIconColor="#3c415e"
	app:mbn_shadowColor="#1f212121"/>
```  
- You can change this properties in **Java** Realtime ⌚.   
  
## Listeners  
```java  
bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {  
    @Override  
  public void onClickItem(MeowBottomNavigation.Model item) {  
        // your codes
  }  
});  
  
bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {  
    @Override  
  public void onShowItem(MeowBottomNavigation.Model item) {  
        // your codes
  }  
});  
  
bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {  
    @Override  
  public void onReselectItem(MeowBottomNavigation.Model item) {  
        // your codes
  }  
});
```
  
## Counter Badge  
Setting One Tab  
```java  
bottomNavigation.setCount(TAB_ID, STRING)  
```  
  
Clearing One Tab  
```java
bottomNavigation.clearCount(TAB_ID)  
```  
  
Clearing All Tabs  
```java
bottomNavigation.clearAllCounts(TAB_ID)  
```  
  
## Set Default Tab  
Use this function  
```java
bottomNavigation.show(TAB_ID)  
```