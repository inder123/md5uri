# md5uri
Utility to shorten an MD5 value for use as a URL parameter

Examples:
```
Md5Uri.fromMd5("9e107d9d372bb6826bd81d3542a419d6") ==>  YrLNM591-5JhnaiKpxHrj1
Md5Uri.toMd5("kqrflQyUl9-0OeBg1S6") ==>  0000bdd3bb56865852a632deadbc62fc
```
Note that toMd5 method appends leading zeros to ensure it is a valid 32 character MD5 string.

The conversion is two way, so you will get the original string back if you convert it back to MD5.
```
toMd5(fromMd5("9e107d9d372bb6826bd81d3542a419d6"))  ==>  9e107d9d372bb6826bd81d3542a419d6
fromMd5(toMd5("cbIKGiMVkLFTeenAa5kgO4"))  ==>  cbIKGiMVkLFTeenAa5kgO4

```

# Use with Gradle
add to your repositories

```
repositories {
    maven { url "https://jitpack.io" }
}
```

In your app build.gradle, add:  `compile "com.github.inder123:md5uri:1.0.0"`

If you are interested in converting MD5 only to a base 62 string (0-9, a-z, A-Z), use [base62](https://github.com/inder123/base62) project.
