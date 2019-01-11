# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep public class org.apache.** {*;}
-keep public class org.w3c.** {*;}
-keep public class java.awt.** {*;}
-keep public class fr.opensagres.** {*;}
-keep public class javax.xml.** {*;}
-dontwarn org.apache.**
-dontwarn java.awt.**
-dontwarn org.w3c.**
-dontwarn fr.opensagres.**
-dontwarn javax.xml.**
#-dontwarn public class org.w3c.dom.** {*;}
#-dontwarn org.dom4j.**
#-dontwarn org.bouncycastle.**
#-dontwarn org.apache.commons.logging.impl.**

#-keep class com.ObjBlockCipherParam{ *; }
#-keep class org.apache.commons.logging.impl.**{*;}

