![translucent](https://github.com/mallethugo/translucent-android/blob/master/translucent.png)

![translucent](https://github.com/mallethugo/translucent/blob/master/demo.gif)
===
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Translucent-green.svg?style=true)](https://android-arsenal.com/details/1/3427) <a href="http://www.methodscount.com/?lib=mallethugo-translucent%3Atranslucentlib%3A1.0.0"><img src="https://img.shields.io/badge/Methods and size-core: 49 | deps: 15068 | 48 KB-e91e63.svg"></img></a>

Translucent draw a transparent text into a Textview

You can get similar effect on iOS platform [here](https://github.com/ekhoo/translucid)

===
# Usage

```xml
<com.mallethugo.translucent.TranslucentView
        android:id="@+id/main_translucent"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:text="@string/translucent"
        android:textSize="85sp"
        android:textStyle="bold"
        android:fontFamily="sans-serif-condensed"
        app:translucentBackgroundColor="#000000" />
```

## Custom Attributes for TranslucentView

- `app:translucentBackgroundColor`

Be carefull if you set android:backgroundColor the "transparent" text will be this color 
## Gradle

At your project `build.gradle` file:

```groovy
dependencies {
    compile 'mallethugo-translucent:translucentlib:1.0.0'
}
```

Done!

#Author
Hugo Mallet:
- hello@mallethugo.com
- [@mallethugo](https://twitter.com/mallethugo)
- [Linkedin](https://www.linkedin.com/in/hugomallet)

# License

The MIT License (MIT)

Copyright (c) 2015 Hugo Mallet

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
