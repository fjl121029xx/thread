package com.li.jdk1_8;


import org.junit.Test;

import java.util.Arrays;

public class Jdk_1_8 {

    /**
     * Lambda表达式（也称为闭包）是Java 8中最大和最令人期待的语言改变。
     * 它允许我们将函数当成参数传递给某个方法，或者把代码本身当作数据处理：函数式开发者非常熟悉这些概念。
     * 很多JVM平台上的语言（Groovy、Scala等）从诞生之日就支持Lambda表达式，
     * 但是Java开发者没有选择，只能使用匿名内部类代替Lambda表达式。
     * Lambda的设计耗费了很多时间和很大的社区力量，最终找到一种折中的实现方案，可以实现简洁而紧凑的语言结构。
     * 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成*/
    /**
     * 1．示例
     */
    @Test
    public void lambda1(){

        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
    }

    /**
     * 2．在上面这个代码中的参数e的类型是由编译器推理得出的，你也可以显式指定该参数的类型，例如：
     */
    @Test
    public void lambda2(){

        Arrays.asList("a","b","c").forEach((String e) -> System.out.println(e));
    }

    /**
     *3．如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体，例如：
     */
    @Test
    public void lambda3(){

        Arrays.asList("a","b","c").forEach((String e) -> {

            System.out.println(e);

            System.out.println(e + "%");

        });
    }

    /**
     * 4．Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的），例如下列两个代码块的效果完全相同：
     */
    @Test
    public void lambda4(){

        String name=",";

        final String name2=",,";

        Arrays.asList("a","b","c").forEach((String e) -> {

            System.out.println(e+name);

            System.out.println(e+name2);

        });
    }
    /**
     *5．Lambda表达式有返回值，返回值的类型也由编译器推理得出。
     * 如果Lambda表达式中的语句块只有一行，则可以不用使用return语句，下列两个代码片段效果相同：
     */
    @Test
    public void lambda5(){

        Arrays.asList("a","b","c").sort((e1,e2)->e1.compareTo(e2));

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {

            int result = e1.compareTo( e2 );

            return result;

        } );
    }
}
