# Keep all classes in the package com.nurazlib.tictactoe
-keep class com.nurazlib.tictactoe.** { *; }

# Keep all methods and fields in the specified classes
-keepclassmembers class com.nurazlib.tictactoe.MainActivity {
    public static void main(java.lang.String[]);
}

# Keep all annotations
-keep @interface * 

# Keep all public methods and fields from being obfuscated
-keep public class * {
    public protected *;
}
