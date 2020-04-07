# LoadingIndicatorView

How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

    gradle
    maven
    sbt
    leiningen

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.nguyenkhiemit:LoadingIndicatorView:Tag'
	}

Usage

...

    <com.nguyen.loadingindicator.LoadingView
      android:id="@+id/loadingView"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:loading_stroke="2dp"/>
     
...
       
