package com.jeethink.common.utils;

import javassist.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2023/04/17/10:24
 * @Description: https://www.cnblogs.com/cxll863/p/16887080.html
 */
public class AsposePdfJarCrac {
    private static final String Desktop="C:\\Users\\Dcjczx\\Desktop\\";
    private static void crackAsposePdfJar(String jarName) {
        try {
            ClassPool.getDefault().insertClassPath(jarName);
            CtClass ctClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
            CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            int num = 0;
            for (int i = 0; i < declaredMethods.length; i++) {
                if (num == 2) {
                    break;
                }
                CtMethod method = declaredMethods[i];
                CtClass[] ps = method.getParameterTypes();
                if (ps.length == 2
                        && method.getName().equals("lI")
                        && ps[0].getName().equals("com.aspose.pdf.ADocument")
                        && ps[1].getName().equals("int")) {
                    //源码ADocument类的这个方法限制页数：
                    // static boolean lI(ADocument var0, int var1) {
                    //        return !lb() && !lj() && !var0.lt() && var1 > 4;
                    //    }
                    // 最多只能转换4页，处理返回false，无限制页数
                    System.out.println(method.getReturnType());
                    System.out.println(ps[1].getName());
                    method.setBody("{return false;}");
                    num = 1;
                }
                if (ps.length == 0 && method.getName().equals("lt")) {
                    // 水印处理
                    method.setBody("{return true;}");
                    num = 2;
                }
            }
            //修改完，把类的输出到指定目录（桌面下）
            ctClass.writeFile(Desktop);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void crackAsposeWordsJarAddLicense1(String jarName){
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(jarName);
            //获取指定的class文件对象
            CtClass zzZJJClass = 				ClassPool.getDefault().getCtClass("com.aspose.words.zzXDb");
            //从class对象中解析获取指定的方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods("zzY0J");
            //遍历重载的方法
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ctMethod.getName().equals("zzY0J")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{this.zzZ3l = new java.util.Date(Long.MAX_VALUE);this.zzWSL = com.aspose.words.zzYeQ.zzXgr;zzWiV = this;}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(Desktop);

            //获取指定的class文件对象
            CtClass zzZJJClassB = ClassPool.getDefault().getCtClass("com.aspose.words.zzYKk");
            //从class对象中解析获取指定的方法
            CtMethod methodB = zzZJJClassB.getDeclaredMethod("zzWy3");
            //替换指定方法的方法体
            methodB.setBody("{return 256;}");
            //这一步就是将破译完的代码放在桌面上
            zzZJJClassB.writeFile(Desktop);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }
    public static void crackAsposeCells(String JarPath) throws NotFoundException,CannotCompileException, IOException {
        // 这个是得到反编译的池
        ClassPool pool = ClassPool.getDefault();

        // 取得需要反编译的jar文件，设定路径
        pool.insertClassPath(JarPath);

        CtClass ctClass = pool.get("com.aspose.cells.License");

        CtMethod method_isLicenseSet = ctClass.getDeclaredMethod("isLicenseSet");
        method_isLicenseSet.setBody("return true;");
        CtMethod method_setLicense = ctClass.getDeclaredMethod("setLicense");
        method_setLicense.setBody("{    a = new com.aspose.cells.License();\n" +
                "    com.aspose.cells.zbkl.a();}");
        CtMethod methodL = ctClass.getDeclaredMethod("l");
        methodL.setBody("return new java.util.Date(Long.MAX_VALUE);");

        ctClass.writeFile(Desktop);
    }
    private static void modifyPptJar(String jarName) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(jarName);
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.slides.internal.of.public");
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ps.length == 3 && ctMethod.getName().equals("do")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    ctMethod.setBody("{}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(Desktop);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }
    public static void main(String[] args) {
//        crackAsposePdfJar("D:\\Dependence\\MAVEN\\repos\\com\\aspose\\aspose-pdf\\21.8\\aspose-pdf-21.8.jar");
        crackAsposeWordsJarAddLicense1("D:\\Dependence\\MAVEN\\repos\\com\\aspose\\aspose-words\\21.11\\aspose-words-21.11-jdk17.jar");
    }
}
