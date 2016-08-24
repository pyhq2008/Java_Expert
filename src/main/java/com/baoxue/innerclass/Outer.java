package com.baoxue.innerclass;

public class Outer {
    private String name ;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**省略getter和setter方法**/

    public class InnerClass{
        public InnerClass(){
            name = "chenssy";
            age = 23;
        }

        public void display(){
            System.out.println("name：" + getName() +"   ;age：" + getAge());
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();

        Outer.InnerClass innerClass = outer.new InnerClass();
//        System.out.println((Outer.InnerClass)outer);
    }
}
